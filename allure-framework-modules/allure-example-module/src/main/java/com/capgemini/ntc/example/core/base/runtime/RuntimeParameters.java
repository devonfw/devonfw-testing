package com.capgemini.ntc.example.core.base.runtime;

import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersI;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class stores various system properties
 * These parameters are accessible while test case executes
 * Example : PARAM_3("param_3", "1410")
 * IS: mvn test -Dparam_3=1525 -Dtest=MyTestClass
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParameters implements RuntimeParametersI {
	
	// NAME(<maven-variable-name>, <default-value>)
	PARAM_1("param_1", "Hello"), // -Dparam_1=Hello
	PARAM_2("param_2", "world"), // -Dparam_2=world
	PARAM_3("param_3", "1410"); // -Dparam_3=1410
	
	private String paramName;
	private String paramValue;
	private String defaultValue;
	
	private RuntimeParameters(String paramName, String defaultValue) {
		this.paramName = paramName;
		this.defaultValue = defaultValue;
		setValue();
		
	}
	
	@Override
	public String getValue() {
		return this.paramValue;
	}
	
	@Override
	public String toString() {
		return paramName + "=" + this.getValue();
	}
	
	@Override
	public void refreshParameterValue() {
		setValue();
	}
	
	private void setValue() {
		
		String paramValue = System.getProperty(this.paramName);
		paramValue = isSystemParameterEmpty(paramValue) ? this.defaultValue : paramValue.toLowerCase();
		;
		
		switch (this.name()) {
		case "PARAM_1":
			if (paramValue.equals("Bye")) {
				paramValue = "Hi";
			}
			break;
		case "PARAM_2":
			break;
		case "PARAM_3":
			break;
		default:
			BFLogger.logError("Unknown RuntimeParameter = " + this.name());
			break;
		}
		
		this.paramValue = paramValue;
		
	}
	
	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || "".equals(systemParameterValue) || "null".equals(systemParameterValue));
	}
	
}
