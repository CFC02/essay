package com.newtop.essay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newtop.essay.bo.ArticleBo;
import com.newtop.essay.entity.Article;
import com.newtop.essay.entity.User;
import com.newtop.essay.service.ArticleService;
import com.newtop.essay.vo.ArticleVo;

@RestController
@RequestMapping(value="/", produces="application/json; charset=utf-8")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;

	/**
	 * 1.0
	 * 根据文章id来查询文章
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/article/articleId",method=RequestMethod.GET)
	public List<ArticleVo> queryArticleByArticleId(Integer articleId) {
		List<ArticleVo> list=articleService.findByArticleId(articleId);
		return list;
	}
	
	/**
	 * 1.0
	 * 根据文章标题模糊查询
	 * @param title
	 * @return
	 */
	@RequestMapping(value="/article/title",method=RequestMethod.GET)
	public List<ArticleVo> queryArticleByTitle(String title){
		List<ArticleVo> list=articleService.findByTitle(title);
		return list;
	}
	
	/**
	 * 1.0
	 * 用户上传文章
	 * @param request
	 * @param articleBo
	 * @return
	 */
	//@RequestBody
	
	
	@RequestMapping(value="/article",method=RequestMethod.POST)
	public String addArticle(@Validated ArticleBo articleBo,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		Boolean isPublished = articleBo.getIsPublished();
		if(isPublished) {
			try {
				articleService.addArticle(user,articleBo);
			} catch (Exception e) {
				return "文章发表失败";
			}
			return "文章发表成功";
		}else {
			try {
				articleService.addArticle(user,articleBo);
			} catch (Exception e) {
				return "草稿存储失败";
			}
			return "草稿存储成功";
			
		}
	}
	
	/**
	 * 1.0
	 * 用户修改文章
	 * @param articleBo
	 * @return
	 */
	@RequestMapping(value="/article",method=RequestMethod.PUT)
	public String updateArticle(@Validated ArticleBo articleBo) {
		Boolean isRecall = articleBo.getIsRecall();
		if(isRecall) {
			try {
				articleService.updateArticle(articleBo);
			} catch (Exception e) {
				return "文章撤回失败";
			}
			return "文章撤回成功";
		}else {
			try {
				articleService.updateArticle(articleBo);
			} catch (Exception e) {
				return "文章修改失败";
			}
			return "文章修改成功";
		}
	}
	
	/**
	 * 1.0
	 * 查询所有文章
	 * @return
	 */
	@RequestMapping(value="/article",method=RequestMethod.GET)
	public List<ArticleVo> queryAllArticle(){
		List<ArticleVo> list=articleService.findAllArticle();
		return list;
	}
	
	/**
	 * 1.0
	 * 根据ID删除文章
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/article",method=RequestMethod.DELETE)
	public String deleteArticleById(Integer articleId) {
		try {
			articleService.deleteArticleById(articleId);
			
		} catch (Exception e) {
			return "删除文章失败";
		}
		return "删除文章成功";
	}
	
}
