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

public class AZ_307_ContractTabHighlightWithGreenIfAssetHasNotExpiedContractTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;

	@Test
	public void az_307_ContractTabHighlightWithGreenIfAssetHasNotExpiedContract(){

		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);

		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		
		dashboard.clickOnSpecificSubmenu("Asset", 2);
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		
		dashboard.selectOption(1, "QATEST");
		dashboard.selectOption(3, "Category1_DND");
		
		signup.enterInput(2, InventoryPageObject.assetName);
		dashboard.selectOption(2, ConfigProperties.location);
		dashboard.selectOption(4, ConfigProperties.manufacturer);
		dashboard.selectOption(5, ConfigProperties.assetModel);
		dashboard.selectOption(6, ConfigProperties.supplier);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, "SL"+BasePage.autogenerateNumber(5), "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 3);
		assetPage.enterSearchString(ConfigProperties.statusLabels, 5, "Status");
		String serialNumber = "SL"+BasePage.autogenerateNumber(5);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, serialNumber, "Serial No");
		signup.clickWithScroll("Create");
		
		String currentDate = basepage.currentOnlyDate();
		String expirationDate = basepage.addDateToCurrentDate(7);
	
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInTable("Asset", InventoryPageObject.assetName), "Fail: ["+InventoryPageObject.assetName+"] doesnt created.");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Asset: Update", 3);
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract"), "Fail: [Contract] tab doesn't present.");
		assetPage.clickOnExpectedTab("Contract");
		assertTrue(assetPage.verifyTabColorAccordingToAssignedContract("Contract", currentDate, expirationDate), "Fail: Assigned Contarct present but Contract Tab isn't highlighted with color.");
		
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Contract Manage Contract");
		signup.click("Actions");
		dashboard.clickButton("New Contract");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Contract");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Client");
		signup.enterInput(3, InventoryPageObject.licenseName);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldExpirationDate_xapth, 1, expirationDate, "Expiration Date");
		signup.clickWithScroll("Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName, 3), "Assert Fail: [Licenses] doesn't create.");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 1, "Edit", 7, "License", 3);
		assetPage.clickOnExpectedButton(InventoryPageObject.assignAsset_xpath, 1, "Assign Asset");
		assertTrue(assetPage.isExpectedPopupOpen("Assign Asset"), "Fail: 'Assign Asset' pop-up midssing.");
		assetPage.selectOption(5, "QATEST");
		assetPage.clickOnArrowIconOfFields("Asset", 6);
		assetPage.enterSearchString(InventoryPageObject.assetName, 1, "Assets");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonAssign_xpath, 1, "Assign");
		
		dashboard.clickOnSpecificSubmenu("Asset", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Asset: Update", 3);
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract"), "Fail: [Contract] tab doesn't present.");
		assetPage.clickOnExpectedTab("Contract");
		assertTrue(assetPage.verifyTabColorAccordingToAssignedContract("Contract", "", expirationDate), "Fail: Assigned Contarct doesn't present but Contract Tab is highlighted with Green color.");
		
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 1, "Edit", 7, "License", 3);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldExpirationDate_xapth, 1, currentDate, "Expiration Date");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSave_xpath, 1, "Save");
		
		dashboard.clickOnSpecificSubmenu("Asset", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Asset: Update", 3);
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract"), "Fail: [Contract] tab doesn't present.");
		assetPage.clickOnExpectedTab("Contract");
		assertTrue(assetPage.verifyTabColorAccordingToAssignedContract("Contract", currentDate, ""), "Fail: Assigned Contarct doesn't Expired but Contract Tab is highlighted with expected color.");
			
		dashboard.clickOnSpecificSubmenu("Asset", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Delete", 3);
		signup.click("Yes");
		log("["+InventoryPageObject.assetName+"] Asset delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 2, "Delete", 7, "Delete", 3);
		signup.click("Yes");
		log("["+InventoryPageObject.licenseName+"] License delete from Grid", ILogLevel.TEST);
		
		
	}



}
