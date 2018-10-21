package com.newtop.essay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtop.essay.bo.UserBo;
import com.newtop.essay.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public void addUser(UserBo userBo) {
		userDao.addUser(userBo);

	}

	@Override
	public void updateUser(UserBo userBo) {
		userDao.updateUser(userBo);
		
	}

}
