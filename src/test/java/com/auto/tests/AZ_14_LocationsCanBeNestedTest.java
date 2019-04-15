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



public class AZ_14_LocationsCanBeNestedTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_14_LocationsCanBeNested(){
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
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.locationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation fail.");
		
		
		assertFalse(assetPage.verifyNestedIconPresent(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1, false),"Fail: [Circle] icon doesn't present.");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_A_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Building", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_B_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Floor", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_C_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Room", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_D_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "ISLE", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_E_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Section", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1); 
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_F_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "BIN", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.verifyNestedIconPresent(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1, true),"Fail: [Nested Arrow] icon doesn't present.");
		//assetPage.clickOnNestedArrowIcon(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1);
		assertTrue(assetPage.isNestedLocationCreated("Location Table: Nested location", 2), "Fail");
		
		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName,1, 4, 3);
		signup.click("Yes");
		
		
		
	}
	

}
