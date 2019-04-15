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

public class AZ_75_AllowOnlycharacterLettersInClientNameTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage system;
	
	@Test
	public void az_75_AllowOnlycharacterLettersInClientName() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    system = new SystemPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
	    signup.clickButton("New to Aszet Sign Up here.");
	    signup.enterExpectedValueInTextField(SignUpPageObject.companyName_xpath, 1, BasePage.autogenerateNumber(5), "Name");
	    assertFalse(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: Client doesn't accept Numbers.");
		Thread.sleep(3000);
		signup.enterExpectedValueInTextField(SignUpPageObject.companyName_xpath, 1, "@@$&@*", "Name");
		assertTrue(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: Client accept Special Characters.");
		Thread.sleep(3000);
		signup.enterExpectedValueInTextField(SignUpPageObject.companyName_xpath, 1, SystemPageObject.nameOfClient, "Name");
		assertFalse(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: validation message appears for valid inputs.");
	    
		signup.clickButton("Log In aszet");
	    
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");
		
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, BasePage.autogenerateNumber(7), "Name");
		assertFalse(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: validation message doesn't appear for invalid inputs.");
		
		Thread.sleep(3000);
		
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "@@$&@*", "Name");
		assertTrue(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: validation message doesn't appear for invalid inputs.");
		
		Thread.sleep(3000);
		
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Name");
		assertFalse(signup.isClientValidationAlertAppears(SignUpPageObject.validationTextForInvalidClient), "Fail: validation message appears for valid inputs.");
	}

}
