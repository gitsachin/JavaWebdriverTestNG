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

public class AZ_289_CheckDataAfterDeleteClientTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;

	@Test(priority=0)
	public void az_289_CreateNewClient(){
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
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);

		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "Qa", "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");

		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient, 2), "Fail: Client doesn't created.");

	}

	@Test(priority=1)
	public void CreateNewLocation(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		helpDeskPage = new HelpDeskPage(driver);
		system = new SystemPage(driver);
		ticketPage = new TicketPage(driver);

		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		dashboard.selectOption(3, SystemPageObject.nameOfClient);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.locationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation is not perform.");
	}

	@Test(priority=2)
	public void CreateNewAssert(){

		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		helpDeskPage = new HelpDeskPage(driver);
		system = new SystemPage(driver);
		ticketPage = new TicketPage(driver);

		dashboard.clickMenuTab("Inventory");

		dashboard.clickOnSpecificSubmenu("Asset", 2);
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");

		dashboard.selectOption(1, SystemPageObject.nameOfClient);

		signup.enterInput(2, InventoryPageObject.assetName);
		dashboard.selectOption(2, InventoryPageObject.locationName);
		dashboard.selectOption(4, "Lava");
		dashboard.selectOption(5, "Lava-1234");
		dashboard.selectOption(6, "Amzone");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, "SL"+BasePage.autogenerateNumber(5), "Serial No");
		String serialNumber = "SL"+BasePage.autogenerateNumber(5);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, serialNumber, "Serial No");
		signup.clickWithScroll("Create");

		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInTable("Asset", InventoryPageObject.assetName), "Fail: ["+InventoryPageObject.assetName+"] doesnt created.");

	}
	
	@Test(priority=3)
	public void DeleteClientAndVerifyAssetAndLocation(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		helpDeskPage = new HelpDeskPage(driver);
		system = new SystemPage(driver);
		ticketPage = new TicketPage(driver);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");

		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Delete", 6, 3);
		signup.click("Yes");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient+"Test", 1, false), "Fail: Searched item present in client grid.");
		
		dashboard.clickMenuTab("Inventory");

		dashboard.clickOnSpecificSubmenu("Asset", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Asset", InventoryPageObject.assetName, 1, false), "Fail: Searched item present in asset grid.");
		
		dashboard.clickButton("Locations");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1, false), "Fail: Searched item present in location grid.");
		
	}


}
