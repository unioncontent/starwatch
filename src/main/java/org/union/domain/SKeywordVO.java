package org.union.domain;

import java.util.Date;

public class SKeywordVO {
	
	private Integer keyword_idx;
	private Integer user_idx;
	private String keyword_main;
	private String keyword;
	private Date createDate;
	private Date updateDate;
	
	public Integer getKeyword_idx() {
		return keyword_idx;
	}
	public void setKeyword_idx(Integer keyword_idx) {
		this.keyword_idx = keyword_idx;
	}
	public Integer getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(Integer user_idx) {
		this.user_idx = user_idx;
	}
	public String getKeyword_main() {
		return keyword_main;
	}
	public void setKeyword_main(String keyword_main) {
		this.keyword_main = keyword_main;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "SKeywordVO [keyword_idx=" + keyword_idx + ", user_idx=" + user_idx + ", keyword_main=" + keyword_main
				+ ", keyword=" + keyword + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
