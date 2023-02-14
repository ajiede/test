package com.sfac.hk.account.dao;

import com.sfac.hk.account.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 */
@Repository
@Mapper
public interface UserDao {

	@Insert("insert into account_user(user_name, password, create_date, update_time) " +
			"values(#{userName}, #{password}, #{createDate}, #{updateTime})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insertUser(User user);

	@Select("select * from account_user where user_name = #{userName} limit 1")
	User getUserByUserName(String userName);

	@Update("update account_user set user_name = #{userName}, password=#{password}, " +
			"update_time = #{updateTime} where id = #{id}")
	void updateUser(User user);
}
