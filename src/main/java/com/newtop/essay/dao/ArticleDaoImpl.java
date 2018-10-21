package com.newtop.essay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.NativeWebRequest;

import com.newtop.essay.bo.ArticleBo;
import com.newtop.essay.entity.Article;
import com.newtop.essay.entity.User;
import com.newtop.essay.vo.ArticleVo;

@Repository
public class ArticleDaoImpl implements ArticleDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> findAll(User user) {
		String userId = user.getUserId();
		String querySql="SELECT DISTINCT u.name,a.`articleId`,title,content,a.time,c.`name` cname FROM USER u LEFT JOIN article a ON u.userId=a.userId LEFT JOIN artcile_category ac ON a.articleId=ac.articleId LEFT JOIN category c ON c.`categoryId`=ac.`categoryId`  WHERE u.`userId`=?";
		List<Map<String, Object>> list = jdbcTemplate.query(querySql, new Object[] {userId}, new RowMapper<Map<String, Object>>() {

			@Override
			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> map=new HashMap<String, Object>();
				String name = rs.getString("name");
				String articleId = rs.getString("articleId");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date date = (Date) rs.getObject("time");
				String cname = rs.getString("cname");
				
				
				map.put("username", name);
				map.put("articleId", articleId);
				map.put("title", title);
				map.put("content", content);
				map.put("date", date);
				map.put("cname", cname);
				
				return map;
			}
		});
		return list;
	}

	@Override
	public List<ArticleVo> findByCategory(Integer categoryId) {
		String sql="select * from article_category ac left join article a on ac.articleId=a.articleId left join user u on u.userId=a.creatorId where ac.categoryId='"+categoryId+"'";
		List<ArticleVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class));
		return list;
	}

	@Override
	public List<ArticleVo> findByArticleId(Integer articleId) {
		String sql="SELECT *  FROM USER u  LEFT JOIN  article a ON  u.`userId`=a.`creatorId` LEFT JOIN article_category ac ON a.`articleId`=ac.articleId LEFT JOIN category c ON c.categoryId=ac.categoryId WHERE a.isDeleted=b'0' AND a.isRecall=b'0' AND a.`articleId`="+articleId;
		List<ArticleVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class));
		return list;
	}

	@Override
	public List<ArticleVo> findByTitle(String title) {
		String sql="SELECT * FROM USER u LEFT JOIN article a ON u.`userId`=a.`creatorId` LEFT JOIN article_category ac ON a.`articleId`=ac.articleId LEFT JOIN category c ON c.categoryId=ac.categoryId WHERE a.`title` LIKE concat('%','"+title+"','%')";
		List<ArticleVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class));
		return list;
	}
	
	/**
	 * 根据类目名称返回数据
	 */
	@Override
	public List<ArticleVo> findByName(String categoryName) {
		String sql="SELECT * FROM USER u LEFT JOIN article a ON u.`userId`=a.`creatorId` LEFT JOIN article_category ac ON a.`articleId`=ac.articleId LEFT JOIN category c ON c.categoryId=ac.categoryId WHERE c.`categoryName` like concat('%','"+categoryName+"','%')";
		List<ArticleVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class));
		return list;
	}

	@Override
	public void addArticle(ArticleBo articleBo) {
		Integer categoryId = articleBo.getCategoryId();
		String content = articleBo.getContent();
		String title = articleBo.getTitle();
		String creatorId = articleBo.getCreatorId();
		Boolean isPublished = articleBo.getIsPublished();
		Boolean isRecall = articleBo.getIsRecall();
		Boolean isDeleted = articleBo.getIsDeleted();
		Date date = new Date();
		Timestamp createTime = new Timestamp(date.getTime());
		
		String addArticleSql="insert into article(title,content,createTime,creatorId,isPublished,isRecall,isDeleted) values('"+title+"','"+content+"','"+createTime+"','"+creatorId+"',"+isPublished+","+isRecall+","+isDeleted+")";
		//返回自增主键
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException{
				PreparedStatement ps = connection.prepareStatement(addArticleSql,Statement.RETURN_GENERATED_KEYS);
				return ps;
			}
		},keyHolder);
		int articleId = keyHolder.getKey().intValue();
		String updateArticle_CategorySql="insert into article_category(categoryId,articleId) values(?,?)";
		Object[] args=new Object[] {categoryId,articleId};
		jdbcTemplate.update(updateArticle_CategorySql, args);
	}

	@Override
	public void updateArticle(ArticleBo articleBo) {
		Integer articleId = articleBo.getArticleId();
		String content = articleBo.getContent();
		String title = articleBo.getTitle();
		Boolean isRecall = articleBo.getIsRecall();
		//Integer isNumRecall=isRecall==true?1:0;
		Object[] args=new Object[] {content,title,isRecall,articleId};
		
		String updateArticleSql="update article set content=?,title=?,isRecall=? where articleId=?";
		jdbcTemplate.update(updateArticleSql, args);

	}

	@Override
	public List<ArticleVo> findAllArticle() {
		String sql="SELECT * FROM USER u LEFT JOIN article a ON u.`userId`=a.`creatorId` LEFT JOIN article_category ac ON a.`articleId`=ac.articleId LEFT JOIN category c ON c.categoryId=ac.categoryId where a.isDeleted=b'0' and a.isRecall=b'0'";
		List<ArticleVo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleVo>(ArticleVo.class));
		return list;
	}

	@Override
	public void deleteArticleById(Integer articleId) {
		String sql="update article set isDeleted=1 where articleId='"+articleId+"'";
		jdbcTemplate.update(sql);
	}

}
