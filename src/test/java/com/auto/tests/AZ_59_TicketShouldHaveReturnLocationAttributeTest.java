package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_59_TicketShouldHaveReturnLocationAttributeTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	UsersPage userPage;
	AssetsPage assetPage;
	
	@Test
	public void az_59_TicketShouldHaveReturnLocationAttributeTest(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        SoftAssert softAssertion= new SoftAssert();
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
		userPage.selectOption(3, ConfigProperties.location);
		signup.clickWithScroll("Done");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 1, "View", 10, "Tickets", 2);
		assertTrue(userPage.isRetrunLocationPresent(ConfigProperties.location), "[Return Loaction] doesn't present on Ticket");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Edit", 10, "Tickets", 2);
		signup.click("Yes");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
		log("Created Ticket delete from Grid", ILogLevel.TEST);
	}

}
