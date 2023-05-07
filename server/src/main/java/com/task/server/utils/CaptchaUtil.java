package com.task.server.utils;

import jakarta.servlet.http.HttpSession;
public class CaptchaUtil {

    // the captcha code is stored in the req session and used to compare
	private static final String prefix = "CAPTCHA_";
	
	public static boolean validate(HttpSession session, String pageId, String value){
		String captcha = (String) session.getAttribute(prefix+pageId);;
		return captcha != null && captcha.equalsIgnoreCase(value);
	}
}