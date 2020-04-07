package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DynamicLoadingPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class DynamicLoadingTest extends TheInternetBaseTest {
	
	private static final int	EXAMPLE_WAITING_TIME	= 30;
	private static final String	EXAMPLE_TEXT			= "Hello World!";
	
	private static DynamicLoadingPage dynamicLoadingPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dynamicLoadingPage = shouldTheInternetPageBeOpened().clickDynamicLoadingLink();
	}
	
	@Override
	public void setUp() {
		
		logStep("Verify if Dynamic Loading page is opened");
		assertTrue("Unable to open Dynamic Loading page", dynamicLoadingPage.isLoaded());
		
		logStep("Verify if dynamic loading message is visible");
		assertTrue("Dynamic loading message is invisible", dynamicLoadingPage.isDynamicLoadingMessageVisible());
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleOne() {
		logStep("Click Example 1 link");
		dynamicLoadingPage.clickExampleOneLink();
		
		logStep("Verify if Example 1 link opened content");
		assertTrue("Fail to load Example 1 content", dynamicLoadingPage.isStartButtonVisible());
		
		logStep("Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		logStep("Verify if loading bar is displayed");
		assertTrue("Fail to display loading bar", dynamicLoadingPage.isLoadingBarVisible());
		
		logStep("Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleOneDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleTwo() {
		logStep("Click Example 2 link");
		dynamicLoadingPage.clickExampleTwoLink();
		
		logStep("Verify if Example 2 link opened content");
		assertTrue("Fail to load Example 2 content", dynamicLoadingPage.isStartButtonVisible());
		
		logStep("Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		logStep("Verify if loading bar is displayed");
		assertTrue("Fail to display loading bar", dynamicLoadingPage.isLoadingBarVisible());
		
		logStep("Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleTwoDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Override
	public void tearDown() {
		logStep("Click back to reset Dynamic Loading page");
		BasePage.navigateBack();
	}
	
}
