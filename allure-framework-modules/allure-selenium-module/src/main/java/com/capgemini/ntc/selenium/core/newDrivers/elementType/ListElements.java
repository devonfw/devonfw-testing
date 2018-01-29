package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;

public class ListElements extends BasicElement implements IBasicElement {
	
	private By cssSelector;
	
	public ListElements(By cssSelector) {
		super(ElementType.LIST, cssSelector);
		this.cssSelector = cssSelector;
	}
	
	public Integer getSize() {
		return BasePage.getDriver()
				.findElementDynamics(this.cssSelector)
				.size();
		
	}
	
	public List<WebElement> getList() {
		return BasePage.getDriver()
				.findElementDynamics(this.cssSelector);
	}
	
	public List<String> getTextList() {
		List<String> list = new ArrayList<String>();
		for (WebElement element : this.getList()) {
			list.add(element.getText());
		}
		return list;
	}
}
