package org.union.domain;

public class PeriodMediaVO {

	
	private String media;
	private String reporter;
	private Integer allCount;
	private Integer searchCount;
	private double matchPercent;
	
	
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	public double getMatchPercent() {
		return matchPercent;
	}
	public void setMatchPercent(double matchPercent) {
		this.matchPercent = matchPercent;
	}
	@Override
	public String toString() {
		return "PeriodMediaVO [media=" + media + ", reporter=" + reporter + ", allCount=" + allCount + ", searchCount="
				+ searchCount + ", matchPercent=" + matchPercent + "]";
	}
	
}
