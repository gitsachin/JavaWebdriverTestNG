package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

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

public class AZ_265_256_ExportFileRecordTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_265_256_ExportFileRecord() throws IOException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
	
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Client");
		assetPage.returnLocationGridSize(InventoryPageObject.searchTableId,1, InventoryPageObject.infoAllPagination_xpath, 1);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.location, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, ConfigProperties.location, 1), "Fail: Search operation is not perform.");
		int locationGridSize = assetPage.returnLocationGridSize(InventoryPageObject.searchTableId,1, InventoryPageObject.infoPagination_xpath, 1);
		signup.click("Actions");
		assetPage.clickActionButtonDropdown(2,"Export Excel Button");
		assertTrue(assetPage.fileExist(".xlsx"), "Fail: Location file doesn't exported.");
		int exportedFileRows = assetPage.countExportedFileRow("Locations");
		assertTrue(assetPage.isTableRecordSameWithExporetedRecord(locationGridSize, exportedFileRows), "Fail: Downloaded Report contains extra values.");
		assetPage.deleteFile();
	}

}
