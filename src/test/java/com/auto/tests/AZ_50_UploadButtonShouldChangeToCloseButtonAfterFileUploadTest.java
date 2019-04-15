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

public class AZ_50_UploadButtonShouldChangeToCloseButtonAfterFileUploadTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_50_UploadButtonShouldChangeToCloseButton(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Asset"), "Fail: [Upload Batch Asset] pop-up doesn'open");
		signup.selectClientFromdropdownList();
		
		assetPage.sendFile(ConfigProperties.assetBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonUpload_xpath, "Upload", "Upload Batch Asset pop-up"), "Fail: [Upload] button doesn't present in pop-up.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonUpload_xpath, 1, "Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Suiccess Message] doesn't present.");
		
		assertFalse(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonUpload_xpath, "Upload", "Upload Batch Asset pop-up"), "Fail: [Upload] button present in pop-up.");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonClose_xpath, "Close", "Upload Batch Asset pop-up"), "Fail: [Close] button doesn't appear.");
		
	}

}
