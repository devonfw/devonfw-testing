package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class WaiterPage extends BasePage {
	
	private static final By selectorOrdersTab = By.xpath("//a[@routerlink='/orders']");
	
	private static final By selectorReservationsTab = By.xpath("//a[@routerlink='/reservations']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return ((getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL + PageSubURLsMyThaiStar.RESERVATIONS.getValue()))
				|| (getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.MY_THAI_STAR_URL + PageSubURLsMyThaiStar.ORDERS.getValue())));
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar waiter page was not loaded.");
		
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public ReservationsPage clickReservationsTab() {
		getDriver().findElementDynamic(selectorReservationsTab)
				.click();
		
		return new ReservationsPage();
	}
}
