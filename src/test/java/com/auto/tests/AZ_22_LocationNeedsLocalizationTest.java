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

public class AZ_22_LocationNeedsLocalizationTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_22_LocationNeedsLocalization(){
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
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.tabLocalization_xpath, "Localization", "New Location"));
		signup.clickButton("Localization");
		assertTrue(assetPage.isExpectedFiledPresent("Timezone", "Location: New Location"), "Fail: 'Timezone' filed missing from screen");
		assertTrue(assetPage.isExpectedFiledPresent("Default language", "Location: New Location"), "Fail: 'Default language' filed missing from screen");
		assertTrue(assetPage.isExpectedFiledPresent("Date Format", "Location: New Location"), "Fail: 'Date Format' filed missing from screen");
	}

}
