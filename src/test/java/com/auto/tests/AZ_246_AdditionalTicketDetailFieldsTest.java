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
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_246_AdditionalTicketDetailFieldsTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;
	RolePage rolePage;
	
	@Test
	public void az_246_AdditionalTicketDetailFields(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    assetPage = new AssetsPage(driver);
	    rolePage = new RolePage(driver);
	    system = new SystemPage(driver);
	   
	    driver.navigate().to(ConfigProperties.signin_url);
	  
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.ticket, 1, "View", 9, "Tickets", 2);
		assertTrue(ticketPage.isExpectedFieldPresentInDetailViewPage(HelpDeskTicketsPageObject.ticketDetailTable,"Manufacturer"));
		assertTrue(ticketPage.isExpectedFieldPresentInDetailViewPage(HelpDeskTicketsPageObject.ticketDetailTable,"Serial"));
		assertTrue(ticketPage.isExpectedFieldPresentInDetailViewPage(HelpDeskTicketsPageObject.ticketDetailTable,"Model"));
	}

}
