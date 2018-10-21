package com.newtop.essay.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtop.essay.bo.CategoryBo;
import com.newtop.essay.dao.CategoryDao;
import com.newtop.essay.vo.CategoryVo;

import net.sf.json.JSONObject;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public void addCategory(CategoryBo categoryBo) {
		categoryDao.addCategory(categoryBo);
	}

	@Override
	public void updateCategory(CategoryBo categoryBo) {
		categoryDao.updateCategory(categoryBo);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryDao.deleteCategory(categoryId);
		
	}

	@Override
	public CategoryVo findAllCategory() {
		List<CategoryVo> list=categoryDao.findAllCategory();
		//System.out.println(list.get(0));
		CategoryVo root = list.get(0);
		CategoryVo node = null;
		for (CategoryVo categoryVo : list) {
			if(categoryVo.getCategoryId()==0) {
				continue;
			}
			Integer categoryId = categoryVo.getCategoryId();
			Integer pId = categoryVo.getPId();
			String categoryName = categoryVo.getCategoryName();
			String remark = categoryVo.getRemark();
			node=new CategoryVo(categoryId, pId, categoryName, remark);
			root.add(node/*, customizePid*/);
			
		}
//        JSONObject obj = JSONObject.fromObject(root);//有根
//        JSONArray obj = JSONArray.fromObject(root.getChildren());// 不要根
//        System.out.println(obj.toString());
        
		return root;
	}

}
