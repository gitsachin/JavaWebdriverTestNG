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

public class AZ_90_LicenseTestPageTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_90_LicenseTestPage(){
		
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
		dashboard.clickOnSpecificSubmenu("Contract", 2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Contract Manage Contract");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.license, "Search string: 'QaLicensesForAutomated_DND");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "Contract", ConfigProperties.license,3), "Assert Fail: [Licenses] doesn't presemt in License grid.");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.license, 1, "Edit", 7, "Contract", 3);
		assertTrue(assetPage.isLicenseOpen(), "Fail: Contract text doesn't present on header.");
		assertFalse(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Edit License"), "Fail: [Edit License] tab still prtesent in Edit screen.");
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract Info"), "Fail: [Contract Info] tab doesn't prtesentEdit screen .");
		assertFalse(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Summary"), "Fail: [Summary] tab still prtesentvEdit screen.");
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Assigned Assets"), "Fail: [Assigned Assets] tab doesn't prtesent Edit screen.");
		assetPage.clickOnExpectedTab(InventoryPageObject.editPageTab_xpath, 2, "Assigned Assets");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.gridHeader_xpath, "Assigned Assets"), "Fail: [Assigned Assets] grid doesn't present under 'Assigned Assets' tab");
		
	}

}
