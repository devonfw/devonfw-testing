package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.selenium.pages.data.User;
import com.capgemini.mrchecker.selenium.pages.mapper.UserMapper;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.HomePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.LoginPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ReservationsPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.WaiterPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserStoriesTest extends BaseTest {
	private static HomePage homePage = new HomePage();
	
	@BeforeClass
	public static void setUpBeforeClass() {
		homePage.load();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		if (!homePage.isLoaded())
			homePage.load();
	}
	
	@Override
	public void tearDown() {
		if (homePage.isUserLogged())
			logOut();
	}
	
	@Test
	public void Test_loginAndLogout() {
		String username = "waiter";
		String password = "waiter";
		
		login(username, password);
		assertTrue("user not logged", homePage.isUserLogged(username));
		logOut();
		assertFalse("user still logged", homePage.isUserLogged(username));
	}
	
	@Test
	@FileParameters(value = "src/test/resources/datadriven/test_fakeUsers.csv")
	public void Test_loginFake(String username, String password) {
		LoginPage loginPage = homePage.clickLogInButton();
		loginPage.enterCredentialsAndLogin(username, password);
		assertFalse("User " + username + " logged",
				homePage.isUserLogged(username));
	}
	
	@Test
	@FileParameters(value = "src/test/resources/datadriven/test_waiters.csv", mapper = UserMapper.class)
	public void Test_bookTable(User waiter) {
		String date, name, email;
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		BookTablePage bookTablePage = new BookTablePage();
		bookTablePage.load();
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, name, email, guests);
		
		assertTrue("Confirmation window not loaded", confirmBookPage.isLoaded());
		confirmBookPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Table not booked", bookTablePage.isConfirmationDialogDisplayed());
		
		homePage.load();
		assertTrue("home page not loaded", homePage.isLoaded());
		
		String username = waiter.getUsername();
		String password = waiter.getPassword();
		
		login(username, password);
		verifyBooking(email, date);
		logOut();
	}
	
	private void login(String username, String password) {
		LoginPage loginPage = homePage.clickLogInButton();
		loginPage.enterCredentialsAndLogin(username, password);
		assertTrue("User " + username + " not logged",
				homePage.isUserLogged(username));
	}
	
	private void logOut() {
		if (homePage.isUserLogged()) {
			homePage.clickLogOutButton();
		}
		assertFalse("Some user logged", homePage.isUserLogged());
	}
	
	private void verifyBooking(String email, String date) {
		WaiterPage waiterPage = new WaiterPage();
		ReservationsPage reservationsPage = waiterPage.clickReservationsTab();
		List<String> reservations = reservationsPage.getReservationsByEmailAndDate(email, date);
		assertFalse("Booking not found", reservations.isEmpty());
	}
	
}
