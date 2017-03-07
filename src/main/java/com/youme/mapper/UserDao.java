package com.youme.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.youme.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserDao {

//	@Select("SELECT username FROM user WHERE password=#{password} AND username=#{username}")
	UserInfo login(UserInfo user);

//	@Insert("insert into user(username,password) value(#{username},#{password})")
	UserInfo signup(UserInfo user);

    List<UserInfo> finduserAll(Map<String, Object> parameter);
}
