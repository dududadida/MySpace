package com.dududadida.service.impl;

import com.dududadida.dao.AccountDao;
import com.dududadida.domain.Account;
import com.dududadida.service.AccountService;
import com.dududadida.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName com.dududadida.service.dao.impl
 * @Author wangzheng
 * @Date 2020/1/28 15:23
 * @Description 账户的业务层实现
 */

@Service("accountService")
public class AccountServiceImpl_OLD implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private TransactionManager tsm;

    public void setTsm(TransactionManager tsm) {
        this.tsm = tsm;
    }

    public List<Account> findAllAccount() {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //提交事务
            tsm.commit();
            //返回结果
            return accounts;
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            tsm.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作
            Account account = accountDao.findAccountById(accountId);
            //提交事务
            tsm.commit();
            //返回结果
            return account;
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
            throw new RuntimeException(e);
        }finally {
            //释放连接
            tsm.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作
            accountDao.saveAccount(account);
            //提交事务
            tsm.commit();
            //返回结果
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
        }finally {
            //释放连接
            tsm.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作
            accountDao.updateAccount(account);
            //提交事务
            tsm.commit();
            //返回结果
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
        }finally {
            //释放连接
            tsm.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作
            accountDao.deleteAccount(accountId);
            //提交事务
            tsm.commit();
            //返回结果
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
        }finally {
            //释放连接
            tsm.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //开启事务
            tsm.beginTransaction();
            //执行操作

            //根据名称查询转出转入账户
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            //加钱和减钱
            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);
            //更新转入和转出账户
            accountDao.updateAccount(source);
            accountDao.updateAccount(target);
            //提交事务
            tsm.commit();
            //返回结果
        }catch (Exception e){
            //回滚操作
            tsm.rollback();
        }finally {
            //释放连接
            tsm.release();
        }
    }
}