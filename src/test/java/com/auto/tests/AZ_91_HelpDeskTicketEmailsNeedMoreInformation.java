package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.ProjectPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_91_HelpDeskTicketEmailsNeedMoreInformation extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	HelpDeskPage helpPage;
	RolePage rolePage;
	
	@Test
	public void az_91_HelpDeskTicketEmailsNeedMoreInformation() throws InterruptedException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        helpPage = new HelpDeskPage(driver);
        rolePage = new RolePage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		Thread.sleep(3000);
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
		signup.clickWithScroll("Done");
		rolePage.getTable(InventoryPageObject.originalGridId,1);
		driver.get(SignUpPageObject.mailinator_url);
		signup.enterInput(0, ConfigProperties.email);
		signup.click("Go!");
		signup.selectMail("New Support Ticket QATEST");
		basepage.switchFrame("msg_body");
		ticketPage.verifyMail();
		signup.clickButton("Click here to view the ticket");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    assertTrue(ticketPage.verifyStatusOfTicket("Ticket","Status",1));
		dashboard.clickButton("All Tickets");
		helpPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,HelpDeskTicketsPageObject.ticketName, 3, "Delete", 10, "Ticket", 2);
		signup.click("Yes");
	    driver.close();
	    driver.switchTo().window(tabs.get(0));
	    signup.deleteMail();
	}

}
