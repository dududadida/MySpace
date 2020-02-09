package com.wz.mybatis.sqlsession.defaults;

import com.wz.mybatis.cfg.Configuration;
import com.wz.mybatis.sqlsession.SqlSession;
import com.wz.mybatis.sqlsession.SqlSessionFactory;

/**
 * @PackageName com.wz.mybatis.sqlsession.defaults
 * @ClassName DefaultSqlSessionFactory
 * @Author wangzheng
 * @Date 2020/2/8 13:06
 * @Description SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;
    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }
    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
