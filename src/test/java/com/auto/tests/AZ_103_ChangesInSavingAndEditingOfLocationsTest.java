package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_103_ChangesInSavingAndEditingOfLocationsTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_103_ChangesInSavingAndEditingOfLocationsTest(){
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
		signup.click("Action");
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.rootLocation, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.rootLocation, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAdded_PresentInLocationGrid(InventoryPageObject.searchTableId, "Location", InventoryPageObject.rootLocation, 1), "Fail: [Location] doesn't created.");
		assertFalse(assetPage.verifyNestedIconPresent(InventoryPageObject.searchTableId, "Location", InventoryPageObject.rootLocation, 1, false),"Fail: [Circle] icon doesn't present.");
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", InventoryPageObject.rootLocation, 1, 4, 2);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1,AssetsCategriesPageObject.locationName, "Location Name");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSave_xpath, 1, "Save");
		
		dashboard.clickButton("Locations");
		signup.click("Action");
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.rootLocation, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.rootLocation, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", InventoryPageObject.rootLocation, 1, 4, 2);
		assetPage.clickOnArrowIconOfFields("Parent Location", 1);
		assetPage.enterSearchString(AssetsCategriesPageObject.locationName, 1, "Parent Location ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSave_xpath, 1, "Save");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.rootLocation, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(assetPage.isItemAdded_PresentInLocationGrid(InventoryPageObject.searchTableId, "Location", InventoryPageObject.rootLocation, 1), "Fail: [Location] doesn't created.");
		assertTrue(assetPage.isLocationPresentInChildTable("Location Table: Nested location", 1, InventoryPageObject.rootLocation), "Fail: ["+InventoryPageObject.rootLocation+"] doesn't present under root.");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, AssetsCategriesPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", AssetsCategriesPageObject.locationName, 1, 4, 3);
		signup.click("Yes");		
		
	}

}
