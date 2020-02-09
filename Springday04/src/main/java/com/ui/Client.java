package com.ui;

import com.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PackageName com.ui
 * @Author wangzheng
 * @Date 2020/1/18 18:29
 * @Description
 */
public class Client {

   /*
    * @Param
    * @Return
    * @Description
    */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        AccountService as = ac.getBean("accountService", AccountService.class);
//        as.saveAccount();

//        AccountService as = ac.getBean("accountService2", AccountService.class);
//        as.saveAccount();

        AccountService as = ac.getBean("accountService3", AccountService.class);
        as.saveAccount();

        //手动关闭容器
        ac.close();
    }
}
