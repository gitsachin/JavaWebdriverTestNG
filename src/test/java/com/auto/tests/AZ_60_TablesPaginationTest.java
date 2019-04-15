package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_60_TablesPaginationTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	UsersPage userPage;
	SystemPage systemPage;
	
	@Test
	public void az_60_TablesPaginationTest(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        systemPage = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("System");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Clients");
		dashboard.clickMenuTab("Clients");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,"QATEST", "Search string: '"+InventoryPageObject.model+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,"22", 1, "View", 6, "All ticket", 1);
		systemPage.clickTab(SystemPageObject.assetTabId,"Assests");
		systemPage.verifyPagination(2);
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.assetCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.assetExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.assetTabId,"Assests");
		systemPage.verifyPagination(2);
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.assetCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.assetExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.licensesTabId,"Licenses");
		systemPage.verifyPagination(2);
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.licensesCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.licensesExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.projectsTabId,"Projects");
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.projectsCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.projectsExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.IssuesTabId,"Issues");
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.issuesCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.issuesExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.ticketsTabId,"Tickets");
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.ticketsCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.ticketsExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.credentialTabId,"Credentials");
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.credentialsCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.credentialsExcelXpath,"Excel",2));
		
		systemPage.clickTab(SystemPageObject.userTabId,"User");
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.usersCsvXpath,"CSV",2));
		assertTrue(systemPage.isExportButtonPresent(SystemPageObject.usersExcelXpath,"Excel",2));
		
		
	}

}
