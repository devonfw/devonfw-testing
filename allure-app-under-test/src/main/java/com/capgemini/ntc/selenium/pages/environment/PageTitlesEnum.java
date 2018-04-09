package com.capgemini.ntc.selenium.pages.environment;

public enum PageTitlesEnum {
	LOGIN_PAGE("Log In to Bank.com"),
	REGISTRATION("Registration | Demoqa"),
	FRAMEANDWINDOWS("Frames and windows | Demoqa"),
	MAIN_PAGE("Demoqa | Just another WordPress site"),
	CREATE_USERNAME_PAGE(""),
	MAIN_PAGE_CLASSIC(""),
	FULL_VIEW_TAB(""),
	TYPOS("The Internet"),
	NOTIFICATIONS("The Internet"),
	NESTED_FRAMES("http://the-internet.herokuapp.com/nested_frames"),
	LARGE_DEEP_DOM("The Internet"),
	IFRAME("The Internet"),
	FLOATING_MENU("The Internet");
	
	private String value;
	
	PageTitlesEnum(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}