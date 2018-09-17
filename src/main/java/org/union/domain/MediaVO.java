package org.union.domain;

import java.util.Date;

public class MediaVO {

	
	private Integer media_idx;
	private String media_name;
	private String media_title;
	private String media_content;
	private String reporter_name;
	private String reporter_ID;
	private String reporter_email;
	private String writeDate;
	private String last_writeDate;
	private Date last_media_title;
	private Date last_media_content;
	private String title_key;
	public String getKeyword_main() {
		return title_key;
	}
	public void setKeyword_main(String keyword_main) {
		this.title_key = keyword_main;
	}
	private String keyword;
	private String keyword_type;
	private String url;
	private String textType;
	private Integer media_state;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	private boolean checkCondition;
	private double total;
	private Integer match_total;
	private Integer match_total2;
	
	public String getTitle_key() {
		return title_key;
	}
	public void setTitle_key(String title_key) {
		this.title_key = title_key;
	}
	public Integer getMedia_idx() {
		return media_idx;
	}
	public void setMedia_idx(Integer media_idx) {
		this.media_idx = media_idx;
	}
	public String getMedia_name() {
		return media_name;
	}
	public void setMedia_name(String media_name) {
		this.media_name = media_name;
	}
	public String getMedia_title() {
		return media_title;
	}
	public void setMedia_title(String media_title) {
		this.media_title = media_title;
	}
	public String getMedia_content() {
		return media_content;
	}
	public void setMedia_content(String media_content) {
		this.media_content = media_content;
	}
	public String getReporter_name() {
		return reporter_name;
	}
	public void setReporter_name(String reporter_name) {
		this.reporter_name = reporter_name;
	}
	public String getReporter_ID() {
		return reporter_ID;
	}
	public void setReporter_ID(String reporter_ID) {
		this.reporter_ID = reporter_ID;
	}
	public String getReporter_email() {
		return reporter_email;
	}
	public void setReporter_email(String reporter_email) {
		this.reporter_email = reporter_email;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getLast_writeDate() {
		return last_writeDate;
	}
	public void setLast_writeDate(String last_writeDate) {
		this.last_writeDate = last_writeDate;
	}
	public Date getLast_media_title() {
		return last_media_title;
	}
	public void setLast_media_title(Date last_media_title) {
		this.last_media_title = last_media_title;
	}
	public Date getLast_media_content() {
		return last_media_content;
	}
	public void setLast_media_content(Date last_media_content) {
		this.last_media_content = last_media_content;
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
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
	}
	public Integer getMedia_state() {
		return media_state;
	}
	public void setMedia_state(Integer media_state) {
		this.media_state = media_state;
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
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getMatch_total() {
		return match_total;
	}
	public void setMatch_total(Integer match_total) {
		this.match_total = match_total;
	}
	public Integer getMatch_total2() {
		return match_total2;
	}
	public void setMatch_total2(Integer match_total2) {
		this.match_total2 = match_total2;
	}
	@Override
	public String toString() {
		return "MediaVO [media_idx=" + media_idx + ", media_name=" + media_name + ", media_title=" + media_title
				+ ", media_content=" + media_content + ", reporter_name=" + reporter_name + ", reporter_ID="
				+ reporter_ID + ", reporter_email=" + reporter_email + ", writeDate=" + writeDate + ", last_writeDate="
				+ last_writeDate + ", last_media_title=" + last_media_title + ", last_media_content="
				+ last_media_content + ", title_key=" + title_key + ", keyword=" + keyword + ", keyword_type="
				+ keyword_type + ", url=" + url + ", textType=" + textType + ", media_state=" + media_state
				+ ", thumbnail=" + thumbnail + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", checkCondition=" + checkCondition + ", total=" + total + ", match_total=" + match_total
				+ ", match_total2=" + match_total2 + "]";
	}

}
