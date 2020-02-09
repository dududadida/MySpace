package com.wz.test;

import com.wz.dao.IUserDao;
import com.wz.domain.User;
import com.wz.mybatis.io.Resources;
import com.wz.mybatis.sqlsession.SqlSession;
import com.wz.mybatis.sqlsession.SqlSessionFactory;
import com.wz.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @PackageName com.wz.test
 * @ClassName Mybatis
 * @Author wangzheng
 * @Date 2020/2/7 16:45
 * @Description mybatis的入门案例
 */
public class MybatisTest {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception{
        //1、读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(inputStream);
        //3、使用工厂生产一个SqlSession对象
        SqlSession session = sessionFactory.openSession();
        //4、使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5、使用代理对象执行方法
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
        //6、释放资源
        session.close();
        inputStream.close();
    }
}
