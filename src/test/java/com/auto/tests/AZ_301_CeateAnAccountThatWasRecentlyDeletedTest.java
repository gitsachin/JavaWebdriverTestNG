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


public class AZ_301_CeateAnAccountThatWasRecentlyDeletedTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage  system;
	
	
	@Test
	public void az_301_CeateAnAccountThatWasRecentlyDeleted(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    system = new SystemPage(driver); 
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		String clientName = SystemPageObject.nameOfClient;
		String username = "QaUser_"+BasePage.autogenerateNumber(3);
		String userEmail = SystemPageObject.admienEmail;
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, clientName, "Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, username, "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, userEmail, "Admin Email");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", clientName, 2), "Fail: Client doesn't created.");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, userEmail, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Users", userEmail, 4), "Search user missing from grid.");
		
		dashboard.clickMenuTab("Clients");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, clientName, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", clientName, 1, false), "Fail: Searched item present in client grid.");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, userEmail, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Users", userEmail, 4), "Search user still present in user grid after delete client.");
		
		dashboard.clickMenuTab("Clients");
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, clientName, "Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, username, "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, userEmail, "Admin Email");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", clientName, 2), "Fail: Client doesn't created.");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, userEmail, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Users", userEmail, 4), "Search user missing from grid.");

		dashboard.clickMenuTab("Clients");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, clientName, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", clientName, 1, false), "Fail: Searched item present in client grid.");
	}

}
