package com.dududadida.exception;

/**
 * @PackageName com.dududadida.exception
 * @Author wangzheng
 * @Date 2020/1/10 12:18
 * @Description 库存不足异常
 */
public class NoNumberException extends RuntimeException {

    public NoNumberException(String message) {
        super(message);
    }

    public NoNumberException(String message, Throwable cause) {
        super(message, cause);
    }

}
