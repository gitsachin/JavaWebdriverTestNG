package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
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

public class AZ_73_UnableToLookAtExistingLicenseTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_73_UnabletoLookExistingLicense(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Contract Manage Contract");
		signup.click("Action");
		dashboard.clickButton("	New Contract");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Contract");
		signup.enterInput(2, InventoryPageObject.licenseName);
		signup.clickWithScroll("Create");
		
		
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: '"+InventoryPageObject.licenseName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName, 3), "Assert Fail: [Licenses] doesn't create.");
		
		assetPage.findAndClickOnNewLicense(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName);
		assertTrue(assetPage.isLicenseOpen(), "Fail: License text doesn't present on header.");
		
		
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: '"+InventoryPageObject.licenseName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 2, "Delete", 6, "License", 3);
		signup.click("Yes");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage);
		log("License delete from Grid", ILogLevel.TEST);
		
		
		
	}

}
