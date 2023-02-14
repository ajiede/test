package com.sfac.hk.account.controller;

import com.sfac.hk.account.entity.User;
import com.sfac.hk.account.service.UserService;
import com.sfac.hk.common.vo.Result;
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
}
