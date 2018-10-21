package com.newtop.essay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newtop.essay.bo.CategoryBo;
import com.newtop.essay.service.ArticleService;
import com.newtop.essay.service.CategoryService;
import com.newtop.essay.vo.ArticleVo;
import com.newtop.essay.vo.CategoryVo;

@RestController
@RequestMapping(value="/",produces="application/json; charset=utf-8")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ArticleService articleService;
	
	/**
	 * 1.0
	 * 添加条目
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String addCategory(@Validated CategoryBo categoryBo) {
		try {
			categoryService.addCategory(categoryBo);
		} catch (Exception e) {
			return "添加条目失败";
		}
		return "添加条目成功";
	}
	
	/**
	 * 1.0
	 * 修改条目
	 * @param categoryBo
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.PUT)
	public String updateCategory(@Validated CategoryBo categoryBo) {
		try {
			categoryService.updateCategory(categoryBo);
		} catch (Exception e) {
			return "修改条目失败";
		}
		return "修改条目成功";
	}
	
	/**
	 * 1.0
	 * 根据条目id逻辑删除
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.DELETE)
	public String deleteCategory(@RequestParam("categoryId") Integer categoryId) {
		try {
			categoryService.deleteCategory(categoryId);
		} catch (Exception e) {
			return "删除条目失败";
		}
		return "删除条目成功";
	}
	
	/**
	 * 根据文章类目ID来查询文章
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/category/categoryId",method=RequestMethod.GET)
	public List<ArticleVo> queryArticleByCategory(Integer categoryId) {
		List<ArticleVo> list=articleService.findByCategoryId(categoryId);
		return list;

	}
	
	/**
	 * 1.0
	 * 根据类目名称模糊查询文章
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/category/categoryName",method=RequestMethod.GET)
	public List<ArticleVo> queryArticleByName(String categoryName){
		List<ArticleVo> list=articleService.findByName(categoryName);
		return list;
	}
	
	/**
	 * 1.0
	 * 查询无限级子目录
	 * @return
	 */
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public CategoryVo queryCategory(){
		CategoryVo categoryVo=categoryService.findAllCategory();
		return categoryVo;
	}

}
