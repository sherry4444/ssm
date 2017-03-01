package com.youme.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userid;
	private String password;
	private String username;

	public UserInfo() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userid=" + userid +
				", password='" + password + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
