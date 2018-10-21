package com.newtop.essay.dao;

import java.util.List;
import java.util.Map;

import com.newtop.essay.bo.CategoryBo;
import com.newtop.essay.vo.CategoryVo;

public interface CategoryDao {

	void addCategory(CategoryBo categoryBo);

	void updateCategory(CategoryBo categoryBo);

	void deleteCategory(Integer categoryId);

	List<CategoryVo> findAllCategory();

}
