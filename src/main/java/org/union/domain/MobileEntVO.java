package org.union.domain;

import java.util.Date;

public class MobileEntVO {
	private Integer ME_idx;
    private String ME_rank;
	private String ME_title;
	private String writeDate;
	private String title_key;
	private String keyword;
	private String keyword_type;
	private String uid;
	private String url;
	private String reporter_name;
	private String reporter_media_name;
	private Integer reply_cnt;
	private Date createDate;
	private Date updateDate;
		
	public Integer getME_idx() {
	    return ME_idx;
	}

	public void setME_idx(Integer mE_idx) {
	    ME_idx = mE_idx;
	}

	public String getME_rank() {
	    return ME_rank;
	}

	public void setME_rank(String mE_rank) {
	    ME_rank = mE_rank;
	}

	public String getME_title() {
	    return ME_title;
	}

	public void setME_title(String mE_title) {
	    ME_title = mE_title;
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

	public String getKeyword_type() {
	    return keyword_type;
	}

	public void setKeyword_type(String keyword_type) {
	    this.keyword_type = keyword_type;
	}

	public String getUid() {
	    return uid;
	}

	public void setUid(String uid) {
	    this.uid = uid;
	}

	public String getUrl() {
	    return url;
	}

	public void setUrl(String url) {
	    this.url = url;
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
	
	public String getReporter_name() {
		return reporter_name;
	}

	public void setReporter_name(String reporter_name) {
		this.reporter_name = reporter_name;
	}

	public String getReporter_media_name() {
		return reporter_media_name;
	}

	public void setReporter_media_name(String reporter_media_name) {
		this.reporter_media_name = reporter_media_name;
	}
	
	public Integer getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(Integer reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	@Override
	public String toString() {
		return "MobileEntVO [ME_idx=" + ME_idx + ", ME_rank=" + ME_rank + ", ME_title=" + ME_title + ", writeDate="
				+ writeDate + ", title_key=" + title_key + ", keyword=" + keyword + ", keyword_type=" + keyword_type
				+ ", uid=" + uid + ", url=" + url + ", reporter_name=" + reporter_name + ", reporter_media_name="
				+ reporter_media_name + ", reply_cnt=" + reply_cnt + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
