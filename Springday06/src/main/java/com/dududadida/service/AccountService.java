package com.dududadida.service;

import com.dududadida.domain.Account;

import java.util.List;

/**
 * @PackageName com.dududadida.service
 * @Author wangzheng
 * @Date 2020/1/28 15:14
 * @Description 账户的业务层接口
 */
public interface AccountService {

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
}
