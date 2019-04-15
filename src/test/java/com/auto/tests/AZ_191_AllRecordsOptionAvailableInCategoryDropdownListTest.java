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

public class AZ_191_AllRecordsOptionAvailableInCategoryDropdownListTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;

	@Test
	public void az_191_AllRecordsOptionAvailableInCategoryDropdownList(){

		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);

		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "Asset Categories"), "Fail: 'All Records' option doesn't not present in Asset Categories>Client list.");
		assetPage.enterSearchString("All Records", 1, "Select Client");
		assetPage.returnGridSize(InventoryPageObject.assetCatTable, "Asset Grid", 1, InventoryPageObject.infoPagination_xpath, 1);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "License Categories"), "Fail: 'All Records' option doesn't not present in License Categories>Client list.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Labels");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "Labels"), "Fail: 'All Records' option doesn't not present in Status Labels>Client list.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "Labels"), "Fail: 'All Records' option doesn't not present in Manufacturers>Client list.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "Models"), "Fail: 'All Records' option doesn't not present in Models>Client list.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Suppliers");
		assetPage.clickOnArrowIconOfFields("Client Drop-down list", 1);
		assertTrue(assetPage.isExpectedOptionAvailableInDropdownList("All Records", "Suppliers"), "Fail: 'All Records' option doesn't not present in Suppliers>Client list.");
	}

}
