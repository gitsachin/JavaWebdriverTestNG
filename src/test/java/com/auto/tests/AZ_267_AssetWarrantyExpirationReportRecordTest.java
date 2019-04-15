package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.ReportsPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.ReportsPage;
import com.auto.pages.SignUpPage;

public class AZ_267_AssetWarrantyExpirationReportRecordTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	ReportsPage reportsPage;
	AssetsPage assetPage;
	
	@Test
	public void az_267_AssetWarrantyExpirationReportRecord() throws FileNotFoundException, InterruptedException {
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        reportsPage = new ReportsPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Reports");
		assertEquals(basepage.getText(By.cssSelector(ReportsPageObject.headerText_css)), "Reports Generare and view reports");
		
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		String warentyExpButton = reportsPage.expectedButtonName(ReportsPageObject.buttonWarentyExp_linkText);
		reportsPage.clickOnExpectedButton(ReportsPageObject.buttonWarentyExp_linkText, 3, warentyExpButton);
		assertTrue(reportsPage.isExpectedPageOpen(DashboardPageObject.headerText_css, "Warrenty Expires"), "Fail: ["+warentyExpButton+"] doesn't open.");
		assertTrue(assetPage.isExpectedFiledPresent("Start Date", "Assets Warrenty Expires In Next 30 Days"), "Fail: User doesn't redirected to Asset page");
		assertTrue(assetPage.isExpectedFiledPresent("End Date", "Assets Warrenty Expires In Next 30 Days"), "Fail: User doesn't redirected to Asset page");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldStartDate_xpath, 1, "2017-01-01", "Start Date");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldEndDate_xpath, 1, "2018-09-18", "End Date");
		signup.click("Generate");
		int gridSize = assetPage.returnAsserReportGridSize(InventoryPageObject.assetReportTable, "Assets Warrenty Expires In Next 30 Days", 1, InventoryPageObject.tableInfo_xpath, 1);
		boolean pagination = assetPage.isPaginationTabPresent(1, InventoryPageObject.tableInfo_xpath, 1);
		if(pagination){
			assetPage.clickOnNextpageOfPagination(1, InventoryPageObject.tableInfo_xpath, 1, 2);
			assertTrue(assetPage.isPaginationTabPresent(1, InventoryPageObject.tableInfo_xpath, 1), "Fail: Pagination tab and info disappear from page.");
		}
		assetPage.clickOnExpectedButton(InventoryPageObject.linkButtonExcel_xpath, 1, "Excel");
		Thread.sleep(2000);
		assertTrue(assetPage.fileExist(".xlsx"), "Fail: Asset file doesn't exported.");
		int exportedFileRows = assetPage.countExportedFileRow("Assets Expiring");
		assertTrue(assetPage.isTableRecordSameWithExporetedRecord(gridSize, exportedFileRows), "Fail: Assets Downloaded records contains extra values.");
		assetPage.deleteFile();
		
	}

}
