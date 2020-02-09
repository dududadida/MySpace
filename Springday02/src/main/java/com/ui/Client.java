package com.ui;

import com.dao.AccountDao;
import com.impl.AccountServiceImpl;
import com.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
     * @Description 获取spring容器的Ioc核心容器，并根据id取出对象
     * ApplicationContext的三个常用实现类：
     * 1、ClassPathXmlApplicationContext:可以加载类路径下的配置文件，不在类路径下的无法加载
     * 2、FileSystemXmlApplicationContext:加载磁盘任意路径下的配置文件，必须有访问权限
     * 3、AnnotationApplicationContext:读取注解创建容器的
     *
     * 核心容器的两个接口引发出的问题
     * ApplicationContext:      单例对象适用
     * 在构建核心容器时创建对象采取的思想是立即加载的方式，只要读取完配置文件，马上
     * 创建对象。
     *
     * BeanFactory:     多例对象适用
     * 在构建核心容器时，创建对象采取的策略是延迟加载的，采用延迟加载的方式，什么时候通过
     * id获取对象，就什么时候加载。
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac =
        //        new FileSystemXmlApplicationContext("C:\\Users\\wangzheng\\Desktop\\bean.xml");
        //2.根据id获取bean对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        AccountDao ad = ac.getBean("accountDao",AccountDao.class);
        as.saveAccount();
        System.out.println(as);
        System.out.println(ad);
    }
}
