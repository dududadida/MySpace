package com.wz.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @PackageName com.wz.jdbctemplate
 * @ClassName JdbcTemplate
 * @Author wangzheng
 * @Date 2020/2/5 9:28
 * @Description JDBCTemplate的最基本用法
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //准备数据源：spring内置数据源
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("sasa");

        //1、创建JDBCTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //给jdbcTemplate设置数据源
        jdbcTemplate.setDataSource(driverManagerDataSource);
        //2、执行操作
        jdbcTemplate.execute("insert into account(name, money)values ('cc', 1000)");
    }
}
