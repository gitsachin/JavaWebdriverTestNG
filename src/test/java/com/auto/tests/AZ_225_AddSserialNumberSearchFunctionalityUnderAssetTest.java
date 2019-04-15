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
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_225_AddSserialNumberSearchFunctionalityUnderAssetTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	TicketPage ticketPage;
	HelpDeskPage helpDeskTicket;
	
	@Test(priority=4)
	public void az_225_AddSserialNumberSearchFunctionalityUnderAsset() throws InterruptedException{
	
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskTicket = new HelpDeskPage(driver);
	   
	    driver.navigate().to(ConfigProperties.signin_url);
	
		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.assetSerialNo, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Asset", ConfigProperties.assetSerialNo, "Serial Number", 5), "Fail: Serial Number Searched Operation.");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assertTrue(assetPage.isEdxpectedFieldsReadOnlyField("Manufacturer", 2), "Fail: Manufacturer field is not Read Only fields.");
		assertTrue(assetPage.isEdxpectedFieldsReadOnlyField("Model", 3), "Fail: Model field is not Read Only fields.");
		
		
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		assetPage.clickOnArrowIconOfFields("Client", 3);
		assetPage.enterSearchString("QATEST", 2, "Client");
		ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
		assetPage.enterSearchString("User1_DND (clientuser123@mailinator.com)", 1, "User or Email Address");
		signup.clickWithScroll("Done");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDeskTicket.isSearchResultPresent(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 2), "Fail: No data appears according to search string.");
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.assetSerialNo, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Asset", ConfigProperties.assetSerialNo, "Serial Number", 5), "Fail: Serial Number Searched Operation.");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.assetSerialNo, 1, "Edit", 8, "Edit Screen", 5);
		Thread.sleep(1000);
		assetPage.clickOnExpectedButton(InventoryPageObject.ticketTab_xpath, 1, "Ticket Tab");
		ticketPage.searchTicket(InventoryPageObject.ticketSearch_css, HelpDeskTicketsPageObject.ticketName, 1);
		assertTrue(ticketPage.isSearchedItemPresentInTicketGrid("Asset>Ticket Page", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't present under Asset>Ticket Page");
		ticketPage.clickOnViewEditDelIconsofTicket(HelpDeskTicketsPageObject.ticketName, 3, "Delete", 9, "Remove", 2);
		signup.click("Yes");
		Thread.sleep(1000);
		ticketPage.searchTicket(InventoryPageObject.ticketSearch_css, HelpDeskTicketsPageObject.ticketName, 1);
		assertFalse(ticketPage.isSearchedItemPresentInTicketGrid("Asset>Ticket Page", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket still present under Asset>Ticket Page");
	}

}
