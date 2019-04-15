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
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_107_DeleteUserWhileDeletingTheClientTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;
	
	@Test
	public void az_107_DeleteUserWhileDeletingTheClient() {
	
	signup = new SignUpPage(driver);
	configprop = new ConfigProperties();
	basepage = new BasePage(driver);
    dashboard = new DashboardPage(driver);
    assetPage = new AssetsPage(driver);
    userPage = new UsersPage(driver);
    helpDeskPage = new HelpDeskPage(driver);
    system = new SystemPage(driver);
    ticketPage = new TicketPage(driver);
    
    
    assertEquals(driver.getTitle(), SignUpPageObject.title);
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
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Name");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
	
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "QaClient", "Admin Name");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
	system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
	system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
	system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient, 2), "Fail: Client doesn't created.");
	
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	signup.click("Action");
	signup.click("Add user account");
	assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
	userPage.selectOption(3, SystemPageObject.nameOfClient);
	userPage.selectOption(4, "Client Admin");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User Re-password");
	signup.clickWithScroll("Create");
	system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
	system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
	
	dashboard.clickMenuTab("Clients");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
	system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
	system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Delete", 6, 3);
	signup.click("Yes");
	system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
	system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient, 1, false), "Fail: Searched item present in client grid.");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
	system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
	}

}
