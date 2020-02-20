package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DropdownPage extends BasePage {
	
	private static final By dropdownListSelector = By.cssSelector("#dropdown");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.DROPDOWN.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Dropdown List' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DROPDOWN.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Selects doropdown's value by given index.
	 *
	 * @param index
	 *            Index of option to be selected
	 */
	public void selectDropdownValueByIndex(int index) {
		getDriver().elementDropdownList(dropdownListSelector)
				.selectDropdownByIndex(index);
	}
	
	/**
	 * Returns text value of first selected dropdown's option.
	 *
	 * @return String object representing value of dropdown's option
	 */
	public String getSelectedDropdownValue() {
		return getDriver().elementDropdownList(dropdownListSelector)
				.getFirstSelectedOptionText();
	}
}
