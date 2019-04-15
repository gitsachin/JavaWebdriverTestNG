package com.auto.tests;

import static org.testng.Assert.assertEquals;
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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;


public class AZ_132_AddRequirementPermissionCheckboxToTicketFieldsTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	HelpDeskPage helpDeskPage;
	TicketPage ticketPage;
	AssetsPage assetPage;
	
	@Test
	public void az_132_AddRequirementPermissionCheckboxToTicketFields(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    ticketPage = new TicketPage(driver);
	    assetPage = new AssetsPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickOnInnerSubmenu("Tickets", "Attributes", "Fields");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewFields_xpath, 1, "New field");
		assertTrue(dashboard.isExpectedPopupOpen("Add Ticket Field"), "Fail: [Add Ticket Field] pop-up doesn't open.");
		assertTrue(ticketPage.isExpectedCheckboxFieldAppears("Required", HelpDeskTicketsPageObject.checkBoxLebelTicketField_xpath, 1), "Fail: [Required] doesn't present");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel");
		
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticketFields, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isSearchedItemPresentInGrid(HelpDeskTicketsPageObject.searchTableId, "Ticket Field", ConfigProperties.ticketFields, 4), "Fail: Searched Operation.");
		assetPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, ConfigProperties.ticketFields, 1, "Edit", 7, "Update", 4);
		ticketPage.clickOnExpectedCheckbox(HelpDeskTicketsPageObject.checkBoxTicketField_xpath, "Required", 1);
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonUpdate_xpath, 1, "Update");
		
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isSearchedItemPresentInGrid(HelpDeskTicketsPageObject.searchTableId, "Ticket Field", ConfigProperties.ticket, 2), "Fail: Searched Operation.");
		assetPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, ConfigProperties.ticket, 2, "Edit", 9, "Update", 2);
		assertTrue(dashboard.isExpectedPopupOpen("Edit Ticket"), "Fail: [Edit Ticket] pop-up doesn't open.");
		assertTrue(ticketPage.isExpectedFiledPresent("Edit Ticket", ConfigProperties.ticketFields), "Fail: ["+ConfigProperties.ticketFields+"] doesn't present.");
		assertTrue(ticketPage.isExpectedFieldRequiredField(ConfigProperties.ticketFields, 4 ), "Fail: ["+ConfigProperties.ticketFields+"] isn't Required field.");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonSave_xpath, 1, "Update");
		assertTrue(dashboard.isExpectedPopupOpen("Edit Ticket"), "Fail: [Edit Ticket] pop-up doesn't open.");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel");
		
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickOnInnerSubmenu("Tickets", "Attributes", "Fields");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticketFields, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isSearchedItemPresentInGrid(HelpDeskTicketsPageObject.searchTableId, "Ticket Field", ConfigProperties.ticketFields, 4), "Fail: Searched Operation.");
		assetPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, ConfigProperties.ticketFields, 1, "Edit", 7, "Update", 4);
		ticketPage.clickOnExpectedCheckboxToUncheck(HelpDeskTicketsPageObject.checkBoxTicketField_xpath, "Required", 1);
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonUpdate_xpath, 1, "Update");
		
	}
	


}
