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

public class AZ_238_UserUnableToCreateNewLocationTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void AZ_238_UserUnableToCreateNewLocation(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assertTrue(assetPage.isExpectedPopupOpen("New Location"), "Fail: 'New Location'pop-up doesn't open.");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.locationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		assertTrue(assetPage.isExpectedPopupOpen("New Location"), "Fail: 'New Location' pop-up doesn't open.");
		assetPage.clickOnArrowIconOfFields("Client", 2);
		assetPage.enterSearchString("QATEST", 1, "Client");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		assertFalse(assetPage.isExpectedPopupOpen("New Location"), "Fail: 'New Location' pop-up doesn't close.");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation is not perform.");
		assetPage.selectLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName, 1);
		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName,1, 4, 3);
		signup.click("Yes");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInGridAfterDelete(InventoryPageObject.blankGrid_xpath, InventoryPageObject.locationName, InventoryPageObject.blankGridtext), "Fail: Location doesn't delete.");
	}

}
