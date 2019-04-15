package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
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
import com.auto.pages.UsersPage;

public class AZ_94_GenerateMultiplehelpDeskTicketsAllWithSameBaseInformationTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	HelpDeskPage helpPage;
	
	@Test
	public void  az_94_GenerateMultiplehelpDeskTicketsAllWithSameBaseInformation(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        helpPage = new HelpDeskPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Active Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		userPage.selectOption(1, "QaDepartmentForAutomationTest");
		userPage.selectOption(3, "QaLocationForAutomation_DND");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, "QaAssetForAutomation", "Asset");
		assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Suggestion list");
		signup.clickWithScroll("Done");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpPage.isSearchResultPresent(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 2), "Fail: No data appears according to search string.");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 2, "Edit", 10, "All ticket", 2);
		dashboard.clickOnExpectedButton(SignUpPageObject.submit_xpath, 1, "Save");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Delete", 10, "All ticket", 2);
		signup.click("Yes");
		log("["+HelpDeskTicketsPageObject.ticketName+"] ticket delete from Help Desk Ticket Grid.", ILogLevel.TEST);
		
	}

}
