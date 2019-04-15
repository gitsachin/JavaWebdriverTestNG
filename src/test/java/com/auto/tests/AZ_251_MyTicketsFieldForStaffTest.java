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
import com.auto.pages.HelpDeskPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_251_MyTicketsFieldForStaffTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;
	IssuePage issuePage;

	@Test
	public void az_251_MyTicketsFieldForStaffTest() throws InterruptedException {

		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		helpDeskPage = new HelpDeskPage(driver);
		system = new SystemPage(driver);
		ticketPage = new TicketPage(driver);
		issuePage = new IssuePage(driver);

		driver.navigate().to(ConfigProperties.signin_url);

		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");

		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		Thread.sleep(2000);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		issuePage.clickOnArrowIconOfFields("Client", 5);
		issuePage.enterSearchString("QATEST", 1, "Client");
		issuePage.clickOnArrowIconOfFields("Role", 6);
		issuePage.enterSearchString(ConfigProperties.role, 1, "Role");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User Confirm Password");
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		dashboard.logOut(ConfigProperties.logout_url);
		driver.navigate().to(ConfigProperties.signin_url);
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		assertTrue(dashboard.verifyServiceRequest("Service Request"),"Service request does not present on page");
		String[] ticketSubMenu = {"Awaiting Reply", "Active Tickets", "All Tickets", "My Ticket", "Basic Settings", "Settings"};
		int length = ticketSubMenu.length;
		assertTrue(dashboard.verrifySubMenu("Service Request", 2, length, ticketSubMenu), "Fail: Expected sub-menu missing Under Ticket list");
		dashboard.logOut(ConfigProperties.logout_url);
		driver.navigate().to(ConfigProperties.signin_url);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");

		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 7, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}

}
