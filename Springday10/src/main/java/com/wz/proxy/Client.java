package com.wz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PackageName com.wz.proxy
 * @Author wangzheng
 * @Date 2020/2/2 9:27
 * @Description 模拟一个消费者
 */
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        /*
         * 动态代理
         *      特点：字节码随用随创建，随用随加载
         *      作用：不修改源码的前提下对方法增强
         *      分类：
         *          1、基于接口的
         *          2、基于子类的
         * 基于接口：
         *      涉及的类：Proxy
         *      提供者：JDK
         * 如何创建代理对象：
         *      用Proxy类中的newProxyInstance方法
         * 创建代理的要求
         *      被代理对象至少实现一个接口，如果没有则不能使用
         * new ProxyInstance方法的参数
         *      ClassLoader：类加载器
         *          用于加载代理对象字节码，和被代理对象使用相同的类加载器
         *      Class[]：字节码数组
         *          让代理对象和被代理对象有相同的方法
         *      InvocationHandler：用于提供增强的代码
         *          写如何代理，一般都是写一个该接口的实现类，通常都是匿名内部类但不必须
         *          此接口的实现类，谁用谁写
         */
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * @Description 执行被代理对象的任何接口方法都会经过该方法
                     * @Param: proxy 代理对象的引用
                     * @Param: method 当前执行的方法
                     * @Param: args 当前执行方法所需的参数
                     * @Return 和被代理方法有相同返回值
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强的代码
                        Object returnValue = null;
                        //1、获取方法执行的参数
                        Float money = (Float)args[0];
                        //2、判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())){
                            returnValue = method.invoke(producer, money*0.8f);
                        }
                        return returnValue;
                    }
                });
        proxyProducer.saleProduct(10000f);
    }
}
