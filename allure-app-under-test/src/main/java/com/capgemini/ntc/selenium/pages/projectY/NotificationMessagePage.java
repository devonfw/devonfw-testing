package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;

public class NotificationMessagePage extends BasePage {
	
	private final static String	URL					= GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			+ PageSubURLsProjectYEnum.NOTIFICATIONS.getValue();
	private final static By		flashMessageLocator	= By.id("flash");
	private final static By		loadMsgLinkLocator	= By.linkText("Click here");
	
	private static WebElement	flashMessage;
	private static WebElement	loadMsgLink;
	
	@Override
	public void load() {
		getDriver().get(URL);
		findElements();
	}
	
	public void findElements() {
		getDriver().waitForPageLoaded();
		flashMessage = getDriver().findElementQuietly(flashMessageLocator); // Element
		// [By.xpath://*[contains(text(),'LEFT')]]
		loadMsgLink = getDriver().findElementQuietly(loadMsgLinkLocator);
	}
	
	public void clickLoadMessageLink() {
		loadMsgLink.click();
		getDriver().waitForPageLoaded();
		while (!isLoaded())
			;
	}
	
	public String getFlashMessageText() {
		return flashMessage.getText();
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		
		return loadCompleted && isUrlAndPageTitleAsCurrentPage(URL);
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
}
