package com.wz.cglib;

import com.wz.proxy.IProducer;

/**
 * @PackageName com.wz.proxy
 * @Author wangzheng
 * @Date 2020/2/2 9:21
 * @Description
 */
public class Producer{
    /*
     * @Param
     * @Return
     * @Description 销售
     */
    public void saleProduct(float money){
        System.out.println("销售产品，并拿到钱：" + money);
    }

    /*
     * @Param
     * @Return
     * @Description 售后
     */
    public void afterService(float money){
        System.out.println("提供售后服务，并拿到钱：" + money);
    }
}
