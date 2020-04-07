package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.SortableDataTablesPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category({TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class})
public class SortableDataTablesTest extends TheInternetBaseTest {

    private static SortableDataTablesPage sortableDataTablesPage;

    private List<String> actualColumnValues;
    private List<String> expectedColumnValues;

    @BeforeClass
    public static void setUpBeforeClass() {
        logStep("Open the Url http://the-internet.herokuapp.com/");
        theInternetPage = new TheInternetPage();
        theInternetPage.load();

        logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
        assertTrue("Unable to load The Internet Page", theInternetPage.isLoaded());
    }

    @Override
    public void setUp() {
        logStep("Click subpage link");
        sortableDataTablesPage = theInternetPage.clickSortableDataTablesLink();

        logStep("Verify if subpage is opened");
        assertTrue("Unable to open Sortable Data Tables page", sortableDataTablesPage.isLoaded());
    }

    @Test
    public void shouldLastNameColumnBeOrderedAscendingAfterSort() {
        int columnNumber = 0;
        int tableNumber = new Random().nextInt(2);

        logStep("Sort 'Last Name' column");
        sortableDataTablesPage.sortColumnAscending(columnNumber, tableNumber);
        assertTrue("Unable to set ascending order for 'Last Name' column",
                sortableDataTablesPage.readColumnClass(columnNumber, tableNumber)
                        .contains("headerSortDown"));

        logStep("Verify data order for 'Last Name' column");
        actualColumnValues = sortableDataTablesPage.getColumnValues(columnNumber, tableNumber);
        expectedColumnValues = new ArrayList<>(actualColumnValues);
        Collections.sort(expectedColumnValues);
        assertEquals("'Last Name' column is not sorted by ascending order",
                expectedColumnValues, actualColumnValues);
    }

    @Test
    public void shouldFirstNameColumnBeOrderedDescendingAfterSort() {
        int columnNumber = 1;
        int tableNumber = new Random().nextInt(2);

        logStep("Sort 'First Name' column");
        sortableDataTablesPage.sortColumnDescending(columnNumber, tableNumber);
        assertTrue("Unable to set descending order for 'First Name' column",
                sortableDataTablesPage.readColumnClass(columnNumber, tableNumber)
                        .contains("headerSortUp"));

        logStep("Verify data order for 'First Name' column");
        actualColumnValues = sortableDataTablesPage.getColumnValues(columnNumber, tableNumber);
        expectedColumnValues = new ArrayList<>(actualColumnValues);
        Collections.sort(expectedColumnValues);
        Collections.reverse(expectedColumnValues);
        assertEquals("'First Name' column is not sorted by descending order",
                expectedColumnValues, actualColumnValues);
    }

}
