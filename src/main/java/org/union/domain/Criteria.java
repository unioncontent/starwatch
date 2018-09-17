package org.union.domain;


public class Criteria {

	private Integer perPageNum, page;


	public void getSearchVO() {

	}

	public void setSearchVO() {

	}

	public Integer getStartPage() {
		return (this.page - 1) * perPageNum;
	}
	
	public Criteria() {
		this.perPageNum = 10;
		this.page = 1;
	}

	public Integer getPerPageNum() {
		
		return perPageNum;
	}

	public void setPerPageNum(Integer perPageNum) {

		if (perPageNum <= 0 || perPageNum > 200) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {

		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	@Override
	public String toString() {
		return "PageVO [perPageNum=" + perPageNum + ", page=" + page + "]";
	}
}
