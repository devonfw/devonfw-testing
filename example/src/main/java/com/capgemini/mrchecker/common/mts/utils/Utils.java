package com.capgemini.mrchecker.common.mts.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class Utils {
	
	public static String getRandomEmail(String name) {
		return name + "_" + new Random().nextInt(1000) + "_@test.com";
	}
	
	public static String getTomorrowDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		
		return new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH).format(calendar.getTime());
	}
	
	public static String getDate(String format, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, offset);
		
		return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());
	}
	
	public static int getRandom1toMax(int max) {
		return new Random().nextInt(max) + 1;
	}
	
	public static String changeDateFormat(String oldDate, String oldFormat, String newFormat) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat, Locale.forLanguageTag("pl"));
		Date date = dateFormat.parse(oldDate);
		dateFormat.applyPattern(newFormat);
		
		return dateFormat.format(date);
	}
	
	public static void sendKeysWithCheck(String text,
			By textFieldSearchCriteria,
			INewWebDriver driver,
			WebDriverWait wait,
			int index) {
		boolean writtenCorrectly;
		WebElement textField;
		char character;
		int numOfChars = text.length();
		
		for (int i = 0; i < numOfChars; i++) {
			character = text.charAt(i);
			writtenCorrectly = false;
			int numOfAttempts = 0;
			
			while (!writtenCorrectly && numOfAttempts < numOfChars) {
				
				textField = driver.findElementDynamics(textFieldSearchCriteria)
						.get(index);
				textField.sendKeys(character + "");
				try {
					
					int l = i;
					wait.until((WebDriver wd) -> driver.findElementDynamic(textFieldSearchCriteria)
							.getAttribute("value")
							.length() == l + 1);
					writtenCorrectly = true;
				} catch (TimeoutException e) {
					numOfAttempts++;
					BFLogger.logInfo("Character not written: " + character);
					BFLogger.logInfo("Waiting for it to be written...");
				}
			}
		}
	}
	
	public static void sendKeysWithCheck(String text,
			By textFieldSearchCriteria,
			INewWebDriver driver,
			WebDriverWait wait) {
		sendKeysWithCheck(text, textFieldSearchCriteria, driver, wait, 0);
	}
}
