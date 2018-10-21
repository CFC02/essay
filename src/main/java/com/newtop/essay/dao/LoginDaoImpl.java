package com.newtop.essay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.newtop.essay.entity.User;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User login(User user) {
		
		String email2 = user.getEmail();
		String password2 = user.getPassword();
		String querySql = "select * from user where email=? and password=?";
		List<User> list = jdbcTemplate.query(querySql, new Object[] {email2,password2}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				String userId = rs.getString("userId");
				String userName = rs.getString("userName");
				int gender = rs.getInt("gender");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String password = rs.getString("password");
				User u = new User();
				u.setAddress(address);
				u.setEmail(email);
				u.setGender(gender);
				u.setUsername(userName);;
				u.setPassword(password);
				u.setUserId(userId);
				return u;
			}
		});
		return list.get(0);
	}

}
