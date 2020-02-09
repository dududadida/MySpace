package com.impl;

import com.dao.AccountDao;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @PackageName com.impl
 * @Author wangzheng
 * @Date 2020/1/18 18:24
 * @Description 模拟保存账户的实现类
 *  曾经的xml配置
 *  <bean id="accountService" class="com.impl.AccountServiceImpl"
 *      scope="" init-method="" destroy-method="">
 *
 *          <property name="" value="" ref=""></property>
 *  </bean>
 *
 *  ==========================用于创建对象的========================
 *  作用和xml文件中编写一个bean标签实现的功能相同
 *      Component
 *      作用：把当前类对象存入spring容器中
 *      属性：
 *          value：用于指定bean的id。当不写时，默认值是当前类名且首字母改小写
 *      Controller:表现层
 *      Service:业务层
 *      Repository:持久层
 *      以上三个注解与Component的作用和属性一模一样。
 *      是spring框架明确三层架构实现的
 *
 *  ==========================用于注入数据的========================
 *  作用:和xml文件中的bean标签的property标签的作用是一样的
 *      Autowired:
 *          自动按照类型注入，只要容器中有唯一的一个bean对象类型和
 *      要注入的变量类型匹配就可以注入成功。如果ioc容器中没有任何一个bean注入失败
 *      如果IOC有多个容器匹配时，先按照类型匹配，再使用变量名称继续匹配。
 *      出现位置：可以是变量上，也可以是方法上
 *      细节：在使用注解注入的方式时，set方法就不是必须的
 *
 *      Qualifier：
 *          作用：在按照类型注入的基础之上再按照名称注入，在给类成员注入时不能单独使用(要和Autowired组合)，但是
 *      给方法参数注入时可以使用。
 *      属性：
 *          value：用于指定注入bean的id
 *
 *      Resource：
 *          作用：直接按照bean的id注入，可以独立使用
 *          属性：
 *              name：用于指定bean的id
 *      以上三个注解都只能注入其他bean类型的数据，基本类型和String类型无法使用上述注解实现
 *      另外，集合类型的注入只能使用xml来实现。
 *
 *      Value:
 *          用于注入String和基本类型的数据
 *          属性：
 *              value：用于指定数据的值，可以使用Spring中的SpEL（也就是Spring的el表达式）
 *                  SpEL写法：${表达式}
 *
 *  ==========================用于改变作用范围的=====================
 *  作用:和bean标签中scope标签的作用一样
 *      Scope:
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值，常用取值：singleton、prototype
 *
 *  ==========================和生命周期相关的======================
 *  作用:和bean标签中init-method和destroy-method作用一样
 *      PreDestroy
 *          作用：用于指定销毁方法
 *      PostConstruct
 *          作用：用于指定初始化方法
 */

@Component
//@Scope("prototype")
public class AccountServiceImpl implements AccountService {
//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private AccountDao accountDao = null;

    public void saveAccount(){
        accountDao.saveAccount();
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法执行");
    }
}
