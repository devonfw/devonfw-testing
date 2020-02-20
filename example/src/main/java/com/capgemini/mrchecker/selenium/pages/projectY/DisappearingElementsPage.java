package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DisappearingElementsPage extends BasePage {
	
	private static final By	selectorGalleryMenuButton	= By.cssSelector("li > a[href*=gallery]");
	private static final By	selectorMenuButtons			= By.cssSelector("li");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.DISAPPEARING_ELEMENTS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Disappearing Elements' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DISAPPEARING_ELEMENTS.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns a number of WebElements representing menu buttons.
	 *
	 * @return A number of WebElements.
	 */
	public int getNumberOfMenuButtons() {
		return getDriver().findElementDynamics(selectorMenuButtons)
				.size();
	}
	
	/**
	 * Returns WebElement representing disappearing element of menu.
	 *
	 * @return Disappearing WebElement if visible, null otherwise.
	 */
	public WebElement getGalleryMenuElement() {
		return getDriver().findElementQuietly(selectorGalleryMenuButton);
	}
	
	/**
	 * Refreshes web page as many times as it is required to appear/disappear menu button WebElement.
	 *
	 * @param shouldAppear
	 *            Determines if element should appear (true) or disappear (false).
	 */
	public void refreshPageUntilWebElementAppears(boolean shouldAppear) {
		int numberOfAttempts = 5;
		int counter = 0;
		while (!isVisibilityAsExpected(shouldAppear) || isMaxNumberOfAttemptsReached(counter++, numberOfAttempts)) {
			refreshPage();
		}
	}
	
	/**
	 * Verify if visibility of Gallery button is the same as expected
	 *
	 * @param expected
	 *            Determines if element should be visible (true) or not visible (false).
	 */
	private boolean isVisibilityAsExpected(boolean expected) {
		boolean isVisibilityDifferentThanExpected = isGalleryMenuElementVisible() ^ expected;
		return !isVisibilityDifferentThanExpected;
	}
	
	private boolean isGalleryMenuElementVisible() {
		boolean result = false;
		WebElement gallery = getGalleryMenuElement();
		if (gallery != null)
			result = gallery.isDisplayed();
		return result;
	}
	
	private boolean isMaxNumberOfAttemptsReached(int attemptNo, int maxNumberOfAttempts) {
		return attemptNo == maxNumberOfAttempts;
	}
	
}
