package com.capgemini.mrchecker.selenium.mts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;
import com.capgemini.mrchecker.common.mts.data.User;
import com.capgemini.mrchecker.selenium.mts.pages.MyThaiStarBasePage;
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
@Epic("Selenium Tests")
@Feature("My Thai Star")
@Story("Login Logout")

public class MyThaiStarLoginLogoutFakeTest extends BaseTest {
	
	private final ThaiHomePage	myThaiStarHome	= PageFactory.getPageInstance(ThaiHomePage.class);
	private final ThaiLoginPage	loginPage		= PageFactory.getPageInstance(ThaiLoginPage.class);
	private final User			fakeUser		= new User("fakeUser", "fakePassword");
	
	@Override
	public void setUp() {
		myThaiStarHome.load();
	}
	
	@Description("Goal of this test case is to check if user **cannot login** with **invalid** credentials")
	@TmsLink("LinkToJira-ID")
	@Test
	public void Test_loginFake() {
		
		login();
		checkIfLoged();
		
	}
	
	@Step("Try to login with invalid credentials")
	private void login() {
		myThaiStarHome.clickLogInButton();
		loginPage.logininvalidUser(fakeUser.getUsername(), fakeUser.getPassword());
	};
	
	@Step("Check if login fails")
	private void checkIfLoged() {
		assertThat("User " + fakeUser.getUsername() + " logged", myThaiStarHome.isUserLogged(fakeUser.getUsername()),
				is(equalTo(false)));
		StepLogger.stepInfo("User not logged");
		
		MyThaiStarBasePage.makeScreenShot();
	}
	
}
