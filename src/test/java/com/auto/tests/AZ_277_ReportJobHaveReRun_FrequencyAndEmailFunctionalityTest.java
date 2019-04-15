package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.ReportsPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.ReportsPage;
import com.auto.pages.SignUpPage;

public class AZ_277_ReportJobHaveReRun_FrequencyAndEmailFunctionalityTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	ReportsPage reportsPage;
    
    
    @Test
	public void az_277_ReportJobHaveReRun_FrequencyAndEmailFunctionality() {
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        reportsPage = new ReportsPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Reports");
		assertEquals(basepage.getText(By.cssSelector(ReportsPageObject.headerText_css)), "Reports Generare and view reports");
		assertFalse(reportsPage.isExpectedTabPresentWithExpectedPosition(ReportsPageObject.tabReport_xpath, "Report Run History"), "Fail: 'Report Run History' tab still present in 'Report' page");
		assertTrue(reportsPage.isExpectedTabPresentWithExpectedPosition(ReportsPageObject.tabReport_xpath, "Report Jobs"), "Fail: 'Report Jobs' tab doesn't present in 'Report' page");
		assertTrue(reportsPage.isExpectedColumnPresent(ReportsPageObject.tableId, "Last Run" ), "Fail: 'Last Run' column doen't present in grid.");
		assertTrue(reportsPage.isExpectedLinkPresentInReportGrid("Edit"), "Fail: 'Edit' link missing from Report Job grid.");
		assertTrue(reportsPage.isReRunLinkPresentInReportGrid("Re-Run"), "Fail: 'Re-Run' link doesn't present in Reports Grid under 'Reports Job' tab.");
		String reporter = reportsPage.returnReporterName();
		String downloadFormat = reportsPage.returnDownloadFormatOfExpectedReporter(reporter);
		reportsPage.clickOnExpectedLink(reporter);
		assertTrue(reportsPage.isExpectedPopupOpen("Edit Report"), "Fail: 'Edit Report' pop-up missing.");
		assertTrue(reportsPage.verifyExportTypeButton(downloadFormat),"Fail: Download format "+downloadFormat+" doesn't math with export format.");
		assertTrue(reportsPage.isExpectedFieldPresent("Run Frequency"), "Fail: 'Run Frquency' filed missing from 'Edit Report' pop-up.");
		assertTrue(reportsPage.isExpectedFieldPresent("Email"), "Fail: 'Email' field missing from 'Edit Report' pop-up");
    }

}
