package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ConfirmInvitationPage extends BasePage {
	
	private static final By selectorConfirmationWindow = By.className("mat-dialog-container");
	
	private static final By selectorSendButton = By.name("inviteFriendsConfirm");
	
	private static final By selectorCancelButton = By.name("inviteFriendsCancel");
	
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
	
	public void clickConfirmBookingButton() {
		getDriver().findElementDynamic(selectorSendButton)
				.click();
	}
	
	public void clickCancelBookingButton() {
		getDriver().findElementDynamic(selectorCancelButton)
				.click();
	}
}
