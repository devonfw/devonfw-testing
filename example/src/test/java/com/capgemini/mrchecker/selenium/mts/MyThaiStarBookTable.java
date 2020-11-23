package com.capgemini.mrchecker.selenium.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.data.Reservation;
import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.selenium.mts.datadrivem.UserAggregator;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiConfirmBookPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiReservationsPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiWaiterPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Tag("MTS_SELENIUM")
@Epic("Selenium Tests")
@Feature("My Thai Star")
@Story("Book table")
@Disabled("Test needs adaptation")
public class MyThaiStarBookTable extends BaseTest {
	
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	
	@Override
	public void setUp() {
		myThaiStarHome.load();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/mts/test_users.csv", numLinesToSkip = 1)
	public void test_bookTable(@AggregateWith(UserAggregator.class) User user) {
		BFLogger.logInfo("USER: " + user);
		String fakeEmail = Utils.getRandomEmail(user.getUsername());
		String date = Utils.getTomorrowDate();
		int guest = Utils.getRandom1toMax(8);
		Reservation reservation = new Reservation(date, user.getUsername(), fakeEmail, guest);
		User waiter = new User("waiter", "waiter");
		
		bookTable(reservation);
		login(waiter);
		verifyBooking(reservation);
		logOut();
	}
	
	@Step("Login user: {user}")
	private void login(User user) {
		myThaiStarHome.clickLogInButton();
		loginPage.loginUser(user.getUsername(), user.getPassword());
		assertThat("User " + user.getUsername() + " not logged", myThaiStarHome.isUserLogged(user.getUsername()),
				is(equalTo(true)));
		StepLogger.stepInfo("User " + user.getUsername() + " is logged");
		
	}
	
	@Step("Book table: {reservation}")
	private void bookTable(Reservation reservation) {
		ThaiBookPage bookPage = myThaiStarHome.clickBookTable();
		ThaiConfirmBookPage confirmPage = bookPage.enterBookingData(reservation);
		confirmPage.confirmBookingData();
		bookPage.checkConfirmationDialog();
	}
	
	@Step("Verify booking: {reservation}")
	private void verifyBooking(Reservation reservation) {
		ThaiWaiterPage myWaiterPage = new ThaiWaiterPage();
		ThaiReservationsPage myReservationsPage = myWaiterPage.switchToReservations();
		HashMap<String, List<Reservation>> reservations = myReservationsPage.searchDatesByEmail(reservation.getEmail());
		Assert.assertTrue("Booking not found", reservations.containsKey(reservation.getDate()));
		List<Reservation> reservationsForDate = reservations.get(reservation.getDate());
		Assert.assertFalse("Booking not found", reservationsForDate.isEmpty());
	}
	
	@Step("Logout user ")
	private void logOut() {
		
		myThaiStarHome.clickLogOutButton();
		assertThat("Some user logged", this.myThaiStarHome.isUserLogged(), is(equalTo(false)));
		
	}
}
