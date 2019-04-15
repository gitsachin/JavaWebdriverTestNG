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

public class AZ_8_AttributesSectionsHaveViewButtonTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_8_AttributesSectionsHaveViewButton(){
		
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
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetCategoryForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaAssetCategoryForAutomation_DND", 1, "Eye Icon", 4, "Asset Category", 2), "Fail: [Eye Icon] doesn't present in 'Asset Category' table");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaLicenseCategoryForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaLicenseCategoryForAutomation_DND", 1, "Eye Icon", 3, "License Category", 2), "Fail: [Eye Icon] present in 'License Category' table");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Labels");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaStatusLabelsForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaStatusLabelsForAutomation_DND", 1, "Eye Icon", 4, "Status Labels", 2), "Fail: [Eye Icon] doesn't present in 'Status Labels' table");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaManufacturerForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaManufacturerForAutomation_DND", 1, "Eye Icon", 4, "Manufacturers", 2), "Fail: [Eye Icon] doesn't present in 'Manufacturers' table");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetModelForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaAssetModelForAutomation_DND", 1, "Eye Icon", 5, "Asset Model", 2), "Fail: [Eye Icon] doesn't present in 'Asset Model' table");
	
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Suppliers");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaSupplierForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isEyeIconPresent(InventoryPageObject.searchTableId, "QaSupplierForAutomation_DND", 1, "Eye Icon", 4, "Suppliers", 2), "Fail: [Eye Icon] doesn't present in 'Suppliers' table");
	}

}
