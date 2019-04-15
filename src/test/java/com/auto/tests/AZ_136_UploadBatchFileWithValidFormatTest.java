package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class AZ_136_UploadBatchFileWithValidFormatTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_136_UploadBatchFileWithValidFormat() throws AWTException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assetPage.clickOnArrowIconOfFields("Client Field", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Asset"), "Fail: [Upload Batch Asset ] pop-up doesn't open");
		basepage.dropdownSelect(By.xpath(InventoryPageObject.selectClient_xpath), "QATEST");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.batchFormatLink_css)), InventoryPageObject.batchUploadFormatDownloadLinkText);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.sizeNote_css)), InventoryPageObject.sizeUploadNoteMessage);
		assetPage.sendFile(ConfigProperties.assetBatchFile);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.viewFileName_css)), InventoryPageObject.fileAttachedMessage);
		signup.click("Upload");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.uploadSuccessMessage_css)), InventoryPageObject.uploadSuccessMessage);	
	}

}
