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
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_275_CreatingNewHelpTicketTryingToAttachAPictureAndXToCloseItClosesTheEntireTicketNothingIsSavedTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	UsersPage userPage;
		
	@Test
	public void az__275_CreatingNewHelpTicketTryingToAttachAPictureAndXToCloseItClosesTheEntireTicketNothingIsSaved(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        project = new ProjectPage(driver);
        assetPage = new AssetsPage(driver);
        ticketPage = new TicketPage(driver);
        helpPage = new HelpDeskPage(driver);
        userPage = new UsersPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
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
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Ticket search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,HelpDeskTicketsPageObject.ticketName, 1, "View", 10, "Ticket", 2);
	    helpPage.countNumberOfComment();
		assetPage.sendFile(ConfigProperties.Image);
		helpPage.clickCloseTicket();
		assertTrue(helpPage.verifyImageNotUploadInTicket(),"Image has been uploaded in ticket");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Ticket search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,HelpDeskTicketsPageObject.ticketName, 3, "Delete", 10, "Ticket", 2);
		signup.click("Yes");
		
	}

}
