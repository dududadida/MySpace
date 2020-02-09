package com.factory;

import com.impl.AccountServiceImpl;
import com.service.AccountService;

/**
 * @PackageName com.factory
 * @Author wangzheng
 * @Date 2020/1/20 19:51
 * @Description
 */
public class StaticFactory {
    public static AccountService getAccount(){
        return new AccountServiceImpl();
    }
}
