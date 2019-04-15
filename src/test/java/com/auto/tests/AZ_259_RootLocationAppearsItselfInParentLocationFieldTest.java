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

public class AZ_259_RootLocationAppearsItselfInParentLocationFieldTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void AZ_259_RootLocationAppearsItselfInParentLocationField(){
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
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		String parentLocation =  InventoryPageObject.locationName;
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, parentLocation, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, parentLocation, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, parentLocation, 1), "Fail: Search operation is not perform.");
		assertFalse(assetPage.verifyNestedIconPresent(InventoryPageObject.searchTableId, "Location", parentLocation, 1, false),"Fail: [Circle] icon doesn't present.");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", parentLocation, 1);
		
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.subLocationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Building", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, parentLocation, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.verifyNestedIconPresent(InventoryPageObject.searchTableId, "Location", parentLocation, 1, true),"Fail: [Nested Arrow] icon doesn't present.");
		assertTrue(assetPage.isNestedLocationCreated("Location Table: Nested location", 2), "Fail");
		
		assetPage.clickOnViewEditDeleteSubLocationIcon(InventoryPageObject.locationChildTable_class, "Location", InventoryPageObject.subLocationName, 1, 2);
		assertTrue(assetPage.verifyParentLoc(InventoryPageObject.parentLocField_xapth, parentLocation, "Edit Sub Location"), "Fail: Root Location deosn't appear in 'Parent Location' fields.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1, "Cancel");
		
		assetPage.clickOnViewEditDeleteLocationIcon(InventoryPageObject.searchTableId, "Location", parentLocation, 1, 4, 2);
		assertTrue(assetPage.verifyParentLoc(InventoryPageObject.parentLocField_xapth, parentLocation, "Edit Parent Location"), "Fail: Root Location deosn't appear in 'Parent Location' fields.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1, "Cancel");

		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName,1, 4, 3);
		signup.click("Yes");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	
		assertTrue(assetPage.isItemPresentInGridAfterDelete(InventoryPageObject.blankGrid_xpath, InventoryPageObject.locationName, InventoryPageObject.blankGridtext), "Fail: Location doesn't delete.");
		
	}

}
