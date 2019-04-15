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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_166_VerifyFieldInLicenseRecordTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	
	@Test(priority=0)
	public void az_166_VerifyFieldInLicenseRecord() throws AWTException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Licenses", 1);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		signup.click("Action");
		dashboard.clickButton("New license");
		assertTrue(assetPage.isExpectedFieldMandatory("Effective Date",8),"Fail: [Effective Date] field doesn't mandatory.");
		assertTrue(assetPage.isExpectedFieldMandatory("Sales Order",9),"Fail: [Sales Order] field doesn't mandatory.");
		
	}

}
