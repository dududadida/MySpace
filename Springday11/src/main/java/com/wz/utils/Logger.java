package com.wz.utils;

/**
 * @PackageName com.wz.utils
 * @ClassName Logger
 * @Author wangzheng
 * @Date 2020/2/3 17:26
 * @Description 用于记录日志的工具类
 */
public class Logger {
    /**
     * 用于打印日志，并计划让其在切入点方法执行之前执行(切入点方法就是业务层方法)
     */
    public void printLog(){
        System.out.println("Logger类中的printLog方法开始记录日志。。。");
    }
}
