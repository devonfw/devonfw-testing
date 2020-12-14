package com.capgemini.mrchecker.common.allure.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class StepLogger {
	@Step("{step}")
	public static void step(String step) {
		BFLogger.logInfo(step);
	}
	
	@Step("[INFO] {step}")
	public static void stepInfo(String step) {
		BFLogger.logInfo(step);
	}
	
	@Step("--Screenshot--")
	public static void makeScreenShot() {
		BFLogger.logInfo("*makeScreenShot*");
		if (System.getProperty("screenshots", "true")
				.equalsIgnoreCase("false")) {
			BFLogger.logInfo("screenshot disabled");
		} else {
			makeScreenShotAlways("Screenshot");
		}
	}
	
	@Attachment(value = "{attachmentName}", type = "image/png")
	private static byte[] makeScreenShotAlways(String attachmentName) {
		BFLogger.logDebug("BasePageGUI.makeScreenShotAlways attachmentName=" + attachmentName);
		byte[] screenshot = "Attachment Content Empty, can't create screenshot".getBytes();
		try {
			screenshot = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.BYTES);
		} catch (Exception e) {
			BFLogger.logDebug("[captureScreenShot] Unable to take screenshot. Error:\n" + e);
		}
		
		return screenshot;
	}
	
}
