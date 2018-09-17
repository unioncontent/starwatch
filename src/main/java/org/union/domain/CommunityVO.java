package org.union.domain;

import java.util.Date;

public class CommunityVO {

	
	private Integer community_idx;
	private String community_name;
	private String community_title;
	private String community_content;
	private String community_writer;
	private String community_writer_IP;
	private String writeDate;
	private String title_key;
	private boolean checkCondition;
	
	public String getKeyword_main() {
		return title_key;
	}
	public void setKeyword_main(String keyword_main) {
		this.title_key = keyword_main;
	}
	private String keyword;
	private String keyword_type;
	private String url;
	private Long board_number;
	private String textType;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	private String domain;

	public Integer getCommunity_idx() {
		return community_idx;
	}
	public void setCommunity_idx(Integer community_idx) {
		this.community_idx = community_idx;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public String getCommunity_title() {
		return community_title;
	}
	public void setCommunity_title(String community_title) {
		this.community_title = community_title;
	}
	public String getCommunity_content() {
		return community_content;
	}
	public void setCommunity_content(String community_content) {
		this.community_content = community_content;
	}
	public String getCommunity_writer() {
		return community_writer;
	}
	public void setCommunity_writer(String community_writer) {
		this.community_writer = community_writer;
	}
	public String getCommunity_writer_IP() {
		return community_writer_IP;
	}
	public void setCommunity_writer_IP(String community_writer_IP) {
		this.community_writer_IP = community_writer_IP;
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
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
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
	public Long getBoard_number() {
		return board_number;
	}
	public void setBoard_number(Long board_number) {
		this.board_number = board_number;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	@Override
	public String toString() {
		return "CommunityVO [community_idx=" + community_idx + ", community_name=" + community_name
				+ ", community_title=" + community_title + ", community_content=" + community_content
				+ ", community_writer=" + community_writer + ", community_writer_IP=" + community_writer_IP
				+ ", writeDate=" + writeDate + ", title_key=" + title_key + ", checkCondition=" + checkCondition
				+ ", keyword=" + keyword + ", keyword_type=" + keyword_type + ", url=" + url + ", board_number="
				+ board_number + ", textType=" + textType + ", thumbnail=" + thumbnail + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", domain=" + domain + "]";
	}
	
	
}
