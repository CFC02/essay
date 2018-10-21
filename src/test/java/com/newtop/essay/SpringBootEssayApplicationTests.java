package com.newtop.essay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.newtop.essay.vo.CategoryVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEssayApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoads() {
		List<String> list = jdbcTemplate.query("select * from t1", new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String name = rs.getString("name");
				return name;
			}
			
		});
		System.out.println(list.get(0));
	}

}
