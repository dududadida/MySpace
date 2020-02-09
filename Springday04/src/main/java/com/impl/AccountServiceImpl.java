package com.impl;

import com.service.AccountService;

import java.util.Date;

/**
 * @PackageName com.impl
 * @Author wangzheng
 * @Date 2020/1/18 18:24
 * @Description 模拟保存账户的实现类
 */
public class AccountServiceImpl implements AccountService {
    //如果是经常变化的数据，不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了" + name + ","
        + age + "," + birthday);
    }
}
