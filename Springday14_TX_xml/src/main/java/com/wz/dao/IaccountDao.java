package com.wz.dao;

import com.wz.domain.Account;

/**
 * @PackageName com.wz.dao
 * @ClassName IaccountDao
 * @Author wangzheng
 * @Date 2020/2/5 11:03
 * @Description 账户的持久层接口
 */
public interface IaccountDao {
    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
