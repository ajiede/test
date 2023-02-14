package com.sfac.hk.property.service;

import com.sfac.hk.common.vo.Result;
import com.sfac.hk.property.entity.Books;

/**
 * @Description BooksService
 * @Author JiangHu
 * @Date 2023/2/14 15:41
 */
public interface BooksService {

	Result<Books> insertBooks(Books books);

	Result<Books> updateBooks(Books books);

	Result<Object> deleteBooksById(int id);

	Books getBooksById(int id);
}
