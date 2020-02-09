package com.wz.mybatis.sqlsession.proxy;

import com.wz.mybatis.cfg.Mapper;
import com.wz.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @PackageName com.wz.mybatis.sqlsession.proxy
 * @ClassName MapperProxy
 * @Author wangzheng
 * @Date 2020/2/8 13:21
 * @Description
 */
public class MapperProxy implements InvocationHandler {

    //map的key是全限定类名加方法名
    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection connection){
        this.mappers = mappers;
        this.connection = connection;
    }
    /**
     * 用于对方法进行增强，调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    public Object invoke(Object proxy, Method method, Object[] args) {
        //1、获取方法名
        String methodName = method.getName();
        //2、获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //3、组合key
        String key = className + "." + methodName;
        //4、获取mapper中的Mapper对象
        Mapper mapper = mappers.get(key);
        //5、判断是否有mapper
        if (mapper == null) {
            throw new IllegalArgumentException("传入的参数有误");
        }
        //6、调用工具类执行查询所有
        return new Executor().selectList(mapper, connection);
    }
}
