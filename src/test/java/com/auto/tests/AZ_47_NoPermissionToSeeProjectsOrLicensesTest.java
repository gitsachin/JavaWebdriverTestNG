package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

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

public class AZ_47_NoPermissionToSeeProjectsOrLicensesTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	SystemPage system;
	
	@Test
	public void az_47_NoPermissionToSeeProjectsOrLicenses(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        system = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		signup.click("Action");
		system.clickOnExpectedButton(SystemPageObject.buttonAddUser_xpath, 1, "Add user account");
		
		system.clickOnArrowIconOfFields("Roles", 1);
		system.enterSearchString("QaRolesForAutomationTest_DND", 1, "Roles");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.name, "name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "Email Address");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Password");
		system.clickOnExpectedButton(SystemPageObject.buttonCreate_xpath, 1, "Create");
		
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		
		dashboard.sideMenuPanel(DashboardPageObject.leftSidePanel_xpath);
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Projects"), "Fail: [Projects] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Licenses"), "Fail: [Licenses] present in left menu panel.");
		
		assertFalse(dashboard.isExpecteItemPresentInDashBoard("Projects"), "Fail: [Projects] appears in dashboard.");
		assertFalse(dashboard.isExpecteItemPresentInDashBoard("Licenses"), "Fail: [Licenses] appears in dashboard.");
		
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, ConfigProperties.email01);
		signup.enterInput(1, ConfigProperties.pass01);
		signup.clickSubmit();
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.name, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.name, 2, "Delete", 5, 2);
		signup.click("Yes");

		
	}
	

}
