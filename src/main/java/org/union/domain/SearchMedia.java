package org.union.domain;

import java.util.Date;

public class SearchMedia {
	
	private String selectKey, company;
	private Date date;
	
	
	public String getSelectKey() {
		return selectKey;
	}

	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SearchMedia [selectKey=" + selectKey + ", company=" + company + ", date=" + date + "]";
	}
}
