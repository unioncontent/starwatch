package org.union.domain;

import java.util.Date;

public class SwearwordVO {
	
	private Integer swearword_idx;
	private String swearword;
	private Date createDate;
	private Date updateDate;
	
	public Integer getSwearword_idx() {
		return swearword_idx;
	}
	public void setSwearword_idx(Integer swearword_idx) {
		this.swearword_idx = swearword_idx;
	}
	public String getSwearword() {
		return swearword;
	}
	public void setSwearword(String swearword) {
		this.swearword = swearword;
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
		return "SwearwordVO [swearword_idx=" + swearword_idx + ", swearword=" + swearword + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
