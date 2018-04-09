package com.capgemini.ntc.selenium.pages.environment;

public enum PageSubURLsProjectYEnum {
	
	BASIC_AUTH("basic_auth"),
	NEW_WINDOW("windows/new"),
	WINDOW("windows"),
	CHECKBOX("checkboxes"),
	CONTEXT_MENU("context_menu"),
	KEY_PRESS("key_presses"),
	TYPOS("typos"),
	NOTIFICATIONS("notification_message_rendered"),
	NESTED_FRAMES("nested_frames"),
	LARGE_DEEP_DOM("large"),
	IFRAME("iframe"),
	FLOATING_MENU("floating_menu"),
	DYNAMIC_CONTENT("dynamic_content"),
	HOVERS("hovers"),
	SORTABLE_DATA_TABLES("tables"),
	REDIRECT("redirector"),
	JAVASCRIPT_ALERTS("javascript_alerts"),
	CHALLENGING_DOM("challenging_dom"),
	STATUS_CODES("status_codes"),
	LOGIN("login");
	
	/*
	 * Sub urls are used as real locations in Bank test environment and for The Internet page
	 */
	private String subURL;
	
	private PageSubURLsProjectYEnum(String subURL) {
		this.subURL = subURL;
	}
	
	private PageSubURLsProjectYEnum() {
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}