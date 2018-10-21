package com.newtop.essay.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtop.essay.bo.ArticleBo;
import com.newtop.essay.dao.ArticleDao;
import com.newtop.essay.entity.Article;
import com.newtop.essay.entity.User;
import com.newtop.essay.vo.ArticleVo;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleDao articleDao;

	@Override
	public List<Map<String, Object>> findAll(User user) {
		List<Map<String, Object>> list=articleDao.findAll(user);
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<ArticleVo> findByCategoryId(Integer categoryId) {
		List<ArticleVo> list=articleDao.findByCategory(categoryId);
		return list;
	}

	@Override
	public List<ArticleVo> findByArticleId(Integer articleId) {
		List<ArticleVo> list=articleDao.findByArticleId(articleId);
		return list;
	}

	@Override
	public List<ArticleVo> findByTitle(String title) {
		List<ArticleVo> list=articleDao.findByTitle(title);
		return list;
	}

	@Override
	public List<ArticleVo> findByName(String categoryName) {
		List<ArticleVo> list=articleDao.findByName(categoryName);
		return list;
	}

	@Override
	public void addArticle(User user, ArticleBo articleBo) {
		String userId = user.getUserId();
		articleBo.setCreatorId(userId);
		articleDao.addArticle(articleBo);
	}

	@Override
	public void updateArticle(ArticleBo articleBo) {
		articleDao.updateArticle(articleBo);
		
	}

	@Override
	public List<ArticleVo> findAllArticle() {
		List<ArticleVo> list=articleDao.findAllArticle();
		return list;
	}

	@Override
	public void deleteArticleById(Integer articleId) {
		articleDao.deleteArticleById(articleId);
	}

}
