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

public class AZ_21_BatchUploadChangesTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_21_BatchUploadChanges() throws AWTException{
		
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
		dashboard.clickButton("Assets");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
		assertTrue(assetPage.isExpectedPopupOpen("Upload Batch Asset"), "Fail: [Upload Batch Asset] pop-up doesn'open");
		assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.helpText_xpath, InventoryPageObject.sizeUploadNoteMessage), "Fail: ["+InventoryPageObject.sizeUploadNoteMessage+"] doesn't present.");
	}

}
