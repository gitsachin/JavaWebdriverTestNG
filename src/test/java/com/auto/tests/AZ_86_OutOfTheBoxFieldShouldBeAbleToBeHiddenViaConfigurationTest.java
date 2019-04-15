package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_86_OutOfTheBoxFieldShouldBeAbleToBeHiddenViaConfigurationTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	RolePage rolePage;
	SystemPage system;
	TicketPage ticketPage;
	HelpDeskPage helpdesk;
	
	@Test
	public void az_86_OutOfTheBoxFieldShouldBeAbleToBeHiddenViaConfiguration() throws AWTException {
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        rolePage = new RolePage(driver);
        system = new SystemPage(driver);
        ticketPage = new TicketPage(driver);
        helpdesk = new HelpDeskPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		signup.click("Action");
		dashboard.clickButton("New  role");
		signup.enterInput(0,RolePageObject.roleName);
		rolePage.checkAllTicketPermission("Ticket", 4, 2);
		rolePage.expectedFieldUncheckedIfChecked("View CC Recipient Field", SystemPageObject.ccRecipient_xpath);
		rolePage.expectedFieldUncheckedIfChecked("View Time Spent Field", SystemPageObject.timeSpent_xpath);
		assertFalse(rolePage.isExpectedFieldUnchecked("View CC Recipient Field", SystemPageObject.ccRecipient_xpath),"Fail: 'View Time Spent Field' doesn't unchecked. ");
		assertFalse(rolePage.isExpectedFieldUnchecked("View Time Spent Field", SystemPageObject.timeSpent_xpath),"Fail: 'View Time Spent Field' doesn't unchecked. ");
		signup.click("Create");
		
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(1, RolePageObject.roleName);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "User Confirm Password");
		signup.clickWithScroll("Create");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Active Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assertFalse(helpdesk.isExpectedFieldPresent("CC Recipients", HelpDeskTicketsPageObject.fieldCC_xpath));
		assertFalse(helpdesk.isExpectedFieldPresent("Time Spent", HelpDeskTicketsPageObject.fieldTime_xpath));
		dashboard.logOut(ConfigProperties.logout_url);
		
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.email, 3, "Delete", 5, 2);
		signup.click("Yes");
		log("Created Users delete from Grid", ILogLevel.TEST);
		
		dashboard.clickButton("Roles");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, RolePageObject.roleName, 3, "Delete", 4, 2);
		signup.click("Yes");
		log("Created Roles delete from Grid", ILogLevel.TEST);	
	
}


}
