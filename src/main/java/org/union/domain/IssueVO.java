package org.union.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class IssueVO {
	
	private Integer issue_idx;
	private String company_name;
	private String title_key;
	private String keyword;
	private String issue_content;
	private String writeDate;
	private Date createDate;
	private String company;
	private String selectKey;
	
	public Integer getIssue_idx() {
		return issue_idx;
	}
	public void setIssue_idx(Integer issue_idx) {
		this.issue_idx = issue_idx;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getTitle_key() {
		return title_key;
	}
	public void setTitle_key(String title_key) {
		this.title_key = title_key;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIssue_content() {
		return issue_content;
	}
	public void setIssue_content(String issue_content) {
		this.issue_content = issue_content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSelectKey() {
		return selectKey;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
	@Override
	public String toString() {
		return "IssueVO [issue_idx=" + issue_idx + ", company_name=" + company_name + ", title_key=" + title_key
				+ ", keyword=" + keyword + ", issue_content=" + issue_content + ", writeDate=" + writeDate
				+ ", createDate=" + createDate + ", company=" + company + ", selectKey=" + selectKey + "]";
	}
}
