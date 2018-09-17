package org.union.domain;

import java.util.Date;

public class EtVO {

	
	private Integer et_idx, et_number;
	
	private String et_title, et_media_name, et_reporter_name, keyword, keyword_type, url, thumbnail;
	
	private Date writeDate, createDate, updateDate;

	
	public Integer getEt_idx() {
		return et_idx;
	}

	public void setEt_idx(Integer et_idx) {
		this.et_idx = et_idx;
	}

	public Integer getEt_number() {
		return et_number;
	}

	public void setEt_number(Integer et_number) {
		this.et_number = et_number;
	}

	public String getEt_title() {
		return et_title;
	}

	public void setEt_title(String et_title) {
		this.et_title = et_title;
	}

	public String getEt_media_name() {
		return et_media_name;
	}

	public void setEt_media_name(String et_media_name) {
		this.et_media_name = et_media_name;
	}

	public String getEt_reporter_name() {
		return et_reporter_name;
	}

	public void setEt_reporter_name(String et_reporter_name) {
		this.et_reporter_name = et_reporter_name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword_type() {
		return keyword_type;
	}

	public void setKeyword_type(String keyword_type) {
		this.keyword_type = keyword_type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
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
		return "EtVO [et_idx=" + et_idx + ", et_number=" + et_number + ", et_title=" + et_title + ", et_media_name="
				+ et_media_name + ", et_reporter_name=" + et_reporter_name + ", keyword=" + keyword + ", keyword_type="
				+ keyword_type + ", url=" + url + ", thumbnail=" + thumbnail + ", writeDate=" + writeDate
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
