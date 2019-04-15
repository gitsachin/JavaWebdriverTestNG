package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import com.auto.pages.SignUpPage;
import com.auto.pages.UsersPage;

public class AZ_93_DashboardLimitResultsInCategoriesAndActiveHelpTicketsSectionTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	AssetsPage assetPage;
	
	
	@Test
	public void az_93_DashboardLimitResultsInCategoriesAndActiveHelpTicketsSectiont() throws AWTException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason"); 
		
		assertTrue(dashboard.isListItemSameAsExpected(DashboardPageObject.listActiveHelpDeskTickets_xpath, 10, "Active Help Desk Tickets"), "Fail: [Active Help Desk Tickets] doesn't contains 10 items in list.");
		assertTrue(dashboard.isListItemSameAsExpected(DashboardPageObject.listAssetByCat_xpath, 10, "Assets by Category"), "Fail: [Assets by Category] doesn't contains 10 items in list.");
		assertTrue(dashboard.isExpectedButtonPresent("Dashboard: Active Help Desk Tickets", DashboardPageObject.buttonViewMore_xpath, "VIEW MORE"), "Fail: [View MOre] button doesn't present.");
		assertTrue(dashboard.isExpectedStringPresentInList(DashboardPageObject.listAssetByCat_xpath, "Other", "Assets by Category"), "Fail: [Other] doesn't present.");
		
		dashboard.clickOnExpectedButton(DashboardPageObject.buttonViewMore_xpath, 1, "VIEW MORE");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		
		
	}

}
