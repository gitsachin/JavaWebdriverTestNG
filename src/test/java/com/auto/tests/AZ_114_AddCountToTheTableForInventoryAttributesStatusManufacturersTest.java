package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.management.relation.Role;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.IssuePage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;

public class AZ_114_AddCountToTheTableForInventoryAttributesStatusManufacturersTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	AssetsPage assetPage;
	RolePage rolePage;
	HelpDeskPage helpPage;
	
	
	@Test
	public void az_114_AddCountToTheTableForInventoryAttributesStatusManufacturersTest() throws IOException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        issuePage = new IssuePage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        helpPage = new HelpDeskPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Asset Categories Manage asset categories");
		assertTrue(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.assetCategories_id,"Count"));
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "License Catgories Manage license categories");
		assertFalse(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.licenseCategories_id,"Count"));
		helpPage.clickButton("Status Labels");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Status Labels Manage status labels");
		assertTrue(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.statusLabel_id,"Count"));
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Manufacturers Manage manufacturers");
		assertTrue(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.manufacturersTables_id,"Count"));
		helpPage.clickButton("Asset Models");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Models Manage asset models");
		assertTrue(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.assetModel_id,"Count"));
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Suppliers");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Suppliers Manage suppliers");
		assertTrue(issuePage.isExpectedColumnPresent(AssetsCategriesPageObject.supplier_id,"Count"));
	}

}
