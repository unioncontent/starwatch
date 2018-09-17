package org.union.domain;

import java.util.Date;

public class NvVO {
	
	private Integer portal_idx;
	private String portal_name;
	private String portal_title;
	private String portal_subtitle;
	private String portal_writer;
	private String url;
	private Integer view_cnt;
	private Integer like_cnt;
	private Integer reply_cnt;
	private Integer share_cnt;
	private String writeDate;
	private Integer board_number;
	private String uid;
	private String title_key;
	private String keyword;
	private String keyword_type;
	private String textType;
	private String thumbnail;
	private Date createDate;
	private Date updateDate;
	private Integer total;
	
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
	public String getPortal_title() {
		return portal_title;
	}
	public void setPortal_title(String portal_title) {
		this.portal_title = portal_title;
	}
	public String getPortal_subtitle() {
		return portal_subtitle;
	}
	public void setPortal_subtitle(String portal_subtitle) {
		this.portal_subtitle = portal_subtitle;
	}
	public String getPortal_writer() {
		return portal_writer;
	}
	public void setPortal_writer(String portal_writer) {
		this.portal_writer = portal_writer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(Integer view_cnt) {
		this.view_cnt = view_cnt;
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
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Integer getBoard_number() {
		return board_number;
	}
	public void setBoard_number(Integer board_number) {
		this.board_number = board_number;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
	}
	
	@Override
	public String toString() {
		return "NvVO [portal_idx=" + portal_idx + ", portal_name=" + portal_name + ", portal_title=" + portal_title
				+ ", portal_subtitle=" + portal_subtitle + ", portal_writer=" + portal_writer + ", url=" + url
				+ ", view_cnt=" + view_cnt + ", like_cnt=" + like_cnt + ", reply_cnt=" + reply_cnt + ", share_cnt="
				+ share_cnt + ", writeDate=" + writeDate + ", board_number=" + board_number + ", uid=" + uid
				+ ", title_key=" + title_key + ", keyword=" + keyword + ", keyword_type=" + keyword_type + ", textType="
				+ textType + ", thumbnail=" + thumbnail + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", total=" + total + ", checkCondition=" + checkCondition + "]";
	}
}
