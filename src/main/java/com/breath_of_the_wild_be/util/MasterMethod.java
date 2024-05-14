package com.breath_of_the_wild_be.util;

import jakarta.servlet.http.HttpServletRequest;

public class MasterMethod {
	public static String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		return authorization.substring(7);
	}
}
