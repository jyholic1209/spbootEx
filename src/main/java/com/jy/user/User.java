package com.jy.user;

public class User {
	
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
