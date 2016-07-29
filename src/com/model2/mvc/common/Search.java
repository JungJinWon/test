package com.model2.mvc.common;

import java.util.ArrayList;

public class Search {
	
	private int page;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	private ArrayList<String> userId;
	private int endRowNum;
	private int startRowNum;
	
	public Search(){
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public ArrayList<String> getUserId() {
		return userId;
	}

	public void setUserId(ArrayList<String> userId) {
		this.userId = userId;
	}
	public int getEndRowNum() {
		return getPage()*getPageSize();
	}
	
	public int getStartRowNum() {
		return (getPage()-1)*getPageSize()+1;
	}

	@Override
	public String toString() {
		return "Search [page=" + page + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageSize=" + pageSize + ", userId=" + userId + ", endRowNum=" + endRowNum + ", startRowNum="
				+ startRowNum + "]";
	}
	
	
}