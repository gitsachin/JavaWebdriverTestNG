package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

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

public class AZ_38_BatchUploadLicensesTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_38_BatchUploadLicenses() throws AWTException{
		
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
		dashboard.clickOnSpecificSubmenu("Licenses", 1);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		
		signup.click("Actions");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Upload Batch License");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.batchFormatLink_css)), InventoryPageObject.batchUploadFormatDownloadLinkText);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.sizeNote_css)), InventoryPageObject.sizeUploadNoteMessage);
		assetPage.sendFile(ConfigProperties.licenseBatchFile);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.uploadSuccessMessage_css)), InventoryPageObject.uploadSuccessMessage);
		signup.click("Close");
		basepage.navigateRefresh();
		assetPage.totalRowAfterUploadBatch();
	}

}
