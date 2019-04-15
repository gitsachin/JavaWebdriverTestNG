package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_316_EditProfileScreenChangesTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	@Test
	public void az_316_EditProfileScreenChanges(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickOnExpectedButton(DashboardPageObject.userName_xpath, 1, "User Name");
		dashboard.clickOnExpectedButton(DashboardPageObject.buttonProfile_xpath, 1, "Profile");
		assertTrue(dashboard.isExpectedFieldLabelPresent("Change Password", "Edit Profile"), "fail: 'Change Password' field verification.");
		assertTrue(dashboard.isExpectedFieldLabelPresent("Confirm Password", "Edit Profile"), "fail: 'Confirm Password' field verification.");
		assertTrue(dashboard.isExpectedFieldLabelPresent("Signature", "Edit Profile"), "fail: 'Signature' field verification.");
		dashboard.enterExpectedValueInTextField(DashboardPageObject.textFieldChangePassword_xpath, 1, "Testing123@a", "Change Password");
		dashboard.enterExpectedValueInTextField(DashboardPageObject.textFieldConfirmPassword_xpath, 1, "Testing123@a", "Confirm Password");
		dashboard.clickOnExpectedButton(DashboardPageObject.buttonSave_xapth, 1,"Save");
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertTrue(signup.isAuthenticationAlertAppersDuringSignup("Authentication Failed!"), "fail: 'Login Authentication' message missing. ");
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, "Testing123@a");
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		assertFalse(signup.isAuthenticationAlertAppersDuringSignup("Authentication Failed!"), "fail: 'Login Authentication' message appears. ");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User is not navigate to Dashboard page.");
		
		dashboard.clickOnExpectedButton(DashboardPageObject.userName_xpath, 1, "User Name");
		dashboard.clickOnExpectedButton(DashboardPageObject.buttonProfile_xpath, 1, "Profile");
		dashboard.enterExpectedValueInTextField(DashboardPageObject.textFieldChangePassword_xpath, 1, ConfigProperties.superAdmin_pass01, "Change Password");
		dashboard.enterExpectedValueInTextField(DashboardPageObject.textFieldConfirmPassword_xpath, 1, ConfigProperties.superAdmin_pass01, "Confirm Password");
		dashboard.clickOnExpectedButton(DashboardPageObject.buttonSave_xapth, 1,"Save");
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertFalse(signup.isAuthenticationAlertAppersDuringSignup("Authentication Failed!"), "fail: 'Login Authentication' message appears. ");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User is not navigate to Dashboard page.");
	}

}
