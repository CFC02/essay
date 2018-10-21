package com.newtop.essay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newtop.essay.entity.User;
import com.newtop.essay.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@GetMapping(value="/login")
	public String login(User user,HttpServletRequest request) {
		User user2=loginService.login(user);
		if(user2!=null) {
			request.getSession().setAttribute("loginUser", user2);
			return "登录成功";
		}else {
			return "登录失败";
		}
	}

}
