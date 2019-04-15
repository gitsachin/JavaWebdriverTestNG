package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

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
import com.auto.pages.UsersPage;

public class AZ_88_LabelAndCopyToFunctionalityForCustomAssetFieldsTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	AssetsPage assetPage;
	
	
	@Test
	public void az_88_LabelAndCopyToFunctionalityForCustomAssetFields() throws AWTException{
		
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
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetCategoryForAutomation_DND", "Search string ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaAssetCategoryForAutomation_DND", 2, "Edit", 4, "Asset Categories", 2);
		
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewCateAttr_xpath, 1, "New Category Attributes");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldAttrName_xpath, 1, InventoryPageObject.attrName, "Categoty Attributes");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldLabel_xpath, 1, "Value 1, Value 2, Value 3", "Labels");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonAddCat_xpath, 1, "Add");
		assetPage.clickOnCopyIcon(InventoryPageObject.attrName);
		assetPage.selectCopyTo();
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCopyCat_xpath, 1, "Copy");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSaveCat_xpath, 1, "Save");
	
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Category1_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "Category1_DND", 2, "Edit", 4, "Asset Categories", 2);
		Assert.assertTrue(assetPage.isAttributesCatgoriesCopied(InventoryPageObject.attrName, "Value 1, Value 2, Value 3"), "Fail: [Category Attributes] doesn't copied.");
		
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSaveCat_xpath, 1, "Save");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaAssetCategoryForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "QaAssetCategoryForAutomation_DND", 2, "Edit", 4, "Asset Categories", 2);
		assetPage.clickOnDeleteIcon(InventoryPageObject.attrName);
		log("[Category Attributes] deleted from Edit Category Pop-up(Copy From) Grid", ILogLevel.TEST);
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSaveCat_xpath, 1, "Save");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Category1_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "Category1_DND", 2, "Edit", 4, "Asset Categories", 2);
		assetPage.clickOnDeleteIcon(InventoryPageObject.attrName);
		log("[Category Attributes] deleted from Edit Category Pop-up(Copy To) Grid", ILogLevel.TEST);
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSaveCat_xpath, 1, "Save");
	}

}
