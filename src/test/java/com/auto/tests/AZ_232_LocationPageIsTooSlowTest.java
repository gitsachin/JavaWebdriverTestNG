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

public class AZ_232_LocationPageIsTooSlowTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_232_LocationPageIsTooSlow() throws InterruptedException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		//Create Location
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		dashboard.selectOption(3, "QATEST");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.locationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		//Create assets with created Location
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Actions");
		String asset[] = new String[5];	
		for(int i=0; i<=4; i++){
			dashboard.clickButton("New asset");
			assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
			dashboard.selectOption(1, "QATEST");
			dashboard.selectOption(3, "Category1_DND");
			asset[i] = "Assets_"+i+"_"+BasePage.autogenerateNumber(7);
			signup.enterInput(2, asset[i]);
			dashboard.selectOption(2, InventoryPageObject.locationName);
			dashboard.selectOption(4, "QaManufacturer_DND");
			dashboard.selectOption(5, "QaAssetModelForAutomation_DND");
			dashboard.selectOption(6, "QaSupplierForAutomation_DND");
			dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, "SL"+BasePage.autogenerateNumber(5), "Serial No");
			assetPage.clickOnArrowIconOfFields("Status", 3);
			assetPage.enterSearchString("QaStatusLabelsForAutomation_DND", 5, "Status");
			signup.clickWithScroll("Create");
			Thread.sleep(2000);
		}
		int newCreatedAssets = asset.length;
		log("Total Asset: "+newCreatedAssets, ILogLevel.TEST);
		
		
		// Navigate to Location and Verify Location grid with created Assets
		dashboard.clickButton("Locations");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation is not perform.");
		int quantity = assetPage.returnQTY(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1);
		assertTrue(assetPage.isQtyisEqualWithAssetGridSize(quantity, newCreatedAssets), "Fail: Qty doesn't match with asset grid size.");
		
		
		//Navigate to Asset and delete created Assets
		dashboard.clickButton("Assets");
		for(int i=0; i<=(newCreatedAssets-1); i++){
			assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, asset[i], "Search string: ");
			assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
			assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, asset[i], 2, "Delete", 8, "Delete", 3);                            
			signup.click("Yes");
			log("["+asset[i]+"] Asset delete from Grid", ILogLevel.TEST);
		}
		
		//Navigate to Location to Edit location name
		dashboard.clickButton("Locations");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1, 4, 2);
		String location = "Location_"+BasePage.autogenerateNumber(7);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, location, "Location Name");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSave_xpath, 1, "Save");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation is not perform.");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, location, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, location, 1), "Fail: Search operation is not perform.");
		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", location,1, 4, 3);
		signup.click("Yes");
		log("["+location+"] Location delete from Grid", ILogLevel.TEST);
		
		//Location batch upload
		String excelSheetLocation = assetPage.readExcelFileCellData(ConfigProperties.locationBatch, "Location", "Sheet1", "Location Name *", 2);
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Location"), "Fail: 'Upload Batch Location' doesn't open.");
		dashboard.selectOption(2, "QATEST");
		assetPage.sendFile(ConfigProperties.locationBatch);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.uploadSuccessMessage_css)), InventoryPageObject.uploadSuccessMessage);
		signup.click("Close");
		assertFalse(assetPage.isExpectedPopupOpen("Upload Batch Location"), "Fail: 'Upload Batch Location' doesn't close.");
		basepage.navigateRefresh();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, excelSheetLocation, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, excelSheetLocation, 1), "Fail: Search operation is not perform.");
		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", excelSheetLocation,1, 4, 3);
		signup.click("Yes");
		log("["+excelSheetLocation+"] Location delete from Grid", ILogLevel.TEST);
		


		
	}

}
