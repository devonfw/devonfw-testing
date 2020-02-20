package com.capgemini.mrchecker.selenium.pages.projectY;

import java.awt.AWTException;
import java.awt.Robot;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ExitIntentPage extends BasePage {
	
	private static final String	MODAL_WINDOW_HIDDEN				= "display: none;";
	private static final String	MODAL_WINDOW_DISPLAYED			= "display: block;";
	private static final String	MODAL_WINDOW_STYLE_ATTRIBUTTE	= "style";
	
	private static final By	selectorModalWindow				= By.cssSelector("div#ouibounce-modal");
	private static final By	selectorExitIntentText			= By.cssSelector("div#content h3");
	private static final By	selectorModalWindowTitle		= By.cssSelector("h3");
	private static final By	selectorModalWindowCloseButton	= By.cssSelector("div.modal-footer > p");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.EXIT_INTENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Exit Intent' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.EXIT_INTENT.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns information if exit intent message is visible or not.
	 *
	 * @return true if exit intent message was found on web page.
	 */
	public boolean isIntentMessageVisible() {
		return getDriver().findElementDynamic(selectorExitIntentText)
				.isDisplayed();
	}
	
	/**
	 * Returns information if modal window is hidden.
	 *
	 * @return true if modal window is hidden.
	 */
	public boolean isModalWindowHidden() {
		return getDriver().findElementDynamic(selectorModalWindow)
				.getAttribute(MODAL_WINDOW_STYLE_ATTRIBUTTE)
				.equals(MODAL_WINDOW_HIDDEN);
	}
	
	/**
	 * Returns information if modal window is showed on web page.
	 *
	 * @return true if modal window is displayed.
	 */
	public boolean isModalWindowVisible() {
		return getDriver().findElementDynamic(selectorModalWindow)
				.getAttribute(MODAL_WINDOW_STYLE_ATTRIBUTTE)
				.equals(MODAL_WINDOW_DISPLAYED);
	}
	
	/**
	 * Returns information if modal window title is shown and correct.
	 *
	 * @param expectedValue
	 *            String representing expected value of modal window's title.
	 * @return true if modal window's title is equal to expected value.
	 */
	public boolean verifyModalWindowTitle(String expectedValue) {
		return getDriver().elementLabel(new ByChained(selectorModalWindow, selectorModalWindowTitle))
				.getText()
				.equals(expectedValue);
	}
	
	/**
	 * Closes modal window by pressing 'close' button.
	 */
	public void closeModalWindow() {
		getDriver().elementButton(new ByChained(selectorModalWindow, selectorModalWindowCloseButton))
				.click();
	}
	
	/**
	 * Moves mouse pointer to the middle of screen at the top, then to the middle of screen and again to the top.
	 * <p>
	 * This move simulates leave of the viewport and encourages the modal to show up. There is java.awt.Robot used
	 * to move mouse pointer out of viewport. There are timeouts used to let the browser detect mouse move.
	 * </p>
	 *
	 * @see java.awt.Robot
	 */
	public void moveMouseOutOfViewport() {
		Robot robot;
		Dimension screenSize = getDriver().manage()
				.window()
				.getSize();
		int halfWidth = new BigDecimal(screenSize.getWidth() / 2).intValue();
		int halfHeight = new BigDecimal(screenSize.getHeight() / 2).intValue();
		
		try {
			robot = new Robot();
			robot.mouseMove(halfWidth, 1);
			getDriver().manage()
					.timeouts()
					.implicitlyWait(1, TimeUnit.SECONDS);
			robot.mouseMove(halfWidth, halfHeight);
			getDriver().manage()
					.timeouts()
					.implicitlyWait(1, TimeUnit.SECONDS);
			robot.mouseMove(halfWidth, 1);
		} catch (AWTException e) {
			BFLogger.logError("Unable to connect with remote mouse");
			e.printStackTrace();
		}
	}
	
}
