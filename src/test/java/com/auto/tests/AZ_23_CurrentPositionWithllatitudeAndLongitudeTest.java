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

public class AZ_23_CurrentPositionWithllatitudeAndLongitudeTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_23_CurrentPositionWithllatitudeAndLongitude(){
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
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, "Sub Location_A_"+assetPage.returnRandomNumber(1000), "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Building", "Location Type");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationCity_xpath,1, "Los Angeles", "City");
		assetPage.clickOnArrowIconOfFieldsOnLocation("Country", 2);
		assetPage.enterSearchString("USA", 1, "Country");
		assetPage.clickOnArrowIconOfFieldsOnLocation("State", 3);
		assetPage.enterSearchString("California", 1, "State");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationZip_xpath,1, "90001", "Zip");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.linklSetLatLog_xpath, "Set Latitude Longitude", "Localization:New Location>Gerneral Tab"));
		assertFalse(assetPage.returnvalueOfExpectedField(InventoryPageObject.textFieldLocationLat_xpath, "Latitude", "Initially"), "Fail: Initially Latitude is set.");
		assertFalse(assetPage.returnvalueOfExpectedField(InventoryPageObject.textFieldLocationLong_xpath, "Longitude", "Initially"), "Fail: Initially Latitude is set.");
		signup.clickButton("Set Latitude Longitude");
		assetPage.setLatLong();
		assertTrue(assetPage.returnvalueOfExpectedField(InventoryPageObject.textFieldLocationLat_xpath, "Latitude", "Set"), "Fail: Initially Latitude is set.");
		assertTrue(assetPage.returnvalueOfExpectedField(InventoryPageObject.textFieldLocationLong_xpath, "Longitude", "Set"), "Fail: Initially Latitude is set.");
		
		//assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
	}

}
