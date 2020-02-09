package com.dududadida.entity;

import java.util.Date;

/**
 * @author:wangzheng
 * @date: 2020/1/3
 */
public class Appointment {
    private long bid;       //书id
    private long sid;       //学生id
    private Date appointTime;   //借书时间
    private Book book;      //实例

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "bid=" + bid +
                ", sid=" + sid +
                ", appointTime=" + appointTime +
                ", book=" + book +
                '}';
    }
}
