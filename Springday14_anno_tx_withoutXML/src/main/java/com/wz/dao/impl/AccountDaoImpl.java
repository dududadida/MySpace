package com.wz.dao.impl;

import com.wz.dao.IaccountDao;
import com.wz.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PackageName com.wz.dao.impl
 * @ClassName AccountDaoImpl
 * @Author wangzheng
 * @Date 2020/2/5 11:05
 * @Description 账户的持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements IaccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId);

        return accounts.isEmpty()? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountName);

        if (accounts.isEmpty()) {
            return null;
        }

        if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }

        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ?, money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }
}
