package com.ui;

import com.dao.AccountDao;
import com.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PackageName com.ui
 * @Author wangzheng
 * @Date 2020/1/18 18:29
 * @Description
 */
public class Client {

    public static void main(String[] args) {
        //获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取bean对象
        AccountService as = ac.getBean("accountServiceImpl", AccountService.class);
//        AccountService as2 = ac.getBean("accountServiceImpl", AccountService.class);
        as.saveAccount();
//        System.out.println(as);
//
//        AccountDao adao = ac.getBean("accountDao", AccountDao.class);
//        System.out.println(adao);
//        System.out.println(as == as2);
        ac.close();
    }
}
