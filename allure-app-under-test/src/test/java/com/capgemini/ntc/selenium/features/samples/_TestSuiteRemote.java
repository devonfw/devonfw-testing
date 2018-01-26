package com.capgemini.ntc.selenium.features.samples;

import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestLocal;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@ExcludeCategories({ TestLocal.class, TestsNONParallel.class })
@SuiteClasses({ "**/samples/**/*Test.class" })

public class _TestSuiteRemote {
	
}
