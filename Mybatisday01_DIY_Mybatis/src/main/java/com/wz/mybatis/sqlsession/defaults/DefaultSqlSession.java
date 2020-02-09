package com.wz.mybatis.sqlsession.defaults;

import com.wz.mybatis.cfg.Configuration;
import com.wz.mybatis.sqlsession.SqlSession;
import com.wz.mybatis.sqlsession.proxy.MapperProxy;
import com.wz.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @PackageName com.wz.mybatis.sqlsession.defaults
 * @ClassName DefaultSqlSession
 * @Author wangzheng
 * @Date 2020/2/8 13:09
 * @Description SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }
    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao接口的字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), connection));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
