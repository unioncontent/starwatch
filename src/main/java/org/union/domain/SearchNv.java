package org.union.domain;

import java.util.Date;

public class SearchNv {
	
	private String url2, createDate;
	private Date date;
	
	public String getUrl() {
		return url2;
	}
	public void setUrl(String url) {
		this.url2 = url2;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "SearchFv [url2=" + url2 + ", createDate=" + createDate + ", date=" + date + "]";
	}
}
