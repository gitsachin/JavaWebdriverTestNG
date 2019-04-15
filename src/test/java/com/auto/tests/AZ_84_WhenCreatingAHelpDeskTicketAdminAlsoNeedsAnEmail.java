package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
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

public class AZ_84_WhenCreatingAHelpDeskTicketAdminAlsoNeedsAnEmail extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
	AssetsPage assetPage;
	TicketPage ticketPage;
	HelpDeskPage helpPage;
	UsersPage userPage;
		
	@Test
	public void az_84_WhenCreatingAHelpDeskTicketAdminAlsoNeedsAnEmail(){
		
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
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		
		ticketPage.clickOnArrowIconOfFields("Client Field", 3);
		ticketPage.enterSearchString("QATEST", 2, "Select Client");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, ConfigProperties.assetSerialNo, "Asset Serial No");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Serial Suggestion list");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep1_xpath, 1, "1st Screen Next");
		
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Serial Number", 1), "Fail: 'Serial Number' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Asset Name", 2), "Fail: 'Asset Name' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Manufacturer", 3), "Fail: 'Manufacturer' field missing.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Model", 4), "Fail: 'Model' field missing.");
		
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep2_xpath, 1, "2nd Screen Next");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.selectOption(8, "QaLocationForAutomation_DND");
		String userEmail = "user_"+BasePage.autogenerateNumber(5)+"@mailinator.com";
		assetPage.enterSearchString(userEmail, 1, "User or Email Address");
		signup.clickWithScroll("Done");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 1, "Eye Ball", 10, "Ticket", 2);
		String ticketNumber = helpPage.getTicketNumber();
		
		driver.navigate().to("https://www.mailinator.com/");
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.click("Go!");
		helpPage.selectTicket("New Support Ticket QATEST "+ticketNumber);
		String adminTicketMail = ticketPage.getTicket(HelpDeskTicketsPageObject.adminTicketXpath);
		assertEquals(adminTicketMail,"New Support Ticket QATEST "+ticketNumber);
		signup.deleteMail();
		driver.navigate().to("https://www.mailinator.com/");
		signup.enterInput(0, userEmail);
		signup.click("Go!");
		helpPage.selectTicket("Ticket "+ticketNumber+" created");
		String userTicketEmail = ticketPage.getTicket(HelpDeskTicketsPageObject.userTicketXpath);
		assertEquals(userTicketEmail,"Ticket "+ticketNumber+" created");
		signup.deleteMail();
		driver.get(ConfigProperties.site_url);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.originalGridId,HelpDeskTicketsPageObject.ticketName, 3, "View", 10, "Ticket", 2);
		signup.click("Yes");
		
	}

}
