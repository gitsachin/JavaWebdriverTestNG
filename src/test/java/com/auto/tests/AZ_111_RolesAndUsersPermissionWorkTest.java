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
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_111_RolesAndUsersPermissionWorkTest extends TestCore {
	SignUpPage signup;
	DashboardPage dashboard;
	BasePage basepage;
	SystemPage system;
	RolePage rolePage;
	UsersPage userPage;
	
	@Test(priority=0)
	public void az_111_RolesAndUsersPermissionWorkForSuperAdmin(){
		signup = new SignUpPage(driver);
		dashboard = new DashboardPage(driver);
		basepage = new BasePage(driver);
		system = new SystemPage(driver);
		rolePage = new RolePage(driver);
		userPage = new UsersPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User doesn't redirected to Dashboard page.");
		
		dashboard.sideMenuPanel(DashboardPageObject.leftSidePanel_xpath);
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Dashboard"), "Fail: [Dashboard] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Inventory"), "Fail: [Inventory] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Projects"), "Fail: [Projects] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Monitoring"), "Fail: [Monitoring] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Reports"), "Fail: [Reports] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Templates"), "Fail: [Templates] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Subscription"), "Fail: [Subscription] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Knowledge Base"), "Fail: [Knowledge Base] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "System"), "Fail: [System] present in left menu panel.");
		
		dashboard.clickMenuTab("System");
		dashboard.sideMenuPanel(DashboardPageObject.leftSideSubMenuPanel_xpath);
		dashboard.clickMenuTab("People");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Users"));
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Staff"));
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Roles"));
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Contacts"));
		
		dashboard.clickOnExpectedMenus("Staff", "[Staff] inner menu");
		signup.click("Action");
		assertTrue(dashboard.isExpectedFieldPresent("Satff", "Add staff account", SystemPageObject.addStaffOption_xpath), "Fail: Add staff account option doesn't present in 'Staff' table.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=1)
	public void az_111_RolesAndUsersPermissionWorkForClientAdmin(){
		
		signup = new SignUpPage(driver);
		dashboard = new DashboardPage(driver);
		basepage = new BasePage(driver);
		system = new SystemPage(driver);
		rolePage = new RolePage(driver);
		userPage = new UsersPage(driver);
		
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User doesn't redirected to Dashboard page.");
		
		dashboard.sideMenuPanel(DashboardPageObject.leftSidePanel_xpath);
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Dashboard"), "Fail: [Dashboard] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Inventory"), "Fail: [Inventory] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Projects"), "Fail: [Projects] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Monitoring"), "Fail: [Monitoring] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Reports"), "Fail: [Reports] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Templates"), "Fail: [Templates] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Subscription"), "Fail: [Subscription] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Knowledge Base"), "Fail: [Knowledge Base] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "System"), "Fail: [System] present in left menu panel.");	
				
		dashboard.clickMenuTab("System");
		dashboard.sideMenuPanel(DashboardPageObject.leftSideSubMenuPanel_xpath);
		dashboard.clickMenuTab("People");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Users"), "[Users] inner sub-menu doesn't present.");
		assertFalse(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Staff"), "[Staff] inner sub-menu present.");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Roles"), "[Roles] inner sub-menu doesn't present.");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("System>People", "Contacts"), "[Contacts] inner sub-menu doesn't present.");
		
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		signup.click("Action");
		assertTrue(dashboard.isExpectedFieldPresent("Users", "Add user account", SystemPageObject.addUserOption_xpath), "Fail: Add users account option doesn't present in 'Users' table.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.email, "Search string: '"+ConfigProperties.email+"'");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isExpectedIconPresentInGrid(InventoryPageObject.searchTableId, "Users Table", ConfigProperties.email, 1, "Edit", 5, "Edit", 3));
		
		dashboard.clickOnExpectedMenus("Roles", "[Roloes] inner menu");
		signup.click("Action");
		assertTrue(dashboard.isNewRoleOptionPresent("Roles", "New role", SystemPageObject.addRoleOption_css), "Fail: New Role option doesn't present in 'Roles' table.");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, ConfigProperties.role, "Roles for search");
		assertTrue(system.isExpectedIconPresentInRolesGrid(SystemPageObject.roleGridSearchId, "Roles", ConfigProperties.role, 1, "Edit", 4, "Edit", 3));
		dashboard.logOut(ConfigProperties.logout_url);
		
	}
	
	@Test(priority=2)
	public void az_111_RolesAndUsersPermissionWorkForStandardUser(){
		
		signup = new SignUpPage(driver);
		dashboard = new DashboardPage(driver);
		basepage = new BasePage(driver);
		system = new SystemPage(driver);
		rolePage = new RolePage(driver);
		userPage = new UsersPage(driver);
	
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Roles", "[Roloes] inner menu");
		signup.click("Action");
			
		dashboard.clickButton("New  role");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, RolePageObject.roleName, "name");		
		dashboard.clickButton("Uncheck All");
		signup.click("Create");
		
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(1, RolePageObject.roleName);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "User Repassword");
		signup.clickWithScroll("Create");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1,SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User doesn't redirected to Dashboard page.");
		
		dashboard.sideMenuPanel(DashboardPageObject.leftSidePanel_xpath);
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Dashboard"), "Fail: [Dashboard] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Inventory"), "Fail: [Inventory] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Projects"), "Fail: [Projects] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Monitoring"), "Fail: [Monitoring] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Reports"), "Fail: [Reports] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Templates"), "Fail: [Templates] present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Subscription"), "Fail: [Subscription] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Knowledge Base"), "Fail: [Knowledge Base] present in left menu panel.");
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "System"), "Fail: [System] present in left menu panel.");	
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.email, 3, "Delete", 5, 2);
		signup.click("Yes");
		log("Created Users delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnExpectedMenus("Roles", "[Roles] inner menu");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, RolePageObject.roleName, 3, "Delete", 4, 2);
		signup.click("Yes");
		log("Created Roles delete from Grid", ILogLevel.TEST);	
	}

}
