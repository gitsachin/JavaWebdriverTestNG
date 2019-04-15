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
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;


public class AZ_263_LocationFieldPresentInUserTicketDashboardTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	AssetsPage assetPage;
	
	@Test
	public void az_263_LocationFieldPresentInUserTicketDashboard(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assertTrue(dashboard.isExpectedColumnPresent(HelpDeskTicketsPageObject.ticketTableHeader_id, "Location"), "Fail: Location column doesn't present in Ticket table.");
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		assertTrue(dashboard.isExpectedColumnPresent(SystemPageObject.userGridHeader_Id, "Location"), "Fail: Location column doesn't present in Ticket table.");	
	}

}
