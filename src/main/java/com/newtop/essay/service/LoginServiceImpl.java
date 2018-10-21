package com.newtop.essay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtop.essay.dao.LoginDao;
import com.newtop.essay.entity.User;

@Service
public class LoginServiceImpl implements LoginService {
	

	@Autowired
	LoginDao loginDao;

	@Override
	public User login(User user) {
		User u=loginDao.login(user);
		return u;
	}
	
	
}
