package org.union.domain;

import java.util.Date;

public class ScoreVO {

	
	private Integer portal_idx;
	private String portal_name;
	private String portal_type;
	private String portal_title;
	private String portal_content;
	private Integer deviceType; // 1 = PC, 2 = Mobile
	private String writeDate;
	private String keyword_main;
	private String keyword;
	private String keyword_type;
	private String url;
	private String textType;
	private String portal_state;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	private String writer;
	private Integer score;
	private boolean checkCondition;
	
	
	public Integer getPortal_idx() {
		return portal_idx;
	}
	public void setPortal_idx(Integer portal_idx) {
		this.portal_idx = portal_idx;
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
	public String getPortal_title() {
		return portal_title;
	}
	public void setPortal_title(String portal_title) {
		this.portal_title = portal_title;
	}
	public String getPortal_content() {
		return portal_content;
	}
	public void setPortal_content(String portal_content) {
		this.portal_content = portal_content;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
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
	public String getPortal_state() {
		return portal_state;
	}
	public void setPortal_state(String portal_state) {
		this.portal_state = portal_state;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
	}
	@Override
	public String toString() {
		return "PortalVO [portal_idx=" + portal_idx + ", portal_name=" + portal_name + ", portal_type=" + portal_type
				+ ", portal_title=" + portal_title + ", portal_content=" + portal_content + ", deviceType=" + deviceType
				+ ", writeDate=" + writeDate + ", keyword_main=" + keyword_main + ", keyword=" + keyword
				+ ", keyword_type=" + keyword_type + ", url=" + url + ", textType=" + textType + ", portal_state="
				+ portal_state + ", thumbnail=" + thumbnail + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", writer=" + writer + ", score=" + score + ", checkCondition=" + checkCondition + "]";
	}
}
