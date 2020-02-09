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

    /*
     * @Param sourceName：转出账户名称
     * @Param targetName：转入账户名称
     * @Param money：转出金额
     * @Return
     * @Description 转账
     */
    void transfer(String sourceName, String targetName, Float money);

    /**
     *
     */
    //void test();//只是连接点，不是切入点，因为没有被增强
}
