package com.app.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class MyCookieUtil {


	public static String getCookie(Cookie[] cookies, String cookieName) {

		// cookies 배열에서 원하는 cookieName을 가진 value 찾아서 반환하기
		String value = null;

		for (Cookie ck : cookies) {
			if (ck.getName().equals(cookieName)) {
				//value = ck.getValue(); //value 그냥 읽기

				//value decode 해서 읽기
				try {
					value = URLDecoder.decode(ck.getValue() , "UTF-8");
					break;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}

	//리팩토링 After
	public static String getCookie(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();
		
		return getCookie(cookies, cookieName);
	}
	
	/*
	//리팩토링 Before
	public static String getCookie(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();

		// cookies 배열에서 원하는 cookieName을 가진 value 찾아서 반환하기
		String value = null;

		for (Cookie ck : cookies) {
			if (ck.getName().equals(cookieName)) {
				//value = ck.getValue(); //value 그냥 읽기

				//value decode 해서 읽기
				try {
					value = URLDecoder.decode(ck.getValue() , "UTF-8");
					break;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}
	*/

	public static Cookie createCookie(String name, String value) {
		Cookie ck = new Cookie(name, encodeValue(value));
		//maxAge 세팅 생략 or maxAge(-1) --> 세션 쿠키 -> 해당 브라우저의 세션 단위까지 사용
		return ck;
	}
	
	public static Cookie createCookie(String name, String value, int maxAge) {
		Cookie ck = new Cookie(name, encodeValue(value));
		ck.setMaxAge(maxAge);
		return ck;
	}
	
	public static Cookie createCookieForRemove(String name) {
		Cookie ck = new Cookie(name, "");
		ck.setMaxAge(0);
		return ck;
	}
	
	public static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}













