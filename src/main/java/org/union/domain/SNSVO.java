package org.union.domain;

import java.util.Date;

public class SNSVO {

	
	private Integer sns_idx;
	private String sns_name;
	private String sns_title;
	private String sns_content;
	private String sns_writer;
	private Integer like_cnt;
	private Integer reply_cnt;
	private Integer share_cnt;
	private Integer view_cnt;
	private String writeDate;
	private String keyword_main;
	private boolean checkCondition;
	
	public String getKeyword_main() {
		return keyword_main;
	}
	public void setKeyword_main(String keyword_main) {
		this.keyword_main = keyword_main;
	}
	private String title_key;
	private String keyword;
	private String keyword_type;
	private String url;
	private String textType;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	public Integer getSns_idx() {
		return sns_idx;
	}
	public void setSns_idx(Integer sns_idx) {
		this.sns_idx = sns_idx;
	}
	public String getSns_name() {
		return sns_name;
	}
	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}
	public String getSns_title() {
		return sns_title;
	}
	public void setSns_title(String sns_title) {
		this.sns_title = sns_title;
	}
	public String getSns_content() {
		return sns_content;
	}
	public void setSns_content(String sns_content) {
		this.sns_content = sns_content;
	}
	public String getSns_writer() {
		return sns_writer;
	}
	public void setSns_writer(String sns_writer) {
		this.sns_writer = sns_writer;
	}
	public Integer getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(Integer like_cnt) {
		this.like_cnt = like_cnt;
	}
	public Integer getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(Integer reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public Integer getShare_cnt() {
		return share_cnt;
	}
	public void setShare_cnt(Integer share_cnt) {
		this.share_cnt = share_cnt;
	}
	public Integer getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(Integer view_cnt) {
		this.view_cnt = view_cnt;
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
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
	}
	
	@Override
	public String toString() {
		return "SNSVO [sns_idx=" + sns_idx + ", sns_name=" + sns_name + ", sns_title=" + sns_title + ", sns_content="
				+ sns_content + ", sns_writer=" + sns_writer + ", like_cnt=" + like_cnt + ", reply_cnt=" + reply_cnt
				+ ", share_cnt=" + share_cnt + ", view_cnt=" + view_cnt + ", writeDate=" + writeDate + ", keyword_main="
				+ keyword_main + ", checkCondition=" + checkCondition + ", title_key=" + title_key + ", keyword="
				+ keyword + ", keyword_type=" + keyword_type + ", url=" + url + ", textType=" + textType
				+ ", thumbnail=" + thumbnail + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}
