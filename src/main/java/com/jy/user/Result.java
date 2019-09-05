package com.jy.user;

public class Result {
	
	private boolean vaild;
	private String errorMsg;
	
	public Result(boolean vaild, String errorMsg) {
		this.vaild = vaild;
		this.errorMsg = errorMsg;
		
	}
	
	public boolean isVaild() {
		return this.vaild;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public static Result ok() {
		return new Result(true, null);
	}
	
	public static Result fail(String errorMsg) {
		return new Result(false, errorMsg);
	}
}
