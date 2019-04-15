package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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

public class AZ_129_AttributesNeedToHaveSharedValuesAcrossAllClientsTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_129_AttributesNeedToHaveSharedValuesAcrossAllClients(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		signup.click("Action");
		signup.click("New asset category");
		assertTrue(assetPage.isExpectedPopupOpen("New Asset Category"), "Fail: [New Asset Category ] pop-up doesn't open");
		assetPage.clickOnArrowIconOfFields("Client Field", 2);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldName_xpath, 1, InventoryPageObject.assetCatName, "Category Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.inputFieldColr_xpath, 1, InventoryPageObject.color, "Badge Color");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSaveCat_xpath, 1,"Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetCatName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Asset Categories", InventoryPageObject.assetCatName, 2), "Fail: Asset Category doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetCatName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Asset Categories", InventoryPageObject.assetCatName, 2), "Fail: Asset Category doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetCatName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetCatName, 1, "Eye Icon", 4, "Eye Icon to Hide", 2);
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.assetCatName), "Fail: Asset Category doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("QATEST", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetCatName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.assetCatName), "Fail: Asset Category doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.clickOnExpectedButton(InventoryPageObject.breadCrumbAssCat_xpath, 1, "Asset Categories Bredcrumb link");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetCatName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetCatName, 3, "Delete", 4, "Remove", 2);
		signup.click("Yes");
		log("Asset Category delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Labels");
		signup.click("Action");
		signup.click("New status label");
		assertTrue(assetPage.isExpectedPopupOpen("Create New Status Label"), "Fail: [Create New Status Label] pop-up doesn't open");
		assetPage.clickOnArrowIconOfFields("Client Field", 2);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldName_xpath, 1, InventoryPageObject.statusLab, "Status Label Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.inputFieldColr_xpath, 1, InventoryPageObject.color, "Badge Color");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateCatgories_xpath, 1,"Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.statusLab, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Status Label", InventoryPageObject.statusLab, 2), "Fail: Status Label doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.statusLab, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Status Label", InventoryPageObject.statusLab, 2), "Fail: Status Label doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.statusLab, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.statusLab, 1, "Eye Icon", 4, "Eye Icon to Hide", 2);
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.statusLab), "Fail: Status Label doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("QATEST", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.statusLab, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.statusLab), "Fail: Status Label doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.clickOnExpectedButton(InventoryPageObject.breadCrumbStatusLabels_xpath, 1, "Status Label Bredcrumb link");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.statusLab, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.statusLab, 3, "Delete", 4, "Remove", 2);
		signup.click("Yes");
		log("Status Label delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		signup.click("Action");
		signup.click("New manufacturer");
		assertTrue(assetPage.isExpectedPopupOpen("Create New Manufacturer"), "Fail: [Create New Manufacturer] pop-up doesn't open");
		assetPage.clickOnArrowIconOfFields("Client Field", 2);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldName_xpath, 1, InventoryPageObject.manufacturer, "Manufacturer Name");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateCatgories_xpath, 1,"Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.manufacturer, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Manufacturer", InventoryPageObject.manufacturer, 2), "Fail: Manufacturer doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.manufacturer, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Manufacturer", InventoryPageObject.manufacturer, 2), "Fail: Manufacturer doesn't added in table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.manufacturer, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.manufacturer, 1, "Eye Icon", 4, "Eye Icon to Hide", 2);
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.manufacturer), "Fail: Manufacturer doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("QATEST", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.manufacturer, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.manufacturer), "Fail: Manufacturer doesn't hide from table.");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.clickOnExpectedButton(InventoryPageObject.breadCrumbManufacturer_xpath, 1, "Manufacturer Bredcrumb link");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.manufacturer, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.manufacturer, 3, "Delete", 4, "Remove", 2);
		signup.click("Yes");
		log("Manufacturer delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		signup.click("Action");
		signup.click("New model");
		assertTrue(assetPage.isExpectedPopupOpen("Create New Model"), "Fail: [Create New Model] pop-up doesn't open");
		assetPage.clickOnArrowIconOfFields("Client Field", 2);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.clickOnArrowIconOfFields("Manufacturers", 3);
		assetPage.enterAndSelectManufacturer(ConfigProperties.manufacturer, 1, "Select Manufacturer");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldName_xpath, 1, InventoryPageObject.assetModel, "Asset Model Name");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateCatgories_xpath, 1,"Create");
		assertFalse(assetPage.isExpectedPopupOpen("Create New Model"), "Fail: [Create New Model] pop-up doesn't close");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetModel, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Asset Model", InventoryPageObject.assetModel, 2), "Fail: Asset Model doesn't added in table.");
		
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetModel, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Asset Model", InventoryPageObject.assetModel, 2), "Fail: Asset Model doesn't added in table.");
		
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetModel, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetModel, 1, "Eye Icon", 5, "Eye Icon to Hide", 2);
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.assetModel), "Fail: Asset Model doesn't hide from table.");
		
		assetPage.clickOnArrowIconOfFields("QATEST", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetModel, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemHide(InventoryPageObject.searchTableId, InventoryPageObject.assetModel), "Fail: Asset Model doesn't hide from table.");
		
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("All Clients", 1, "Select Client");
		assetPage.clickOnExpectedButton(InventoryPageObject.breadCrumbAssModel_xpath, 1, "Asset Model Bredcrumb link");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetModel, "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetModel, 3, "Delete", 5, "Remove", 2);
		signup.click("Yes");
		log("Asset Model delete from Grid", ILogLevel.TEST);
		
		
	}

}
