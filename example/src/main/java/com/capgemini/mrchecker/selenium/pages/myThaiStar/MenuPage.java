package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;

public class MenuPage extends BasePage {
	
	private static final By selectorAddToOrderRiceButton = By.cssSelector("div[style$='Mzz6n//2Q==\");'] + own-menu-card-details button");
	
	private static final By selectorAddToOrderCurryButton = By.cssSelector("div[style$='pHRP/2Q==\");'] + own-menu-card-details button");
	
	private static final By selectorBookingIdInput = By.cssSelector("input[name='orderBookingID']");
	
	private static final By selectorAcceptTermsCheckbox = By.cssSelector("mat-checkbox[data-name='orderTerms']");
	
	private static final By selectorSubmitButton = By.cssSelector("button[name='orderSubmit']");
	
	private static final By selectorCancelButton = By.cssSelector("button[name='orderCancel']");
	
	private static final By selectorDialog = By.className("bgc-green-600");
	
	private static final By selectorSearchInput = By.cssSelector("input[placeholder='Search our dishes']");
	
	private static final By selectorClearFiltersButton = By.cssSelector("div[class='filter-actions'] button:nth-of-type(1)");
	
	private static final By selectorApplyFiltersButton = By.cssSelector("div[class='filter-actions'] button:nth-of-type(2)");
	
	private static final By selectorSortDropdown = By.cssSelector("mat-select[id='mat-select-0']");
	
	private static final By selectorRiceCheckbox = By.cssSelector("mat-checkbox[id='mat-checkbox-6']");
	
	private static final By selectorCardContainer = By.cssSelector("div[class='card-container ng-star-inserted']");
	
	private static final By selectorPriceSlider = By.cssSelector("mat-slider[formcontrolname='maxPrice'] div.mat-slider-thumb");
	
	private static final By selectorPriceSliderLabel = By.cssSelector("mat-slider[formcontrolname='maxPrice']");
	
	private static final By selectorExpansionPanel = By.cssSelector("td-expansion-panel");
	
	private static final By selectorAddButton = By.cssSelector("div[class='push-bottom-xs'] button:nth-of-type(2)");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.MENU.getValue());
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.MENU.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public void clickAddToOrderRiceButton() {
		getDriver().waitUntilElementIsClickable(selectorAddToOrderRiceButton);
		getDriver().findElementDynamic(selectorAddToOrderRiceButton)
				.click();
	}
	
	public void clickAddToOrderCurryButton() {
		getDriver().waitUntilElementIsClickable(selectorAddToOrderCurryButton);
		getDriver().findElementDynamic(selectorAddToOrderCurryButton)
				.click();
	}
	
	public void enterBookingIdInput(String id) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + id + "';", getDriver().findElementDynamic(selectorBookingIdInput));
	}
	
	public void setAcceptTermsCheckbox() {
		WebElement checkbox = getDriver().findElementDynamic(selectorAcceptTermsCheckbox);
		WebElement square = checkbox.findElement(By.className("mat-checkbox-inner-container"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", square);
	}
	
	public void clickSubmitButton() {
		getDriver().waitUntilElementIsClickable(selectorSubmitButton);
		getDriver().findElementDynamic(selectorSubmitButton)
				.click();
	}
	
	public void makeAnOrder(String id) {
		clickAddToOrderRiceButton();
		enterBookingIdInput(id);
		setAcceptTermsCheckbox();
		clickSubmitButton();
		
	}
	
	public void makeBiggerOrder(String id) {
		clickAddToOrderCurryButton();
		clickAddButton();
		clickCancelButton();
		makeAnOrder(id);
		
	}
	
	public boolean checkConfirmationDialog() {
		WebElement greenConfirmationDialog = getDriver().findElementDynamic(selectorDialog);
		
		return greenConfirmationDialog.isDisplayed();
	}
	
	public void clickApplyFiltersButton() {
		getDriver().findElementDynamic(selectorApplyFiltersButton)
				.click();
	}
	
	public void clickClearFiltersButton() {
		getDriver().findElementDynamic(selectorClearFiltersButton)
				.click();
	}
	
	public void enterSearchInput(String filter) {
		getDriver().findElementDynamic(selectorSearchInput)
				.sendKeys(filter);
	}
	
	public void setRiceCheckbox() {
		WebElement checkbox = getDriver().findElementDynamic(selectorRiceCheckbox);
		WebElement square = checkbox.findElement(By.className("mat-checkbox-inner-container"));
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", square);
	}
	
	public boolean findElementInContainer(String name) {
		WebElement container = getDriver().findElementDynamic(selectorCardContainer);
		try {
			container.findElement(By.cssSelector("div[style$='" + name + "']"));
		} catch (BFElementNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public void slide(int quantity) {
		getDriver().findElementDynamic(selectorExpansionPanel)
				.click();
		getDriver().findElementDynamic(selectorPriceSlider)
				.click();
		
		if (quantity < 0) {
			for (int i = 0; i < Math.abs(quantity); i++) {
				
				getDriver().findElementDynamic(selectorPriceSliderLabel)
						.sendKeys(Keys.ARROW_LEFT);
			}
		} else {
			for (int i = 0; i < quantity; i++) {
				
				getDriver().findElementDynamic(selectorPriceSliderLabel)
						.sendKeys(Keys.ARROW_RIGHT);
			}
		}
	}
	
	public void selectSortDropdown(int index) {
		
		getDriver().findElementDynamic(selectorSortDropdown)
				.click();
		By selectorItem = By.cssSelector("mat-option[id='mat-option-" + index + "']");
		getDriver().findElementDynamic(selectorItem)
				.click();
	}
	
	public void clickCancelButton() {
		getDriver().waitUntilElementIsClickable(selectorCancelButton);
		getDriver().findElementDynamic(selectorCancelButton)
				.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickAddButton() {
		getDriver().waitUntilElementIsClickable(selectorAddButton);
		getDriver().findElementDynamic(selectorAddButton)
				.click();
	}
}