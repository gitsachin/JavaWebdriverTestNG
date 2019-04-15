package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_179_ErrorOnSideMenuWhenAttemptingToAccessUsersTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage  system;
	UsersPage userPage;
	RolePage rolePage;
	TicketPage ticketPage;
	
	@Test(priority=1)
	public void az_179_ErrorOnSideMenuWhenAttemptingToAccessUsers() throws InterruptedException, AWTException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    userPage = new UsersPage(driver);
	    rolePage = new RolePage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	   
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
			
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		signup.click("Action");
		dashboard.clickButton("New client role");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Client Role");
		rolePage.clickOnClient();
		ticketPage.enterSearchString(RolePageObject.clientName);
		signup.enterInput(1, RolePageObject.roleName);
		dashboard.clickButton("Check All");
		signup.click("Create");
		
		// Need to codes for delete created role 
		
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(3, "QATEST");
		userPage.selectOption(4, RolePageObject.roleName);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Password");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "Confirm password");
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		dashboard.clickButton("Users");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 6, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", "QaTestUser 01", 1, false), "Fail: Searched item present in User grid.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		
		signup.clickSubmit();
		assertTrue(signup.isAuthenticationAlertAppersDuringSignup("Authentication Failed!"), "fail: 'Authentication Failed!' alert doesn't appear.");
	}

}
