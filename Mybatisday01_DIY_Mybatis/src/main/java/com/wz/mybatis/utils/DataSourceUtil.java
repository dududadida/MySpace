package com.wz.mybatis.utils;

import com.wz.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @PackageName com.wz.mybatis.utils
 * @ClassName DataSourceUtils
 * @Author wangzheng
 * @Date 2020/2/8 17:55
 * @Description 用于创建数据源的工具类
 */
public class DataSourceUtil {
    /**
     * 用于获取一个连接
     * @param configuration
     * @return
     */
    public static Connection getConnection(Configuration configuration){
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(
                    configuration.getUrl(),
                    configuration.getUsername(),
                    configuration.getPassword());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
