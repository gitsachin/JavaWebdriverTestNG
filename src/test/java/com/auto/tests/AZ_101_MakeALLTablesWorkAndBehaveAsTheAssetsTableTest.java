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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.UsersPage;

public class AZ_101_MakeALLTablesWorkAndBehaveAsTheAssetsTableTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	
	@Test
	public void AZ_101_MakeALLTablesWorkAndBehaveAsTheAssetsTable(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Assets", "Category", 2);
		assertTrue(assetPage.isExpectedFilterBoxPresent("category"), "Fail: Category filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Assets", "Model", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("model"), "Fail: Model filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Assets", "Related Entities", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("entity"), "Fail: Related Entities filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Assets", "Status", 7);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Assets", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in Assets table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Assets", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in Assets table.");
		assertTrue(assetPage.isExpectedFieldPresent("Assets", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in Assets table.");
		
		dashboard.clickButton("Licenses");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Licenses", "Category", 2);
		assertTrue(assetPage.isExpectedFilterBoxPresent("category"), "Fail: Category filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Licenses", "Status", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Licenses", "Expiration Date", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("licenseExpireDateRange"), "Fail: LicenseExpireDateRange filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Licenses", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in Licenses table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Licenses", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in Licenses table.");
		assertTrue(assetPage.isExpectedFieldPresent("Licenses", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in Licenses table.");
		
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Submitter", 3);
		assertTrue(assetPage.isExpectedFilterBoxPresent("submitter"), "Fail: submitter filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Assigned To", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("assigned"), "Fail: Assigned filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Related Entities", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("asset"), "Fail: Asset filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Department", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("department"), "Fail: Department filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Last Reply", 7);
		assertTrue(assetPage.isExpectedFilterBoxPresent("reply"), "Fail: Reply filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Awaiting Reply", "Status", 8);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Awaiting Reply", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in 'Awaiting Reply' table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Awaiting Reply", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in 'Awaiting Reply' table.");
		assertTrue(assetPage.isExpectedFieldPresent("Awaiting Reply", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in 'Awaiting Reply' table.");
		
		dashboard.clickButton("Active Tickets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Submitter", 3);
		assertTrue(assetPage.isExpectedFilterBoxPresent("submitter"), "Fail: submitter filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Assigned To", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("assigned"), "Fail: Assigned filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Related Entities", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("asset"), "Fail: Asset filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Department", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("department"), "Fail: Department filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Last Reply", 7);
		assertTrue(assetPage.isExpectedFilterBoxPresent("reply"), "Fail: Reply filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Active Tickets", "Status", 8);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Active Tickets", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in 'Active Tickets' table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Active Tickets", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in 'Active Tickets' table.");
		assertTrue(assetPage.isExpectedFieldPresent("Active Tickets", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in 'Active Tickets' table.");
		
		dashboard.clickButton("All Tickets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Submitter", 3);
		assertTrue(assetPage.isExpectedFilterBoxPresent("submitter"), "Fail: submitter filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Assigned To", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("assigned"), "Fail: Assigned filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Related Entities", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("asset"), "Fail: Asset filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Department", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("department"), "Fail: Department filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Last Reply", 7);
		assertTrue(assetPage.isExpectedFilterBoxPresent("reply"), "Fail: Reply filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("All Tickets", "Status", 8);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("All Tickets", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in 'All Tickets' table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("All Tickets", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in 'All Tickets' table.");
		assertTrue(assetPage.isExpectedFieldPresent("All Tickets", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in 'All Tickets' table.");
		
		
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Assigned To", 3);
		assertTrue(assetPage.isExpectedFilterBoxPresent("assigned"), "Fail: Assigned filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Related Entities", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("asset"), "Fail: Related Entities filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Due Date", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("dueDate"), "Fail: Due date filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Status", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in Active Issues table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in Active Issues table.");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in Active Issues table.");
		
		dashboard.clickButton("All Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Assigned To", 3);
		assertTrue(assetPage.isExpectedFilterBoxPresent("assigned"), "Fail: Assigned filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Related Entities", 4);
		assertTrue(assetPage.isExpectedFilterBoxPresent("asset"), "Fail: Related Entities filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Due Date", 5);
		assertTrue(assetPage.isExpectedFilterBoxPresent("dueDate"), "Fail: Due date filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assetPage.clickOnExpectedFilterIconFromTableHeader("Issues", "Status", 6);
		assertTrue(assetPage.isExpectedFilterBoxPresent("status"), "Fail: Status filter box doesn't present.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCancel_xpath, 1,"Cancel");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "Searching Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Searching field doesn't present in Active Issues table.");
		signup.click("Action");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "Excel Export Option", InventoryPageObject.excelOption_xpath), "Fail: Excel Export option doesn't present in Active Issues table.");
		assertTrue(assetPage.isExpectedFieldPresent("Active Issues", "CSV Export Option", InventoryPageObject.csvOption_xpath), "Fail: CSV Export option doesn't present in Active Issues table.");
		
	}

}
