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

public class AZ_167_AbilityToAddCategoriesByBatchUploadTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_167_AbilityToAddCategoriesByBatchUpload(){
		
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
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		signup.click("Actions");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Asset Categories"), "Fail: [Batch Upload] doesn't present in 'Asset Category' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameAssetCat_xpath), "Fail: ["+InventoryPageObject.popupNameAssetCat_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.assetCatBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "License Categories"), "Fail: [Batch Upload] doesn't present in 'License Categories' table");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameLicenseCat_xpath), "Fail: ["+InventoryPageObject.popupNameLicenseCat_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.licenseCatBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Labels");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Status Labels"), "Fail: [Batch Upload] doesn't present in 'Status Labels' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameStatusLabels_xpath), "Fail: ["+InventoryPageObject.popupNameStatusLabels_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.statusLabelsBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Manufacturers"), "Fail: [Batch Upload] doesn't present in 'Manufacturers' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameManufacturers_xpath), "Fail: ["+InventoryPageObject.popupNameManufacturers_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.manufacturesBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Asset Models"), "Fail: [Batch Upload] doesn't present in 'Asset Models' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameAssetModel_xpath), "Fail: ["+InventoryPageObject.popupNameAssetModel_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.assetModelsBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
	
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Suppliers");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Suppliers"), "Fail: [Batch Upload] doesn't present in 'Suppliers' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameSuppliers_xpath), "Fail: ["+InventoryPageObject.popupNameSuppliers_xpath+"] pop-up doesn'open");
		assetPage.sendFile(ConfigProperties.suppliersBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
	}


}
