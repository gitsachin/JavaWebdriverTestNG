package com.auto.tests;

import static org.testng.Assert.assertEquals;

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

public class AZ_6_Batch_upload_of_assets_via_excelTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test(priority=0)
	public void uploadExcelFileAndVerifyAssetinTable(){
		
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
		assetPage.totalAssetBeforeUploadBatch();
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Upload Batch Asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.batchFormatLink_css)), InventoryPageObject.batchUploadFormatDownloadLinkText);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.sizeNote_css)), InventoryPageObject.sizeUploadNoteMessage);
		assetPage.sendFile(ConfigProperties.assetBatchFile);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.uploadSuccessMessage_css)), InventoryPageObject.uploadSuccessMessage);
		signup.click("Close");
		basepage.navigateRefresh();
		assetPage.totalAssetAfterUploadBatch();
		
		
		
	}

}
