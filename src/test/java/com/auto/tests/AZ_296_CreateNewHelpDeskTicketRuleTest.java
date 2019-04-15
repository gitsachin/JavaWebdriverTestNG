package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_296_CreateNewHelpDeskTicketRuleTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	HelpDeskPage helpDeskPage;
	TicketPage ticketPage;
	
	@Test(priority=4)
	public void az_296_CreateNewHelpDeskTicketRule(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver); 
	    
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickOnSpecificSubmenu("Settings", 2);
		dashboard.clickOnSpecificSubmenu("Ticket Rules", 1);
		signup.click("Action");
		signup.click("New Rule");
		assertTrue(dashboard.isExpectedPopupOpen("Add Ticket Rule"),"Fail: 'Add Ticket Rule' pop-up doesn't open.");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.ticketFieldName_xpath, 1, HelpDeskTicketsPageObject.ticketRuleName, "Ticket Rules Name");
		ticketPage.clickOnArrowIconOfFields("Client Field", 2);
		ticketPage.enterSearchString("QATEST", 3, "Select Client");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextInBasicTab_xpath, 1, "Basic Tab>Next");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextInConditionTab_xpath, 1, "Condition Tab>Next");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextInActionTab_xpath, 1, "Action Tab>Next");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCreateRules_xpath, 1, "Notifications Tab>Create");
		
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketRuleName, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDeskPage.isSearchResultPresent(HelpDeskTicketsPageObject.searchTableId, HelpDeskTicketsPageObject.ticketRuleName, 3), "Fail: No data appears according to search string.");
		ticketPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, HelpDeskTicketsPageObject.ticketRuleName, 2, "Delete", 4, "Ticket Rules", 3);
		signup.click("Yes");
		log("Created Ticket Rules ["+HelpDeskTicketsPageObject.ticketRuleName+"] delete from Grid", ILogLevel.TEST);
	}
	

}
