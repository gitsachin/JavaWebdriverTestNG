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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_92_BLANKFilterIfBlanksExistTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	AssetsPage assetPage;
	TicketPage ticketPage;
	
	
	@Test
	public void az_92_BLANKFilterIfBlanksExist() throws AWTException{
		
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
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.clickOnFilterArrowIcon(4);
		assetPage.clickOnBlankCheckbox(InventoryPageObject.modelFilterId);
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonApplyFilter_xpath, 1, "[Apply] Filter");
		assertTrue(assetPage.isFilterApplied(InventoryPageObject.searchTableId, 4, "Model: Blank"), "Fail: Model filter doesn't applied");
		
	}

}
