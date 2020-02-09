package com.wz.mybatis.sqlsession;

/**
 * @PackageName com.wz.mybatis.sqlsession
 * @ClassName SqlSession
 * @Author wangzheng
 * @Date 2020/2/8 11:47
 * @Description 自定义mybatis中的和数据库交互的核心类
 * 可以创建Dao接口的代理对象
 */
public interface SqlSession {
    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao接口的字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
