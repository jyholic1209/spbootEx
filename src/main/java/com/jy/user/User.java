package com.jy.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=20)
	private String uId;
	private String uPw;
	private String uName;
	private String uEmail;
	
	public String getuId() {
		return uId;
	}
	public String getuPw() {
		return uPw;
	}
	public String getuName() {
		return uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uPw=" + uPw + ", uName=" + uName + ", uEmail=" + uEmail + "]";
	}
	
	

}
