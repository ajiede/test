package com.sfac.hk.account.controller;

import com.github.pagehelper.PageInfo;
import com.sfac.hk.account.entity.User;
import com.sfac.hk.account.service.UserService;
import com.sfac.hk.common.vo.Result;
import com.sfac.hk.common.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

/**
 * @Description UserController
 * @Author JiangHu
 * @Date 2023/2/14 10:54
 */
@RestController
@RequestMapping("/api/account")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 127.0.0.1/api/account/user ---- post
	 * {"userName":"hujiang", "password":"111111"}
	 */
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}

	/**
	 * 127.0.0.1/api/account/user ---- put
	 * {"id":1, "userName":"hujiang111111", "password":"222222"}
	 */
	@PutMapping(value="/user", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	/**
	 * 127.0.0.1/api/account/user/10 ---- delete
	 */
	@DeleteMapping(value = "/user/{id}")
	public Result<Object> deleteUserById(@PathVariable int id) {
		return userService.deleteUserById(id);
	}

	/**
	 * 127.0.0.1/api/account/user/1 ---- get
	 */
	@GetMapping(value = "/user/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	/**
	 * 127.0.0.1/api/account/users ---- post
	 * {"currentPage":1, "pageSize":5, "sort":"id", "direction":"desc", "keyword":""}
	 */
	@PostMapping(value="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<User> getUsersBySearch(@RequestBody Search search) {
		return userService.getUsersBySearch(search);
	}
}
