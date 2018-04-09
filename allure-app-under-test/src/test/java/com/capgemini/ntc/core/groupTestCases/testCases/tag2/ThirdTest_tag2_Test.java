package com.capgemini.ntc.core.groupTestCases.testCases.tag2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSmoke;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsTag2;
import com.capgemini.ntc.selenium.pages.projectX.registration.RegistryPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

import ru.yandex.qatools.allure.annotations.Features;

@Features("TAG3")
@Category({ TestsTag2.class, TestsSmoke.class })
public class ThirdTest_tag2_Test extends BaseTest {
	
	private RegistryPage registryPage;
	
	@Override
	public void setUp() {
		BFLogger.logInfo("[Step 1] As a standard user I will open Registry Page,  So that my I can fill data");
		registryPage = new RegistryPage();
		
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void QCID_StayOnResistryPage_Tag2_Third() throws InterruptedException {
		
		BFLogger.logInfo(
						"[Step 2] As a standard user I click Submit button,  So that I will stay on Registry page");
		registryPage.clickSubmit();
		assertThat(true, is(registryPage.isLoaded()));
		
		TimeUnit.SECONDS.sleep(3); // This is for demo. Do not do it at home
		
	}
	
	@Test
	public void testTag2_Third() {
		BFLogger.logInfo("ThirdTest_tag2.test()");
		
	}
	
}
