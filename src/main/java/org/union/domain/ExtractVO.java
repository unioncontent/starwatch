package org.union.domain;

public class ExtractVO {

	
	private String domain, domainType, company,keyword_main, keyword, title, content, writeDate,writer,
				createDate, url, textType, thumbnail, view_cnt, reply_cnt, like_cnt, reporter_name, reporter_media_name, writer_IP, ME_rank;
	private Integer sns_idx, community_idx, media_idx, portal_idx, reply_idx, mreply_cnt;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getKeyword_main() {
		return keyword_main;
	}
	public void setKeyword_main(String keyword_main) {
		this.keyword_main = keyword_main;
	}
	public String getDomainType() {
		return domainType;
	}
	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public Integer getSns_idx() {
		return sns_idx;
	}
	public void setSns_idx(Integer sns_idx) {
		this.sns_idx = sns_idx;
	}
	public Integer getCommunity_idx() {
		return community_idx;
	}
	public void setCommunity_idx(Integer community_idx) {
		this.community_idx = community_idx;
	}
	public Integer getMedia_idx() {
		return media_idx;
	}
	public void setMedia_idx(Integer media_idx) {
		this.media_idx = media_idx;
	}
	public Integer getPortal_idx() {
		return portal_idx;
	}
	public void setPortal_idx(Integer portal_idx) {
		this.portal_idx = portal_idx;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Integer getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(Integer reply_idx) {
		this.reply_idx = reply_idx;
	}
	
	public String getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(String view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(String reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public String getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(String like_cnt) {
		this.like_cnt = like_cnt;
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
	public String getWriter_IP() {
		return writer_IP;
	}
	public void setWriter_IP(String writer_IP) {
		this.writer_IP = writer_IP;
	}
	public Integer getMreply_cnt() {
		return mreply_cnt;
	}
	public void setMreply_cnt(Integer mreply_cnt) {
		this.mreply_cnt = mreply_cnt;
	}
	public String getME_rank() {
		return ME_rank;
	}
	public void setME_rank(String mE_rank) {
		ME_rank = mE_rank;
	}
	@Override
	public String toString() {
		return "ExtractVO [domain=" + domain + ", domainType=" + domainType + ", company=" + company + ", keyword_main="
				+ keyword_main + ", keyword=" + keyword + ", title=" + title + ", content=" + content + ", writeDate="
				+ writeDate + ", writer=" + writer + ", createDate=" + createDate + ", url=" + url + ", textType="
				+ textType + ", thumbnail=" + thumbnail + ", view_cnt=" + view_cnt + ", reply_cnt=" + reply_cnt
				+ ", like_cnt=" + like_cnt + ", reporter_name=" + reporter_name + ", reporter_media_name="
				+ reporter_media_name + ", writer_IP=" + writer_IP + ", ME_rank=" + ME_rank + ", sns_idx=" + sns_idx
				+ ", community_idx=" + community_idx + ", media_idx=" + media_idx + ", portal_idx=" + portal_idx
				+ ", reply_idx=" + reply_idx + ", mreply_cnt=" + mreply_cnt + "]";
	}
}
