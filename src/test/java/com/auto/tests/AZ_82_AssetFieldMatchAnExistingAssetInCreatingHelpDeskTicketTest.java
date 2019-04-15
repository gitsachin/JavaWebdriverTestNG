package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;


public class AZ_82_AssetFieldMatchAnExistingAssetInCreatingHelpDeskTicketTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	
	
	@Test
	public void az_82_AssetFieldMatchAnExistingAssetInCreatingHelpDeskTicket() throws AWTException, InterruptedException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        ticketPage = new TicketPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName); 
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Active Tickets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, "Qa Sublect 01", "Subject");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, "asset", "Asset");
		assertTrue(ticketPage.isSuggestionListAppear("asset", ""), "Fail: Asset suggestion doesn't appear.");
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, "Invalid String", "Asset");
		Thread.sleep(3);
		assertTrue(ticketPage.isSuggestionListAppear("", "No Asset Found"), "Fail: [No Asset Found] text doesn't appear.");
		driver.findElement(By.xpath(HelpDeskTicketsPageObject.textFieldAsset_xpath)).clear();
		/*assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCreate_xpath, 1, "Create");
		assertTrue(ticketPage.isFieldValidate(), "Fail: [Asset] doesn't required field.");*/
		
		
	}
}
