package com.capgemini.mrchecker.selenium.pages.myThaiStar;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsMyThaiStar;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnumMyThaiStar;

public class HomePage extends BasePage {
	
	private static final By selectorLoginButton = By.name("login");
	
	private static final By selectorAccountButton = By.name("account");
	
	private static final By selectorLoginLabel = By.xpath("//span[@data-name='userNameLogged']");
	
	private static final By selectorMenuTab = By.xpath("//a[@routerlink='/menu']");
	
	private static final By selectorBookTableTab = By.name("buttons.bookTableNavigate");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.equals(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.HOME.getValue());
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.MY_THAI_STAR_URL.getValue() + PageSubURLsMyThaiStar.HOME.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return PageTitlesEnumMyThaiStar.MAIN_PAGE.toString();
	}
	
	public LoginPage clickLogInButton() {
		getDriver().findElementDynamic(selectorLoginButton)
				.click();
		
		return new LoginPage();
	}
	
	public void clickLogOutButton() {
		getDriver().findElementDynamic(selectorAccountButton)
				.click();
		
		String clickLogOutScript = "var we = document.getElementsByClassName(\"mat-menu-item\"); we[we.length-1].click();";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(clickLogOutScript);
	}
	
	public boolean isUserLogged(String username) {
		return getUserLoggedName().equals(username);
	}
	
	public boolean isUserLogged() {
		return !getUserLoggedName().isEmpty();
	}
	
	private String getUserLoggedName() {
		WebElement accountLabel = getDriver().findElementQuietly(selectorLoginLabel);
		
		return accountLabel != null ? accountLabel.getText() : StringUtils.EMPTY;
	}
	
	public BookTablePage clickBookTableTab() {
		getDriver().findElementDynamic(selectorBookTableTab)
				.click();
		
		return new BookTablePage();
	}
}
