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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_125_CreateNewAccountTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	SystemPage  system;
	DashboardPage dashboard ;
	
	
	@Test(priority=0)
	public void az_125_CreateNewAccountTest(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		system = new SystemPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.clickLink("Sign Up");
		assertEquals(driver.getCurrentUrl(),SignUpPageObject.signup_url);
		signup.enterInput(0, SignUpPageObject.companyNames);
		signup.enterInput(1, SignUpPageObject.Name);
		signup.enterInput(2, SignUpPageObject.email);
		signup.enterInput(3, "12345678");
		signup.enterInput(4, "12345678");
		signup.enterInput(5,SignUpPageObject.captcha);
		signup.clickSubmit();
		assertTrue(signup.isSignUpformPresent(), "Fail: Signup form doesn't present");
		assertFalse(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.textThanksSignup_xpath, SignUpPageObject.textThanksSignup, "SignUp"), "Fail: Thanks mesaage appears after Signup.");
		signup.enterInput(3, "testing1234a");
		signup.enterInput(4, "testing1234a");
		signup.clickSubmit();
		assertTrue(signup.isSignUpformPresent(), "Fail: Signup form doesn't present");
		assertFalse(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.textThanksSignup_xpath, SignUpPageObject.textThanksSignup, "SignUp"), "Fail: Thanks mesaage appears after Signup.");
		signup.enterInput(3, "testing@123a");
		signup.enterInput(4, "testing@123a");
		signup.clickSubmit();
		assertTrue(signup.isSignUpformPresent(), "Fail: Signup form doesn't present");
		assertFalse(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.textThanksSignup_xpath, SignUpPageObject.textThanksSignup, "SignUp"), "Fail: Thanks mesaage appears after Signup.");
		signup.enterInput(3, "Testing@123a");
		signup.enterInput(4, "Testing@123a");
		signup.clickSubmit();
		assertFalse(signup.isSignUpformPresent(), "Fail: Signup form closed");
		assertTrue(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.textThanksSignup_xpath, SignUpPageObject.textThanksSignup, "SignUp"), "Fail: Thanks mesaage doesn't appears after Signup.");
		
		driver.navigate().to(ConfigProperties.signin_url);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SignUpPageObject.companyNames, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SignUpPageObject.companyNames, 2), "Fail: Client doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SignUpPageObject.companyNames, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SignUpPageObject.companyNames, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SignUpPageObject.companyNames, 1, false), "Fail: Searched item present in client grid.");
			
	}

}
