package com.apceps.util;


public class StringUtil {

    public static final String LINE_SEPARTOR = System.getProperty("line.separator");
	
	public static boolean isEmpty(String input) {

		if (null == input || "".equals(input.trim()))
			return true;

		return false;
	}

	
}
