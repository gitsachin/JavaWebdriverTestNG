package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

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
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_46_HelpDeskTicketsNeedCustomizableFieldsTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	TicketPage ticketPage;
	AssetsPage assetPage;
	
	@Test(priority=0)
	public void az_46_HelpDeskTicketsNeedCustomizableFields() throws AWTException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
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
		dashboard.clickButton("Ticket Settings");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Settings");
		
		ticketPage.createDeartmentIfNotPresentInGrid(ConfigProperties.departName, "NEW DEPARTMENT", "Create");
		
		/*ticketPage.deleteItemFromGrid(1, HelpDeskTicketsPageObject.departName, 3, "Remove", "Delete", 4, 2);
		signup.click("Yes");
		log("created Department delete from Grid", ILogLevel.TEST);*/
		
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickOnInnerSubmenu("Tickets", "Attributes", "Fields");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewFields_xpath, 1, "New field");
		ticketPage.clickOnArrowIconForDepartmentField();
		ticketPage.enterSearchString(ConfigProperties.departName);
		assertEquals(basepage.getText(By.xpath(HelpDeskTicketsPageObject.containerDepartmentField)), ConfigProperties.departName);
		
		
	}

}
