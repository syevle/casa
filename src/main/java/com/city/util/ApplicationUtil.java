package com.city.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationUtil {

	public static String getTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getCapitalizeFirstLetter(String text) {
		StringBuffer sb = new StringBuffer();
		try {
			String test = text;
			String[] testSplit = test.split(" ");

			for (int i = 0; i < testSplit.length; i++) {
				String capitalVer = capitalizeFirstLetter(testSplit[i]);
				if (i != 0) {
					sb.append(" ");
				}
				sb.append(capitalVer);
			}
			return sb.toString();
		} catch (Exception es) {

		}
		return sb.toString();
	}

	public static String capitalizeFirstLetter(final String string) {
		if (string == null || string.equals(""))
			throw new NullPointerException("no string ");

		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
}
