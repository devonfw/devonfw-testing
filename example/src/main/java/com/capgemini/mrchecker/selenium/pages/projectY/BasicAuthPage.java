package com.capgemini.mrchecker.selenium.pages.projectY;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class BasicAuthPage extends BasePage {
	
	private static final By selectorTextMessage = By.cssSelector("#content > div > p");
	
	public BasicAuthPage() {
		
	}
	
	public BasicAuthPage(String login, String password) {
		this.enterLoginAndPasswordByUrl(login, password);
	}
	
	@Override
	public boolean isLoaded() {
		return true;
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Authenticates user passing credentials into URL.
	 *
	 * @param login
	 *            User's login
	 * @param password
	 *            User's password
	 */
	private void enterLoginAndPasswordByUrl(String login, String password) {
		getDriver().get("http://" + login + ":" + password + "@" + "the-internet.herokuapp.com/" + PageSubURLsProjectYEnum.BASIC_AUTH.getValue());
	}
	
	/**
	 * Authenticates user using standard simple authentication popup.
	 *
	 * @param login
	 *            User's login
	 * @param password
	 *            User's password
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void enterLoginAndPassword(String login, String password) throws AWTException, InterruptedException {
		Robot rb = new Robot();
		
		Thread.sleep(2000);
		
		StringSelection username = new StringSelection(login);
		Toolkit.getDefaultToolkit()
				.getSystemClipboard()
				.setContents(username, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		
		StringSelection pwd = new StringSelection(password);
		Toolkit.getDefaultToolkit()
				.getSystemClipboard()
				.setContents(pwd, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		
	}
	
	/**
	 * Returns message displayed by system after user's log in.
	 *
	 * @return String object representing message displayed by system after user's log in
	 */
	public String getMessageValue() {
		return getDriver().findElementDynamic(selectorTextMessage)
				.getText();
	}
}
