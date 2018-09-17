package org.union.domain;

import java.util.Date;

public class ViralVO {

	
	private Integer viral_idx;
	private Integer user_idx;
	private String viral_time;
	private String portal_name;
	private String portal_type;
	private Integer viral_isUser;
	private Integer viral_rank;
	private String viral_title;
	private Date writeDate;
	private String keyword;
	private String url;
	private String textType;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	
	
	public Integer getViral_idx() {
		return viral_idx;
	}
	public void setViral_idx(Integer viral_idx) {
		this.viral_idx = viral_idx;
	}
	public Integer getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(Integer user_idx) {
		this.user_idx = user_idx;
	}
	public String getViral_time() {
		return viral_time;
	}
	public void setViral_time(String viral_time) {
		this.viral_time = viral_time;
	}
	public String getPortal_name() {
		return portal_name;
	}
	public void setPortal_name(String portal_name) {
		this.portal_name = portal_name;
	}
	public String getPortal_type() {
		return portal_type;
	}
	public void setPortal_type(String portal_type) {
		this.portal_type = portal_type;
	}
	public Integer getViral_isUser() {
		return viral_isUser;
	}
	public void setViral_isUser(Integer viral_isUser) {
		this.viral_isUser = viral_isUser;
	}
	public Integer getViral_rank() {
		return viral_rank;
	}
	public void setViral_rank(Integer viral_rank) {
		this.viral_rank = viral_rank;
	}
	public String getViral_title() {
		return viral_title;
	}
	public void setViral_title(String viral_title) {
		this.viral_title = viral_title;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
		return "ViralVO [viral_idx=" + viral_idx + ", user_idx=" + user_idx + ", viral_time=" + viral_time
				+ ", portal_name=" + portal_name + ", portal_type=" + portal_type + ", viral_isUser=" + viral_isUser
				+ ", viral_rank=" + viral_rank + ", viral_title=" + viral_title + ", writeDate=" + writeDate
				+ ", keyword=" + keyword + ", url=" + url + ", textType=" + textType + ", thumbnail=" + thumbnail
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
