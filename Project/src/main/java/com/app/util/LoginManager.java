package com.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginManager {


	public static final String SESSION_LOGIN_USER_KEY = "loginUserId";
	
	public static void setSessionLoginUserId(HttpSession session, String id) {
		session.setAttribute(SESSION_LOGIN_USER_KEY, id);
	}
	
	public static void setSessionLoginUserId(HttpServletRequest request, String id) {
		setSessionLoginUserId(request.getSession(), id);
	}
	
	
	public static String getLoginUserId(HttpSession session) {
		return (String)session.getAttribute(SESSION_LOGIN_USER_KEY);
	}
	
	public static String getLoginUserId(HttpServletRequest request) {
		return getLoginUserId(request.getSession());
	}
	
	
	public static boolean isLogin(HttpSession session) {
		// getLoginUserId(session) != null
		if(session.getAttribute(SESSION_LOGIN_USER_KEY) != null) {
			return true; //로그인 상태면 true 리턴
		}
		
		return false; //아니면 false 리턴
	}
	
	public static boolean isLogin(HttpServletRequest request) {
		return isLogin(request.getSession());
	}
	
	
	public static void logout(HttpSession session) {
		session.invalidate();
	}
	
	public static void logout(HttpServletRequest request) {
		logout(request.getSession());
	}
	
}
