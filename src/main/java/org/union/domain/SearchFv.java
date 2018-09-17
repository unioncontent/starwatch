package org.union.domain;

import java.util.Date;

public class SearchFv {
	
	private String url, createDate;
	private Date date;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
		return "SearchFv [url=" + url + ", createDate=" + createDate + ", date=" + date + "]";
	}
}
