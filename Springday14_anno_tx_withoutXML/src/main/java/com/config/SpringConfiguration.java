package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PackageName com.config
 * @ClassName SpringConfiguration
 * @Author wangzheng
 * @Date 2020/2/6 19:54
 * @Description spring的配置类，相当于bean.xml
 */
@Configuration
@ComponentScan("com.wz")
@Import({JdbcConfig.class, TransactionConfig.class})
@PropertySource(value = "jdbcConfig.properties")
@EnableTransactionManagement
public class SpringConfiguration {
}
