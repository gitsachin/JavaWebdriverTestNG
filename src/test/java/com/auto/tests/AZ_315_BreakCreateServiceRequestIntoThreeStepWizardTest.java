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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_315_BreakCreateServiceRequestIntoThreeStepWizardTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	
	@Test
	public void az_315_BreakCreateServiceRequestIntoThreeStepWizard() throws InterruptedException{
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
		signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assertTrue(ticketPage.verifyCreateTicketThreeStepWizard(HelpDeskTicketsPageObject.createTicketPopup1_xpath, "Add Ticket"), "Fail: 'Add Ticket' pop-up missing.");
		assetPage.clickOnArrowIconOfFields("Client Field", 3);
		assetPage.enterSearchString("QATEST", 2, "Select Client");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, ConfigProperties.assetSerialNo, "Asset Serial No");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Serial Suggestion list");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep1_xpath, 1, "1st Screen Next");
		assertTrue(ticketPage.verifyCreateTicketThreeStepWizard(HelpDeskTicketsPageObject.createTicketPopup2_xpath, "Add Ticket"), "Fail: 'Add Ticket' pop-up missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Serial Number", 1), "Fail: 'Serial Number' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Asset Name", 2), "Fail: 'Asset Name' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Manufacturer", 3), "Fail: 'Manufacturer' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Model", 4), "Fail: 'Model' field missing.");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep2_xpath, 1, "2nd Screen Next");
		assertTrue(ticketPage.verifyCreateTicketThreeStepWizard(HelpDeskTicketsPageObject.createTicketPopup3_xpath, "Add Ticket"), "Fail: 'Add Ticket' pop-up missing.");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.selectOption(8, "QaLocationForAutomation_DND");
		assetPage.enterSearchString("user_"+BasePage.autogenerateNumber(3)+"@mailinator.com", 1, "User or Email Address");
		signup.clickWithScroll("Done");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,HelpDeskTicketsPageObject.ticketName, 3, "Delete", 10, "Ticket", 2);
		signup.click("Yes");
		log("Created Ticket delete from Grid", ILogLevel.TEST);
		
	}

}
