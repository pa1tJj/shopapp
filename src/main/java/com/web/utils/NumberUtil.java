package com.web.utils;

public class NumberUtil {
	public static Boolean check(String value) {
		if(value == null) return false;
		try {
			Long number = Long.parseLong(value);
		} catch (NumberFormatException e) {
		    return false;
		}
		return true;
	}
}
