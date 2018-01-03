package com.capgemini.ntc.test.core;

public interface ITestObserver {
	public void onTestSuccess();
	
	public void onTestFailure();
	
	public void onTestFinish();
	
	public void addObserver();
}
