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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;


public class AZ_104_ClientsFilterForAllStaffUserTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	SystemPage  system;
	UsersPage userPage;
	
	@Test
	public void az_104_ClientsFilterForAllStaffUser(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    system = new SystemPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.asset, "Asset Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Asset", ConfigProperties.asset, 3), "Fail: Search operation isn't perform.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, ConfigProperties.asset, 3, "Edit", 8, 1);
		String clientName = assetPage.returnClientName();
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Staff");
		signup.click("Action");
		signup.click("Add saff account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add Staff Account");
		userPage.selectOption(1, clientName);
		userPage.selectOption(2, "Super Administrator");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.staffName, "Staff Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.staff_Email, "Staff Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Staff Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "Staff Confirm Password");
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staffName, "Staff Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Staff", SystemPageObject.staffName, 2), "Fail: New staff doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		
		signup.enterInput(0, SystemPageObject.staff_Email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.staffName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.asset, "Asset Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Asset", ConfigProperties.asset, 3), "Fail: Search operation isn't perform.");
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.user, "User Search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User", ConfigProperties.user, 3), "Fail: Search operation isn't perform.");
		String clientInGrid = assetPage.returnClientnameFromGrid(InventoryPageObject.searchTableId, ConfigProperties.user);
		assertEquals(clientName, clientInGrid, "Fail: Different clien find here.");
		
		dashboard.clickButton("Staff");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staffName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.staffName, 2, "Delete", 6, 2);
		signup.click("Yes");
		assertTrue(signup.isUserNavigateToExpectedPage("signOut_Url"), "Fail: User doesn't nevigate to Signup page.");
		
	}
}
