package com.wz.mybatis.cfg;

/**
 * @PackageName com.wz.mybatis.cfg
 * @ClassName Mapper
 * @Author wangzheng
 * @Date 2020/2/8 12:31
 * @Description 用于封装执行的SQL语句和结果类型的全限定类名
 */
public class Mapper {
    private String queryString;     //执行的SQL语句
    private String resultType;      //全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
