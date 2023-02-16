package com.sfac.hk.account.service;


import com.github.pagehelper.PageInfo;
import com.sfac.hk.account.entity.User;
import com.sfac.hk.common.vo.Result;
import com.sfac.hk.common.vo.Search;

/**
 * UserService
 */
public interface UserService {

	Result<User> insertUser(User user);

	Result<User> updateUser(User user);

	Result<Object> deleteUserById(int id);

	User getUserById(int id);

	PageInfo<User> getUsersBySearch(Search search);
}
