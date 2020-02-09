package com.wz.jdbctemplate;

import com.wz.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.AccessibleObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @PackageName com.wz.jdbctemplate
 * @ClassName JdbcTemplate
 * @Author wangzheng
 * @Date 2020/2/5 9:28
 * @Description JDBCTemplate的CRUD操作
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {
        //获取容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //获取bean对象
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        //保存
        //jdbcTemplate.update("insert into account(name, money) values(?, ?)","ee",3333f);
        //更新
        //jdbcTemplate.update("update account set name = ?, money = ? where id = ?","fe", 4000f, 5);
        //删除
        //jdbcTemplate.update("delete from account where id = ?", 7);
        //查询所有
        //List<Account> accounts = jdbcTemplate.query("select * from account where money > ?", new AccountRowMapper(),1000f);
//        List<Account> accounts = jdbcTemplate.query("select * from account where money > ?", new BeanPropertyRowMapper<Account>(Account.class),1000f);
//        for (Account account : accounts
//             ) {
//            System.out.println(account);
//        }
        //查询一个
//        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?", new AccountRowMapper(),10);
//        System.out.println(accounts.isEmpty()? "没有内容" : accounts.get(0));
        //查询返回一行一列（使用聚合函数但不加group by）
        Long count = jdbcTemplate.queryForObject("select count(*) from account where money > ?", Long.class, 1000f);
        System.out.println(count);
    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {
    /**
     * 把结果集中的数据封装到Account中，由spring把每个Account加到集合中
     * @param resultSet
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
