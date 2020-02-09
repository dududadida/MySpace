package com.impl;

import com.dao.AccountDao;
import com.service.AccountService;

/**
 * @PackageName com.impl
 * @Author wangzheng
 * @Date 2020/1/18 18:24
 * @Description 模拟保存账户的实现类
 */
public class AccountServiceImpl implements AccountService {
    public AccountServiceImpl(){
        System.out.println("构造函数被重写，对象创建了");
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了");
    }

    public void initAccount() {
        System.out.println("对象初始化了");
    }

    public void destroyAccount() {
        System.out.println("对象销毁了");
    }
}
