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

public class AZ_109_LocationsQTYTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_109_LocationsQTYTest(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.location, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, ConfigProperties.location, 1), "Fail: Search operation is not perform.");
		int quantity = assetPage.returnQTY(InventoryPageObject.searchTableId, "Location", ConfigProperties.location, 1);
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", ConfigProperties.location, 1);
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", ConfigProperties.location, 1, 4, 1);
		int assetGridSize = assetPage.returnGridSize(InventoryPageObject.assettable, "Assets",2,  InventoryPageObject.infoPagination_xpath, 2);
		assertTrue(assetPage.isQtyisEqualWithAssetGridSize(quantity, assetGridSize), "Fail: Qty doesn't match with asset grid size.");
		

	}
	

}
