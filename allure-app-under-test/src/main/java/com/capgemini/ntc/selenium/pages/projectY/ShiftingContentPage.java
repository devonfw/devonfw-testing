package com.capgemini.ntc.selenium.pages.projectY;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class ShiftingContentPage extends BasePage {
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.SHIFTING_CONTENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Shifting Content' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.SHIFTING_CONTENT.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
}
