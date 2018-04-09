package com.capgemini.ntc.selenium.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.selenium.core.base.properties.PropertiesSelenium;
import com.capgemini.ntc.selenium.core.base.runtime.RuntimeParametersSelenium;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.newDrivers.DriverManager;
import com.capgemini.ntc.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.ntc.selenium.core.utils.WindowUtils;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.ModuleType;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Guice;

import ru.yandex.qatools.allure.annotations.Attachment;

abstract public class BasePage implements IBasePage, ITestObserver {
	
	// in seconds; this value should be used for very shot delay purpose e.g. to
	// wait for JavaScript take effort on element
	public static final int EXPLICITY_SHORT_WAIT_TIME = 1;
	
	public static final int PROGRESSBARWAITTIMER = 60;
	// in seconds. timer used in findElementDynamic
	public static final int EXPLICITYWAITTIMER = 20;
	
	public static final int MAX_COMPONENT_RELOAD_COUNT = 3;
	
	private static DriverManager	driver	= null;
	private static WebDriverWait	webDriverWait;
	
	private BasePage parent;
	
	private static IEnvironmentService environmentService;
	private final static IAnalytics analytics;
	public final static String			analitycsCategoryName	= "Selenium-NewDrivers";
	
	private final static PropertiesSelenium propertiesSelenium;
	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();
		
		// Get and then set properties information from selenium.settings file
		propertiesSelenium = setPropertiesSettings();
		
		// Read System or maven parameters
		setRuntimeParametersSelenium();
		
		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}
	
	public static IAnalytics getAnalytics() {
		return BasePage.analytics;
	}
	
	public BasePage() {
		this(getDriver(), null);
	}
	
	public BasePage(BasePage parent) {
		this(getDriver(), parent);
	}
	
	public BasePage(INewWebDriver driver, BasePage parent) {
		// Add given module to Test core Observable list
		this.addObserver();
		
		webDriverWait = new WebDriverWait(getDriver(), BasePage.EXPLICITYWAITTIMER);
		
		this.setParent(parent);
		
		// If the page is not yet loaded, then load it
		if (!(this.isLoaded())) { // In this scenario check if
			this.load();
		}
		
	}
	
	@Override
	public void addObserver() {
		BaseTestWatcher.addObserver(this);
	}
	
	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BasePage.onTestFailure    " + this.getClass()
				.getSimpleName());
		makeScreenshotOnFailure();
		makeSourcePageOnFailure();
	}
	
	@Override
	public void onTestSuccess() {
		BFLogger.logDebug("BasePage.onTestSuccess    " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public void onTestFinish() {
		BFLogger.logDebug("BasePage.onTestFinish   " + this.getClass()
				.getSimpleName());
		BaseTestWatcher.removeObserver(this);
	}
	
	@Override
	public void onTestClassFinish() {
		BFLogger.logDebug("BasePage.onTestClassFinish   " + this.getClass()
				.getSimpleName());
		BFLogger.logDebug("driver:" + getDriver().toString());
		DriverManager.closeDriver();
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.SELENIUM;
	}
	
	@Attachment("Screenshot on failure")
	public byte[] makeScreenshotOnFailure() {
		byte[] screenshot = null;
		try {
			screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		} catch (UnhandledAlertException e) {
			BFLogger.logDebug("[makeScreenshotOnFailure] Unable to take screenshot.");
		}
		return screenshot;
	}
	
	@Attachment("Source Page on failure")
	public String makeSourcePageOnFailure() {
		return DriverManager.getDriver()
						.getPageSource();
	}
	
	public String getActualPageTitle() {
		return getDriver().getTitle();
	}
	
	public void refreshPage() {
		getDriver().navigate()
						.refresh();
	}
	
	public static INewWebDriver getDriver() {
		if (BasePage.driver == null) {
			
			BasePage.driver = new DriverManager(propertiesSelenium);
			
		}
		return BasePage.driver.getDriver();
		
	}
	
	public static void navigateBack() {
		navigateBack(true);
	}
	
	/**
	 * Navigates to previous site (works like pressing browsers 'Back' button)
	 * 
	 * @param andWait
	 *            - wait for progress bars to load if true
	 */
	public static void navigateBack(boolean andWait) {
		getDriver().navigate()
						.back();
		getDriver().waitForPageLoaded();
	}
	
	public static Actions getAction() {
		return new Actions(getDriver());
	}
	
	public void setParent(BasePage parent) {
		this.parent = parent;
	}
	
	public BasePage getParent() {
		return this.parent;
	}
	
	public abstract String pageTitle();
	
	public void loadPage(String url) {
		BFLogger.logDebug(getClass().getName() + ": Opening  page: " + url);
		getDriver().get(url);
		getDriver().waitForPageLoaded();
		
	}
	
	public boolean isUrlAndPageTitleAsCurrentPage(String url) {
		getDriver().waitForPageLoaded();
		String pageTitle = this.pageTitle();
		String currentUrl = BasePage.getDriver()
						.getCurrentUrl();
		String currentPageTitle = BasePage.getDriver()
						.getTitle();
		if (!currentUrl.contains(url) || !pageTitle.equals(currentPageTitle)) {
			BFLogger.logDebug(getClass().getName() + ": Current loaded page (" + url + ") with pageTitle ("
							+ currentPageTitle + "). Page to load: (" + url + ") ,for page title: (" + pageTitle + ")");
			return false;
		}
		return true;
	}
	
	/**
	 * Recommended to use. It is method to check is element visible. If element not exists in DOM , method throw
	 * BFElementNotFoundException
	 * 
	 * @throws BFElementNotFoundException
	 * @param cssSelector
	 * @return false if element have an attribute displayed = none, otherwise return true;
	 */
	public static boolean isElementDisplayed(By cssSelector) {
		List<WebElement> elements = getDriver().findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		return elements.get(0)
						.isDisplayed();
	}
	
	/**
	 * It is method to check is element visible. It search element inside parent. If element not exists in DOM , method
	 * throw BFElementNotFoundException
	 * 
	 * @throws BFElementNotFoundException
	 * @param cssSelector
	 * @param parent
	 * @return false if element have an attribute displayed = none, otherwise return true;
	 */
	public static boolean isElementDisplayed(By cssSelector, WebElement parent) {
		List<WebElement> elements = parent.findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		return elements.get(0)
						.isDisplayed();
	}
	
	/**
	 * Check if given selector is displayed on the page and it contain a specific text
	 * 
	 * @param cssSelector
	 * @param text
	 * @return true if a given element is displayed with a specific text
	 * @throws BFElementNotFoundException
	 *             if element is not found in DOM
	 */
	public static boolean isElementDisplayed(By cssSelector, String text) {
		List<WebElement> elements = getDriver().findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		boolean retValue = elements.get(0)
						.isDisplayed();
		if (retValue && text != null) {
			retValue = elements.get(0)
							.getText()
							.equals(text);
		}
		return retValue;
	}
	
	/**
	 * Check if given selector is displayed on the page
	 * 
	 * @param selector
	 * @param parent
	 * @return true if a given element is displayed
	 */
	public static boolean isElementDisplayedNoException(By selector, WebElement parent) {
		try {
			return BasePage.isElementDisplayed(selector, parent);
		} catch (BFElementNotFoundException exc) {
			return false;
		}
	}
	
	/**
	 * Check if given selector is displayed on the page
	 * 
	 * @param selector
	 * @return true if a given element is displayed
	 */
	public static boolean isElementDisplayedNoException(By selector) {
		try {
			return BasePage.isElementDisplayed(selector);
		} catch (BFElementNotFoundException exc) {
			return false;
		}
	}
	
	public static boolean isElementPresent(By cssSelector) {
		return !getDriver().findElements(cssSelector)
						.isEmpty();
	}
	
	public static boolean isLinkClickable(By selector) {
		WebElement linkElement = getDriver().findElement(selector);
		return isLinkClickable(linkElement);
	}
	
	public static boolean isLinkClickable(WebElement element) {
		return !element.getAttribute("href")
						.equals("");
	}
	
	public static WebDriverWait getWebDriverWait() {
		if (null == webDriverWait) {
			BasePage.webDriverWait = new WebDriverWait(getDriver(), BasePage.EXPLICITYWAITTIMER);
		}
		return BasePage.webDriverWait;
	}
	
	private static boolean isTitleElementDisplayed(By selector, String title) {
		List<WebElement> pageTitle = getDriver().findElements(selector);
		boolean resValue = !pageTitle.isEmpty();
		if (resValue) {
			resValue = pageTitle.get(0)
							.isDisplayed()
							&& pageTitle.get(0)
											.getText()
											.equals(title);
		}
		return resValue;
	}
	
	/**
	 * Open link in new tab
	 * 
	 * @param url
	 */
	public static void openInNewTab(String url) {
		JavascriptExecutor js = (JavascriptExecutor) BasePage.getDriver();
		js.executeScript("window.open(arguments[0], '_blank');", url);
		WindowUtils.switchWindow(url, true);
	}
	
	private static PropertiesSelenium setPropertiesSettings() {
		// Get and then set properties information from selenium.settings file
		PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesSettingsModule.init())
						.getInstance(PropertiesSelenium.class);
		return propertiesSelenium;
	}
	
	private static void setRuntimeParametersSelenium() {
		// Read System or maven parameters
		BFLogger.logDebug(java.util.Arrays.asList(RuntimeParametersSelenium.values())
						.toString());
		
	}
	
	private static void setEnvironmetInstance() {
		/*
		 * Environment variables either from environmnets.csv or any other input data. For now there is no properties
		 * settings file for Selenium module. In future, please have a look on Core Module IEnvironmentService
		 * environmetInstance = Guice.createInjector(new EnvironmentModule()) .getInstance(IEnvironmentService.class);
		 */
		
	}
	
}
