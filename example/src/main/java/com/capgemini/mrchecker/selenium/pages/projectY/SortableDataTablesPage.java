package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.mrchecker.selenium.jsoupHelper.JsoupHelper;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SortableDataTablesPage extends BasePage {

    private static final By selectorTable = By.cssSelector("table.tablesorter");
    private static final By selectorHeader = By.cssSelector("th");

    @Override
    public boolean isLoaded() {
        getDriver().waitForPageLoaded();
        return getDriver().getCurrentUrl()
                .contains(PageSubURLsProjectYEnum.SORTABLE_DATA_TABLES.getValue());
    }

    @Override
    public void load() {
        BFLogger.logDebug("Load 'Data Tables' page.");
        getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.SORTABLE_DATA_TABLES.getValue());
        getDriver().waitForPageLoaded();
    }

    @Override
    public String pageTitle() {
        return getActualPageTitle();
    }

    /**
     * Sorts data in given column using ascending order.
     *
     * @param columnNumber The number of column where data should be sorted
     * @param tableNumber  The number of table where data should be sorted
     */
    public void sortColumnAscending(int columnNumber, int tableNumber) {
        WebElement header = this.getTableHeaders(columnNumber, tableNumber);
        String className = header.getAttribute("class");
        if (className.contains("headerSortUp") || !className.contains("headerSortDown")) {
            header.click();
        }
    }

    /**
     * Sorts data in given column using descending order.
     *
     * @param columnNumber The number of column where data should be sorted
     * @param tableNumber  The number of table where data should be sorted
     */
    public void sortColumnDescending(int columnNumber, int tableNumber) {
        WebElement header = this.getTableHeaders(columnNumber, tableNumber);
        String className = header.getAttribute("class");
        if (!className.contains("headerSortUp")) {
            header.click();
            if (!className.contains("headerSortDown")) {
                header.click();
            }
        }
    }

    /**
     * Return given column values from chosen table.
     *
     * @param columnNumber The number of column where data should be retrieved from
     * @param tableNumber  The number of table where data should be retrieved from
     * @return list of values from given column
     */
    public List<String> getColumnValues(int columnNumber, int tableNumber) {
        WebElement table = getTable(tableNumber);
        return JsoupHelper.findTexts(table, By.cssSelector("tr > td:nth-child(" + (columnNumber + 1) + ")"));
    }

    /**
     * Returns column's class name.
     *
     * @param columnNumber The number of column to get class number
     * @param tableNumber  The number of table to get column class name
     * @return String object representing column's class name
     */
    public String readColumnClass(int columnNumber, int tableNumber) {
        return this.getTableHeaders(columnNumber, tableNumber)
                .getAttribute("class");
    }

    private WebElement getTable(int tableNumber) {
        return new ListElements(selectorTable).getList()
                .get(tableNumber);
    }

    private WebElement getTableHeaders(int columnNumber, int tableNumber) {
        return getTable(tableNumber).findElements(selectorHeader)
                .get(columnNumber);
    }

}
