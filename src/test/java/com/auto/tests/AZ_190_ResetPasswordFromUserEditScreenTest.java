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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_190_ResetPasswordFromUserEditScreenTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	SystemPage  system;
	
	@Test(priority=1)
	public void az_190_ResetPassswordFromUserEditScreen() throws InterruptedException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);   
	    system = new SystemPage(driver);
	
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(5, "QATEST");
		userPage.selectOption(6, ConfigProperties.role);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		String password = "Password@"+basepage.genAutoNumber(3)+"p";
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, password, "User Confirm Password");
		signup.clickWithScroll("Create"); 
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		

		dashboard.logOut(ConfigProperties.logout_url); //Logout from Super Admin account and login as User  
		
		driver.navigate().to(ConfigProperties.site_url);
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		
		dashboard.logOut(ConfigProperties.logout_url); //Logout from User account and login as Super Admin
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User Name", SystemPageObject.userName, 3), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Edit", 7, 1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.adminPassword, "Reset Password");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Reset Re-Password");
		signup.clickWithScroll("Save");
		
		
		dashboard.logOut(ConfigProperties.logout_url); //Logout from Super Admin account and login as User  
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, password);
		signup.clickSubmit();
		assertFalse(system.isConfirmationMessageAppear(SystemPageObject.passwordResetAlert), "Fail: ["+SystemPageObject.passwordResetAlert+"] success aleart still present.");
		assertTrue(system.isSignUpFeedBackAlert(SystemPageObject.alertCss, SystemPageObject.authenticationMessage, "Failed Alert"), "Fail: Failed alert doesn't present.");
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.adminPassword);
		signup.clickSubmit();
		assertFalse(system.isConfirmationMessageAppear(SystemPageObject.passwordResetAlert), "Fail: ["+SystemPageObject.passwordResetAlert+"] success aleart still present.");
		assertFalse(system.isSignUpFeedBackAlert(SystemPageObject.alertCss, SystemPageObject.authenticationMessage, "Failed Alert"), "Fail: Failed alert still present.");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		
		dashboard.logOut(ConfigProperties.logout_url); //Logout from User account and login as Super Admin
		
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User Name", SystemPageObject.userName, 3), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 7, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}

}
