package com.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @PackageName com.factory
 * @Author wangzheng
 * @Date 2020/1/18 18:34
 * @Description 创建bean对象的工厂
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //定义一个Map用于存放创建的对象。把它称之为容器。
    private static Map<String, Object> beans;
    //使用静态块为Properties对象赋值
    static {
        //实例化对象
        props = new Properties();
        //获取properties文件的流对象
        InputStream inputStream =
                BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            props.load(inputStream);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的Key
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败！");
        }
    }

    /*
     * @Param
     * @Return
     * @Description 根据bean的名称获取bean对象
     */

    public static Object getBean(String beanName){

        return beans.get(beanName);
    }
//    public static Object getBean(String beanName){
//        Object bean = null;
//        try {
//            String beanPath = props.getProperty(beanName);
//            bean = Class.forName(beanPath).newInstance();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return bean;
//    }
}
