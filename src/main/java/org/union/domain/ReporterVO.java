package org.union.domain;

import java.util.Date;

public class ReporterVO {

	
	private Integer reporter_idx;
	private String reporter_media_name;
	private String reporter_name;
	private String reporter_ID;
	private String reporter_email;
	private String reporter_phoneNum;
	private String reporter_part_name;
	private String reporter_memo;
	private Date createDate;
	private Date updateDate;
	
	
	public Integer getReporter_idx() {
		return reporter_idx;
	}
	public void setReporter_idx(Integer reporter_idx) {
		this.reporter_idx = reporter_idx;
	}
	public String getReporter_media_name() {
		return reporter_media_name;
	}
	public void setReporter_media_name(String reporter_media_name) {
		this.reporter_media_name = reporter_media_name;
	}
	public String getReporter_name() {
		return reporter_name;
	}
	public void setReporter_name(String reporter_name) {
		this.reporter_name = reporter_name;
	}
	public String getReporter_ID() {
		return reporter_ID;
	}
	public void setReporter_ID(String reporter_ID) {
		this.reporter_ID = reporter_ID;
	}
	public String getReporter_email() {
		return reporter_email;
	}
	public void setReporter_email(String reporter_email) {
		this.reporter_email = reporter_email;
	}
	public String getReporter_phoneNum() {
		return reporter_phoneNum;
	}
	public void setReporter_phoneNum(String reporter_phoneNum) {
		this.reporter_phoneNum = reporter_phoneNum;
	}
	public String getReporter_part_name() {
		return reporter_part_name;
	}
	public void setReporter_part_name(String reporter_part_name) {
		this.reporter_part_name = reporter_part_name;
	}
	public String getReporter_memo() {
		return reporter_memo;
	}
	public void setReporter_memo(String reporter_memo) {
		this.reporter_memo = reporter_memo;
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
	
	
	@Override
	public String toString() {
		return "ReporterVO [reporter_idx=" + reporter_idx + ", reporter_media_name=" + reporter_media_name
				+ ", reporter_name=" + reporter_name + ", reporter_ID=" + reporter_ID + ", reporter_email="
				+ reporter_email + ", reporter_phoneNum=" + reporter_phoneNum + ", reporter_part_name="
				+ reporter_part_name + ", reporter_memo=" + reporter_memo + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
}
