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
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.IssuePage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_279_CreateTicketsFromIssuesTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	IssuePage issuePage;
	
	@Test
	public void az_279_CreateTicketsFromIssues(){
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
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("All Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.activeIssue, "Search string");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(issuePage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Issue Name", ConfigProperties.activeIssue, 2), "Fail: Searched Operation.");
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, ConfigProperties.activeIssue, 2, "Edit", 7, 1);
		assertTrue(dashboard.isExpectedPopupOpen("Edit Issue"), "Fail: User doesn't navigate to Edit Issue pop-up");
		String assetDetails = issuePage.returnExpectedFieldValues("Asset Details", IssuePageObject.fieldAssetName_xapth);
		issuePage.clickOnExpectedButton(IssuePageObject.buttonCreateTicket_xpath, 1, "Create New Ticket");
		assertTrue(dashboard.isExpectedPopupOpen("Add Ticket"), "Fail: User doesn't navigate to 'Add Ticket' pop-up");
		assertTrue(ticketPage.isAssetInfoCarriedOver("Asset", HelpDeskTicketsPageObject.assetField_xpath, assetDetails), "Fail: Asset details doesn't carry out to another page.");
		
	}

}
