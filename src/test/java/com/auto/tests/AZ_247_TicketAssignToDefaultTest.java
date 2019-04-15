package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_247_TicketAssignToDefaultTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	TicketPage ticketPage;
	AssetsPage assetPage;
	HelpDeskPage helpDesk;
	
	@Test
	public void az_247_TicketAssignToDefault() throws FileNotFoundException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        helpDesk = new HelpDeskPage(driver);
        
        signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		signup.click("New ticket");
		assertTrue(dashboard.isExpectedPopupOpen("Add Ticket"), "Fail: [Add Ticket] pop-up missing.");
		assertTrue(ticketPage.verifyAssignee("Client", 3, "None", "Assign To", 4, "Nobody"), "Fail: Assignee  doesn't appears according to Client.");
		assetPage.clickOnArrowIconOfFields("Client Field", 3);
		assetPage.enterSearchString("QATEST", 2, "Select Client");
		assertTrue(ticketPage.verifyAssignee("Client", 3, "QATEST", "Assign To", 4, "SA"), "Fail: Assignee  doesn't appears according to Client.");
		assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel");
		ticketPage.clickOnArrowIconOfFields("Client", 1);
		ticketPage.enterSearchString("QATEST", 2, "Client");
		signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonTicketCsv_xpath, 1, "Export as CSV");
		assertTrue(assetPage.fileExist(".csv"), "Fail: Ticket file doesn't exported as .CSV.");
	   	assetPage.deleteFile();
	   	signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonTicketExcel_xpath, 1, "Export as Excel");
		assertTrue(assetPage.fileExist(".xlsx"), "Fail: Ticket file doesn't exported as .xlsx.");
		assetPage.deleteFile();
		
	}

}
