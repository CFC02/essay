package com.newtop.essay.service;

import java.util.List;
import java.util.Map;

import com.newtop.essay.bo.ArticleBo;
import com.newtop.essay.entity.Article;
import com.newtop.essay.entity.User;
import com.newtop.essay.vo.ArticleVo;

public interface ArticleService {

	List<Map<String, Object>> findAll(User user);

	List<ArticleVo> findByCategoryId(Integer categoryId);

	List<ArticleVo> findByArticleId(Integer articleId);

	List<ArticleVo> findByTitle(String title);

	List<ArticleVo> findByName(String categoryName);

	void addArticle(User user, ArticleBo articleBo);

	void updateArticle(ArticleBo articleBo);

	List<ArticleVo> findAllArticle();

	void deleteArticleById(Integer articleId);

}
