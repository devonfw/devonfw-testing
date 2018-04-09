package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.IFramePage;
import com.capgemini.ntc.test.core.BaseTest;

public class IFrameTest extends BaseTest {
	
	private final static IFramePage iFramePage = new IFramePage();
	
	private final static String[] invocationTestData = {
					"Litwo, Ojczyzno moja\n",
					"Tys jest jak zdrowie"
	};
	
	@Override
	public void setUp() {
		iFramePage.load();
	}
	
	@Test
	public void testHeaderVisibility() {
		assertTrue(iFramePage.getHeaderVisibility());
	}
	
	@Test
	public void testEditorVisibility() {
		assertTrue(iFramePage.getEditorVisibility());
	}
	
	@Test
	public void testMenuVisibility() {
		assertTrue(iFramePage.getMenuVisibility());
	}
	
	@Test
	public void testToolbarVisibility() {
		assertTrue(iFramePage.getToolbarVisibility());
	}
	
	@Test
	public void testTypingBoldText() {
		iFramePage.createNewDocument();
		iFramePage.clickBoldButton();
		
		for (String anInvocationTestData : invocationTestData)
			iFramePage.addTextToEditor(anInvocationTestData);
		
		assertEquals("p » strong", iFramePage.getStatusbarText());
		assertEquals(invocationTestData[0].concat(invocationTestData[1]), iFramePage.getTextFromEditor());
		iFramePage.findElements();
		iFramePage.clearEditor();
	}
	
	@Test
	public void testTyping() {
		iFramePage.createNewDocument();
		typeInvocation();
		
		assertEquals(invocationTestData[0].concat(invocationTestData[1]), iFramePage.getTextFromEditor());
		iFramePage.findElements();
		iFramePage.clearEditor();
	}
	
	@Test
	public void testNewDocument() {
		iFramePage.createNewDocument();
		iFramePage.clickBoldButton();
		typeInvocation();
		iFramePage.createNewDocument();
		assertEquals("", iFramePage.getTextFromEditor());
		assertFalse(iFramePage.isBoldButtonPressed());
	}
	
	@Test
	public void testBoldingWithShortcut() {
		iFramePage.createNewDocument();
		typeInvocation();
		iFramePage.changeWholeTextBold();
		assertEquals("p » strong", iFramePage.getStatusbarText());
		iFramePage.changeWholeTextBold();
		assertEquals("p", iFramePage.getStatusbarText());
		iFramePage.clearEditor();
	}
	
	@Test
	public void testItalicingWithShortcut() {
		iFramePage.createNewDocument();
		typeInvocation();
		iFramePage.changeWholeTextItalic();
		assertEquals("p » em", iFramePage.getStatusbarText());
		iFramePage.changeWholeTextItalic();
		assertEquals("p", iFramePage.getStatusbarText());
		iFramePage.clearEditor();
	}
	
	@Test
	public void testFontAttributesChangeWithShortcut() {
		iFramePage.createNewDocument();
		typeInvocation();
		iFramePage.changeWholeTextItalic();
		iFramePage.changeWholeTextBold();
		iFramePage.changeWholeTextBoldItalicUnderline();
		assertEquals("p » span", iFramePage.getStatusbarText());
		iFramePage.clearEditor();
	}
	
	@Override
	public void tearDown() {
	}
	
	private void typeInvocation() {
		iFramePage.putTextToEditor(invocationTestData[0]);
		
		for (int i = 1; i < invocationTestData.length; i++)
			iFramePage.addTextToEditor(invocationTestData[i]);
	}
	
}
