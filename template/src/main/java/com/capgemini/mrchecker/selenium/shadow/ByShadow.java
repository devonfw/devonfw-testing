package com.capgemini.mrchecker.selenium.shadow;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.WrapsDriver;

public class ByShadow {
	public static By cssSelector(String selector) {
		return new ByShadowCss(selector);
	}
	
	public static class ByShadowCss extends By implements Serializable {
		private static final long	serialVersionUID	= -1230258723099459239L;
		private final String		cssSelector;
		
		public ByShadowCss(String cssSelector) {
			if (cssSelector.equals(null))
				throw new IllegalArgumentException("Selector can not be null");
			this.cssSelector = cssSelector;
			
		}
		
		@Override
		public WebElement findElement(SearchContext context) {
			if (context instanceof FindsByCssSelector) {
				JavascriptExecutor jsExecutor;
				if (context instanceof JavascriptExecutor) {
					jsExecutor = (JavascriptExecutor) context;
				} else {
					jsExecutor = (JavascriptExecutor) ((WrapsDriver) context).getWrappedDriver();
				}
				String[] subSelectors = cssSelector.split(">>>");
				FindsByCssSelector currentContext = (FindsByCssSelector) context;
				WebElement result = null;
				for (String subSelector : subSelectors) {
					result = currentContext.findElementByCssSelector(subSelector);
					currentContext = (FindsByCssSelector) jsExecutor.executeScript("return arguments[0].shadowRoot", result);
				}
				return result;
			}
			
			throw new WebDriverException(
					"Element '" + cssSelector + "' was not found");
		}
		
		@Override
		public List<WebElement> findElements(SearchContext context) {
			if (context instanceof FindsByCssSelector) {
				JavascriptExecutor jsExecutor;
				if (context instanceof JavascriptExecutor) {
					jsExecutor = (JavascriptExecutor) context;
				} else {
					jsExecutor = (JavascriptExecutor) ((WrapsDriver) context).getWrappedDriver();
				}
				String[] subSelectors = cssSelector.split(">>>");
				FindsByCssSelector currentContext = (FindsByCssSelector) context;
				for (int i = 0; i < subSelectors.length - 1; i++) {
					WebElement nextRoot = currentContext.findElementByCssSelector(subSelectors[i]);
					currentContext = (FindsByCssSelector) jsExecutor.executeScript("return arguments[0].shadowRoot", nextRoot);
				}
				return currentContext.findElementsByCssSelector(subSelectors[subSelectors.length - 1]);
			}
			
			throw new WebDriverException(
					"Driver does not support finding elements by selector: " + cssSelector);
		}
		
		@Override
		public String toString() {
			return "By.cssSelector: " + cssSelector;
		}
	}
	
}
