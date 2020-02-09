package com.dududadida.test;

import com.dududadida.domain.Account;
import com.dududadida.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @PackageName com.dududadida.test
 * @Author wangzheng
 * @Date 2020/1/29 10:33
 * @Description 使用junit单元测试配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //执行方法
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(5000f);
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //执行方法
        as.saveAccount(account);
        System.out.println("成功保存账户");
    }

    @Test
    public void testUpdate() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //执行方法
        Account account = as.findAccountById(4);
        account.setMoney(3300f);
        as.updateAccount(account);
        System.out.println("更新账户成功");
    }

    @Test
    public void testDelete() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        //执行方法
        as.deleteAccount(4);
        System.out.println("成功删除账户");
    }
}
