package com.capgemini.mrchecker.selenium.mts.pages;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.common.allure.utils.StepLogger;

import io.qameta.allure.Step;

public class ThaiLoginPage extends MyThaiStarBasePage {
	private static final List<String>	MESSAGE_LOGIN_SUCCESFUL		= Arrays.asList("Login successful", "Jestes zalogowany");
	private static final List<String>	MESSAGE_LOGIN_UNSUCCESFUL	= Arrays.asList("Http failure response");
	
	private static final By	selectorUsernameInput	= By.name("username");
	private static final By	selectorPasswordInput	= By.name("password");
	private static final By	selectorLoginButton		= By.name("submitLogin");
	private static final By	selectorPopupMessage	= By.cssSelector("simple-snack-bar");
	
	@Override
	protected By getDisplayableElementSelector() {
		return selectorPasswordInput;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	@Step("Login {username}")
	public void loginUser(String username, String password) {
		
		fillUserName(username);
		
		fillPassword(password);
		
		makeScreenShot();
		
		clickLoginButton();
		
		validatePopupMessage(MESSAGE_LOGIN_SUCCESFUL);
		
	}
	
	@Step("Try to login {username}")
	public void logininvalidUser(String username, String password) {
		
		fillUserName(username);
		
		fillPassword(password);
		
		makeScreenShot();
		
		clickLoginButton();
		
		validatePopupMessage(MESSAGE_LOGIN_UNSUCCESFUL);
		
	}
	
	@Step("Validate popup message {expectedMessages} ")
	private void validatePopupMessage(List<String> expectedMessages) {
		getDriver().waitForElementVisible(selectorPopupMessage);
		makeScreenShot();
		String popupText = getMessageText();
		
		for (String oneMessage : expectedMessages) {
			if (popupText.contains(oneMessage)) {
				
				StepLogger.step("Popup message is shown: " + popupText);
				return;
			}
		}
		
		fail("Popup message not shown. Expected: " + expectedMessages);
		
		getDriver().findElementDynamic(selectorPopupMessage)
				.click();
		
	}
	
	public void fillUserName(String username) {
		getDriver().findElementDynamic(selectorUsernameInput)
				.sendKeys(username);
	}
	
	public void fillPassword(String password) {
		getDriver().findElementDynamic(selectorPasswordInput)
				.sendKeys(password);
	}
	
	public void clickLoginButton() {
		getDriver().findElementDynamic(selectorLoginButton)
				.click();
	}
	
	public String getMessageText() {
		
		getDriver().waitForElement(selectorPopupMessage);
		return getDriver().findElementDynamic(selectorPopupMessage)
				.getText();
	}
	
}
