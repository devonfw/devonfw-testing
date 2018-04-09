package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.NestedFramesPage;
import com.capgemini.ntc.selenium.pages.projectY.NestedFramesPage.Frame;
import com.capgemini.ntc.test.core.BaseTest;

public class NestedFramesTest extends BaseTest {
	
	private final static NestedFramesPage nestedFramesPage = new NestedFramesPage();
	
	@Override
	public void setUp() {
		nestedFramesPage.load();
	}
	
	@Test
	public void testFramesVisibility() {
		assertTrue(nestedFramesPage.areFramesDisplayed());
	}
	
	@Test
	public void testLeftFrameContent() {
		assertEquals("LEFT", nestedFramesPage.getFrameContent(Frame.LEFT));
	}
	
	@Test
	public void testMiddleFrameContent() {
		assertEquals("MIDDLE", nestedFramesPage.getFrameContent(Frame.MIDDLE));
	}
	
	@Test
	public void testRightFrameContent() {
		assertEquals("RIGHT", nestedFramesPage.getFrameContent(Frame.RIGHT));
	}
	
	@Test
	public void testBottomFrameContent() {
		assertEquals("BOTTOM", nestedFramesPage.getFrameContent(Frame.BOTTOM));
	}
	
	@Override
	public void tearDown() {
	}
	
}
