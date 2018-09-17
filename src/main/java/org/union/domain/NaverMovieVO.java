package org.union.domain;

import java.util.Date;

public class NaverMovieVO {
    
	private Integer NM_idx;
	private String NM_title;
	private String writer;
	private String writeDate;
	private String title_key;
	private String keyword;
	private String keyword_type;
	private String url;
	private Date createDate;
	private Date updateDate;
	
	public Integer getNM_idx() {
	    return NM_idx;
	}

	public void setNM_idx(Integer nM_idx) {
	    NM_idx = nM_idx;
	}

	public String getNM_title() {
	    return NM_title;
	}

	public void setNM_title(String nM_title) {
	    NM_title = nM_title;
	}

	public String getWriter() {
	    return writer;
	}

	public void setWriter(String writer) {
	    this.writer = writer;
	}

	public String getWriteDate() {
	    return writeDate;
	}

	public void setWriteDate(String writeDate) {
	    this.writeDate = writeDate;
	}

	public String getTitle_key() {
	    return title_key;
	}

	public void setTitle_key(String title_key) {
	    this.title_key = title_key;
	}

	public String getKeyword() {
	    return keyword;
	}

	public void setKeyword(String keyword) {
	    this.keyword = keyword;
	}

	public String getKeyword_type() {
	    return keyword_type;
	}

	public void setKeyword_type(String keyword_type) {
	    this.keyword_type = keyword_type;
	}

	public String getUrl() {
	    return url;
	}

	public void setUrl(String url) {
	    this.url = url;
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
		return "NaverMovieVO [NM_idx=" + NM_idx + ", NM_title=" + NM_title + ", writeDate=" + writeDate 
				+ ", keyword=" + keyword + ", keyword_type=" + keyword_type + ", url=" + url 
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
}
