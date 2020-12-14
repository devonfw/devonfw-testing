package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.MTS;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.HomePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.LoginPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.MenuPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ReservationsPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.WaiterPage;
import com.capgemini.mrchecker.test.core.BaseTest;

@MTS
public class MakeOrderTest extends BaseTest {
	
	private static BookTablePage	bookTablePage	= new BookTablePage();
	private static MenuPage			menuPage		= new MenuPage();
	
	private static HomePage		homePage;
	private static WaiterPage	waiterPage;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		bookTablePage.load();
	}
	
	@AfterAll
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
		
	}
	
	@Test
	public void Test_MakeOrder() {
		
		menuPage.makeAnOrder(prepareToOrder());
		
		assertTrue("Test failed: Order not made", menuPage.checkConfirmationDialog());
		StepLogger.makeScreenShot();
		StepLogger.stepInfo("Test passed: Order made");
	}
	
	@Test
	public void Test_MakeBiggerOrder() {
		
		menuPage.makeBiggerOrder(prepareToOrder());
		
		assertTrue("Test failed: Order not made", menuPage.checkConfirmationDialog());
		StepLogger.makeScreenShot();
		StepLogger.stepInfo("Test passed: Order made");
	}
	
	private void login(String username, String password) {
		LoginPage loginPage = homePage.clickLogInButton();
		loginPage.enterCredentialsAndLogin(username, password);
		assertTrue("User " + username + " not logged",
				homePage.isUserLogged(username));
		StepLogger.makeScreenShot();
		StepLogger.stepInfo("User " + username + " logged");
	}
	
	private String prepareToOrder() {
		
		String date, name, email, id;
		String username = "waiter", password = "waiter";
		int guestsNumber;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		String guests = "" + guestsNumber;
		
		ConfirmBookPage confirmBookPage = bookTablePage.enterBookingDataAndBookTable(date, name, email, guests);
		confirmBookPage.clickConfirmBookingButton();
		
		homePage = new HomePage();
		homePage.load();
		login(username, password);
		
		waiterPage = new WaiterPage();
		ReservationsPage reservationsPage = waiterPage.clickReservationsTab();
		
		id = reservationsPage.getReservationId(email, date);
		
		menuPage.load();
		
		assertTrue("Empty booking id", id != null);
		StepLogger.makeScreenShot();
		StepLogger.stepInfo("Booking id: " + id);
		
		return id;
	}
	
}