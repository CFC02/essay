package com.newtop.essay.vo;

import java.util.ArrayList;
import java.util.HashSet;

public class CategoryVo {
	
	private Integer categoryId;
	private String categoryName;
	private Integer pId;
	private String remark;
	private ArrayList<CategoryVo> children = new ArrayList<CategoryVo>();
	private Boolean isDeleted;
	
	public CategoryVo(Integer categoryId,Integer pId, String categoryName,String remark) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.pId = pId;
		this.remark = remark;
	}
	
	 public CategoryVo() {
		super();
	}

	public void add(CategoryVo CategoryVoNode/*,Integer customizePid*/) {//递归添加节点
	        if ("0".equals(CategoryVoNode.pId)) {
	            this.children.add(CategoryVoNode);
	        } else if (CategoryVoNode.pId.equals(this.categoryId)) {
	            this.children.add(CategoryVoNode);
	        } else {
	            for (CategoryVo tmp_node : children) {
	                tmp_node.add(CategoryVoNode/*,customizePid*/);
	            }
	        }
	}
	
	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ArrayList<CategoryVo> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<CategoryVo> children) {
		this.children = children;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
