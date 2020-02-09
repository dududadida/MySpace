package com.wz.mybatis.sqlsession;

/**
 * @PackageName com.wz.mybatis.sqlsession
 * @ClassName SqlSessionFactory
 * @Author wangzheng
 * @Date 2020/2/8 11:45
 * @Description
 */
public interface SqlSessionFactory {
    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
