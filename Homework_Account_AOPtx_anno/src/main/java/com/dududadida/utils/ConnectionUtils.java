package com.dududadida.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @PackageName com.dududadida.utils
 * @Author wangzheng
 * @Date 2020/2/1 18:00
 * @Description 连接工具类，用于从数据源中获取一个连接，并且实现和线程的绑定
 */
@Component("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;

    /*
     * @Param
     * @Return
     * @Description 获取当前线程上的连接
     */
    public Connection getThreadConnection(){
        try {
            //先从ThreadLocal上获取
            Connection connection = tl.get();
            //判断当前线程上是否有连接
            if (connection == null) {
                //从数据源中获取一个连接，并且和线程绑定
                connection = dataSource.getConnection();
                tl.set(connection);
            }
            //返回当前线程上的连接
            return connection;
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    //把连接和线程解绑
    public void removeConnection(){
        tl.remove();
    }
}
