package com.wz.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @PackageName com.wz.utils
 * @ClassName Logger
 * @Author wangzheng
 * @Date 2020/2/3 17:26
 * @Description 用于记录日志的工具类
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {
    @Pointcut("execution(* com.wz.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 前置通知
     */
    //@Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog()方法开始记录日志。。。");
    }

    /**
     * 后置通知
     */
    //@AfterReturning("pt1()")
    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog()方法开始记录日志。。。");
    }
    /**
     * 异常通知
     */
    //@AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog()方法开始记录日志。。。");
    }

    /**
     * 最终通知
     */
    //@After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog()方法开始记录日志。。。");
    }

    /**
     * 环绕通知
     * 问题：
     *  当配置了环绕通知之后，切入点方法没有执行，通知执行了
     * 分析：
     *  通过对比动态代理中的环绕通知代码，发现动态代理中的环绕通知有明确的切入点方法调用，而代码中没有。
     * 解决：
     *  spring框架提供了接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确
     *  调用切入点方法，该接口可以作为环绕通知的方法参数，在程序执行是spring框架会提供该接口的实现类
     *  供使用。
     *
     * Spring中的环绕通知：
     *  是spring框架提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint){
        Object rtValue = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();//得到方法执行所需的参数
            System.out.println("Logger类中的aroundPrintLog()方法开始记录日志。。。前置");
            rtValue = proceedingJoinPoint.proceed(args);//明确调用切入点方法
            System.out.println("Logger类中的aroundPrintLog()方法开始记录日志。。。后置");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("Logger类中的aroundPrintLog()方法开始记录日志。。。异常");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("Logger类中的aroundPrintLog()方法开始记录日志。。。最终");
        }
    }
}
