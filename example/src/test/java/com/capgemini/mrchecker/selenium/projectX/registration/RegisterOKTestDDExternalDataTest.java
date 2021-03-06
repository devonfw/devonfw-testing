package com.capgemini.mrchecker.selenium.projectX.registration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.environment.PageTitlesEnum;
import com.capgemini.mrchecker.selenium.pages.projectX.registration.RegistrationPage;
import com.capgemini.mrchecker.selenium.projectX.registration.utils.FormDataContainer;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@TestsSelenium
@Disabled("Registration site not not on the Web")
public class RegisterOKTestDDExternalDataTest extends BaseTest {
	
	public RegistrationPage registrationPage;
	
	@Override
	public void setUp() {
		registrationPage = PageFactory.getPageInstance(RegistrationPage.class);
	}
	
	@Override
	public void tearDown() {
		registrationPage.load();
	}
	
	@ParameterizedTest
	@MethodSource("com.capgemini.mrchecker.selenium.projectX.registration.utils.DataProviderExternalJsonFile#provide")
	public void registrationTest(FormDataContainer data) {
		assertEquals("Site title: " + registrationPage.getActualPageTitle(), registrationPage.getActualPageTitle(), PageTitlesEnum.REGISTRATION.toString());
		
		registrationPage.setFirstName(data.getFirstName());
		registrationPage.setLastName(data.getLastName());
		registrationPage.setMaritalStatus(data.getMaritalStatus());
		registrationPage.setHobby(data.getHobby());
		registrationPage.setCountry(data.getCountry());
		registrationPage.setBirthDate(data.getBirthDate());
		registrationPage.setPhoneNumber(data.getPhoneNumber());
		registrationPage.setUsername(data.getUsername());
		registrationPage.setEmail(data.getEmail());
		registrationPage.setProfilePicture(data.getYourProfilePhotoPath());
		registrationPage.setAboutYourself(data.getAboutYourself());
		registrationPage.setPassword(data.getPassword());
		registrationPage.setConfirmPassword(data.getConfirmPassword());
		
		registrationPage.clickSubmit();
		
		assertTrue("Registration succeed text visible: ", registrationPage.isRegistryErrorTextVisible());
	}
}
