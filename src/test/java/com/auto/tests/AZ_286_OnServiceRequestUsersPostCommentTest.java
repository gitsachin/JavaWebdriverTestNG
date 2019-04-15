package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_286_OnServiceRequestUsersPostCommentTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	
	
	@Test
	public void az_286_OnServiceRequestUsersPostComment() throws InterruptedException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        project = new ProjectPage(driver);
        assetPage = new AssetsPage(driver);
        ticketPage = new TicketPage(driver);
        helpPage = new HelpDeskPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		ticketPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.ticket, 1, "Eye Ball", 10, "Ticket: "+ConfigProperties.ticket, 2);
		ticketPage.clickOnExpectedTab("Comments");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.iconComment_xpath, 1, "Comment icon");
		String comment = "My Comment_"+basepage.autogenerateNumber(3);
		ticketPage.enterComment(comment);
		signup.clickWithScroll("Create");
		assertTrue(ticketPage.isCommentAdded(comment), "Fail: "+comment+" doen't added in comment history.");
		assertTrue(ticketPage.isEditDeleteButtonApperInPostedComment(comment, "Edit"), "Fail: 'Edit' button doesn't appear in comment section.");
		assertTrue(ticketPage.isEditDeleteButtonApperInPostedComment(comment, "Delete"), "Fail: 'Delete' button doesn't appear in comment section.");	
		dashboard.logOut(ConfigProperties.logout_url);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		ticketPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.ticket, 1, "Eye Ball", 10, "Ticket: "+ConfigProperties.ticket, 2);
		ticketPage.clickOnExpectedTab("Comments");
		assertTrue(ticketPage.isCommentAdded(comment), "Fail: "+comment+" doen't added in comment history on other User: "+DashboardPageObject.clientAdminName);
		assertFalse(ticketPage.isEditDeleteButtonApperInPostedComment(comment, "Edit"), "Fail: 'Edit' button present in comment section on other User: "+DashboardPageObject.clientAdminName);
		assertFalse(ticketPage.isEditDeleteButtonApperInPostedComment(comment, "Delete"), "Fail: 'Delete' button present in comment section on other User: "+DashboardPageObject.clientAdminName);
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		ticketPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.ticket, 1, "Eye Ball", 10, "Ticket: "+ConfigProperties.ticket, 2);
		ticketPage.clickOnExpectedTab("Comments");
		ticketPage.clickOnEdit_DeleteCommentButton(comment, "Delete");
		signup.click("Yes");
		log("["+comment+"] deleted from Comment Section", ILogLevel.TEST);
		
		
		
	}

}
