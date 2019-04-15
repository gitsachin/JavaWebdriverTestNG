package com.auto.tests;

import static org.testng.Assert.assertEquals;
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

public class AZ_258_WrongDataAppearsWhenUserSwitchToAnotherClientTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_258_WrongDataAppearsWhenUserSwitchToAnotherClient(){
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
		assetPage.clickOnArrowIconOfFields("Client", 2);
		assetPage.enterSearchString("QATEST", 1, "Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationName_xpath, 1, InventoryPageObject.locationName, "Location Name");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLocationType_xpath, 1, "Region", "Location Type");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreateInpopup_xpath,1, "Create");
		
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, InventoryPageObject.locationName, 1), "Fail: Search operation is not perform.");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("abcd1", 1, "Client");
		assertTrue(assetPage.isLocationsPresentInGrid(InventoryPageObject.searchTableId), "Location present on grid table");
		driver.navigate().refresh();
		
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("abcd1", 1, "Client");
		assertTrue(assetPage.isLocationsPresentInGrid(InventoryPageObject.searchTableId), "Location present on grid table");
		
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Client");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.locationName, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.deleteLocation(InventoryPageObject.searchTableId, "Location", InventoryPageObject.locationName,1, 4, 3);
		signup.click("Yes");
	}

}
