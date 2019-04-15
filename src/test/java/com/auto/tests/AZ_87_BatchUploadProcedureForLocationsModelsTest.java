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

public class AZ_87_BatchUploadProcedureForLocationsModelsTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_87_BatchUploadProcedureForLocationsModels(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Models");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Asset Models"), "Fail: [Batch Upload] doesn't present in 'Asset Models' table");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameAssetModel_xpath), "Fail: ["+InventoryPageObject.popupNameAssetModel_xpath+"] pop-up doesn't open");
		assertTrue(assetPage.isExpectedFieldPresent("Asset Model", "Select Client", InventoryPageObject.clientIdSelectField_xpath), "Fail: Client Id Select field does'n present.");
		assetPage.selectOptionFromDropdownList(InventoryPageObject.clientIdSelectField_xpath, "Select Client", "QATEST");
		assetPage.sendFile(ConfigProperties.assetModelsBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		assertFalse(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameAssetModel_xpath), "Fail: ["+InventoryPageObject.popupNameAssetModel_xpath+"] pop-up doesn't close");
		
		dashboard.clickButton("Locations");
		signup.click("Action");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonBatchUploadWithIndex_xpath, "Batch upload", "Locations"), "Fail: [Batch Upload] doesn't present in 'Locations' table.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameLocation_xpath), "Fail: ["+InventoryPageObject.popupNameLocation_xpath+"] pop-up doesn't open");
		assertTrue(assetPage.isExpectedFieldPresent("Locations", "Select Client", InventoryPageObject.selectClientField_xpath), "Fail: Client Id Select field doesn't present.");
		assetPage.clickOnArrowIconOfFields(InventoryPageObject.arrowIconDepartmentField, 2);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		assetPage.sendFile(ConfigProperties.locationBatch); //Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonClose_xpath, 1, "Close");
		assertFalse(assetPage.isExpectedPopupOpen(InventoryPageObject.popupNameLocation_xpath), "Fail: ["+InventoryPageObject.popupNameLocation_xpath+"] pop-up doesn't closed");
			
	}

}
