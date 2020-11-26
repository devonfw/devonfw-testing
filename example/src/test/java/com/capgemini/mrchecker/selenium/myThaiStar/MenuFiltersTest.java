package com.capgemini.mrchecker.selenium.myThaiStar;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.MTS;
import com.capgemini.mrchecker.selenium.pages.myThaiStar.MenuPage;
import com.capgemini.mrchecker.test.core.BaseTest;

@NotThreadSafe
@MTS
public class MenuFiltersTest extends BaseTest {
	
	private static MenuPage menuPage = new MenuPage();
	
	@BeforeAll
	public static void setUpBeforeClass() {
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		
	}
	
	@Override
	public void setUp() {
		menuPage.load();
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void Test_Search() {
		menuPage.enterSearchInput("garlic");
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", !menuPage.findElementInContainer("garlic"));
	}
	
	@Test
	public void Test_Sort() {
		menuPage.selectSortDropdown(0);
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("THAI THIGHS FISH/PRAWNS"));
	}
	
	@Test
	public void Test_Checkboxes() {
		menuPage.setRiceCheckbox();
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("THAI SPICY BASIL FRIED RICE"));
	}
	
	@Test
	public void Test_Sliders() throws AWTException {
		menuPage.slide(4);
		menuPage.clickApplyFiltersButton();
		
		assertTrue("Test failed: Results incorrect", menuPage.findElementInContainer("5XggQv/9k=\");"));
	}
}
