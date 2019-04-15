package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
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

public class AZ_51_CreateAssetFilterColumnsTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_51_CreateAssetFilterColumns() throws InterruptedException {
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		Assert.assertTrue(assetPage.isFilterArrowIconPresent(InventoryPageObject.assetsTableHeader_xpath), "Fail: Filter arrow icon doesn't present.");
		
		assetPage.clickOnExpectedFilterArrowIcon("Category",1);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Category"), "Fail: [Category] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",3);
		assetPage.clickOnApplyButton(6);
		assertTrue(assetPage.verifyColor(0,"rgba(255, 0, 0, 1)"),"Red color not present on screen");
		assetPage.clickOnArrow();
		Assert.assertTrue(assetPage.isFilterPanelOpen("Category"), "Fail: [Category] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",3);
		assetPage.clickOnApplyButton(6);
		assertTrue(assetPage.verifyColor(0,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		assetPage.clickOnExpectedFilterArrowIcon("Category",1);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Category"), "Fail: [Category] apply filter doesn't open.");
		assetPage.clickOnLabel("Select all",3);
		assetPage.clickOnApplyButton(6);
		assertTrue(assetPage.verifyColor(0,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		
		
		
		assetPage.clickOnExpectedFilterArrowIcon("Model",2);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Model"), "Fail: [Modely] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",4);
		assetPage.clickOnApplyButton(7);
		assertTrue(assetPage.verifyColor(1,"rgba(255, 0, 0, 1)"),"Red color not present on screen");
		assetPage.clickOnArrow();
		Assert.assertTrue(assetPage.isFilterPanelOpen("Model"), "Fail: [Modely] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",4);
		assetPage.clickOnApplyButton(7);
		assertTrue(assetPage.verifyColor(1,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		assetPage.clickOnExpectedFilterArrowIcon("Model",2);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Model"), "Fail: [Modely] apply filter doesn't open.");
		assetPage.clickOnLabel("Select all",4);
		assetPage.clickOnApplyButton(7);
		assertTrue(assetPage.verifyColor(1,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		
		assetPage.clickOnExpectedFilterArrowIcon("Locations",3);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Locations"), "Fail: [Locations] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",5);
		assetPage.clickOnApplyButton(8);
		assertTrue(assetPage.verifyColor(2,"rgba(255, 0, 0, 1)"),"Red color not present on screen");
		assetPage.clickOnArrow();
		Assert.assertTrue(assetPage.isFilterPanelOpen("Entity"), "Fail: [Locations] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",5);
		assetPage.clickOnApplyButton(8);
		assertTrue(assetPage.verifyColor(2,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		assetPage.clickOnExpectedFilterArrowIcon("Related Entities",3);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Entity"), "Fail: [Locations] apply filter doesn't open.");
		assetPage.clickOnLabel("Select all",5);
		assetPage.clickOnApplyButton(8);
		assertTrue(assetPage.verifyColor(2,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		
		assetPage.clickOnExpectedFilterArrowIcon("Status",4);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Status"), "Fail: [Statusy] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",6);
		assetPage.clickOnApplyButton(9);
		assertTrue(assetPage.verifyColor(3,"rgba(255, 0, 0, 1)"),"Red color not present on screen");
		assetPage.clickOnArrow();
		Assert.assertTrue(assetPage.isFilterPanelOpen("Status"), "Fail: [Statusy] apply filter doesn't open.");
		assetPage.clickOnLabel("Blank",6);
		assetPage.clickOnApplyButton(9);
		assertTrue(assetPage.verifyColor(3,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
		assetPage.clickOnExpectedFilterArrowIcon("Status",4);
		Assert.assertTrue(assetPage.isFilterPanelOpen("Status"), "Fail: [Statusy] apply filter doesn't open.");
		assetPage.clickOnLabel("Select all",6);
		assetPage.clickOnApplyButton(9);
		assertTrue(assetPage.verifyColor(3,"rgba(119, 119, 119, 1)"),"gray color not present on screen");
	}

}
