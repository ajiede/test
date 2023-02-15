package com.sfac.hk.property.dao;

import com.sfac.hk.common.vo.Search;
import com.sfac.hk.property.entity.Books;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BooksDao
 */
@Repository
@Mapper
public interface BooksDao {
	@Insert("insert into economy_books(user_id, name, books_type, default_books, create_date, update_time) " +
			"values(#{userId}, #{name}, #{booksType}, #{defaultBooks}, #{createDate}, #{updateTime})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insertBooks(Books books);

	@Select("select * from economy_books where name=#{name} limit 1")
	Books getBooksByName(String name);

	@Update("update economy_books set user_id = #{userId}, name = #{name}, " +
			"books_type = #{booksType}, default_books = #{defaultBooks}, " +
			"update_time = #{updateTime} where id = #{id}")
	void updateBooks(Books books);

	@Delete("delete from economy_books where id = #{id}")
	void deleteBooksById(int id);

	@Select("select * from economy_books where id = #{id}")
	@Results(id="booksResults", value = {
			@Result(column = "id", property = "id"),
			@Result(column = "id",
					property = "members",
					javaType = List.class,
					many = @Many(select="com.sfac.hk.account.dao.UserDao.getUsersByBooksId")
			)
	})
	Books getBooksById(int id);


	@Select("<script>"
			+ "select * from economy_books "
			+ "<where> "
			+ "<if test='keyword != \"\" and keyword != null'>"
			+ " and (name like '%${keyword}%' or books_type like '%${keyword}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='sort != \"\" and sort != null'>"
			+ " order by ${sort} ${direction}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<Books> getBooksListBySearch(Search search);
}
