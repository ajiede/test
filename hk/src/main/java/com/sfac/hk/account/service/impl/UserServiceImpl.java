package com.sfac.hk.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfac.hk.account.dao.UserDao;
import com.sfac.hk.account.entity.User;
import com.sfac.hk.account.service.UserService;
import com.sfac.hk.common.vo.Result;
import com.sfac.hk.common.vo.Search;
import com.sfac.hk.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

/**
 * @Description UserServiceImpl
 * @Author JiangHu
 * @Date 2023/2/14 10:20
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Result<User> insertUser(User user) {
		// 根据用户名查询用户是否存在，存在返回错误消息，不存在继续插入
		User temp = userDao.getUserByUserName(user.getUserName());
		if (temp != null) {
			return Result.failed("用户名重复。");
		}
		// 初始化 user 某些属性
		user.setCreateDate(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		// 插入 user
		userDao.insertUser(user);
		// 返回结果
		return Result.ok(user);
	}

	@Override
	@Transactional
	public Result<User> updateUser(User user) {
		// 根据用户名查询用户是否存在，判断是否和别人的名字一样，相同则返回错误信息
		User temp = userDao.getUserByUserName(user.getUserName());
		if (temp != null && temp.getId() != user.getId()) {
			return Result.failed("用户名重复。");
		}
		// 初始化 user 相关属性
		user.setUpdateTime(LocalDateTime.now());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		// 进行更新操作
		userDao.updateUser(user);
		// 返回正确的结果
		return Result.ok(user);
	}

	@Override
	@Transactional
	public Result<Object> deleteUserById(int id) {
		// 删除对应user
		userDao.deleteUserById(id);
		// 返回结果
		return Result.ok();
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public PageInfo<User> getUsersBySearch(Search search) {
		// 初始化search对象
		search.initSearch();
		// 开启分页
		PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
		// 返回 pageinfo 对象
//		return new PageInfo<>(userDao.getUsersBySearch(search));
		return new PageInfo<>(Optional
				.ofNullable(userDao.getUsersBySearch(search))
				.orElse(Collections.emptyList()));
	}
}
