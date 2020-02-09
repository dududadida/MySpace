package com.dududadida.service.impl;

import com.dududadida.dao.AccountDao;
import com.dududadida.domain.Account;
import com.dududadida.service.AccountService;
import com.dududadida.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName com.dududadida.service.dao.impl
 * @Author wangzheng
 * @Date 2020/1/28 15:23
 * @Description 账户的业务层实现
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
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

    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("发起转账申请.....");
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        //加钱和减钱
        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);
        //更新转入和转出账户
        accountDao.updateAccount(source);
        accountDao.updateAccount(target);
    }
}