package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.TyposPage;
import com.capgemini.ntc.test.core.BaseTest;

public class TyposTest extends BaseTest {
	
	private final static TyposPage typosPage = new TyposPage();
	
	@Override
	public void setUp() {
		typosPage.load();
	}
	
	@Test
	public void testHeader() {
		assertEquals("Typos", typosPage.getHeaderText());
	}
	
	@Test
	public void testFirstLine() {
		int reloadCount = 7;
		
		while (reloadCount > -1) {
			assertEquals("This example demonstrates a typo being introduced. It does it randomly on each page load.", typosPage.getTextLine(0));
			
			typosPage.reload();
			typosPage.findElements();
			reloadCount--;
		}
	}
	
	@Test
	public void testSecondLine() {
		int reloadCount = 5;
		
		while (reloadCount > -1) {
			assertTrue(typosPage.getTextLine(1)
					.equals("Sometimes you'll see a typo, other times you won't.")
					|| typosPage.getTextLine(1)
							.equals("Sometimes you'll see a typo, other times you won,t."));
			// assertion should use 'anyOf' from Hamcrest library, but it doesn't work in Java 8
			
			typosPage.reload();
			typosPage.findElements();
			reloadCount--;
		}
	}
	
	@Override
	public void tearDown() {
	}
	
}
