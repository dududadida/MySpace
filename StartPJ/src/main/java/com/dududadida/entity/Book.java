package com.dududadida.entity;

/**
 * @author:wangzheng
 * @date: 2020/1/3
 */
public class Book {
    private long bid;       //书ID
    private String bname;       //书名
    private int bnumber;        //书的数量

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBnumber() {
        return bnumber;
    }

    public void setBnumber(int bnumber) {
        this.bnumber = bnumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bnumber=" + bnumber +
                '}';
    }
}
