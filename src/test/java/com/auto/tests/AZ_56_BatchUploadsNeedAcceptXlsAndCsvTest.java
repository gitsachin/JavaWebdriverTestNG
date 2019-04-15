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

public class AZ_56_BatchUploadsNeedAcceptXlsAndCsvTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_56_BatchUploadsNeedAcceptXlsAndCsv() throws InterruptedException{
		
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
		
		signup.click("Actions");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Asset"), "Fail: [Upload Batch Asset] pop-up doesn'open");
		signup.selectClientFromdropdownList();
		
		assetPage.sendFile(ConfigProperties.wrongFileToUpload);//Upload Invalid File Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		Thread.sleep(3000);
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadErrorMessage_xpath, InventoryPageObject.uploadErrorMessage), "Fail: [Upload error Message] doesn't present.");
		
		assetPage.sendFile(ConfigProperties.assetBatchFile);//Upload valid File .xlsx Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Suiccess Message] doesn't present.");
		signup.click("Close");
		
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Asset"), "Fail: [Upload Batch Asset] pop-up doesn'open");
		signup.selectClientFromdropdownList();
		
		assetPage.sendFile(ConfigProperties.assetCsvFile);//Upload valid File .csv Format
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, "Batch sucessfully uploaded"), "Fail: [Upload Suiccess Message] doesn't present.");

	} 

}
