package com.youme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youme.mapper.UserMapper;
import com.youme.pojo.UserInfo;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public UserInfo login(String account, String password) {
		return userMapper.login(account, password);
	}

    public UserInfo signup(UserInfo user) {
		return userMapper.signup(user);
    }
}
