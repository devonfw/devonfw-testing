package com.capgemini.mrchecker.selenium.pages.environment;

import java.util.Objects;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

/**
 * @author lucst Takes values saved in /src/resources/environments/environment.csv When -Denv is not set, then it takes
 *         default DEV
 */
public enum GetEnvironmentParam {
	
	// Name if enum must be in line with cell name in /src/resources/environments/environment.csv
	
	WWW_FONT_URL,
	TOOLS_QA,
	WEB_SERVICE,
	THE_INTERNET_MAIN_PAGE,
	ELEMENTAL_SELENIUM_PAGE,
	MAY_THAI_STAR_URL;
	
	public String getValue() {
		
		if (Objects.isNull(BaseTest.getEnvironmentService())) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
				.getValue(name());
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
