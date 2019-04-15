package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_187_ContinueButtonDisappearFromPasswordSetSecrrenTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	
	@Test
	public void az_187_ContinueButtonDisappearFromPasswordSetSecrren(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		dashboard.clickOnExpectedButton(SignUpPageObject.linkForgetPassword_xpath, 1, "Forgot your password?");
		assertTrue(dashboard.isExpectedFieldPresent("Forgot Password", "Emial Id", SignUpPageObject.textFieldEmail_xpath), "fail: [Email Id] field doesn't located.");
		assertTrue(dashboard.isExpectedButtonPresent("Forgot Password", SignUpPageObject.buttonContinue_xpath, "Continue"), "Fail: [Continue] button doesn't located.");
		dashboard.enterExpectedValueInTextField(SignUpPageObject.textFieldEmail_xpath, 1, "testing@mailinator.com", "Email Id"); // Invalid email id
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonContinue_xpath, 1, "Continue");
		assertTrue(signup.isValidationMessageAppears(), "Fail: [Email Does not exist!] message doesn't appears.");
		assertTrue(dashboard.isExpectedButtonPresent("Forgot Password", SignUpPageObject.buttonContinue_xpath, "Continue"), "Fail: [Continue] button doesn't located.");
		
	}

}
