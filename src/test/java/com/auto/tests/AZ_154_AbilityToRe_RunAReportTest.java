package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

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

public class AZ_154_AbilityToRe_RunAReportTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	ReportsPage reportsPage;
	
	@Test
	public void az_154_AbilityToRe_RunAReport() throws IOException {
	
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
		String reporter = reportsPage.clickOnReRunButton();
		assertFalse(reportsPage.isReRunLinkPresentAfterClickOnIt(reporter), "Fail: Re-Run link still present after click.");
		
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		String warentyExpButton = reportsPage.expectedButtonName(ReportsPageObject.buttonWarentyExp_linkText);
		reportsPage.clickOnExpectedButton(ReportsPageObject.buttonWarentyExp_linkText, 3, warentyExpButton);
		assertTrue(reportsPage.isExpectedPageOpen(DashboardPageObject.headerText_css, "Warrenty Expires"), "Fail: ["+warentyExpButton+"] doesn't open.");
		assertTrue(assetPage.isExpectedFiledPresent("Start Date", "Assets Warrenty Expires In Next 30 Days"), "Fail: User doesn't redirected to Asset page");
		assertTrue(assetPage.isExpectedFiledPresent("End Date", "Assets Warrenty Expires In Next 30 Days"), "Fail: User doesn't redirected to Asset page");
		
		String startDate = "2018-01-01";
		String endDate = "2018-09-18";
		reportsPage.enterExpectedValueInTextField(InventoryPageObject.textFieldStartDate_xpath, 1, startDate, "Start Date");
		reportsPage.enterExpectedValueInTextField(InventoryPageObject.textFieldEndDate_xpath, 1, endDate, "End Date");
		signup.click("Generate");
		int gridSize = assetPage.returnAsserReportGridSize(InventoryPageObject.assetReportTable, "Assets Warrenty Expires In Next 30 Days", 1, InventoryPageObject.tableInfo_xpath, 1);
		assertTrue(reportsPage.countDaysBetweenDate(), "Fail: [warranty is about to expire in the next 30 days]");
		
	}

}
