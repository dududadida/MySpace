package com.impl;

import com.dao.AccountDao;
import com.service.AccountService;

/**
 * @PackageName com.impl
 * @Author wangzheng
 * @Date 2020/1/18 18:24
 * @Description 模拟保存账户的实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();
    //private int i;
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
