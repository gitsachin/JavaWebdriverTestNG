package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;

public class AZ_66_ExportAsCsvExcelAssetFilteredResultsTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	TicketPage ticketPage;
	AssetsPage assetPage;

	@Test
	public void az_66_ExportAsCsvExcelAssetFilteredResultsTest() throws IOException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        issuePage = new IssuePage(driver);
        ticketPage = new TicketPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, AssetsCategriesPageObject.assetName, "search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
		assertTrue(ticketPage.verifyElementOnActionDropdown(0,"Export as CSV"),"[Export as CSV] not present in dropdown list");
		assertTrue(ticketPage.verifyElementOnActionDropdown(1,"Export as Excel"),"[Export as Excel] not present in drodown list");
		assetPage.clickActionButtonDropdown(1,"Export CSV Button");
		assertTrue(assetPage.fileExist(".csv"));
	    assertTrue(assetPage.verifyDataInCSVFile(AssetsCategriesPageObject.assetName));
	    assetPage.deleteFile();
	    assertTrue(ticketPage.isButtonPresent("Action"),"Action button not present on page");
	    assetPage.clickActionButtonDropdown(2,"Export Excel Button");
	    assertTrue(assetPage.fileExist(".xlsx"));
	    assertTrue(assetPage.verifyDataInExcelFile(AssetsCategriesPageObject.assetName));
	    assetPage.deleteFile();
	    
	}

}
