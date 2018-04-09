package com.capgemini.ntc.selenium.pages.projectY;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class NestedFramesPage extends BasePage {
	
	public enum Frame {
		TOP,
		LEFT,
		MIDDLE,
		RIGHT,
		BOTTOM;
		
		public String getName() {
			switch (this) {
				case TOP:
					return "frame-top";
				case LEFT:
					return "frame-left";
				case MIDDLE:
					return "frame-middle";
				case RIGHT:
					return "frame-right";
				case BOTTOM:
					return "frame-bottom";
				default:
					return "";
			}
		}
	}
	
	private static final String URL = GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			+ PageSubURLsProjectYEnum.NESTED_FRAMES.getValue();
	
	private static WebElement	topFrame;
	private static WebElement	leftFrame;
	private static WebElement	middleFrame;
	private static WebElement	rightFrame;
	private static WebElement	bottomFrame;
	
	private static final By	topFrameLocalizer		= By.name(Frame.TOP.getName());
	private static final By	leftFrameLocalizer		= By.name(Frame.LEFT.getName());
	private static final By	middleFrameLocalizer	= By.name(Frame.MIDDLE.getName());
	private static final By	rightFrameLocalizer		= By.name(Frame.RIGHT.getName());
	private static final By	bottomFrameLocalizer	= By.name(Frame.BOTTOM.getName());
	
	@Override
	public void load() {
		BFLogger.logInfo("Page " + URL + " is loaded = " + isLoaded());
		getDriver().get(URL);
		findElements();
		BFLogger.logInfo("Page " + URL + " is loaded = " + isLoaded());
	}
	
	public boolean areFramesDisplayed() {
		boolean leftFrameDisplayed = false;
		boolean middleFrameDisplayed = false;
		boolean rightFrameDisplayed = false;
		
		if (topFrame != null) {
			getDriver().switchTo()
					.frame(Frame.TOP.getName());
			
			leftFrameDisplayed = leftFrame.isDisplayed();
			middleFrameDisplayed = middleFrame.isDisplayed();
			rightFrameDisplayed = rightFrame.isDisplayed();
			
			getDriver().switchTo()
					.defaultContent();
		}
		
		return leftFrameDisplayed
				&& middleFrameDisplayed
				&& rightFrameDisplayed
				&& bottomFrame.isDisplayed();
	}
	
	private void findElements() {
		topFrame = getDriver().findElementQuietly(topFrameLocalizer);
		bottomFrame = getDriver().findElementQuietly(bottomFrameLocalizer);
		
		if (topFrame != null) {
			getDriver().switchTo()
					.frame(Frame.TOP.getName());
			
			leftFrame = getDriver().findElementQuietly(leftFrameLocalizer);
			middleFrame = getDriver().findElementQuietly(middleFrameLocalizer);
			rightFrame = getDriver().findElementQuietly(rightFrameLocalizer);
			
			getDriver().switchTo()
					.defaultContent();
		}
	}
	
	public String getFrameContent(Frame frame) {
		
		if (frame.equals(Frame.TOP) || frame.equals(Frame.BOTTOM))
			return getOuterFrameContent(frame);
		else
			return getInnerFrameContent(frame);
	}
	
	private String getOuterFrameContent(Frame frame) {
		getDriver().switchTo()
				.frame(frame.getName());
		WebElement body = getDriver().findElementQuietly(By.tagName("body"));
		String content = Optional.ofNullable(body)
				.map(WebElement::getText)
				.orElse("");
		
		getDriver().switchTo()
				.defaultContent();
		return content;
	}
	
	private String getInnerFrameContent(Frame frame) {
		getDriver().switchTo()
				.frame(Frame.TOP.getName());
		return getOuterFrameContent(frame);
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		
		return loadCompleted && isUrlAndPageTitleAsCurrentPage(URL);
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
}
