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


public class AZ_39_AddTextToAssignLicenseOnAssetTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
		
	@Test
	public void az_39_AddTextToAssignLicenseOnAsset(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetForAutomation", "Search string: 'QaAssetForAutomation'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaAssetForAutomation", 1, "Edit", 8, "Edit", 3);
		assetPage.clickOnExpectedIcon(InventoryPageObject.iconPinForAssignLicense_xpath, 4, "Assign License pin");
		
		assertTrue(assetPage.isExpectedPopupOpen("Assign License"), "Fail: [Assign License] pop-up doesn'topen");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.contentText_xpath, InventoryPageObject.contentText), "Fail: [Please select from the existing licenses below] doesn't present.");
		assetPage.clickOnArrowIconOfFields("Assign License", 9);
		assetPage.enterSearchString("QaLicensesForAutomated_DND", 5, "Assign License");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonAssign_xpath, 1, "Assign");
		assetPage.clickOnExpectedButton(InventoryPageObject.tabLicense_xpath, 1, "License tab");
		assertTrue(assetPage.isLicenseAddedInTable("Licenses", "QaLicensesForAutomated_DND"), "Fail: [Assign License] doesn't present under License tab.");
		assetPage.deleteAddedLicenseFromTable("Licenses", "QaLicensesForAutomated_DND");
		signup.click("Yes");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage);
		
		
	}

}
