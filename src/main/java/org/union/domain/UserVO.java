package org.union.domain;

import java.util.Date;

public class UserVO {

	
	private Integer user_idx;
	private Integer user_type; // 1 = 관리자, 2 = CP
	private String user_ID;
	private String user_PW;
	private String user_name;
	private String user_phoneNum;
	private String user_email;
	private String company_name;
	private String company_type;
	private String company_licensee;
	private String company_location;
	private String thumbnail;
	private Date createDate;
	private Date updateDAte;
	
	
	public Integer getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(Integer user_idx) {
		this.user_idx = user_idx;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getUser_PW() {
		return user_PW;
	}
	public void setUser_PW(String user_PW) {
		this.user_PW = user_PW;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phoneNum() {
		return user_phoneNum;
	}
	public void setUser_phoneNum(String user_phoneNum) {
		this.user_phoneNum = user_phoneNum;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_type() {
		return company_type;
	}
	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}
	public String getCompany_licensee() {
		return company_licensee;
	}
	public void setCompany_licensee(String company_licensee) {
		this.company_licensee = company_licensee;
	}
	public String getCompany_location() {
		return company_location;
	}
	public void setCompany_location(String company_location) {
		this.company_location = company_location;
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
	public Date getUpdateDAte() {
		return updateDAte;
	}
	public void setUpdateDAte(Date updateDAte) {
		this.updateDAte = updateDAte;
	}
	
	
	@Override
	public String toString() {
		return "UserVO [user_idx=" + user_idx + ", user_type=" + user_type + ", user_ID=" + user_ID + ", user_PW="
				+ user_PW + ", user_name=" + user_name + ", user_phoneNum=" + user_phoneNum + ", user_email="
				+ user_email + ", company_name=" + company_name + ", company_type=" + company_type
				+ ", company_licensee=" + company_licensee + ", company_location=" + company_location + ", thumbnail="
				+ thumbnail + ", createDate=" + createDate + ", updateDAte=" + updateDAte + "]";
	}
	
}
