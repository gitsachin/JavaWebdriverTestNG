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



public class AZ_157_PhpErrorDuringExportAssetListTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_157_PhpErrorDuringExportAssetList(){
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
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCSV_xpath, 1, "CSV");
		assertTrue(assetPage.isExpectedUrlPresent(InventoryPageObject.urlAssetAfterClickCSVExcelButton), "CSV");
		assertTrue(assetPage.isExpectedButtonPresent(SignUpPageObject.buttonActionDropDown_xpath, "Action", "Asset page"), "Fail: [CSV] button doesnot present in 'Assets' pager");
		
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonExcel_xpath, 1, "Excel");
		assertTrue(assetPage.isExpectedUrlPresent(InventoryPageObject.urlAssetAfterClickCSVExcelButton), "CSV");
		assertTrue(assetPage.isExpectedButtonPresent(SignUpPageObject.buttonActionDropDown_xpath, "Action", "Asset page"), "Fail: [Excel] button doesn't present in 'Assets' pager");
		
		
	}

}
