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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_268_ToolTipAppearsOnEditTicketpopupTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	HelpDeskPage helpPage;
	
	@Test
	public void AZ_268_ToolTipAppearsOnEditTicketpopup(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        helpPage = new HelpDeskPage(driver);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpPage.isSearchResultPresent(InventoryPageObject.searchTableId, ConfigProperties.ticket, 2), "Fail: No data appears according to search string.");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.ticket, 1, "Eye Ball", 10, "All ticket", 2);
		ticketPage.mouseOverOnElement(HelpDeskTicketsPageObject.butonEdit_xpath);
		assertTrue(ticketPage.isTooltipAppearForExpectedElement(HelpDeskTicketsPageObject.butonEdit_xpath, "Edit Ticket", "Ticket Details"),"Fail: [Edit Ticket] tool tip doesn't visible.");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.butonEdit_xpath, 1, "Edit Ticket");
		assertTrue(dashboard.isExpectedPopupOpen("Edit Ticket"), "Fail: [Edit Ticket] popup missing.");
		assertFalse(ticketPage.isTooltipAppearForExpectedElement(HelpDeskTicketsPageObject.tooltip_xpath, "Edit Ticket", "Ticket Details"),"Fail: [Edit Ticket] tool tip still visible.");
		
	}


}
