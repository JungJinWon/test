package com.model2.mvc.common;

public class Page {
	
	private int currentPage;
	private int totalPage;
	private int pageUnit;
	private int total;
	private int pageSize;
	private int beginPage;
	private int endPage;
	
	public Page(){
	}
	public Page(int currentPage, int total,	int pageUnit, int pageSize ) {
		this.total = total;
		this.pageUnit = pageUnit;
		this.pageSize = pageSize;
		
		this.totalPage = (pageSize == 0) ? total :  (total-1)/pageSize +1;
		this.currentPage = ( currentPage > totalPage) ? totalPage : currentPage;
		
		this.beginPage = ( (currentPage-1) / pageUnit ) * pageUnit +1 ;
		
		if( totalPage <= pageUnit ){
			this.endPage = totalPage;
		}else{
			this.endPage = beginPage + (pageUnit -1);
			if( totalPage <= endPage){
				this.endPage = totalPage;
			}
		}
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", total="
				+ total + ", pageUnit=" + pageUnit + ", pageSize="
				+ pageSize + ", totalPage=" + totalPage + ", beginPage="
				+ beginPage + ", endPage=" + endPage + "]";
	}
	
}
