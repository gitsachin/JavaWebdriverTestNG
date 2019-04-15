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
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_188_SearchFieldNotPresentInDeleteUserSectionTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage system;
	UsersPage userPage;
	
	@Test
	public void az_188_SearchFieldNotPresentInDeleteUserSection() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    system = new SystemPage(driver);
	    userPage = new UsersPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(3, ConfigProperties.clientPlan);
		userPage.selectOption(4, ConfigProperties.role);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "Use Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Useer Password");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "user Confirm Password");
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 6, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
		
		system.clickOnExpectedButton(SystemPageObject.tabDeleteUsers_xpath, 1, "Deleted Users");
		assertTrue(system.isSearchFieldPresent("Deleted User"), "Fail: Search filed doesn't present in Deleted User screen.");
		
		system.enterExpectedValueInTextField("("+InventoryPageObject.textFieldSearch_xpath+")[2]", 1, SystemPageObject.userName, "Search string");	
		system.clickOnExpectedButton("("+InventoryPageObject.buttonSearch_xpath+")[2]", 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.deleteSearchTableId, "User Name", SystemPageObject.userName, 3), "Fail: User Searched Operation.");
		system.clickOnActiveOrDeleteButton(SystemPageObject.deleteSearchTableId, SystemPageObject.userName, 3, "Delete", 6, 2);
		system.enterExpectedValueInTextField("("+InventoryPageObject.textFieldSearch_xpath+")[2]", 1, SystemPageObject.userName, "Search string");	
		system.clickOnExpectedButton("("+InventoryPageObject.buttonSearch_xpath+")[2]", 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.deleteSearchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
	}

}
