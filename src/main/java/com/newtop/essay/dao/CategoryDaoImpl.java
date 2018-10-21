package com.newtop.essay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.newtop.essay.bo.CategoryBo;
import com.newtop.essay.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addCategory(CategoryBo categoryBo) {
		String sql="insert into category(categoryName,pId,remark,isDeleted) values(?,?,?,?)";
		String categoryName = categoryBo.getCategoryName();
		Integer parentId = categoryBo.getParentId();
		String remark = categoryBo.getRemark();
		Boolean isDeleted = categoryBo.getIsDeleted();
		jdbcTemplate.update(sql, new Object[] {categoryName,parentId,remark,isDeleted});

	}

	@Override
	public void updateCategory(CategoryBo categoryBo) {
		Integer categoryId = categoryBo.getCategoryId();
		String categoryName = categoryBo.getCategoryName();
		Integer parentId = categoryBo.getParentId();
		String remark = categoryBo.getRemark();
		String sql="update category set categoryName=?,pId=?,remark=? where categoryId=?";
		jdbcTemplate.update(sql, new Object[] {categoryName,parentId,remark,categoryId});
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		String sql="update category set isDeleted=1 where categoryId=?";
		jdbcTemplate.update(sql, new Object[] {categoryId});
	}

	@Override
	public List<CategoryVo> findAllCategory() {
		String sql="select * from category";
		
		List<CategoryVo> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<CategoryVo>(CategoryVo.class));
		return list;
	}

}
