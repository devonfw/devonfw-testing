package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class BookTableTest extends BaseTest {
	
	private static BookTablePage bookTablePage = new BookTablePage();
	
	private static final String	dateFormat			= "MM/dd/yyyy";
	private static final String	dateAndTimeFormat	= dateFormat.concat(" hh:mm a");
	private final String		clientName			= "client";
	private final int			maxGuestAmount		= 8;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		bookTablePage.load();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		if (!bookTablePage.isLoaded()) {
			bookTablePage.load();
		}
	}
	
	@Override
	public void tearDown() {
		bookTablePage.load();
	}
	
	/* Test Case 1 */
	@Test
	public void Test_BookTableWithProperData() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, Utils.getRandom1toMax(30));
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, clientName, email, Integer.toString(guestsNumber));
		confirmBookPage.clickConfirmBookingButton();
		
		assertTrue("Table not booked after entering proper data", bookTablePage.isConfirmationDialogDisplayed());
	}
	
	/* Test Case 2 */
	@Test
	public void Test_BookTableWithPastDate() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, -1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong date", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 3 */
	/* Skipped because of product defect */
	@Ignore
	@Test
	public void Test_BookTableWithNegativeHour() {
		String date, email, invalidTime;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		invalidTime = " -01:00 AM";
		
		date = date.concat(invalidTime);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong hour", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 4 */
	@Test
	public void Test_BookTableWithInvalidHour() {
		String date, email, invalidTime;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		invalidTime = " 25:00 PM";
		
		date = date.concat(invalidTime);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong hour", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 5 */
	/* Skipped because of product defect */
	@Ignore
	@Test
	public void Test_BookTableWithNegativeMinutes() {
		String date, email, invalidTime;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		invalidTime = " 01:-01 PM";
		
		date = date.concat(invalidTime);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong hour", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 6 */
	@Test
	public void Test_BookTableWithInvalidMinutes() {
		String date, email, invalidTime;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		invalidTime = " 01:60 PM";
		
		date = date.concat(invalidTime);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong hour", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 7 */
	/* Skipped because of product defect */
	@Ignore
	@Test
	public void Test_BookTableWithEmptyName() {
		String date, emptyName, email;
		int guestsNumber;
		
		emptyName = " ";
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		
		bookTablePage.enterBookingData(date, emptyName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering empty name", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 8 */
	@Test
	public void Test_BookTableWithInvalidEmail() {
		String date, email;
		int guestsNumber;
		
		email = "client@email";
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering wrong email", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 9 */
	@Test
	public void Test_BookTableWithZeroGuests() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = 0;
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering zero guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	@Test
	public void Test_BookTableWithNegativeGuestNumber() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount) * (-1);
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering negative guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 10 */
	@Test
	public void Test_BookTableWithTooManyGuests() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = Utils.getRandom1toMax(90) + maxGuestAmount;
		
		bookTablePage.enterBookingData(date, clientName, email, Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable after entering too big guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 11 */
	/* Skipped because of product defect */
	@Ignore
	@Test
	public void Test_BookTableWithNonIntegerGuestNumber() {
		String date, email, guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = "3.14";
		
		bookTablePage.enterBookingData(date, clientName, email, guestsNumber);
		
		assertFalse("Book table button clickable after entering non integer guests amount", bookTablePage.isBookTableButtonClickable());
	}
	
	/* Test Case 12 */
	@Test
	public void Test_BookTableWithTermsUnaccepted() {
		String date, email;
		int guestsNumber;
		
		email = Utils.getRandomEmail(clientName);
		date = Utils.getDate(dateAndTimeFormat, 1);
		guestsNumber = Utils.getRandom1toMax(maxGuestAmount);
		
		bookTablePage.enterTimeAndDateInputBooking(date);
		bookTablePage.enterNameInputBooking(clientName);
		bookTablePage.enterEmailInputBooking(email);
		bookTablePage.enterGuestsNumberInput(Integer.toString(guestsNumber));
		
		assertFalse("Book table button clickable when accept terms checkbox unchecked", bookTablePage.isBookTableButtonClickable());
	}
	
}
