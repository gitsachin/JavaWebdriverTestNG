package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.auto.base.TestCoreMobileDevices;
import com.auto.configproperties.ConfigProperties;
import com.auto.mobilePages.MobileSignUp;
import com.auto.mobilePagesObject.MobileSignUpPageObject;
import com.auto.pageobject.SignUpPageObject;

public class AZ_44_LoginPageOnMobilePhoneScrollToSeeThePasswordTest extends TestCoreMobileDevices {
	MobileSignUp signup;
	@Test
	public void az_44_LoginPageOnMobilePhoneScrollToSeeThePassword(){
		signup = new MobileSignUp(driver1);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		assertTrue(signup.isExpectedFieldPresent(MobileSignUpPageObject.textFieldEmail_xpath, "Email Id", "Login Page"), "Fail: Email filed doesn't present.");
		assertTrue(signup.isExpectedFieldPresent(MobileSignUpPageObject.textFieldPassword_xpath, "Password", "Login Page"), "Fail: Password filed doesn't present.");
		signup.enterInput(MobileSignUpPageObject.textFieldEmail_xpath, ConfigProperties.email, "Email Id");
		signup.enterInput(MobileSignUpPageObject.textFieldPassword_xpath, ConfigProperties.pass, "Password");
		assertTrue(signup.ScrollingUpPage("Login Page"), "Fail: Page doesn't scroll.");
		assertTrue(signup.ScrollingDownPage(MobileSignUpPageObject.textFieldPassword_xpath, "Login Page."), "Fail: Page doesn't scrll");
	}

}
