package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.ReportsPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.ReportsPage;
import com.auto.pages.SignUpPage;

public class AZ_78_CreateReportingJobFrameworkTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	ReportsPage reportsPage;
	
	@Test
	public void az_78_CreateReportingJobFramework(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        reportsPage = new ReportsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Reports");
		assertEquals(basepage.getText(By.cssSelector(ReportsPageObject.headerText_css)), "Reports Generare and view reports");
	
		assertTrue(reportsPage.isExpectedTabPresent(ReportsPageObject.reportRunHisTab_xpath, "Report Run History"), "Fail: 'Report Run History' tab doesn't present.");
		assertTrue(reportsPage.isExpectedTabPresent(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run"), "Fail: 'Report You Can Run' tab doesn't present.");
	
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Requested Date"), "Fail: 'Requested Date' column doesn't present.");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Status"), "Fail: 'Status' column doesn't present.");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Report Name"), "Fail: 'Report Name' column doesn't present.");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Records"), "Fail: 'Records' column doesn't present.");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Report Type"), "Fail: 'Report Type' column doesn't present.");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.reportsTable, "Action"), "Fail: 'Action' column doesn't present.");
		
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		assertTrue(reportsPage.expectedSectionPresent("Timesheet"), "Fail: [Timesheet] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Timesheet With Summary"), "Fail: [Timesheet With Summary] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Client Summary"), "Fail: [Client Summary] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Other Reports"), "Fail: [Other Reports] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("User Summary"), "Fail: [User Summary] section doesn't present at 'Report You Can Run' tab.");
		
	}

}
