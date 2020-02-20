package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmBookPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.HomePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.LoginPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.MenuPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ReservationsPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.WaiterPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class MakeOrderTest extends BaseTest {
	
	private static BookTablePage	bookTablePage	= new BookTablePage();
	private static MenuPage			menuPage		= new MenuPage();
	
	private static HomePage		homePage;
	private static WaiterPage	waiterPage;
	
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
		
	}
	
	@Test
	public void Test_MakeOrder() {
		
		menuPage.makeAnOrder(prepareToOrder());
		
		assertTrue("Test failed: Order not made", menuPage.checkConfirmationDialog());
	}
	
	@Test
	public void Test_MakeBiggerOrder() {
		
		menuPage.makeBiggerOrder(prepareToOrder());
		
		assertTrue("Test failed: Order not made", menuPage.checkConfirmationDialog());
	}
	
	private void login(String username, String password) {
		LoginPage loginPage = homePage.clickLogInButton();
		loginPage.enterCredentialsAndLogin(username, password);
		assertTrue("User " + username + " not logged",
				homePage.isUserLogged(username));
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
		
		return id;
	}
	
}