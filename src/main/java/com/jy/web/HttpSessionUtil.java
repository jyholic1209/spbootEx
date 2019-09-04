package com.jy.web;

import javax.servlet.http.HttpSession;

import com.jy.user.User;

public class HttpSessionUtil {
	public static final String USER_SESSION_KEY = "sessionUser";
	
	public static boolean isLoginUser(HttpSession session) {
		if(session.getAttribute(USER_SESSION_KEY) == null) {
			return false;
		}
		return true;
	}
	
	public static User getUserFormSession(HttpSession session) {
		if(!isLoginUser(session)) {
			return null;
		}
		return (User) session.getAttribute(USER_SESSION_KEY);
	}


}
