package com.dududadida.exception;

/**
 * @PackageName com.dududadida.exception
 * @Author wangzheng
 * @Date 2020/1/10 12:19
 * @Description 重复预约异常
 */
public class RepeatAppointException extends RuntimeException {

    public RepeatAppointException(String message) {
        super(message);
    }

    public RepeatAppointException(String message, Throwable cause) {
        super(message, cause);
    }

}