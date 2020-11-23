package com.capgemini.mrchecker.selenium.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.selenium.mts.datadrivem.UserAggregator;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiHomePage;
import com.capgemini.mrchecker.selenium.mts.pages.ThaiLoginPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Tag("MTS_SELENIUM")
@Tag("DATA_DRIVEN")
@Epic("Selenium Tests")
@Feature("My Thai Star")
@Story("Login Logout")

public class MyThaiStarLoginLogoutOKTest extends BaseTest {
	
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	
	@Override
	public void setUp() {
		myThaiStarHome.load();
	}
	
	@Description("Goal of this test case is to check if user **logins** with **valid**  credentials")
	@TmsLink("LinkToJira-ID")
	@DisplayName("Test_loginAndLogOut - abc")
	
	@ParameterizedTest
	@CsvFileSource(resources = "/datadriven/mts/test_users.csv", numLinesToSkip = 1)
	public void Test_loginAndLogOut(@AggregateWith(UserAggregator.class) User user) {
		login(user);
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
	
	@Step("Logout user ")
	private void logOut() {
		
		myThaiStarHome.clickLogOutButton();
		assertThat("Some user logged", this.myThaiStarHome.isUserLogged(), is(equalTo(false)));
		
	}
	
}
