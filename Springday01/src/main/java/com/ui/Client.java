package com.ui;

import com.factory.BeanFactory;
import com.impl.AccountServiceImpl;
import com.service.AccountService;

/**
 * @PackageName com.ui
 * @Author wangzheng
 * @Date 2020/1/18 18:29
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        //AccountService as = new AccountServiceImpl();
        for (int i=0; i<5; i++) {
            AccountService as = (AccountService) BeanFactory.getBean("accountService");
            as.saveAccount();
            System.out.println(as);
        }
    }
}
