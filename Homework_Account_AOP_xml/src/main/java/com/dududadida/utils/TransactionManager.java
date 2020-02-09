package com.dududadida.utils;

import java.sql.SQLException;

/**
 * @PackageName com.dududadida.utils
 * @Author wangzheng
 * @Date 2020/2/1 19:25
 * @Description 和事务管理相关的工具类，包含了开启、提交、回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

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
}
