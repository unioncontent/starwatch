package org.union.domain;

public class TextTypeDateVO {
	
	private String writeDate;
	private Integer lik, dis, cu, etc, al;
	
	public TextTypeDateVO() {
		super();
	}

	public TextTypeDateVO(String writeDate, Integer lik, Integer dis, Integer cu, Integer etc, Integer al) {
		super();
		this.writeDate = writeDate;
		this.lik = lik;
		this.dis = dis;
		this.cu = cu;
		this.etc = etc;
		this.al = al;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public Integer getLik() {
		return lik;
	}

	public void setLik(Integer lik) {
		this.lik = lik;
	}

	public Integer getDis() {
		return dis;
	}

	public void setDis(Integer dis) {
		this.dis = dis;
	}

	public Integer getCu() {
		return cu;
	}

	public void setCu(Integer cu) {
		this.cu = cu;
	}

	public Integer getEtc() {
		return etc;
	}

	public void setEtc(Integer etc) {
		this.etc = etc;
	}

	public Integer getAl() {
		return al;
	}

	public void setAl(Integer al) {
		this.al = al;
	}
}
