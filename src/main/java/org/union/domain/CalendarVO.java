package org.union.domain;

import java.util.Date;

public class CalendarVO {

	
	Integer calendar_idx;
	String calendar_title, calendar_date;
	Date  createDate;
	public Integer getCalendar_idx() {
		return calendar_idx;
	}
	public void setCalendar_idx(Integer calendar_idx) {
		this.calendar_idx = calendar_idx;
	}
	public String getCalendar_title() {
		return calendar_title;
	}
	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}
	public String getCalendar_date() {
		return calendar_date;
	}
	public void setCalendar_date(String calendar_date) {
		this.calendar_date = calendar_date;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "CalendarVO [calendar_idx=" + calendar_idx + ", calendar_title=" + calendar_title + ", calendar_date="
				+ calendar_date + ", createDate=" + createDate + "]";
	}
	
	
}
