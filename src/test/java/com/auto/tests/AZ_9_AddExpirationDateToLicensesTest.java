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

public class AZ_9_AddExpirationDateToLicensesTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_9_AddExpirationDateToLicenses(){
		
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
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Contract Manage Contarct");
		
		assertTrue(assetPage.isExpectedColumnPresent(InventoryPageObject.licenseTable, "Expiration Date"), "Fail: Expiration Date column doesn't present.");
		
		signup.click("Action");
		dashboard.clickButton("New Contract");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Contract");
		signup.enterInput(2, InventoryPageObject.licenseName);
		signup.clickWithScroll("Create");
		
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: '"+InventoryPageObject.licenseName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName, 3), "Assert Fail: [Licenses] doesn't create.");
		String expirtionDate = assetPage.returnExpirationDate(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName);
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 1, "Edit", 6, "Edit", 3);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldExpirationDate_xapth, 1, InventoryPageObject.enterDate, "Expiration Date: ["+InventoryPageObject.enterDate+"]");
		signup.clickWithScroll("Save");
		
		dashboard.clickOnSpecificSubmenu("Licenses", 1);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.licenseName, "Search string: '"+InventoryPageObject.licenseName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(assetPage.isExpirationDateUpdate(InventoryPageObject.searchTableId, "Licenses", InventoryPageObject.licenseName, expirtionDate), "Fail: Expiration date doesn't updated.");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.licenseName, 2, "Delete", 6, "Delete", 3);
		
		signup.click("Yes");
	}

}
