package com.capgemini.mrchecker.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

@Tag("Unit")
@Tag("Done")
@Feature("StringTest")
public class StringTest extends BaseTest {
	
	@RegisterExtension
	public static final TestWatcher allureAttachmentTestWatcher = new TestWatcher() {
		
		@Override
		public void testFailed(ExtensionContext context, Throwable cause) {
			attachToAllureWhenFailed("This text will be attached to Allure when test fails");
		}
		
		@Override
		public void testSuccessful(ExtensionContext context) {
			attachToAllureWhenPassed("This text will be attached to Allure when test passes");
		}
		
		@Attachment
		private byte[] attachToAllureWhenFailed(String attachment) {
			return attachment.getBytes();
		}
		
		@Attachment
		private byte[] attachToAllureWhenPassed(String attachment) {
			return attachment.getBytes();
		}
		
	};
	
	@Test
	@Description("Test case description 1")
	public void shouldConcatenateTwoStrings() {
		String str1 = "a";
		String str2 = "b";
		
		BFLogger.logDebug("Concatenating: " + str1 + " and " + str2);
		String concatenated = concatStrings(str1, str2);
		
		assertStringEqual(concatenated, "ab");
	}
	
	@Test
	@Description("Test case description 2")
	public void shouldReturnStringLength() {
		String str1 = "a";
		
		BFLogger.logInfo("Getting length of: " + str1);
		assertStringLen(str1, 1);
	}
	
	@Test
	@Description("Test case description 3")
	public void shouldStringLengthFail() {
		String str1 = "a";
		
		BFLogger.logDebug("Getting length of: " + str1);
		assertStringLen(str1, 2);
	}
	
	@Step("Get string length of: {str}")
	private static int getStringLength(String str) {
		return str.length();
	}
	
	@Step("Concatenate two strings: {str1} and {str2}")
	private static String concatStrings(String str1, String str2) {
		return str1.concat(str2);
	}
	
	@Step("Assert string \"{str}\" has length: {len}")
	private static void assertStringLen(String str, int len) {
		assertThat(getStringLength(str), is(equalTo(len)));
	}
	
	@Step("Assert strings \"{str1}\" and \"{str2}\" are equal")
	private static void assertStringEqual(String str1, String str2) {
		assertThat(str1, is(equalTo(str2)));
	}
}
