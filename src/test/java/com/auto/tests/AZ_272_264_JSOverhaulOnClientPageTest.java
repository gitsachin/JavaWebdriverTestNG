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

public class AZ_272_264_JSOverhaulOnClientPageTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage  system;
	
	@Test
	public void az_272_264_JSOverhaulOnClientPage(){
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
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		
		for(int i=1; i<=2; i++){
			signup.click("Actions");
			system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
			assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] doesn't visible.");
			system.clickOnExpectedButton(SystemPageObject.button_Cancel, 1, "Cancel");
		}
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] doesn't visible.");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		assertTrue(system.validateFieldBorderColor(SystemPageObject.textFieldsName_xpath, "Clinet Name"), "Fail: [Clinet Name] filed dpesn't validate.");
		assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] doesn't visible.");
		
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Client Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "Qa", "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		assertTrue(system.validateFieldBorderColor(SystemPageObject.textFieldsAdminPass_xpath, "Client Admin Password"), "Fail: [Client Admin Password] filed dpesn't validate.");
		assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] doesn't visible.");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] doesn't visible.");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient, 2), "Fail: Client doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient, 1, false), "Fail: Searched item present in client grid.");
		
	}

}
