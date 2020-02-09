package com.wz.test;

import com.wz.service.AccountService;
import com.wz.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @PackageName com.wz.test
 * @ClassName AOPTest
 * @Author wangzheng
 * @Date 2020/2/3 17:58
 * @Description 测试AOP的配置
 */
public class AOPTest {
    public static void main(String[] args) {
        //1、获取容器
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("bean.xml");
        //2、获取bean对象
        AccountService accountService =
                applicationContext.getBean("accountService", AccountService.class);
        //3、执行方法
        accountService.saveAccount();
        accountService.updateAccount(3);
        accountService.deleteAccount();
    }
}
