package com.wz.mybatis.io;

import java.io.InputStream;

/**
 * @PackageName com.wz.mybatis
 * @ClassName Resources
 * @Author wangzheng
 * @Date 2020/2/8 11:41
 * @Description 使用类加载器读取配置文件的类
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
