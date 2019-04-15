package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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


public class AZ_170_RefreshGridAfterDeleteReordsTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_170_RefreshGridAfterDeleteReords(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		
		assertTrue(assetPage.isNavigateToExpectedPage(InventoryPageObject.urlLocationpage, 1, "Home"), "Fail: Doesn't redirented to Home page grid.");
		
		boolean pagination = assetPage.isPaginationTabPresent(1, InventoryPageObject.infoPagination_xpath, 1);
		int gridSize = assetPage.returnLocationGridSize(InventoryPageObject.originalGridId, 1,  InventoryPageObject.infoPagination_xpath, 1);
		
		assetPage.checkGridSizeAndCreateLocation(pagination, gridSize, 2, InventoryPageObject.infoPagination_xpath, 1);
		assertTrue(assetPage.isNavigateToExpectedPage(InventoryPageObject.urlActiveLocationpageOfPagination, 2, "2nd"), "Fail: Doesn't redirented to 2nd page.");
		
		assetPage.deleteRandomLocation(InventoryPageObject.originalGridId, "Location", 4, 3);
		signup.click("Yes");
		
		assertTrue(assetPage.isNavigateToExpectedPage(InventoryPageObject.urlLocationpage, 1, "Home"), "Fail: Doesn't redirented to Home page grid.");
		
	}

}
