package com.sfac.hk.account.service;


import com.sfac.hk.account.entity.User;
import com.sfac.hk.common.vo.Result;

/**
 * UserService
 */
public interface UserService {

	Result<User> insertUser(User user);

	Result<User> updateUser(User user);
}
