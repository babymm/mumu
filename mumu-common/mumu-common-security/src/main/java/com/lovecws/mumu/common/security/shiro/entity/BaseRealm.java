package com.lovecws.mumu.common.security.shiro.entity;

import java.io.Serializable;

public class BaseRealm implements Serializable {

	private static final long serialVersionUID = -8181953474804190529L;

	private String userName;
	private String password;
	private String salt;

	public BaseRealm() {
		super();
	}

	public BaseRealm(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "BaseRealm [userName=" + userName + ", password=" + password + ", salt=" + salt + "]";
	}

}
