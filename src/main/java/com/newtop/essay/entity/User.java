package com.newtop.essay.entity;

import java.io.Serializable;
import java.util.HashSet;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String username;
	private Integer gender;
	private String address;
	private String email;
	private String password;
	
	private HashSet<Article> articles;
	
	
	
	
	
	public HashSet<Article> getArticles() {
		return articles;
	}
	public void setArticles(HashSet<Article> articles) {
		this.articles = articles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", gender=" + gender + ", address=" + address
				+ ", email=" + email + ", password=" + password + ", articles=" + articles + "]";
	}
	
	
	

}
