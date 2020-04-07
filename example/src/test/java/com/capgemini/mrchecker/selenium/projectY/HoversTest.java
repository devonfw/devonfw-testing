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
import com.capgemini.mrchecker.selenium.pages.projectY.HoversPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class HoversTest extends TheInternetBaseTest {
	
	private static HoversPage	hoversPage;
	private final String		names[]	= { "name: user1", "name: user2", "name: user3" };
	
	@BeforeClass
	public static void setUpBeforeClass() {
		hoversPage = shouldTheInternetPageBeOpened().clickHoversLink();
		
		logStep("Verify if Hovers page is opened");
		assertTrue("Unable to open Hovers page", hoversPage.isLoaded());
	}
	
	@Test
	public void shouldProperInformationBeDisplayedWhenMousePointerHoveredOverRandomElement() {
		logStep("Hover mouse pointer over every element");
		
		for (int i = 0; i < names.length; i++) {
			hoversPage.hoverOverAvatar(i);
			assertEquals("Picture's information is different than expected", names[i],
					hoversPage.getAvatarsInformation(i));
		}
	}
	
}
