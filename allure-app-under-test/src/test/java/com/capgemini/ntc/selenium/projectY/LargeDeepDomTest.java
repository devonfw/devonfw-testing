package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.LargeDeepDomPage;
import com.capgemini.ntc.test.core.BaseTest;

public class LargeDeepDomTest extends BaseTest {
	
	private final static int				EXPECTED_TIER_COUNT	= 50;
	private final static int				EXPECTED_ITEM_COUNT	= 3;
	private final static LargeDeepDomPage	largeDeepDomPage	= new LargeDeepDomPage();
	
	@Override
	public void setUp() {
		largeDeepDomPage.load();
	}
	
	@Test
	public void testSiblingsCount() {
		assertEquals(51, largeDeepDomPage.getSiblingTiersCount());
	}
	
	@Test
	public void testSiblingsContent() {
		for (int tier = 1; tier < EXPECTED_TIER_COUNT; tier++)
			for (int item = 1; item < EXPECTED_ITEM_COUNT; item++)
				assertEquals(String.valueOf(tier) + "." + String.valueOf(item), largeDeepDomPage.getSiblingDivText(tier, item));
	}
	
	@Test
	public void testSiblingsOrder() {
		for (int tier = 1; tier < EXPECTED_TIER_COUNT; tier++) {
			String siblingDivText = largeDeepDomPage.getWholeParentSiblingDiv(tier);
			
			for (int innerTier = tier; innerTier < EXPECTED_TIER_COUNT; innerTier++) {
				assertTrue(siblingDivText.contains(String.valueOf(tier) + "."));
			}
		}
	}
	
	@Test
	public void testTableHeader() {
		List<String> headers = largeDeepDomPage.getTableHeaderContent();
		assertEquals(EXPECTED_TIER_COUNT, headers.size());
		
		for (int expHdrItem = 1; expHdrItem <= EXPECTED_TIER_COUNT; expHdrItem++) {
			assertEquals(String.valueOf(expHdrItem), headers.get(expHdrItem - 1));
		}
	}
	
	@Test
	public void testTableContent() {
		assertEquals("2.44", largeDeepDomPage.getCell(2, 44));
		assertEquals("", largeDeepDomPage.getCell(1, 51));
		assertEquals("", largeDeepDomPage.getCell(17, 0));
		assertEquals("17.1", largeDeepDomPage.getCell(17, 1));
		assertEquals("33.50", largeDeepDomPage.getCell(33, 50));
	}
	
	@Test
	public void testVisibility() {
		assertTrue(largeDeepDomPage.areElementsVisible());
	}
	
	@Override
	public void tearDown() {
	}
}
