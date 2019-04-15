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
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_241_ClientUpdateTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	SystemPage  system;
	TicketPage ticketPage;
	
	
	@Test(priority=0)
	public void AZ_241_ClientUpdate(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    userPage = new UsersPage(driver);
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
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Edit", 6, 2);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Edit Client");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient+"Test", "Name");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Save");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient,2), "Fail: Searched Operation.");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient+"Test",2), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient+"Test", 2, "Edit", 6, 2);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Edit Client");
		assertFalse(system.isDataUpdateInExpectedField(SystemPageObject.textFieldsName_xpath, "Client Name", SystemPageObject.nameOfClient), "Fail: Edit Operation.");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient+"Test", 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient+"Test", 1, false), "Fail: Searched item present in client grid.");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients","QATEST", 2), "Fail: Client doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId,"QATEST", 2, "Eye Ball", 6, 1);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerTextlog_css)), "QATEST");
		assertTrue(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.clientTab_xpath, "Tickets"), "Fail: Client>Tickets tab missing.");
		system.clickOnExpectedTab(SystemPageObject.clientTab_xpath, "Tickets");
		system.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldClientTicketSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		system.clickOnExpectedButton(HelpDeskTicketsPageObject.textFieldClientTicketGoButton_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(HelpDeskTicketsPageObject.searchClientTicketTableId, "Ticket", ConfigProperties.ticket, 2),"Fail: Ticket doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
	} 

}
