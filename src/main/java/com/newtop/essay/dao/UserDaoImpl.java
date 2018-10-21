package com.newtop.essay.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.newtop.essay.bo.UserBo;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void addUser(UserBo userBo) {
		String address = userBo.getAddress();
		String email = userBo.getEmail();
		Integer gender = userBo.getGender();
		String name = userBo.getName();
		String password = userBo.getPassword();
		
		String sql="insert into user(address,email,gender,name,password) values(?,?,?,?,?)";
		Object[] args=new Object[] {address,email,gender,name,password};
		jdbcTemplate.update(sql, args);
		
	}

	@Override
	public void updateUser(UserBo userBo) {
		Integer userId = userBo.getUserId();
		String address = userBo.getAddress();
		String email = userBo.getEmail();
		Integer gender = userBo.getGender();
		String name = userBo.getName();
		String password = userBo.getPassword();
		
		String sql="update user set address=?,email=?,gender=?,name=?,password=? where userId=?";
		Object[] args=new Object[] {address,email,gender,name,password,userId};
		jdbcTemplate.update(sql, args);
		
	}

}
