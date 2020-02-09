package com.wz.service.impl;

import com.wz.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.wz.service.impl
 * @ClassName AccountServiceImpl
 * @Author wangzheng
 * @Date 2020/2/3 17:25
 * @Description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新" + i);
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
