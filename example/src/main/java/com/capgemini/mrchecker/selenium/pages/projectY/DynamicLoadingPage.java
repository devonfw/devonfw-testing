package com.capgemini.mrchecker.selenium.pages.projectY;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DynamicLoadingPage extends BasePage {
	
	private static final By	selectorExampleOneLink		= By.cssSelector("a[href*='dynamic_loading/1']");
	private static final By	selectorExampleTwoLink		= By.cssSelector("a[href*='dynamic_loading/2']");
	private static final By	selectorDynamicLoadingText	= By.cssSelector("div#content h3");
	private static final By	selectorStartButton			= By.cssSelector("div#start button");
	private static final By	selectorLoadingBar			= By.cssSelector("div#loading");
	private static final By	selectorExampleText			= By.cssSelector("div#finish h4");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.DYNAMIC_LOADING.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Dynamically Loaded Page Elements' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_LOADING.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns information if dynamic loading message is visible or not.
	 *
	 * @return true if dynamic loading message was found on web page.
	 */
	public boolean isDynamicLoadingMessageVisible() {
		return getDriver().findElementDynamic(selectorDynamicLoadingText)
				.isDisplayed();
	}
	
	/**
	 * Clicks Example 1 link.
	 */
	public void clickExampleOneLink() {
		getDriver().findElementDynamic(selectorExampleOneLink)
				.click();
	}
	
	/**
	 * Clicks Example 2 link.
	 */
	public void clickExampleTwoLink() {
		getDriver().findElementDynamic(selectorExampleTwoLink)
				.click();
	}
	
	/**
	 * Returns information if Start button is visible or not.
	 *
	 * @return true if Start button was found on web page.
	 */
	public boolean isStartButtonVisible() {
		return getDriver().findElementDynamic(selectorStartButton)
				.isDisplayed();
	}
	
	/**
	 * Clicks Start button.
	 */
	public void clickStartButton() {
		getDriver().findElementDynamic(selectorStartButton)
				.click();
	}
	
	/**
	 * Returns information if loading bar is visible or not.
	 *
	 * @return true if loading bar was found on web page.
	 */
	public boolean isLoadingBarVisible() {
		return getDriver().findElementDynamic(selectorLoadingBar)
				.isDisplayed();
	}
	
	/**
	 * Waits until WebElement representing waiting bar disappear and returns example text.
	 *
	 * @param waitTime
	 *            The amount of time designated for waiting until waiting bar disappears.
	 * @return String representing example's text.
	 */
	public String getExampleOneDynamicText(int waitTime) {
		WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
		wait.until((Function<? super WebDriver, Boolean>) ExpectedConditions.invisibilityOfElementLocated(selectorLoadingBar));
		return getDriver().findElementDynamic(selectorExampleText)
				.getText();
	}
	
	/**
	 * Returns example text.
	 * <p>
	 * Waits until WebElement representing waiting bar disappear. Then waits until example text will show up.
	 * And after that returns example text.
	 * </p>
	 *
	 * @param waitTime
	 *            The amount of time designated for waiting until waiting bar disappears and example text shows.
	 * @return String representing example's text.
	 */
	public String getExampleTwoDynamicText(int waitTime) {
		WebDriverWait wait = new WebDriverWait(getDriver(), waitTime);
		wait.until((Function<? super WebDriver, Boolean>) ExpectedConditions.invisibilityOfElementLocated(selectorLoadingBar));
		wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.visibilityOfElementLocated(selectorExampleText));
		return getDriver().findElementDynamic(selectorExampleText)
				.getText();
	}
	
}
