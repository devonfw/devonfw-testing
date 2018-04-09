package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.selenium.pages.projectY.FloatingMenuPage;
import com.capgemini.ntc.test.core.BaseTest;

public final class FloatingMenuTest extends BaseTest {
	
	private static final String				FLOATING_MENU_URL	= GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			.concat(PageSubURLsProjectYEnum.FLOATING_MENU.getValue());
	private final static FloatingMenuPage	floatingMenuPage	= new FloatingMenuPage();
	
	@Override
	public void setUp() {
		floatingMenuPage.load();
	}
	
	@Test
	public void testVisibilityWhileScrolling() {
		assertTrue(floatingMenuPage.isMenuDisplayed());
		int scrollPosition = 0;
		int singleScrollVal = 250;
		while (scrollPosition < floatingMenuPage.getPageHeight()) {
			floatingMenuPage.scrollPageDown(singleScrollVal);
			assertTrue(floatingMenuPage.isMenuDisplayed());
			scrollPosition += singleScrollVal;
		}
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testHomeLink() {
		floatingMenuPage.clickHomeLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#home"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
	}
	
	@Test
	public void testNewsLink() {
		floatingMenuPage.clickNewsLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#news"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
	}
	
	@Test
	public void testContactLink() {
		floatingMenuPage.clickContactLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#contact"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
	}
	
	@Test
	public void testAboutLink() {
		floatingMenuPage.clickAboutLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#about"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
		
	}
	
	@Test
	public void testHomeLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight() - 100);
		floatingMenuPage.clickHomeLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#home"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
		
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight() - 100);
	}
	
	@Test
	public void testNewsLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight());
		floatingMenuPage.clickNewsLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#news"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
		
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testContactLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight() - 200);
		floatingMenuPage.clickContactLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#contact"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight() - 200);
	}
	
	@Test
	public void testAboutLinkWithScrolling() {
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight());
		floatingMenuPage.clickAboutLink();
		assertEquals(FloatingMenuTest.FLOATING_MENU_URL
				.concat("#about"),
				FloatingMenuPage.getDriver()
						.getCurrentUrl());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testMainTextVisibilityWithScrolling() {
		assertTrue(floatingMenuPage.isPageTextDisplayed());
		floatingMenuPage.scrollPageDown(floatingMenuPage.getPageHeight());
		assertTrue(floatingMenuPage.isPageTextDisplayed());
		floatingMenuPage.scrollPageUp(floatingMenuPage.getPageHeight());
	}
	
	@Test
	public void testParagraphsCount() {
		assertEquals(10, floatingMenuPage.getParagraphsCount());
	}
	
	@Test
	public void testGithubLink() {
		floatingMenuPage.clickGithubLink();
		
		assertEquals("https://github.com/tourdedave/the-internet", FloatingMenuPage.getDriver()
				.getCurrentUrl());
		
		FloatingMenuPage.getDriver()
				.navigate()
				.back();
	}
	
	@Override
	public void tearDown() {
	}
}
