package com.dududadida.service;
import com.dududadida.dto.AppointExecution;
import com.dududadida.entity.Book;

import java.util.List;

/**
 * @FileRelativePath StartPJ\src\main\java\com\dududadida\service\BookService.java
 * @Author wangzheng
 * @Date 2020/1/10 9:37
 * @Description 业务接口，站在使用者角度
 */

public interface BookService {

    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    Book getById(long bookId);

    /**
     * 查询所有图书
     *
     * @return
     */
    List<Book> getList();

    /**
     * 预约图书
     *
     * @param bookId
     * @param studentId
     * @return
     */
    AppointExecution appoint(long bookId, long studentId);

}