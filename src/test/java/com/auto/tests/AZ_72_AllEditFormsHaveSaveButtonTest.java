package com.auto.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.Assert;
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

public class AZ_72_AllEditFormsHaveSaveButtonTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_72_AllEditFormsHaveSaveButton(){
		
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
		dashboard.clickMenuListOption(0);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetForAutomation", "Search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaAssetForAutomation", 1, "Edit", 8, "Assets", 3);
		Assert.assertTrue(assetPage.isSaveButtonPresent("Asset Edit"), "Fail: Save button doesn't present in [Assets Edit] screen.");
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickOnSpecificSubmenu("All Issues", 1);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaIssueForAutomation", "Search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaIssueForAutomation", 1, "Edit", 7, "Issues", 2);
		Assert.assertTrue(assetPage.isSaveButtonPresent("Issues Edit"), "Fail: Save button doesn't present in [Issues Edit] screen.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1, "Cancel");
		
		dashboard.clickMenuTab("Projects");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaProjectForAutomation", "Search string: 'QaProjectForAutomation'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaProjectForAutomation", 2, "Edit", 5, "Projects", 3);
		Assert.assertTrue(assetPage.isSaveButtonPresent("Projects  Edit"), "Fail: Save button doesn't present in [Projects  Edit] screen.");
	
	}
	
	

}
