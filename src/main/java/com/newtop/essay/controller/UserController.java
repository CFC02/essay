package com.newtop.essay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newtop.essay.bo.UserBo;
import com.newtop.essay.entity.User;
import com.newtop.essay.service.ArticleService;
import com.newtop.essay.service.UserService;

@RestController
@RequestMapping(value="/",produces="application/json; charset=utf-8")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	
	/**
	 * 根据session中的用户id查询其发表的文章
	 * @param request
	 * @return
	 */
//	@RequestMapping(value="/user")
//	public List<Map<String, Object>> user(HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("loginUser");
//		List<Map<String, Object>> list=articleService.findAll(user);
//		return list;
//	}
//	
	/**
	 * 根据用户id来查询其发表的文章
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/user")
	public List<Map<String, Object>> queryArticleByUserId(String userId) {
		User user=new User();
		//设置用户id
		user.setUserId(userId);
		List<Map<String, Object>> list=articleService.findAll(user);
		return list;
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String addUser(UserBo userBo) {
		try {
			userService.addUser(userBo);
		} catch (Exception e) {
			return "添加失败";
		}
		return "添加成功";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	public String updateUser(UserBo userBo) {
		try {
			userService.updateUser(userBo);
		} catch (Exception e) {
			return "修改失败";
		}
		return "修改成功";
	}


}
