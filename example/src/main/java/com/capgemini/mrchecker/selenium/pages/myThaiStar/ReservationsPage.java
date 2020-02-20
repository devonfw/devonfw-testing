package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.utils.Utils;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ReservationsPage extends BasePage {
	
	private static final By selectorReservationsTable = By.cssSelector("tbody[class='td-data-table-body'] > tr");
	
	private static final By selectorReservationsTableContent = By.cssSelector("div > span");
	
	private static final By selectorSearchFilterBar = By.className("td-expansion-panel-header-content");
	
	private static final By selectorEmailInput = By.xpath("//input[@name=\"email\"]");
	
	private static final By selectorApplyButton = By.xpath("//button[@type='submit']");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL + PageSubURLsMyThaiStar.RESERVATIONS.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar reservation page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public List<String> getReservationsByEmailAndDate(String email, String date) {
		
		getDriver().waitUntilElementIsClickable(selectorSearchFilterBar);
		WebElement searchBar = getDriver().findElementDynamic(selectorSearchFilterBar);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", searchBar);
		
		getDriver().waitForElement(selectorEmailInput);
		WebElement emailInput = getDriver().findElementDynamics(selectorEmailInput)
				.get(1);
		emailInput.sendKeys(email);
		
		getDriver().waitForElement(selectorApplyButton);
		getDriver().findElementDynamic(selectorApplyButton)
				.click();
		
		return getReservationsTableContentFromSearch(email, date);
	}
	
	private List<String> getReservationsTableContentFromSearch(String email_arg, String date_arg) {
		List<String> reservationsByDate = new ArrayList<String>();
		String date, id, email;
		
		getDriver().waitForElementVisible(selectorReservationsTable);
		WebElement reservationsTable = getDriver().findElementDynamic(selectorReservationsTable);
		List<String> reservations = JsoupHelper.findTexts(reservationsTable,
				selectorReservationsTableContent);
		
		for (int i = 0; i < reservations.size(); i += 3) {
			date = reservations.get(i);
			BFLogger.logDebug(i + " Date: " + date);
			email = reservations.get(i + 1);
			BFLogger.logDebug(i + " Email: " + email);
			id = reservations.get(i + 2);
			BFLogger.logDebug(i + " Id: " + id);
			
			try {
				date = Utils.changeDateFormat(date, "MMM dd, yyyy hh:mm a", "MM/dd/yyyy hh:mm a");
			} catch (ParseException e) {
				System.err.println("Date not formated properly at getReservationsShownByDate in ReservationsPage");
				e.printStackTrace();
			}
			
			if (email.equals(email_arg) && date.equals(date_arg)) {
				reservationsByDate.add(id);
			}
		}
		
		return reservationsByDate;
	}
	
	@Deprecated
	private List<String> getReservationsTableContent(String email_arg, String date_arg) {
		
		List<String> reservationsByDate = new ArrayList<String>();
		String date, id, email;
		
		getDriver().waitForElement(selectorReservationsTable);
		List<WebElement> reservations = getDriver().findElementDynamics(selectorReservationsTable);
		
		for (int i = 1; i <= reservations.size(); i++) {
			try {
				date = getDriver().findElementDynamic(findDataCell(i, 1))
						.getText();
				email = getDriver().findElementDynamic(findDataCell(i, 2))
						.getText();
				id = getDriver().findElementDynamic(findDataCell(i, 3))
						.getText();
				try {
					date = Utils.changeDateFormat(date, "MMM dd, yyyy hh:mm a", "MM/dd/yyyy hh:mm a");
				} catch (ParseException e) {
					System.err.println("Date not formated properly at getReservationsShownByDate in ReservationsPage");
					e.printStackTrace();
				}
				
				if (email.equals(email_arg) && date.equals(date_arg)) {
					reservationsByDate.add(id);
				}
				
			} catch (BFElementNotFoundException e) {
				break;
			}
			
		}
		
		return reservationsByDate;
	}
	
	@Deprecated
	private By findDataCell(int indexRow, int indexCol) {
		return By.xpath("//tbody[@class='td-data-table-body']/tr[" + indexRow + "]/td[" + indexCol + "]//span");
	}
	
	public String getReservationId(String email_arg, String date_arg) {
		
		String date, id, email;
		
		getDriver().waitUntilElementIsClickable(selectorSearchFilterBar);
		WebElement searchBar = getDriver().findElementDynamic(selectorSearchFilterBar);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", searchBar);
		
		getDriver().waitForElement(selectorEmailInput);
		WebElement emailInput = getDriver().findElementDynamics(selectorEmailInput)
				.get(1);
		emailInput.sendKeys(email_arg);
		
		getDriver().waitForElement(selectorApplyButton);
		getDriver().findElementDynamic(selectorApplyButton)
				.click();
		
		getDriver().waitForElementVisible(selectorReservationsTable);
		WebElement reservationsTable = getDriver().findElementDynamic(selectorReservationsTable);
		List<String> reservations = JsoupHelper.findTexts(reservationsTable, selectorReservationsTableContent);
		
		for (int i = 0; i < reservations.size(); i += 3) {
			date = reservations.get(i);
			
			email = reservations.get(i + 1);
			
			id = reservations.get(i + 2);
			
			try {
				date = Utils.changeDateFormat(date, "MMM dd, yyyy hh:mm a", "MM/dd/yyyy hh:mm a");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (email.equals(email_arg) && date.equals(date_arg)) {
				return id;
			}
		}
		return null;
	}
}