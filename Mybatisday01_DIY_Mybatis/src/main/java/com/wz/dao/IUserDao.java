package com.wz.dao;

import com.wz.domain.User;
import com.wz.mybatis.annotation.Select;

import java.util.List;

/**
 * @PackageName com.wz
 * @ClassName IUserDao
 * @Author wangzheng
 * @Date 2020/2/7 10:12
 * @Description 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
