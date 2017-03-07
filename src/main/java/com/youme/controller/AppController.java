package com.youme.controller;

import javax.enterprise.inject.Model;
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

	/*登录*/
	@GetMapping("/")
	public String index(Model model) {
		logger.debug(">>>>>>>>我就测试下日志 debug  <<<<<<<<<");
		logger.info(">>>>>>>> 我就测试下日志 info   <<<<<<<<<");
		return "home/login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute UserInfo user, HttpServletRequest request,
			HttpSession session) {
		System.out.println("/login post提交");
		String message = "";
		UserInfo userInfo = userService.login(user);
        System.out.println(userInfo.toString());
		if (null == userInfo) {
			message = "用户名密码不匹配，登录失败！";
		} else {
			session.setAttribute("user", userInfo);
			message = "登录成功！";
		}
		logger.info("[" + user.getUserName() + "] 登录系统，" + message);
		request.setAttribute("message", message);
		return "home/success";
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
		logger.info("[" + user.getUserName()+ "] 注册系统，" + message);
		request.setAttribute("message", message);
		return "home/success";
	}
}
