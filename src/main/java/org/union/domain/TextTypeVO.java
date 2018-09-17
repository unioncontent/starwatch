package org.union.domain;

public class TextTypeVO {

	private Integer lik, dis, cu, etc, al;

	String name1, name2, email, phoneNumber;
	
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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "TextTypeVO [lik=" + lik + ", dis=" + dis + ", cu=" + cu + ", etc=" + etc + ", al=" + al + ", name1="
				+ name1 + ", name2=" + name2 + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
