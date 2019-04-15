package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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


public class AZ_81_CreateAnAssetWithSuperUserAccountTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	
	@Test
	public void az_81_CreateAnAssetWithSuperUserOnVeritivAccount() throws AWTException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        SoftAssert softAssertion= new SoftAssert();
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		
		signup.click("Actions");
		dashboard.clickButton("New asset");
		
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 5, "Client");
		signup.enterInput(2, InventoryPageObject.assetName);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, BasePage.autogenerateNumber(5), "Serial No");
		userPage.selectOption(4, "QaManufacturer_DND");
		userPage.selectOption(5, "QaAssetModelForAutomation_DND");
		userPage.selectOption(6, "QaSupplierForAutomation_DND");
		
		signup.clickWithScroll("Create");
		
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
		assetPage.totalAssetBeforeUploadBatch();
		
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		
		signup.selectClientFromdropdownList();
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
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Delete", 3);	                              
		signup.click("Yes");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
		
		log("["+InventoryPageObject.assetName+"] Asset delete from Grid", ILogLevel.TEST);
		
		
		
	} 

}
