package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;

public class AZ_26_ClientComboIsAvailableTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	AssetsPage assetPage;
	
	@Test
	public void az_26_ClientComboIsAvailable() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        issuePage = new IssuePage(driver);
        assetPage = new AssetsPage(driver);
        
        driver.navigate().to(ConfigProperties.site_url);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		
		log("*************** Navigate to Project page ***************", ILogLevel.TEST);
		dashboard.clickMenuTab("Projects");
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		signup.click("Action");
		signup.click("New issue");
		
		assertTrue(dashboard.verifyClientComboInExpectedFileds("None", IssuePageObject.clientFiled_xapth, IssuePageObject.assignToFiled_xapth, "Nobody"), "Fail: [None] doesn't apeears in fileds.");
		issuePage.clickOnArrowIconOfFields("Assigned To", 4);
		assertFalse(dashboard.verifyClientComboSuggestionListInExpectedFileds("Assigned To", IssuePageObject.assignToFiledSuggestionList_xapth, IssuePageObject.clientFiled_xapth), "Fail: Assigned To Dropdown suggestion list appears without selecting Client.");
		issuePage.clickOnArrowIconOfFields("Assigned To", 4);
		issuePage.clickOnArrowIconOfFields("Project", 5);
		assertFalse(dashboard.verifyClientComboSuggestionListInExpectedFileds("Project", IssuePageObject.projectFiledSuggestionList_xapth, IssuePageObject.clientFiled_xapth), "Fail: Project dropdown suggestion list appears without selecting Client.");
		issuePage.clickOnArrowIconOfFields("Project", 5);
		
		issuePage.clickOnArrowIconOfFields("Client", 3);
		issuePage.enterSearchString("QATEST", 1, "Client");
		
		assertTrue(dashboard.verifyClientComboInExpectedFileds("QATEST", IssuePageObject.clientFiled_xapth, IssuePageObject.assignToFiled_xapth, "SA"), "Fail: [QATEST: SA] doesn't apeears in fileds.");
		issuePage.clickOnArrowIconOfFields("Assigned To", 4);
		assertTrue(dashboard.verifyClientComboSuggestionListInExpectedFileds("Assigned To", IssuePageObject.assignToFiledSuggestionList_xapth, IssuePageObject.clientFiled_xapth), "Fail: Assigned To Dropdown suggestion list doesnn't appears after selecting Client.");
		issuePage.clickOnArrowIconOfFields("Assigned To", 4);
		issuePage.clickOnArrowIconOfFields("Project", 5);
		assertTrue(dashboard.verifyClientComboSuggestionListInExpectedFileds("Project", IssuePageObject.projectFiledSuggestionList_xapth, IssuePageObject.clientFiled_xapth), "Fail: Project dropdown suggestion list doesnn't appears after selecting Client.");
	
		log("*************** Navigate to [Asset] page ***************", ILogLevel.TEST);
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		
		assertTrue(dashboard.verifyClientComboInExpectedFileds("None", InventoryPageObject.clientFiled_xapth, InventoryPageObject.assignToFiled_xapth, "None"), "Fail: [None] doesn't apeears in fileds.");
		assetPage.clickOnArrowIconOfFields("Asset Admin", 4);
		assertFalse(dashboard.verifyClientComboSuggestionListInExpectedFileds("Asset Admin", InventoryPageObject.assignToFiledSuggestionList_xapth, InventoryPageObject.clientFiled_xapth), "Fail: Assigned To Dropdown suggestion list appears without selecting Client.");
		assetPage.clickOnArrowIconOfFields("Asset Admin", 4);
		assetPage.clickOnArrowIconOfFields("Status", 3);
		assertFalse(dashboard.verifyClientComboSuggestionListInExpectedFileds("Status", InventoryPageObject.statusResult_xpath, InventoryPageObject.clientFiled_xapth), "Fail: Status dropdown suggestion list appears without selecting Client.");
		assetPage.clickOnArrowIconOfFields("Status", 3);
		
		assetPage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterSearchString("QATEST", 5, "Client");
		Thread.sleep(3000);
		log("After select client", ILogLevel.TEST);
		assertTrue(dashboard.verifyClientComboInExpectedFileds("QATEST", InventoryPageObject.clientFiled_xapth, InventoryPageObject.assignToFiled_xapth, "SA"), "Fail: [QATEST: SA] doesn't apeears in fileds.");
		assetPage.clickOnArrowIconOfFields("Asset Admin", 4);
		assertTrue(dashboard.verifyClientComboSuggestionListInExpectedFileds("Assigned Admin", InventoryPageObject.assignToFiledSuggestionList_xapth, InventoryPageObject.clientFiled_xapth), "Fail: Asset admin To Dropdown suggestion list doesnn't appears after selecting Client.");
		assetPage.clickOnArrowIconOfFields("Asset Admin", 4);
		assetPage.clickOnArrowIconOfFields("Status", 3);
		assertTrue(dashboard.verifyClientComboSuggestionListInExpectedFileds("Status", InventoryPageObject.statusResult_xpath, IssuePageObject.clientFiled_xapth), "Fail:Status dropdown suggestion list doesnn't appears after selecting Client.");
	}

}
