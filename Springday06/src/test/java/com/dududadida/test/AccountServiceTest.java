package com.dududadida.test;

import com.dududadida.domain.Account;
import com.dududadida.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @PackageName com.dududadida.test
 * @Author wangzheng
 * @Date 2020/1/29 10:33
 * @Description 使用junit单元测试配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService as;
    @Test
    public void testFindAll() {
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(5000f);
        as.saveAccount(account);
        System.out.println("成功保存账户");
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(4);
        account.setMoney(3300f);
        as.updateAccount(account);
        System.out.println("更新账户成功");
    }

    @Test
    public void testDelete() {
        as.deleteAccount(4);
        System.out.println("成功删除账户");
    }
}
