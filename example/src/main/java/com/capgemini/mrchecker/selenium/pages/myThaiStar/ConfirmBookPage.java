package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Step;

public class ConfirmBookPage extends BasePage {
	private static final By selectorConfirmationWindow = By.className("mat-dialog-container");
	
	private static final By	selectorSendButton		= By.name("bookTableConfirm");
	private static final By	selectorCancelButton	= By.name("bookTableCancel");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForElementVisible(selectorConfirmationWindow);
		WebElement confirmationWindow = getDriver().findElementQuietly(selectorConfirmationWindow);
		return confirmationWindow != null ? confirmationWindow.isDisplayed() : false;
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar booking confirmation page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	@Step("Click confirm booking button")
	public void clickConfirmBookingButton() {
		getDriver().findElementDynamic(selectorSendButton)
				.click();
	}
	
	@Step("Click cancel booking button")
	public void clickCancelBookingButton() {
		getDriver().findElementDynamic(selectorCancelButton)
				.click();
	}
}
