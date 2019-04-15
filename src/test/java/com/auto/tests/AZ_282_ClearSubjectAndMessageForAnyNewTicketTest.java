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
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_282_ClearSubjectAndMessageForAnyNewTicketTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	
	@Test
	public void AZ_282_ClearSubjectAndMessageForAnyNewTicket() throws InterruptedException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        project = new ProjectPage(driver);
        assetPage = new AssetsPage(driver);
        ticketPage = new TicketPage(driver);
        helpPage = new HelpDeskPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.clientUser_email);
		signup.enterInput(1, ConfigProperties.clientUser_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientUserName);
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.selectOption(2, "QaLocationForAutomation_DND");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, ConfigProperties.assetSerialNo, "Asset Serial No");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Serial Suggestion list");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.messageField_xpath, 1, HelpDeskTicketsPageObject.ticketMessage, "Message");
		
		String assignTo = ticketPage.returnvalueOfExpectedField("Assign To", HelpDeskTicketsPageObject.fieldAssignTo_xapth, "After Entered, ");
		String returnLocation = ticketPage.returnvalueOfExpectedField( "Retrn Location", HelpDeskTicketsPageObject.fieldReturnLocation_xapth, "After Entered, ");
		String message = ticketPage.returnvalueOfExpectedField( "Message", HelpDeskTicketsPageObject.messageField_xpath, "After Enter");
		
		signup.clickWithScroll("Next Ticket");
		Thread.sleep(5000);
		assertFalse(ticketPage.isFiledvalueCarried("Subject", HelpDeskTicketsPageObject.textFieldSubject_xpath, HelpDeskTicketsPageObject.ticketName), "Fail: Subject filed doesn't empty.");
		assertTrue(ticketPage.isFiledvalueCarried("Assign To", HelpDeskTicketsPageObject.fieldAssignTo_xapth, assignTo), "Fail: 'Assign To' filed empty.");
		assertTrue(ticketPage.isFiledvalueCarried("Return Location", HelpDeskTicketsPageObject.fieldReturnLocation_xapth, returnLocation), "Fail: 'Assign To' filed empty.");
		assertFalse(ticketPage.isFiledvalueCarried("Message", HelpDeskTicketsPageObject.messageField_xpath, message), "Fail: Subject filed doesn't empty.");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel Button");
		
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpPage.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		ticketPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Edit", 10, "Tickets", 2);
		signup.click("Yes");
		log("Created Ticket delete from Grid", ILogLevel.TEST);
	}

}
