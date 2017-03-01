package com.youme.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.youme.pojo.UserInfo;

public interface UserMapper {

//	@Select("SELECT username FROM user WHERE password=#{password} AND username=#{username}")
	UserInfo login(@Param("username") String username, @Param("password") String password);

//	@Insert("insert into user(username,password) value(#{username},#{password})")
	UserInfo signup(UserInfo user);
}
