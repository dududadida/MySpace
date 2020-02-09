package com.dududadida.dao;

import com.dududadida.domain.Account;

import java.util.List;

/**
 * @PackageName com.dududadida.service.dao
 * @Author wangzheng
 * @Date 2020/1/28 15:27
 * @Description 账户的持久层接口
 */
public interface AccountDao {
    /*
     * @Param
     * @Return
     * @Description 查询所有
     */
    List<Account> findAllAccount();

    /*
     * @Param
     * @Return
     * @Description 查询一个
     */
    Account findAccountById(Integer accountId);

    /*
     * @Param
     * @Return
     * @Description 保存操作
     */
    void saveAccount(Account account);

    /*
     * @Param
     * @Return
     * @Description 更新操作
     */
    void updateAccount(Account account);

    /*
     * @Param
     * @Return
     * @Description 删除操作
     */
    void deleteAccount(Integer accountId);

    /*
     * @Param
     * @Return 如果有唯一结果返回，如果没有结果返回null，如果超过一个抛出异常
     * @Description 根据名称查询账户
     */
    Account findAccountByName(String accountName);
}
