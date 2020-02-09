package com.wz.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @PackageName com.wz.jdbctemplate
 * @ClassName JdbcTemplate
 * @Author wangzheng
 * @Date 2020/2/5 9:28
 * @Description JDBCTemplate的最基本用法
 */
public class JdbcTemplateDemo2 {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //获取bean对象
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        //执行操作
        jdbcTemplate.execute("insert into account(name, money)values ('dd', 2000)");
    }
}
