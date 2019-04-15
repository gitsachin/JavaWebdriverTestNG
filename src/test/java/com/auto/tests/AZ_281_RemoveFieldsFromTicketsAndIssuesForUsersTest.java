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
import com.auto.pages.IssuePage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_281_RemoveFieldsFromTicketsAndIssuesForUsersTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	IssuePage issuePage;
	
	@Test
	public void AZ_281_RemoveFieldsFromTicketsAndIssuesForUsers(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        project = new ProjectPage(driver);
        assetPage = new AssetsPage(driver);
        ticketPage = new TicketPage(driver);
        helpPage = new HelpDeskPage(driver);
        issuePage = new IssuePage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.clientUser_email);
		signup.enterInput(1, ConfigProperties.clientUser_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientUserName);
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assertFalse(ticketPage.isExpectedFiledPresent("Add Ticket", "Department"), "Fail: 'Department' field present on 'Add Ticket' pop-up.");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel Button");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpPage.isSearchResultPresent(InventoryPageObject.searchTableId, ConfigProperties.ticket, 2), "Fail: No data appears according to search string.");
		assetPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, ConfigProperties.ticket, 2, "Edit", 10, "All ticket", 2);
		assertFalse(ticketPage.isExpectedFiledPresent("EditTicket", "Department"), "Fail: 'Department' field present on 'Edit Ticket' pop-up.");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel Button");
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		signup.click("Action");
		signup.click("New issue");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Department"), "Fail: 'Department' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Time Spent"), "Fail: 'Time Spent' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Due Date"), "Fail: 'Due Date' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Priority"), "Fail: 'Priority' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Status"), "Fail: 'Status' field present on 'Add Issue' pop-up.");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonCancel_xpath, 1, "Cancel Button");
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.activeIssue, "Search string");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(issuePage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Issue Name", ConfigProperties.activeIssue, 2), "Fail: Searched Operation.");
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, ConfigProperties.activeIssue, 2, "Edit", 7, 1);
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Department"), "Fail: 'Department' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Time Spent"), "Fail: 'Time Spent' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Due Date"), "Fail: 'Due Date' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Priority"), "Fail: 'Priority' field present on 'Add Issue' pop-up.");
		assertFalse(issuePage.isExpectedFiledPresent("Add Issue", "Status"), "Fail: 'Status' field present on 'Add Issue' pop-up.");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonCancel_xpath, 1, "Cancel Button");
	}

}
