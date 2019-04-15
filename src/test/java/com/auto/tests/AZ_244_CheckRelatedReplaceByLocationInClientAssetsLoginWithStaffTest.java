package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_244_CheckRelatedReplaceByLocationInClientAssetsLoginWithStaffTest extends TestCore {
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
	public void az_244_CheckRelatedReplaceByLocationInClientAssetsLoginWithStaff(){
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
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		
		assertTrue(assetPage.isExpectedColumnPresent(InventoryPageObject.assettable,"Locations"),"[Locations] arrow icon does not present in row");
		assertFalse(assetPage.isExpectedColumnPresent(InventoryPageObject.assettable,"Related"),"[Related] arrow present in row");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
        system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaTest", "Search string");
		
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QATEST", 2, "View", 6, 1);
		dashboard.clickOnSpecificSubmenu("Assets",2);
		
		assertTrue(assetPage.isExpectedColumnPresent(InventoryPageObject.assetDataTable,"Locations"),"[Locations] column does not present in row");
		assertFalse(assetPage.isExpectedColumnPresent(InventoryPageObject.assetDataTable,"Related"),"[Related] column icon present in row");
	}

}
