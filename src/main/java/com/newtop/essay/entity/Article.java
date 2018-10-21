package com.newtop.essay.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer articleId;
	private String title;
	private String content;
	private Date createTime;
	private String creatorId ;
	private Boolean isPublished;
	private Boolean isDeleted;
	private Boolean isRecall;
	
	
	
	
	
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Boolean getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Boolean getIsRecall() {
		return isRecall;
	}
	public void setIsRecall(Boolean isRecall) {
		this.isRecall = isRecall;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
