package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class TheInternetPage extends BasePage {
	
	private static final By	abTestLinkSelector					= By.cssSelector("li > a[href*='abtest']");
	private static final By	basicAuthLinkSelector				= By.cssSelector("li > a[href*='basic_auth']");
	private static final By	brokenImageLinkSelector				= By.cssSelector("li > a[href*='broken_images']");
	private static final By	challengingDomLinkSelector			= By.cssSelector("li > a[href*='challenging_dom']");
	private static final By	checkboxesLinkSelector				= By.cssSelector("li > a[href*='checkboxes']");
	private static final By	contextMenuLinkSelector				= By.cssSelector("li > a[href*='context_menu']");
	private static final By	disappearingElementsLinkSelector	= By.cssSelector("li > a[href*='disappearing_elements']");
	private static final By	dragAndDropLinkSelector				= By.cssSelector("li > a[href*='drag_and_drop']");
	private static final By	dropdownLinkSelector				= By.cssSelector("li > a[href*='dropdown']");
	private static final By	dynamicContentLinkSelector			= By.cssSelector("li > a[href*='dynamic_content']");
	private static final By	dynamicControlsLinkSelector			= By.cssSelector("li > a[href*='dynamic_controls']");
	private static final By	dynamicLoadingLinkSelector			= By.cssSelector("li > a[href*='dynamic_loading']");
	private static final By	exitIntentLinkSelector				= By.cssSelector("li > a[href*='exit_intent']");
	private static final By	fileDownloadLinkSelector			= By.cssSelector("li > a[href$='download']");
	private static final By	fileUploadLinkSelector				= By.cssSelector("li > a[href*='upload']");
	private static final By	floatingMenuLinkSelector			= By.cssSelector("li > a[href*='floating_menu']");
	private static final By	forgotPasswordLinkSelector			= By.cssSelector("li > a[href*='forgot_password']");
	private static final By	formAuthenticationLinkSelector		= By.cssSelector("li > a[href*='login']");
	private static final By	framesLinkSelector					= By.cssSelector("li > a[href*='frames']");
	private static final By	geolocationLinkSelector				= By.cssSelector("li > a[href*='geolocation']");
	private static final By	horizontalSliderLinkSelector		= By.cssSelector("li > a[href*='horizontal_slider']");
	private static final By	hoversLinkSelector					= By.cssSelector("li > a[href*='hovers']");
	private static final By	infiniteScrollLinkSelector			= By.cssSelector("li > a[href*='infinite_scroll']");
	private static final By	javaScriptAlertLinkSelector			= By.cssSelector("li > a[href*='javascript_alerts']");
	private static final By	javaScriptErrorLinkSelector			= By.cssSelector("li > a[href*='javascript_error']");
	private static final By	jQueryUIMenuLinkSelector			= By.cssSelector("li > a[href*='jqueryui/menu']");
	private static final By	keyPressesLinkSelector				= By.cssSelector("li > a[href*='key_presses']");
	private static final By	largeAndDeepDOMLinkSelector			= By.cssSelector("li > a[href*='large']");
	private static final By	multipleWindowsLinkSelector			= By.cssSelector("li > a[href*='windows']");
	private static final By	nestedFramesLinkSelector			= By.cssSelector("li > a[href*='nested_frames']");
	private static final By	notificationMessagesLinkSelector	= By.cssSelector("li > a[href*='notification_message']");
	private static final By	redirectLinkSelector				= By.cssSelector("li > a[href*='redirector']");
	private static final By	secureFileDownloadLinkSelector		= By.cssSelector("li > a[href*='download_secure']");
	private static final By	shiftingContentLinkSelector			= By.cssSelector("li > a[href*='shifting_content']");
	private static final By	slowResourcesLinkSelector			= By.cssSelector("li > a[href*='slow']");
	private static final By	sortableDataTablesLinkSelector		= By.cssSelector("li > a[href*='tables']");
	private static final By	statusCodesLinkSelector				= By.cssSelector("li > a[href*='status_codes']");
	private static final By	typosLinkSelector					= By.cssSelector("li > a[href*='typos']");
	private static final By	wYSIWYGEditorLinkSelector			= By.cssSelector("li > a[href*='tinymce']");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The internet page is loaded: " + getDriver().getCurrentUrl());
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'The internet' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public ABtestPage clickABtestingLink() {
		new Button(abTestLinkSelector).click();
		return new ABtestPage();
	}
	
	public BasicAuthPage clickBasicAuthLink() {
		getDriver().waitForPageLoaded();
		WebElement link = getDriver().findElementDynamic(basicAuthLinkSelector);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
		return new BasicAuthPage();
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		new Button(brokenImageLinkSelector).click();
		return new BrokenImagePage();
	}
	
	public ChallengingDomPage clickChallengingDomLink() {
		new Button(challengingDomLinkSelector).click();
		return new ChallengingDomPage();
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		new Button(checkboxesLinkSelector).click();
		return new CheckboxesPage();
	}
	
	public ContextMenuPage clickContextMenuLink() {
		new Button(contextMenuLinkSelector).click();
		return new ContextMenuPage();
	}
	
	public DisappearingElementsPage clickDisappearingElementsLink() {
		new Button(disappearingElementsLinkSelector).click();
		return new DisappearingElementsPage();
	}
	
	public DragAndDropPage clickDragAndDropLink() {
		new Button(dragAndDropLinkSelector).click();
		return new DragAndDropPage();
	}
	
	public DropdownPage clickDropdownLink() {
		new Button(dropdownLinkSelector).click();
		return new DropdownPage();
	}
	
	public DynamicContentPage clickDynamicContentLink() {
		new Button(dynamicContentLinkSelector).click();
		return new DynamicContentPage();
	}
	
	public DynamicControlsPage clickDynamicControlsLink() {
		new Button(dynamicControlsLinkSelector).click();
		return new DynamicControlsPage();
	}
	
	public DynamicLoadingPage clickDynamicLoadingLink() {
		new Button(dynamicLoadingLinkSelector).click();
		return new DynamicLoadingPage();
	}
	
	public ExitIntentPage clickExitIntentLink() {
		new Button(exitIntentLinkSelector).click();
		return new ExitIntentPage();
	}
	
	public FileDownloadPage clickFileDownloadLink() {
		new Button(fileDownloadLinkSelector).click();
		return new FileDownloadPage();
	}
	
	public FileUploadPage clickFileUploadLink() {
		new Button(fileUploadLinkSelector).click();
		return new FileUploadPage();
	}
	
	public FloatingMenuPage clickFloatingMenuLink() {
		new Button(floatingMenuLinkSelector).click();
		return new FloatingMenuPage();
	}
	
	public ForgotPasswordPage clickForgotPasswordLink() {
		new Button(forgotPasswordLinkSelector).click();
		return new ForgotPasswordPage();
	}
	
	public FormAuthenticationPage clickFormAuthenticationLink() {
		new Button(formAuthenticationLinkSelector).click();
		return new FormAuthenticationPage();
	}
	
	public FramesPage clickFramesLink() {
		new Button(framesLinkSelector).click();
		return new FramesPage();
	}
	
	public GeolocationPage clickGeolocationLink() {
		new Button(geolocationLinkSelector).click();
		return new GeolocationPage();
	}
	
	public HorizontalSliderPage clickHorizontalSliderLink() {
		new Button(horizontalSliderLinkSelector).click();
		return new HorizontalSliderPage();
	}
	
	public HoversPage clickHoversLink() {
		new Button(hoversLinkSelector).click();
		return new HoversPage();
	}
	
	public InfiniteScrollPage clickInfiniteScrollLink() {
		new Button(infiniteScrollLinkSelector).click();
		return new InfiniteScrollPage();
	}
	
	public JavaScriptAlertsPage clickJavaScriptAlertLink() {
		new Button(javaScriptAlertLinkSelector).click();
		return new JavaScriptAlertsPage();
	}
	
	public JavaScriptErrorPage clickJavaScriptErrorLink() {
		new Button(javaScriptErrorLinkSelector).click();
		return new JavaScriptErrorPage();
	}
	
	public JQueryUIMenuPage clickJQueryUIMenuLink() {
		new Button(jQueryUIMenuLinkSelector).click();
		return new JQueryUIMenuPage();
	}
	
	public KeyPressesPage clickKeyPressesLink() {
		new Button(keyPressesLinkSelector).click();
		return new KeyPressesPage();
	}
	
	public LargeAndDeepDOMPage clickLargeAndDeepDOMLink() {
		new Button(largeAndDeepDOMLinkSelector).click();
		return new LargeAndDeepDOMPage();
	}
	
	public MultipleWindowsPage clickmultipleWindowsLink() {
		new Button(multipleWindowsLinkSelector).click();
		return new MultipleWindowsPage();
	}
	
	public NestedFramesPage clickNestedFramesLink() {
		new Button(nestedFramesLinkSelector).click();
		return new NestedFramesPage();
	}
	
	public NotificationMessagesPage clickNotificationMessagesLink() {
		new Button(notificationMessagesLinkSelector).click();
		return new NotificationMessagesPage();
	}
	
	public RedirectLinkPage clickRedirectLink() {
		new Button(redirectLinkSelector).click();
		return new RedirectLinkPage();
	}
	
	public SecureFileDownloadPage clickSecureFileDownloadLink() {
		new Button(secureFileDownloadLinkSelector).click();
		return new SecureFileDownloadPage();
	}
	
	public ShiftingContentPage clickShiftingContentLink() {
		new Button(shiftingContentLinkSelector).click();
		return new ShiftingContentPage();
	}
	
	public SlowResourcesPage clickSlowResourcesLink() {
		new Button(slowResourcesLinkSelector).click();
		return new SlowResourcesPage();
	}
	
	public SortableDataTablesPage clickSortableDataTablesLink() {
		new Button(sortableDataTablesLinkSelector).click();
		return new SortableDataTablesPage();
	}
	
	public StatusCodesHomePage clickStatusCodesLink() {
		new Button(statusCodesLinkSelector).click();
		return new StatusCodesHomePage();
	}
	
	public TyposPage clickTyposLink() {
		new Button(typosLinkSelector).click();
		return new TyposPage();
	}
	
	public WYSIWYGEditorPage clickWYSIWYGEditorLink() {
		new Button(wYSIWYGEditorLinkSelector).click();
		return new WYSIWYGEditorPage();
	}
	
}