package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	private static final By selectorUsernameInput = By.name("username");
	
	private static final By selectorPasswordInput = By.name("password");
	
	private static final By selectorLoginButton = By.name("submitLogin");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForElementVisible(selectorPasswordInput);
		WebElement passwordInput = getDriver().findElementQuietly(selectorPasswordInput);
		return passwordInput != null ? passwordInput.isEnabled() : false;
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar login page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	@Step("Fill username input with: {username}")
	public void enterUsernameInput(String username) {
		getDriver().waitForElement(selectorUsernameInput);
		getDriver().findElementDynamic(selectorUsernameInput)
				.sendKeys(username);
	}
	
	@Step("Fill password input with: {password}")
	public void enterPasswordInput(String password) {
		getDriver().findElementDynamic(selectorPasswordInput)
				.sendKeys(password);
	}
	
	@Step("Click login button")
	public void clickLoginButton() {
		getDriver().waitForElement(selectorLoginButton);
		getDriver().findElementDynamic(selectorLoginButton)
				.click();
	}
	
	@Step("Login as: {username}")
	public void enterCredentialsAndLogin(String username, String password) {
		enterUsernameInput(username);
		enterPasswordInput(password);
		clickLoginButton();
	}
	
}
