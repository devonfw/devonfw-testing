package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;

public class DisappearingElementsPage extends BasePage {

    private static final By selectorGalleryMenuButton = By.cssSelector("li > a[href*=gallery]");
    private static final By selectorAboutMenuButton = By.cssSelector("li > a[href*=about]");
    private static final By selectorMenuButtons = By.cssSelector("li");

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
        return findWebElementBySelector(selectorGalleryMenuButton);
    }

    /**
     * Returns WebElement representing NOT-disappearing element of menu.     *
     */
    public WebElement getAboutMenuElement() {
        WebElement webElement = findWebElementBySelector(selectorAboutMenuButton);
        if (webElement != null) {
            return webElement;
        } else {
            throw new InvalidSelectorException("About Menu WebElement not found");
        }
    }

    /**
     * Finds webelement by its selector
     *
     * @param selector
     * @return WebElement or null if not found
     */
    public WebElement findWebElementBySelector(By selector) {
        return getDriver().findElementQuietly(selector);
    }

    /**
     * Refreshes web page until WebElement has expected visibility
     *
     * @param selector          Tested element
     * @param shouldBeDisplayed Determines if element should be displayed.
     * @param maxRefreshes      Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilWebElementHasExpectedVisibility(By selector, boolean shouldBeDisplayed, int maxRefreshes) {
        WebElement webElement;
        for (int refreshesDone = 0; refreshesDone < maxRefreshes; refreshesDone++) {
            refreshPage();
            webElement = findWebElementBySelector(selector);
            if (webElement != null && webElement.isDisplayed() == shouldBeDisplayed) {
                return true;
            }
        }
        return false;
    }

    /**
     * Refreshes web page until WebElement has wrong visibility
     *
     * @param selector          Tested element
     * @param shouldBeDisplayed Determines if element should be displayed.
     * @param maxRefreshes      Maximal number of refreshes
     * @return true if element had correct visibility after every refresh
     */
    public boolean hasSameVisibilityAfterEveryRefresh(By selector, boolean shouldBeDisplayed, int maxRefreshes) {
        WebElement webElement;
        for (int refreshesDone = 0; refreshesDone < maxRefreshes; refreshesDone++) {
            refreshPage();
            webElement = findWebElementBySelector(selector);
            if (webElement == null || webElement.isDisplayed() != shouldBeDisplayed) {
                return false;
            }
        }
        return true;
    }

    /**
     * Refreshes page until Gallery menu element is displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilGalleryIsVisible(int maxRefreshes) {
        return refreshPageUntilWebElementHasExpectedVisibility(selectorGalleryMenuButton, true, maxRefreshes);
    }

    /**
     * Refreshes page until Gallery menu element is not displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean refreshPageUntilGalleryIsNotVisible(int maxRefreshes) {
        return refreshPageUntilWebElementHasExpectedVisibility(selectorAboutMenuButton, false, maxRefreshes);
    }

    /**
     * Refreshes page until Gallery menu element is displayed
     *
     * @param maxRefreshes Maximal number of refreshes
     * @return true if element has expected visibility after last refresh
     */
    public boolean isAboutButtonVisibleAfterEveryRefresh(int maxRefreshes) {
        return hasSameVisibilityAfterEveryRefresh(selectorAboutMenuButton, true, maxRefreshes);
    }

}
