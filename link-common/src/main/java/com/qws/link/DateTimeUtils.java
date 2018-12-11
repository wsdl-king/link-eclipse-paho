package com.qws.link;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	private DateTimeUtils() {
	}

	/**
	 * Format the date with "yyyy-MM-dd HH:mm:ss".
	 */
	public static String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}
	/**yyyy-MM-dd HH:mm:ss.SSS*/
	public static String formatCurrentTime2(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dateFormat.format(Calendar.getInstance().getTime());
	}


	public static Date parseDate(String date, String format)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}

}
