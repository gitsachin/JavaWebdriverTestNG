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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_242_ReplaceAssetSearchFieldWithSerialNumberTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	
	
	@Test
	public void az_242_ReplaceAssetearchFieldWithSerialNumber(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assertTrue(ticketPage.isExpectedFiledPresent("New ticket", "Serial Number"),"Fail: 'Serial Number' field doesn't present in 'Add Ticket' box.");
		assertTrue(ticketPage.isExpectedFiledPresent("New ticket", "Asset"), "Fail: 'Asset' field doesn't present in 'Add Ticket' box.");
		assertTrue(ticketPage.isExpectedFiledPresent("New ticket", "Manufacturer"), "Fail: 'Manufacturer' field doesn't present in 'Add Ticket' box.");
		assertTrue(ticketPage.isExpectedFiledPresent("New ticket", "Model"), "Fail: 'Model' field doesn't present in 'Add Ticket' box.");
		
		ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Asset", 1), "Fail: Manufacturer field is not Read Only fields.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Manufacturer", 2), "Fail: Manufacturer field is not Read Only fields.");
		assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Model", 3), "Fail: Model field is not Read Only fields.");
	}
	

}
