package com.wz.proxy;

/**
 * @PackageName com.wz.proxy
 * @Author wangzheng
 * @Date 2020/2/2 9:23
 * @Description 对生产厂家要求的接口
 */
public interface IProducer {
    /*
     * @Param
     * @Return
     * @Description 销售
     */
    public void saleProduct(float money);

    /*
     * @Param
     * @Return
     * @Description 售后
     */
    public void afterService(float money);
}
