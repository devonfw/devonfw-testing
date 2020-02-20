package com.capgemini.mrchecker.selenium.environment;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

/**
 * @author lucst Takes values saved in /src/resources/environments/environment.csv When -Denv is not set, then it takes
 *         default DEV
 */
public enum GetEnvironmentParam {
	
	// Name if enum must be in line with cell name in /src/resources/environments/environment.csv
	;
	
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
