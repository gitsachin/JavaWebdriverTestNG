package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_25_AssetModelsHaveAssetManufacturerParentTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_25_AssetModelsHaveAssetManufacturerParent(){
		
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
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewModel_xpath, 1, "New Model");
		assertTrue(assetPage.isExpectedPopupOpen("Create New Model"), "Fail: [Create New Model] pop-up doesn't open");
		assertTrue(assetPage.isExpectedFieldMandatory("Manufacturer", 1), "Fail: [Manufacturer] field doesn't mandatory.");
		assertTrue(assetPage.isExpectedFieldMandatory("Model Name", 2), "Fail: [Model Name] field doesn't mandatory.");
		assetPage.clickOnArrowIconOfFields(InventoryPageObject.arrowIconDepartmentField, 1);
		assetPage.enterSearchString("Sample Manufacturer 1", 1, "Manufacturer");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldName_xpath, 1, InventoryPageObject.model, "Model Name");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreate_xpath, 1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.model, "Search string: '"+InventoryPageObject.model+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Model", InventoryPageObject.model,2), "Assert Fail: [Licenses] doesn't presemt in License grid.");	
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.model, 3, "Delete", 5, "Model", 2);;
		
		signup.click("Yes");
		
		log("Model delete from Grid", ILogLevel.TEST);
	}

}
