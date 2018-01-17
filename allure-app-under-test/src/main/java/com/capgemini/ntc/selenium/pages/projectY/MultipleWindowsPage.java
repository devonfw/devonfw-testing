package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class MultipleWindowsPage extends BasePage {
	
	private final static By selectorLink = By.cssSelector("#content > div > a");
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
						.equals("http://the-internet.herokuapp.com/windows");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		getDriver().get("http://the-internet.herokuapp.com/windows");
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public NewWindowPage clickHereLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorLink);
		elementLink.click();
		getDriver().waitForPageLoaded();
		return new NewWindowPage();
	}
}
