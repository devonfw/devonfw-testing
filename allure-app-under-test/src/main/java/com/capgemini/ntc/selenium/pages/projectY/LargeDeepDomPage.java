package com.capgemini.ntc.selenium.pages.projectY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class LargeDeepDomPage extends BasePage {
	
	private static final int	EXP_TBL_HDR_CELLS_CNT		= 50;
	private final static String	URL							= GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			+ PageSubURLsProjectYEnum.LARGE_DEEP_DOM.getValue();
	private static final By		mainHeaderLocator			= By.xpath("//div[@class='example']/h3");
	private static final By		noSiblingsHeaderLocator		= By.xpath("//div[@class='example']/h4[1]");
	private static final By		siblingsHeaderLocator		= By.xpath("//div[@class='example']/h4[2]");
	private static final By		descriptionParagraphLocator	= By.xpath("//div[@class='example']/p[1]");
	private static final By		noSiblingsParagraphLocator	= By.id("no-siblings");
	private static final By		siblingsDivLocator			= By.id("no-siblings");
	private static final By		largeTableLocator			= By.id("large-table");
	
	private static WebElement				mainHeader;
	private static WebElement				noSiblingsHeader;
	private static WebElement				siblingsHeader;
	private static WebElement				descriptionParagraph;
	private static WebElement				noSiblingsParagraph;
	private static WebElement				siblingsDiv;
	private static WebElement				largeTable;
	private static final List<WebElement>	siblings	= new ArrayList<>();
	private static final List<WebElement>	headerCells	= new ArrayList<>(EXP_TBL_HDR_CELLS_CNT);
	private static final List<WebElement>	tableRows	= new ArrayList<>(EXP_TBL_HDR_CELLS_CNT);
	
	@Override
	public void load() {
		getDriver().get(URL);
		BFLogger.logInfo("Page " + URL + " is loaded = " + isLoaded());
		
		findElements();
	}
	
	private void findElements() {
		mainHeader = getDriver().findElementQuietly(mainHeaderLocator);
		noSiblingsHeader = getDriver().findElementQuietly(noSiblingsHeaderLocator);
		siblingsHeader = getDriver().findElementQuietly(siblingsHeaderLocator);
		descriptionParagraph = getDriver().findElementQuietly(descriptionParagraphLocator);
		noSiblingsParagraph = getDriver().findElementQuietly(noSiblingsParagraphLocator);
		siblingsDiv = getDriver().findElementQuietly(siblingsDivLocator);
		largeTable = getDriver().findElementQuietly(largeTableLocator);
		
		int hdrCellIndex = 1;
		headerCells.clear();
		while (hdrCellIndex <= EXP_TBL_HDR_CELLS_CNT) {
			By byHdrCellLocator = By.id("header-" + String.valueOf(hdrCellIndex));
			WebElement hdrCell = getDriver().findElementQuietly(byHdrCellLocator);
			if (hdrCell != null)
				headerCells.add(hdrCell);
			hdrCellIndex++;
		}
		
		int tableRowIndex = 1;
		tableRows.clear();
		while (tableRowIndex <= EXP_TBL_HDR_CELLS_CNT) {
			By byRowLocator = By.className("row-" + String.valueOf(tableRowIndex));
			WebElement row = getDriver().findElementQuietly(byRowLocator);
			if (row != null)
				tableRows.add(row);
			tableRowIndex++;
		}
	}
	
	public List<String> getTableHeaderContent() {
		findElements();
		List<String> headers = new ArrayList<>();
		for (WebElement hdrCell : headerCells) {
			headers.add(hdrCell.getText());
		}
		return headers;
	}
	
	public String getCell(int rowIndex, int columnIndex) {
		findElements();
		
		if (rowIndex < 1 || rowIndex > tableRows.size()) {
			BFLogger.logError("No row with index " + rowIndex + " in Table. Available rows: 1-" + tableRows.size());
			return "";
		} else {
			
			WebElement cell = getDriver().findElementQuietly(tableRows.get(rowIndex - 1), By.className("column-" + String.valueOf(columnIndex)));
			return Optional.ofNullable(cell)
					.map(WebElement::getText)
					.orElse("");
		}
	}
	
	public boolean areElementsVisible() {
		return mainHeader.isDisplayed()
				&& noSiblingsHeader.isDisplayed()
				&& siblingsHeader.isDisplayed()
				&& descriptionParagraph.isDisplayed()
				&& noSiblingsParagraph.isDisplayed()
				&& siblingsDiv.isDisplayed()
				&& largeTable.isDisplayed();
	}
	
	public int getSiblingTiersCount() {
		By numberedSiblingsLocator = By.xpath("//div[contains(@class, 'parent') and contains(@class, 'large-12')]");
		return Optional.ofNullable(getDriver().findElementDynamics(numberedSiblingsLocator))
				.map(List::size)
				.orElse(0);
	}
	
	public String getSiblingDivText(int tier, int item) {
		return (item == 1)
				? getParentSiblingDivHeader(tier)
				: getNonParentSiblingDivText(tier, item);
	}
	
	public String getParentSiblingDivHeader(int tier) {
		String siblingText = getWholeParentSiblingDiv(tier);
		
		return ("".equals(siblingText))
				? ""
				: siblingText
						.substring(0, siblingText.indexOf('\n'));
	}
	
	public String getWholeParentSiblingDiv(int tier) {
		By siblingSelector = By.cssSelector(".parent.large-12.tier-" + String.valueOf(tier) + ".item-1");
		
		WebElement sibling = getDriver().findElementQuietly(siblingSelector);
		return Optional.ofNullable(sibling)
				.map(WebElement::getText)
				.orElse("");
	}
	
	private String getNonParentSiblingDivText(int tier, int item) {
		By siblingSelector = By.cssSelector(".large-12.tier-" + String.valueOf(tier) + ".item-" + String.valueOf(item));
		
		WebElement sibling = getDriver().findElementQuietly(siblingSelector);
		return Optional.ofNullable(sibling)
				.map(WebElement::getText)
				.orElse("");
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		
		return loadCompleted && isUrlAndPageTitleAsCurrentPage(URL);
	}
	
}
