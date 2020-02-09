package com.dududadida.service.impl;

import com.dududadida.service.AccountService;
import com.dududadida.dao.AccountDao;
import com.dududadida.domain.Account;

import java.util.List;

/**
 * @PackageName com.dududadida.service.dao.impl
 * @Author wangzheng
 * @Date 2020/1/28 15:23
 * @Description 账户的业务层实现
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }
}
