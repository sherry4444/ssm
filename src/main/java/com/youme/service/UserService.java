package com.youme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youme.mapper.UserDao;
import com.youme.pojo.UserInfo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public UserInfo login(UserInfo user) {
		return userDao.login(user);
	}

    public UserInfo signup(UserInfo user) {
		return userDao.signup(user);
    }
}
