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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_152_ConsolidationOfButtonActionsIntoActionDropdownTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	
	@Test
	public void az_152_VerifyActionDropdown(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"New ticket"),"[New ticket] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickButton("Active Tickets");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"New ticket"),"[New ticket] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickButton("All Tickets");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"New ticket"),"[New ticket] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickButton("Ticket Settings");
		assertFalse(ticketPage.isButtonPresent("Action"),"Action button is present on page");
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickButton("Ticket Fields");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"New field"),"[New ticket] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyFieldInActionDropdown("New asset"),"[New assert] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Batch upload"),"[Batch upload] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickButton("Licenses");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyFieldInActionDropdown("New license"),"[New license] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Batch upload"),"[Batch upload] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		dashboard.clickButton("Locations");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		
	}

}
