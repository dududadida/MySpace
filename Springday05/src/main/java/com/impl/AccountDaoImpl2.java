package com.impl;

import com.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * @PackageName com.impl
 * @Author wangzheng
 * @Date 2020/1/18 18:27
 * @Description 账户的持久层实现类
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements AccountDao {
//    public AccountDaoImpl2(){
//        System.out.println("对象已经被创建");
//    }

    public void saveAccount() {
        System.out.println("保存了该账户2");
    }
}
