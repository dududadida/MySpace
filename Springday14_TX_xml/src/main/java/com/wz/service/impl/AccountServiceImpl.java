package com.wz.service.impl;

import com.wz.dao.IaccountDao;
import com.wz.domain.Account;
import com.wz.service.AccountService;

/**
 * @PackageName com.wz.service.impl
 * @ClassName AccountServiceImpl
 * @Author wangzheng
 * @Date 2020/2/6 11:29
 * @Description
 */
public class AccountServiceImpl implements AccountService{

    private IaccountDao iaccountDao;

    public void setIaccountDao(IaccountDao iaccountDao) {
        this.iaccountDao = iaccountDao;
    }

    public Account findAccountById(Integer accountId) {
        return iaccountDao.findAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        Account source = iaccountDao.findAccountByName(sourceName);
        Account target = iaccountDao.findAccountByName(targetName);

        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);
        //int i = 7/0;
        iaccountDao.updateAccount(source);
        iaccountDao.updateAccount(target);
    }
}
