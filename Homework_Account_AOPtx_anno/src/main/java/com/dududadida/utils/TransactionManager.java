package com.dududadida.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @PackageName com.dududadida.utils
 * @Author wangzheng
 * @Date 2020/2/1 19:25
 * @Description 和事务管理相关的工具类，包含了开启、提交、回滚事务和释放连接
 */
@Component("tsm")
public class TransactionManager {
    @Pointcut("execution(* com.dududadida.service.impl.*.*(..))")
    private void pt1(){}

    @Autowired
    private ConnectionUtils connectionUtils;

    /*
     * @Param
     * @Return
     * @Description 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Param
     * @Return
     * @Description 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Param
     * @Return
     * @Description 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Param
     * @Return
     * @Description 释放连接
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回池中
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        Object rtValue = null;
        try {
            //1、获取参数
            Object[] args = proceedingJoinPoint.getArgs();
            //2、开启事务
            this.beginTransaction();
            //3、执行方法
            rtValue = proceedingJoinPoint.proceed(args);
            //4、提交事务
            this.commit();

            //返回结果
            return rtValue;

        } catch (Throwable e){
            //5、回滚事务
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            //6、释放资源
            this.release();
        }
    }
}
