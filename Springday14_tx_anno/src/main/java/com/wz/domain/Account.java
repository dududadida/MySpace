package com.wz.domain;

import java.io.Serializable;

/**
 * @PackageName com.wz.domain
 * @ClassName Account
 * @Author wangzheng
 * @Date 2020/2/5 9:25
 * @Description 账户的实体类
 */
public class Account implements Serializable {
    private String name;
    private Integer id;
    private Float money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", money=" + money +
                '}';
    }
}
