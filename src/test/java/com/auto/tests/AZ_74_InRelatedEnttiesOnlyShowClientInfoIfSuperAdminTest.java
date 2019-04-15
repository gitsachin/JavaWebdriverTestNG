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
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_74_InRelatedEnttiesOnlyShowClientInfoIfSuperAdminTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	UsersPage userPage;
	SystemPage system;
	
	@Test
	public void az_74_InRelatedEnttiesOnlyShowClientInfoIfSuperAdmin(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        system = new SystemPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Action");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		userPage.selectOption(2, "Category1_DND");
		signup.enterInput(1, InventoryPageObject.assetName);
		userPage.selectOption(3,"Lava");
		userPage.selectOption(4,"Lava-1234");
		signup.enterInput(3,"1234456");
		userPage.selectOption(5,"Amzone");
		signup.clickWithScroll("Create");
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)),DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.isFilterArrowIconPresent(InventoryPageObject.assetsTableHeader_xpath);
		system.searchResult(InventoryPageObject.searchTableId,"Assert","QATEST",6);
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Asset", 3);
		signup.click("Yes");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.deleteSucessMessage);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isItemPresentInGridAfterDelete(InventoryPageObject.noRecordGrid_xpath, InventoryPageObject.blankRecords, InventoryPageObject.assetName), "Fail: Item doesn't deleted.");
	}

}
