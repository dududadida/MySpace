package com.wz.mybatis.sqlsession;

import com.wz.mybatis.cfg.Configuration;
import com.wz.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.wz.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @PackageName com.wz.mybatis.sqlsession
 * @ClassName SqlSessionFactoryBuilder
 * @Author wangzheng
 * @Date 2020/2/8 11:44
 * @Description 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(cfg);
    }
}
