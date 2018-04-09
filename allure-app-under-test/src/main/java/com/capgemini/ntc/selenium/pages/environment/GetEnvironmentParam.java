package com.capgemini.ntc.selenium.pages.environment;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;

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
	GITHUB_PROJECT_PAGE;
	
	public String getValue() {
		
		if (null == BaseTest.getEnvironmentService()) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
				.getValue(this.name());
		
	}
	
	@Override
	public String toString() {
		
		return this.getValue();
		
	}
	
}
