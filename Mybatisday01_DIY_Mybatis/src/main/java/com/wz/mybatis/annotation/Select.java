package com.wz.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PackageName com.wz.mybatis.annotation
 * @ClassName Select
 * @Author wangzheng
 * @Date 2020/2/9 11:55
 * @Description 查询的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {
    /**
     * 配置sql语句的value
     * @return
     */
    String value();
}
