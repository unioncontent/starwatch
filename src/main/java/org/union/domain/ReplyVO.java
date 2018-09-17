package org.union.domain;

import java.util.Date;

public class ReplyVO {
	
	private Integer reply_idx;
	private Integer news_idx;
	private Integer reply_comm_num;
	private String reply_content;
	private String reply_writer;
	private String writeDate;
	private String textType;
	private String title_key;
	private String keyword;
	private Date createDate;
	private Date updateDate;
	private String media_title;
	private String url;
	private String company_name; 
	
	public ReplyVO() {
		super();
	}

	public ReplyVO(Integer reply_idx, Integer news_idx, Integer reply_comm_num, String reply_content,
			String reply_writer, String writeDate, String textType, String title_key, String keyword, Date createDate,
			Date updateDate, String media_title, String url) {
		super();
		this.reply_idx = reply_idx;
		this.news_idx = news_idx;
		this.reply_comm_num = reply_comm_num;
		this.reply_content = reply_content;
		this.reply_writer = reply_writer;
		this.writeDate = writeDate;
		this.textType = textType;
		this.title_key = title_key;
		this.keyword = keyword;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.media_title = media_title;
		this.url = url;
	}

	public Integer getReply_idx() {
		return reply_idx;
	}

	public void setReply_idx(Integer reply_idx) {
		this.reply_idx = reply_idx;
	}

	public Integer getNews_idx() {
		return news_idx;
	}

	public void setNews_idx(Integer news_idx) {
		this.news_idx = news_idx;
	}

	public Integer getReply_comm_num() {
		return reply_comm_num;
	}

	public void setReply_comm_num(Integer reply_comm_num) {
		this.reply_comm_num = reply_comm_num;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public String getReply_writer() {
		return reply_writer;
	}

	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getTextType() {
		return textType;
	}

	public void setTextType(String textType) {
		this.textType = textType;
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

	public String getMedia_title() {
		return media_title;
	}

	public void setMedia_title(String media_title) {
		this.media_title = media_title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}
