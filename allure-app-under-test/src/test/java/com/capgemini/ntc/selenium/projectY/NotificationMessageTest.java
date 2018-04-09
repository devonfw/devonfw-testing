package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.ntc.selenium.pages.projectY.NotificationMessagePage;
import com.capgemini.ntc.test.core.BaseTest;

public class NotificationMessageTest extends BaseTest {
	
	private final static NotificationMessagePage notificationMsgPage = new NotificationMessagePage();
	
	@Override
	public void setUp() {
		notificationMsgPage.load();
	}
	
	@Test
	public void testMessageChange() {
		final String unsuccessfulMsg = "Action unsuccesful, please try again";// Page has a typo - should be
		// 'unsuccessful'
		final String successfulMsg = "Action successful";
		
		int clickCount = 7;
		
		while (clickCount >= 0) {
			
			notificationMsgPage.clickLoadMessageLink();
			notificationMsgPage.findElements();
			
			assertTrue(notificationMsgPage.getFlashMessageText()
					.startsWith(unsuccessfulMsg)
					|| notificationMsgPage.getFlashMessageText()
							.startsWith(successfulMsg));
			
			clickCount--;
		}
	}
	
	@Override
	public void tearDown() {
	}
	
}
