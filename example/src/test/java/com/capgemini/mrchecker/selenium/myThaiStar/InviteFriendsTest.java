package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.MTS;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.ConfirmInvitationPage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.InviteFriendsPage;
import com.capgemini.mrchecker.test.core.BaseTest;

@MTS
public class InviteFriendsTest extends BaseTest {
	
	private static BookTablePage	bookTablePage	= new BookTablePage();
	InviteFriendsPage				inviteFriendsPage;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		// bookTablePage.load();
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		if (!bookTablePage.isLoaded()) {
			bookTablePage.load();
		}
		
		bookTablePage.waitForCheckboxToBeVisible();
		inviteFriendsPage = bookTablePage.clickInviteFriendsTab();
		assertTrue(inviteFriendsPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		bookTablePage.load();
	}
	
	@Test
	public void Test_InviteFriendsAndCheckConfirmation() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = Utils.getRandom1toMax(8);
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			inviteFriendsPage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		inviteFriendsPage.clickInviteFriendsButton();
		
		ConfirmInvitationPage confirmInvitationPage = new ConfirmInvitationPage();
		confirmInvitationPage.clickConfirmBookingButton();
		
		assertTrue("Test failed: Friends not invited", inviteFriendsPage.isConfirmationDialogDisplayed());
		StepLogger.makeScreenShot();
		StepLogger.stepInfo("Test passed: Friends invited");
	}
}
