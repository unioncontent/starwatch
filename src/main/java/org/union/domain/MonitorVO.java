package org.union.domain;

public class MonitorVO {

	private Integer monitor_idx;
	private String monitor_domain, monitor_site, monitor_id;
	private boolean checkCondition;
	private String title, url, createDate;
	
	public Integer getMonitor_idx() {
		return monitor_idx;
	}
	public void setMonitor_idx(Integer monitor_idx) {
		this.monitor_idx = monitor_idx;
	}
	public String getMonitor_domain() {
		return monitor_domain;
	}
	public void setMonitor_domain(String monitor_domain) {
		this.monitor_domain = monitor_domain;
	}
	public String getMonitor_site() {
		return monitor_site;
	}
	public void setMonitor_site(String monitor_site) {
		this.monitor_site = monitor_site;
	}
	public String getMonitor_id() {
		return monitor_id;
	}
	public void setMonitor_id(String monitor_id) {
		this.monitor_id = monitor_id;
	}
	public boolean isCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(boolean checkCondition) {
		this.checkCondition = checkCondition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "MonitorVO [monitor_idx=" + monitor_idx + ", monitor_domain=" + monitor_domain + ", monitor_site="
				+ monitor_site + ", monitor_id=" + monitor_id + ", checkCondition=" + checkCondition + ", title="
				+ title + ", url=" + url + ", createDate=" + createDate + "]";
	}
	
}
