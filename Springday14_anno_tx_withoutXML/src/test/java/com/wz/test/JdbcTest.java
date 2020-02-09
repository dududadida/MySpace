package com.wz.test;

import com.config.SpringConfiguration;
import com.wz.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @PackageName com.wz.test
 * @ClassName JdbcTest
 * @Author wangzheng
 * @Date 2020/2/6 11:36
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class JdbcTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("aa", "bb", 100f);
    }
}
