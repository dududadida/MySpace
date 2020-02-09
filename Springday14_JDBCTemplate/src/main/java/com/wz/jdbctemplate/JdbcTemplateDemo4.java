package com.wz.jdbctemplate;

import com.wz.dao.IaccountDao;
import com.wz.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @PackageName com.wz.jdbctemplate
 * @ClassName JdbcTemplate
 * @Author wangzheng
 * @Date 2020/2/5 9:28
 * @Description JDBCTemplate的最基本用法
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //获取bean对象
        IaccountDao accountDao = applicationContext.getBean("accountDao", IaccountDao.class);
        //执行操作
        Account account = accountDao.findAccountById(1);
        System.out.println(account);
        account.setMoney(40000f);
        accountDao.updateAccount(account);
    }
}
