package com.dududadida.test;

import com.dududadida.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @PackageName com.dududadida.test
 * @Author wangzheng
 * @Date 2020/1/29 10:33
 * @Description 使用junit单元测试配置
 *  Spring整合junit配置
 *      1、导入spring整合Junit的jar包/左边
 *      2、使用Junit提供的注解把原有的main方法替换成spring提供的
 *          @Runwith
 *      3、告知spring的运行器，spring的IOC创建是基于XML还是注解的，并说明位置
 *          @ContextConfigruation
 *              Locations：指定xml文件的位置加classpath关键字，表示在类路径下
 *              classes：指定注解类所在的位置
 *     当使用spring 5.X版本时，要求Junit的jar版本必须是4.1.2及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("proxyAccountService")
    private AccountService as;

    @Test
    public void testTransfer(){
        as.transfer("aa", "bb", 200f);
    }
}
