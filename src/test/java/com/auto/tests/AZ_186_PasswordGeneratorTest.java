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
import com.auto.pages.MonitoringPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.ReportsPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_186_PasswordGeneratorTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;
	RolePage rolePage;
	IssuePage issuePage;
	ProjectPage projectPage;
	MonitoringPage monitoring;
	ReportsPage reportsPage;

	@Test(priority=0)
	public void az_186_TC1_createClientWithPasswordGenerator(){

		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		helpDeskPage = new HelpDeskPage(driver);
		system = new SystemPage(driver);
		ticketPage = new TicketPage(driver);


		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);

		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "Qa", "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	

		system.clickRefreshButton(1);
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient, 2), "Fail: Client doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient, 1, false), "Fail: Searched item present in client grid.");

		dashboard.logOut(ConfigProperties.logout_url);
	}	
	
	@Test(priority=1)
	public void az_186_TC2_createUserWithPasswordGenerator(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	    
	    driver.navigate().to(ConfigProperties.site_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(5, "QATEST");
		userPage.selectOption(6, "QaRolesForAutomation_DND");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "email");	
		signup.clickWithScroll("Create");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		
		system.clickRefreshButton(1);
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 7, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=2)
	public void az_186_TC3_createStaffWithPasswordGenerator(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	    
	    driver.navigate().to(ConfigProperties.site_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
	    
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Staff");
		signup.click("Action");
		signup.click("Add saff account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add Staff Account");
		userPage.selectOption(2, "QATEST");
		userPage.selectOption(3, "Super Administrator");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.staffName, "Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.staff_Email, "Email");	
		signup.clickWithScroll("Create");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add Staff Account");
		
		system.clickRefreshButton(1);
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staffName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Staff", SystemPageObject.staffName, 2), "Fail: New staff doesn't created.");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.staffName, 2, "Delete", 6, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staffName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Staff", SystemPageObject.staffName, 1, false), "Fail: Searched item present in Staff grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
}
