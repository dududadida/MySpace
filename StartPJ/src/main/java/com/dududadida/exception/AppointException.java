package com.dududadida.exception;

/**
 * @PackageName com.dududadida.exception
 * @Author wangzheng
 * @Date 2020/1/10 12:20
 * @Description 预约业务异常
 */
public class AppointException extends RuntimeException {

    public AppointException(String message) {
        super(message);
    }

    public AppointException(String message, Throwable cause) {
        super(message, cause);
    }

}