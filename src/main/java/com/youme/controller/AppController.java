package com.youme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.youme.pojo.UserInfo;
import com.youme.service.UserService;

@Controller
public class AppController {
	private static Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
			HttpSession session) {
		System.out.println("/login post提交");
		String message = "";
		UserInfo userInfo = userService.login(username, password);
        System.out.println(userInfo.toString());
		if (null == userInfo) {
			message = "用户名密码不匹配，登录失败！";
		} else {
			session.setAttribute("user", userInfo);
			message = "登录成功！";
		}
		logger.info("[" + username + "] 登录系统，" + message);
		request.setAttribute("message", message);
		return "main";
	}

	@GetMapping("/signup")
	public String signupGet() {
		return "signup";
	}

	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public String signup(@ModelAttribute UserInfo user, HttpServletRequest request,
						HttpSession session) {
		System.out.println("/signup post提交");

		String message = "";
		try {
			userService.signup(user);

		}catch (Exception e){
			e.printStackTrace();
			message = "注册失败！";
		}
		session.setAttribute("user", user);
		message = "注册成功！";
		logger.info("[" + user.getUsername()+ "] 注册系统，" + message);
		request.setAttribute("message", message);
		return "main";
	}
}
