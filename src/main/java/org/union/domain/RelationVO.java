package org.union.domain;

import java.util.Date;

public class RelationVO {

	
	private Integer relation_idx;
	private String relation_keyword;
	private String relation_word;
	private Date createDate;
	private Date updateDate;
	
	
	public Integer getRelation_idx() {
		return relation_idx;
	}
	public void setRelation_idx(Integer relation_idx) {
		this.relation_idx = relation_idx;
	}
	public String getRelation_keyword() {
		return relation_keyword;
	}
	public void setRelation_keyword(String relation_keyword) {
		this.relation_keyword = relation_keyword;
	}
	public String getRelation_word() {
		return relation_word;
	}
	public void setRelation_word(String relation_word) {
		this.relation_word = relation_word;
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
		return "RelationVO [relation_idx=" + relation_idx + ", relation_keyword=" + relation_keyword
				+ ", relation_word=" + relation_word + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
	
	
}
