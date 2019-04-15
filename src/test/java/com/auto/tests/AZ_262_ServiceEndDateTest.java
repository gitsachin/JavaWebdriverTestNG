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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_262_ServiceEndDateTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	

	@Test(priority=4)
	public void az_262_ServiceEndDate(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		assertTrue(assetPage.isExpectedFiledPresent("Service Start Date", "Asset: Create New Asset"),"Fail: 'Service Start Date' field doesn't present in 'Create New Asset' screen.");
		assertTrue(assetPage.isExpectedFiledPresent("Service End Date", "Asset: Create New Asset"),"Fail: 'Service End Date' field doesn't present in 'Create New Asset' screen.");
		assertFalse(assetPage.isExpectedFiledPresent("Manufacturer Warranty Start Date(s)", "Asset: Create New Asset"),"Fail: 'Manufacturer Warranty Start Date(s)' field is present in 'Create New Asset' screen.");
		assertFalse(assetPage.isExpectedFiledPresent("Manufacturer Warranty End Date(s)", "Asset: Create New Asset"),"Fail: 'Manufacturer Warranty End Date(s)' field is present in 'Create New Asset' screen.");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.asset, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.asset, 1, "Edit", 8, "Assets", 3);
		assertTrue(assetPage.isExpectedFiledPresent("Service Start Date", "Asset: Edit Asset"),"Fail: 'Service Start Date' field doesn't present in 'Create New Asset' screen.");
		assertTrue(assetPage.isExpectedFiledPresent("Service End Date", "Asset: Edit Asset"),"Fail: 'Service End Date' field doesn't present in 'Create New Asset' screen.");
		assertFalse(assetPage.isExpectedFiledPresent("Manufacturer Warranty Start Date(s)", "Asset: Edit Asset"),"Fail: 'Manufacturer Warranty Start Date(s)' field is present in 'Create New Asset' screen.");
		assertFalse(assetPage.isExpectedFiledPresent("Manufacturer Warranty End Date(s)", "Asset: Edit Asset"),"Fail: 'Manufacturer Warranty End Date(s)' field is present in 'Create New Asset' screen.");
	}

}
