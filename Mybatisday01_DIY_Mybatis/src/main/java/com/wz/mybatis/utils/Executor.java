package com.wz.mybatis.utils;

import com.wz.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName com.wz.mybatis.utils
 * @ClassName Executor
 * @Author wangzheng
 * @Date 2020/2/8 13:29
 * @Description 负责执行SQL语句并封装结果
 */
public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection connection) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            //1、取出mapper中的数据
            String queryString = mapper.getQueryString();   //select * from
            String resultType = mapper.getResultType();     //全限定类名
            Class domainClass = Class.forName(resultType);
            //2、获取PreparedStatement对象
            pstm = connection.prepareStatement(queryString);
            //3、执行sql语句，获取结果集
            rs = pstm.executeQuery();
            //4、封装结果集
            List<E> list = new ArrayList<E>(); //定义返回值
            while (rs.next()){
                //实例化要封装的实体类对象
                E obj = (E) domainClass.newInstance();

                //取出结果集的元信息：ResultSetMateData
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出总列数
                int columnCount = rsmd.getColumnCount();
                //遍历总列数
                for (int i = 1; i <= columnCount; i ++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到的列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值:使用java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName, domainClass);
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值给对象赋值
                    writeMethod.invoke(obj, columnValue);
                }
                //把赋好的值加入集合
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(pstm, rs);
        }
    }

    private void release(PreparedStatement pstm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if (pstm != null) {
            try {
                pstm.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
