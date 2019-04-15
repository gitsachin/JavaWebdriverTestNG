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

public class AZ_224_AddDateRangeToWarrantyReportTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	ReportsPage reportsPage;
	
	@Test
	public void az_224_AddDateRangeToWarrantyReport(){
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
		assertTrue(reportsPage.isExpectedFieldPresent("Start Date"),"Fail: [Start Date] doesn't present in Repors page.");
		assertTrue(reportsPage.isExpectedFieldPresent("End Date"),"Fail: [End Date] doesn't present in Repors page.");		
	}

}
