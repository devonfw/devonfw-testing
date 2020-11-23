package com.capgemini.mrchecker.selenium.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiMenuPage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiSummaryPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Tag("MTS_SELENIUM")
@Epic("Selenium Tests")
@Feature("My Thai Star")
@Story("Order from Menu")
@Disabled("Test needs adaptation")
public class MyThaiStarOrderMenu extends BaseTest {
	
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	private final ThaiMenuPage	menuPage		= PageFactory.getPageInstance(ThaiMenuPage.class);
	
	@Override
	public void setUp() {
		myThaiStarHome.load();
		
	}
	
	@Test
	@Description("Waiter is ordering from menu")
	public void test_orderMenu() {
		order();
	}
	
	@Step("Order someting from menu")
	private void order() {
		String bookingId = "CB_20170510_123502655Z";
		myThaiStarHome.clickMenuButton();
		ThaiSummaryPage summaryPage = menuPage.clickFirstMenu();
		summaryPage.orderMenu(bookingId);
		fail("Test not implemented");
	}
	
	@Step("Login user: {user}")
	private void login(User user) {
		myThaiStarHome.clickLogInButton();
		loginPage.loginUser(user.getUsername(), user.getPassword());
		assertThat("User " + user.getUsername() + " not logged", myThaiStarHome.isUserLogged(user.getUsername()),
				is(equalTo(true)));
		StepLogger.stepInfo("User " + user.getUsername() + " is logged");
		
	}
	
}
