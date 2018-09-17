package org.union.domain;

import java.util.Date;

public class NavertalkVO {
	
	private Integer portal_idx;
	private String portal_content;
	private String portal_name;
	private String writer;
	private String writeDate;
	private String title_key;
	private String keyword;
	private String url;
	private String textType;
	private Date createDate;
	private Date updateDate;
	public Integer getPortal_idx() {
		return portal_idx;
	}
	public void setPortal_idx(Integer portal_idx) {
		this.portal_idx = portal_idx;
	}
	public String getPortal_content() {
		return portal_content;
	}
	public void setPortal_content(String portal_content) {
		this.portal_content = portal_content;
	}
	public String getPortal_name() {
		return portal_name;
	}
	public void setPortal_name(String portal_name) {
		this.portal_name = portal_name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
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
		return "NavertalkVO [portal_idx=" + portal_idx + ", portal_content=" + portal_content + ", portal_name="
				+ portal_name + ", writer=" + writer + ", writeDate=" + writeDate + ", title_key=" + title_key
				+ ", keyword=" + keyword + ", url=" + url + ", textType=" + textType + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
