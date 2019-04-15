package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

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
import com.auto.pages.SignUpPage;
import com.auto.pages.UsersPage;

public class AZ_20_Creating_Assets_Who_Can_Do_WhatTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	AssetsPage assetPage;
	
	@Test(priority=0)
	public void verifyClientDropDownInSuperAdminAccount(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("System");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Clients");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=1)
	public void verifyClientDropDownInClientAdminAccount(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("System");
		assertNotEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Clients");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=2)
	public void verifyBatchUploadButtonWithoutPermissionInUserAccount(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.clientUser_email);
		signup.enterInput(1, ConfigProperties.clientUser_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "User1_DND");
		assertFalse(dashboard.verifyIcon(DashboardPageObject.menuIcon_css),"System menu tab present on page");
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Action");
		assertFalse(dashboard.verifyIcon(InventoryPageObject.uploadIcon_css),"Batch upload present on assert page");
	}
	
	@Test(priority=3)
	public void createNewAssetsInUserAccount(){
		
		dashboard = new DashboardPage(driver);
		userPage = new UsersPage(driver);
		signup = new SignUpPage(driver);
		assetPage = new AssetsPage(driver);
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		
		signup.click("Action");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		userPage.selectOption(2, "Category1_DND");
		signup.enterInput(1, InventoryPageObject.assetName);
		userPage.selectOption(3, "QaManufacturer_DND");
		userPage.selectOption(4, "QaAssetModelForAutomation_DND");
		userPage.selectOption(5, "QaSupplierForAutomation_DND");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, BasePage.autogenerateNumber(5), "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 2);
		assetPage.enterSearchString("QaStatusLabelsForAutomation_DND", 5, "Status");
		signup.clickWithScroll("Create");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.sucessMessage);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: '"+InventoryPageObject.assetName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,InventoryPageObject.assetName, 2, "Delete", 8, "Asset", 3);
		
		signup.click("Yes");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.deleteSucessMessage);
		log("["+InventoryPageObject.assetName+"] Asset delete from Grid", ILogLevel.TEST);
		dashboard.logOut(ConfigProperties.logout_url);
		
	}

}
