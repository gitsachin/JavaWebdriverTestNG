package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_130_VerifySaveAndSendEmailButtonOnHelpDeskTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	HelpDeskPage helpPage;
	
	@Test(priority=0)
	public void az_130_HelpDeskTicketsVerifyButtonAndEmail() throws AWTException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        helpPage = new HelpDeskPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		signup.click("Action");
		signup.click("New ticket");
		assertTrue(assetPage.isExpectedPopupOpen("Add Ticket"), "Fail: [Add Ticket] pop-up doesn't open.");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		userPage.selectOption(1, ConfigProperties.departName);
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, ConfigProperties.asset, "Asset");
		ticketPage.clickSearchOption();
		basepage.waitForWorkAroundTime();
		signup.clickWithScroll("Create");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 2, "Edit", 10, "All ticket", 2);
		assertEquals(driver.findElement(By.xpath(HelpDeskTicketsPageObject.saveButton_xapth)).getText(),"Save & Send E-Mail");
		signup.clickWithScroll("Save & Send E-Mail");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Delete", 10, "All ticket", 2);
		signup.click("Yes");
		log("Ticket delete from Grid", ILogLevel.TEST);
		
	}

}
