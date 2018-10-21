package com.newtop.essay.dao;

import com.newtop.essay.bo.UserBo;

public interface UserDao {

	void addUser(UserBo userBo);

	void updateUser(UserBo userBo);

}
