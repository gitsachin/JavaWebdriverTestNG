package com.auto.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_106_LogsNeedsToBePaginatedTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	RolePage rolePage;
	SystemPage systemPage;
	
	@Test(priority=0)
	public void az_106_LogsNeedsToBePaginatedTest(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        systemPage = new SystemPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)),DashboardPageObject.superAdminName);
		basepage.navigateUrl(ConfigProperties.site_url+"?route=system/logs");
		log("URL: "+ConfigProperties.site_url+"/?route=system/logs",ILogLevel.TEST);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerTextlog_css)), "Logs View application logs");
		boolean pagination = assetPage.isPaginationTabPresent(1, InventoryPageObject.infoPagination_xpath, 1);
		systemPage.isColumnPresent(1,5);
		systemPage.verifyPagination(1);
		dashboard.clickButton("Email Message Log");
		boolean pagination1 = assetPage.isPaginationTabPresent(1, InventoryPageObject.infoPagination_xpath, 1);
		systemPage.isColumnPresent(6,11);
		systemPage.verifyPagination(2);
		dashboard.clickButton("SMS Message Log");
		boolean pagination2 = assetPage.isPaginationTabPresent(1, InventoryPageObject.infoPagination_xpath, 1);
		systemPage.isColumnPresent(12,17);
		
	}

}
