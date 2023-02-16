package com.sfac.hk.account.entity;

import com.sfac.hk.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description User
 * @Author JiangHu
 * @Date 2023/2/14 9:41
 */
@Entity
@Table(name = "account_user")
public class User extends AbstractEntity {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
