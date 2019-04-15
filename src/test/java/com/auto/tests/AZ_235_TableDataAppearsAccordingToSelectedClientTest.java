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

public class AZ_235_TableDataAppearsAccordingToSelectedClientTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_235_TableDataAppearsAccordingToSelectedClient(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
 
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assertTrue(assetPage.isClientListOpen(InventoryPageObject.listClient_css, "Clients", "Locations"), "Fail: Client list doesn't open");
		assetPage.enterSearchString("QATEST", 1, "Client");
		assertFalse(assetPage.isClientListOpen(InventoryPageObject.listClient_css, "Clients", "Locations"), "Fail: Client list doesn't close");
		String client = assetPage.returnExpectedText(InventoryPageObject.locationPageClientField_xapath);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.location, "Location search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnEditLocationIcon(InventoryPageObject.searchTableId, "Location", 4, 2);
		assertTrue(assetPage.isExpectedTextPresentInExpectedField(InventoryPageObject.locationPageClientField_xapath, client, "Clients", "Location>Edit Location Screen"), "Fail: Client name appears diffrent");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSave_xpath, 1, "Save");
		
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Models Manage asset models");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assertTrue(assetPage.isClientListOpen(InventoryPageObject.listClient_css, "Clients", "Asset Models"), "Fail: Client list doesn't open in 'Asset Models' screen");
		assetPage.enterSearchString("QATEST", 1, "Client");
		assertFalse(assetPage.isClientListOpen(InventoryPageObject.listClient_css, "Clients", "Asset Models"), "Fail: Client list doesn't close in 'Asset Models' screen");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.assetModels, "Asset Models search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.assetModels, 2, "Edit", 5, "Edit Screen", 2);
		assertTrue(assetPage.isExpectedTextPresentInExpectedField(InventoryPageObject.locationPageClientField_xapath, client, "Clients", "Asset Models>Edit Model Screen"), "Fail: Client name appears diffrent");
		
	}

}
