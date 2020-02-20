package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.myThaiStar.BookTablePage;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.InviteFriendsPage;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.BaseTest;

public class InviteFriendsNegativeTest extends BaseTest {
	
	private static BookTablePage	bookTablePage	= new BookTablePage();
	InviteFriendsPage				inviteFriendsPage;
	
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
		
		bookTablePage.waitForCheckboxToBeVisible();
		inviteFriendsPage = bookTablePage.clickInviteFriendsTab();
		assertTrue(inviteFriendsPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		bookTablePage.load();
	}
	
	@Test
	public void Test_InviteFriendsWithWrongDate() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", -1);
		guestsNumber = 2;
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			inviteFriendsPage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		
		assertFalse("Test failed: Friends invited", inviteFriendsPage.isInviteFriendsButtonClickable());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongName() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "";
		guestName = "guest";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = 2;
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			inviteFriendsPage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		
		assertFalse("Test failed: Friends invited", inviteFriendsPage.isInviteFriendsButtonClickable());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongEmail() {
		String date, name, guestName, email;
		int guestsNumber;
		
		name = "client";
		guestName = "guest";
		email = "client@email";
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		guestsNumber = 2;
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		for (int i = 0; i < guestsNumber; i++) {
			inviteFriendsPage.enterInvitationEmailInput(Utils.getRandomEmail(guestName));
		}
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		
		assertFalse("Test failed: Friends invited", inviteFriendsPage.isInviteFriendsButtonClickable());
	}
	
	@Test
	public void Test_InviteFriendsWithZeroGuests() {
		String date, name, email;
		
		name = "client";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		
		assertFalse("Test failed: Friends invited", inviteFriendsPage.isInviteFriendsButtonClickable());
	}
	
	@Test
	public void Test_InviteFriendsWithWrongGuestEmail() {
		String date, name, guestEmail, email;
		
		name = "client";
		guestEmail = "guest@email";
		email = Utils.getRandomEmail(name);
		date = Utils.getDate("MM/dd/yyyy hh:mm a", 1);
		
		inviteFriendsPage.enterTimeAndDateInputInvitation(date);
		inviteFriendsPage.enterNameInputInvitation(name);
		inviteFriendsPage.enterEmailInputInvitation(email);
		inviteFriendsPage.enterInvitationEmailInput(guestEmail);
		
		inviteFriendsPage.clickAcceptTermsCheckboxInvitation();
		
		assertFalse("Test failed: Friends invited", inviteFriendsPage.isInviteFriendsButtonClickable());
	}
}
