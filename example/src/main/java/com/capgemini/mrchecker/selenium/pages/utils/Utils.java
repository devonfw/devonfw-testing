package com.capgemini.mrchecker.selenium.pages.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {
	
	private static Random random = new Random();
	
	public static String getRandomEmail(String name) {
		int rint = random.nextInt(1000);
		
		return name + "_" + rint + "_@test.com";
	}
	
	public static String getDate(String format, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		
		return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());
	}
	
	public static int getRandom1toMax(int max) {
		
		return random.nextInt(max) + 1;
	}
	
	public static String changeDateFormat(String oldDate, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat, Locale.ENGLISH);
		Date date = dateFormat.parse(oldDate);
		dateFormat.applyPattern(newFormat);
		
		return dateFormat.format(date);
	}
}
