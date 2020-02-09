package com.wz.service;

import com.wz.domain.Account;

/**
 * @PackageName com.wz.service
 * @ClassName AccountService
 * @Author wangzheng
 * @Date 2020/2/6 11:27
 * @Description 账户的业务层接口
 */
public interface AccountService {
    /**
     * 根据id查询
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账操作
     * @param sourceName
     * @param targetName
     * @param money
     */
    void fransfer(String sourceName, String targetName, Float money);
}
