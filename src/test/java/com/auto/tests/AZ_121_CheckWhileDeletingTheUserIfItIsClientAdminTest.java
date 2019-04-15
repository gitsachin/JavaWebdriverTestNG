package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_121_CheckWhileDeletingTheUserIfItIsClientAdminTest extends TestCore {
	SignUpPage signup;
	DashboardPage dashboard;
	BasePage basepage;
	SystemPage system;
	RolePage rolePage;
	UsersPage userPage;
	
    @Test
	public void az_121_CheckWhileDeletingTheUserIfItIsClientAdmin(){
		signup = new SignUpPage(driver);
		dashboard = new DashboardPage(driver);
		basepage = new BasePage(driver);
		system = new SystemPage(driver);
		rolePage = new RolePage(driver);
		userPage = new UsersPage(driver);
		SoftAssert softAssertion= new SoftAssert();
	
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		
		dashboard.clickButton("Users");
		// Create random user. 
		/*signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(3, "QATEST");
		userPage.selectOption(4, "Client Admin");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "password");	
		signup.clickWithScroll("Create");*/
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST@mailinator.com", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QATEST@mailinator.com", 4, "Delete", 6, 2);
		
		signup.click("Yes");
		log("CLick on 'Yes' button to give permission for delete.", ILogLevel.TEST);
		softAssertion.assertTrue(system.isAlertAppearsAgainstDeleteOperation(SystemPageObject.error), "Fail: Delete alert doesn't appear.");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST@mailinator.com", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User", "QATEST@mailinator.com", 4), "fail: Searched user doesn't deleted.");
		
	}
	

}
