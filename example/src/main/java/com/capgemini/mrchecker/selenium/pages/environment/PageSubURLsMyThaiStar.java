package com.capgemini.mrchecker.selenium.pages.environment;

public enum PageSubURLsMyThaiStar {
	
	HOME("restaurant"),
	MENU("menu"),
	BOOK_TABLE("bookTable"),
	ORDERS("orders"),
	RESERVATIONS("reservations");
	
	/*
	 * Sub urls are used as real locations in Bank test environment
	 */
	private String subURL;
	
	private PageSubURLsMyThaiStar(String subURL) {
		this.subURL = subURL;
	}
	
	;
	
	private PageSubURLsMyThaiStar() {
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
	public String getValue() {
		return subURL;
	}
	
}
