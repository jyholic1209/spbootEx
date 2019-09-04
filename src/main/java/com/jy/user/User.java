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
	
	@Column(nullable=false, length=20, unique=true)
	private String uId;
	private String uPw;
	private String uName;
	private String uEmail;
	
	public long getId() {
		return id;
	}
	
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
		return "User [id=" + id + ", uId=" + uId + ", uPw=" + uPw + ", uName=" + uName + ", uEmail=" + uEmail + "]";
	}

	public void update(User newUser) {
		this.uPw = newUser.uPw;
		this.uName = newUser.uName;
		this.uEmail = newUser.uEmail;
	}
	
	
	

}
