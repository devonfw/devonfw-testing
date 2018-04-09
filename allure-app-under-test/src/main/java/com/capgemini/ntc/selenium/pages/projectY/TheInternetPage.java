package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheInternetPage extends BasePage {
	
	private static final By	selectorCheckboxesLink				= By.cssSelector("li > a[href*=checkboxes]");
	private static final By	selectorElementClick				= By.cssSelector("li > a[href*=abtest]");
	private static final By	selectorBrokenImageClickLink		= By.cssSelector("li > a[href*=broken_images]");
	private static final By	selectorDropdownClickLink			= By.cssSelector("li > a[href*=dropdown]");
	private static final By	selectorMultipleWindowsLink			= By.cssSelector("li > a[href*=windows]");
	private static final By	selectorBasicAuthLink				= By.cssSelector("li > a[href*=basic_auth]");
	private static final By	selectorKeyPressesLink				= By.cssSelector("li > a[href*=key_presses]");
	private static final By	selectorRedirectLink				= By.cssSelector("li > a[href*=redirector]");
	private static final By	selectorJavaScriptAlertLink			= By.cssSelector("li > a[href*=javascript_alerts]");
	private static final By	selectorHoversLink					= By.cssSelector("li > a[href*=hovers]");
	private static final By	selectorSortableDataTablesLink		= By.cssSelector("li > a[href*=tables]");
	private static final By	selectorChallengingDomClick			= By.cssSelector("li > a[href*=challenging_dom]");
	private static final By	selectorStatusCodesLink				= By.cssSelector("li > a[href*=status_codes]");
	private static final By	selectorDynamicContent				= By.cssSelector("li > a[href*=dynamic_content]");
	private static final By	selectorHorizontalSliderLink		= By.cssSelector("li > a[href*=horizontal_slider]");
	private static final By	selectorFormAuthenticationLink		= By.cssSelector("li > a[href*=login]");
	private static final By	selectorFileDownloadLink			= By.cssSelector("li > a[href='/download']");
	private static final By	selectorForgotPasswordLink			= By.cssSelector("li > a[href*=forgot_password]");
	private static final By	selectorExitIntentLink				= By.cssSelector("li > a[href*=exit_intent]");
	private static final By	selectorDynamicLoadingLink			= By.cssSelector("li > a[href*=dynamic_loading]");
	private static final By	selectorDisappearingElementsLink	= By.cssSelector("li > a[href*=disappearing_elements]");
	private static final By	selectorDragAndDrop					= By.cssSelector("li > a[href*=drag_and_drop]");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The internet page is loaded: " + getDriver().getCurrentUrl());
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load page");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		Button elementLink = new Button(selectorCheckboxesLink);
		elementLink.click();
		return new CheckboxesPage();
	}
	
	public ABtestPage clickABtestingLink() {
		Button elementLink = new Button(selectorElementClick);
		elementLink.click();
		return new ABtestPage();
	}
	
	public ChallengingDomPage clickChallengingDomLink() {
		Button elementLink = new Button(selectorChallengingDomClick);
		elementLink.click();
		return new ChallengingDomPage();
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		Button elementLink = new Button(selectorBrokenImageClickLink);
		elementLink.click();
		return new BrokenImagePage();
	}
	
	public DropdownPage clickDropdownLink() {
		Button elementLink = new Button(selectorDropdownClickLink);
		elementLink.click();
		return new DropdownPage();
	}
	
	public KeyPressesPage clickKeyPressesLink() {
		Button elementLink = new Button(selectorKeyPressesLink);
		elementLink.click();
		return new KeyPressesPage();
	}
	
	public SortableDataTablesPage clickSortableDataTablesLink() {
		Button elementLink = new Button(selectorSortableDataTablesLink);
		elementLink.click();
		return new SortableDataTablesPage();
	}
	
	public JavaScriptAlertsPage clickJavaScriptAlertLink() {
		Button elementLink = new Button(selectorJavaScriptAlertLink);
		elementLink.click();
		return new JavaScriptAlertsPage();
	}
	
	public DynamicContentPage clickDynamicContentPage() {
		Button elementLink = new Button(selectorDynamicContent);
		elementLink.click();
		return new DynamicContentPage();
	}
	
	public MultipleWindowsPage clickmultipleWindowsPageLink() {
		Button elementLink = new Button(selectorMultipleWindowsLink);
		elementLink.click();
		return new MultipleWindowsPage();
	}
	
	public RedirectLinkPage clickRedirectLinkPage() {
		Button elementLink = new Button(selectorRedirectLink);
		elementLink.click();
		return new RedirectLinkPage();
	}
	
	public TheBasicAuthPage clickBasicAuthLink() {
		getDriver().waitForPageLoaded();
		WebElement link = getDriver().findElementDynamic(selectorBasicAuthLink);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
		return new TheBasicAuthPage();
	}
	
	public HoversPage clickHoversLink() {
		Button elementLink = new Button(selectorHoversLink);
		elementLink.click();
		return new HoversPage();
	}
	
	public StatusCodesHomePage clickStatusCodesLink() {
		getDriver().findElementDynamic(selectorStatusCodesLink)
				.click();
		return new StatusCodesHomePage();
	}
	
	public HorizontalSliderPage clickHorizontalSliderLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorHorizontalSliderLink);
		elementLink.click();
		return new HorizontalSliderPage();
	}
	
	public FormAuthenticationPage clickFormAuthenticationLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFormAuthenticationLink);
		elementLink.click();
		return new FormAuthenticationPage();
	}
	
	public FileDownloadPage clickFileDownloadLink() {
		Button elementLink = new Button(selectorFileDownloadLink);
		elementLink.click();
		return new FileDownloadPage();
	}
	
	public ForgotPasswordPage clickForgotPasswordLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorForgotPasswordLink);
		elementLink.click();
		return new ForgotPasswordPage();
	}
	
	public ExitIntentPage clickExitIntentLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorExitIntentLink);
		elementLink.click();
		return new ExitIntentPage();
	}
	
	public DynamicLoadingPage clickDynamicLoadingLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDynamicLoadingLink);
		elementLink.click();
		return new DynamicLoadingPage();
	}
	
	public DisappearingElementsPage clickDisappearingElementsLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDisappearingElementsLink);
		elementLink.click();
		return new DisappearingElementsPage();
	}
	
	public DragAndDropPage clickDragAndDropLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDragAndDrop);
		elementLink.click();
		return new DragAndDropPage();
	}
	
}