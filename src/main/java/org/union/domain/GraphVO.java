package org.union.domain;

public class GraphVO {

	
	private String startDate;
	private String endDate;
	private String sns_name;
	private String writeDate;
	private String writeDate2;
	private Integer likeCount;
	private Integer shareCount;
	private Integer replyCount;
	private Integer facebookCount, instagramCount, twitterCount, youtubeCount;
	private Integer type1, type2, type3, type4, type5, type6;
	private String company, selectKey;
	private String media;
	private double media_total;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSelectkey() {
		return selectKey;
	}
	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSns_name() {
		return sns_name;
	}
	public void setSns_name(String sns_name) {
		this.sns_name = sns_name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	public String getWriteDate2() {
		return writeDate2;
	}
	public void setWriteDate2(String writeDate2) {
		this.writeDate2 = writeDate2;
	}
	public String getSelectKey() {
		return selectKey;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Integer getFacebookCount() {
		return facebookCount;
	}
	public void setFacebookCount(Integer facebookCount) {
		this.facebookCount = facebookCount;
	}
	public Integer getInstagramCount() {
		return instagramCount;
	}
	public void setInstagramCount(Integer instagramCount) {
		this.instagramCount = instagramCount;
	}
	public Integer getTwitterCount() {
		return twitterCount;
	}
	public void setTwitterCount(Integer twitterCount) {
		this.twitterCount = twitterCount;
	}
	public Integer getYoutubeCount() {
		return youtubeCount;
	}
	public void setYoutubeCount(Integer youtubeCount) {
		this.youtubeCount = youtubeCount;
	}
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	public Integer getType3() {
		return type3;
	}
	public void setType3(Integer type3) {
		this.type3 = type3;
	}
	public Integer getType4() {
		return type4;
	}
	public void setType4(Integer type4) {
		this.type4 = type4;
	}
	
	public Integer getType5() {
		return type5;
	}
	public void setType5(Integer type5) {
		this.type5 = type5;
	}
	public Integer getType6() {
		return type6;
	}
	public void setType6(Integer type6) {
		this.type6 = type6;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	
	public double getMedia_total() {
		return media_total;
	}
	public void setMedia_total(double media_total) {
		this.media_total = media_total;
	}
	@Override
	public String toString() {
		return "GraphVO [startDate=" + startDate + ", endDate=" + endDate + ", sns_name=" + sns_name + ", writeDate="
				+ writeDate + ", writeDate2=" + writeDate2 + ", likeCount=" + likeCount + ", shareCount=" + shareCount
				+ ", replyCount=" + replyCount + ", facebookCount=" + facebookCount + ", instagramCount="
				+ instagramCount + ", twitterCount=" + twitterCount + ", youtubeCount=" + youtubeCount + ", type1="
				+ type1 + ", type2=" + type2 + ", type3=" + type3 + ", type4=" + type4 + ", type5=" + type5 + ", type6="
				+ type6 + ", company=" + company + ", selectKey=" + selectKey + ", media=" + media + ", media_total="
				+ media_total + "]";
	}
}
