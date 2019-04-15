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

public class AZ_54_UsersShouldOnlySeeTheirTicketsTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	RolePage rolePage;
	TicketPage ticketPage;
	SystemPage system;
	UsersPage userPage;
	AssetsPage assetPage;
	HelpDeskPage helpDesk;
	
	
	@Test (priority=0)
	public void az_54_allTicketsPermissions() throws AWTException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        rolePage = new RolePage(driver);
        ticketPage = new TicketPage(driver);
        system = new SystemPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        helpDesk = new HelpDeskPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user1_Email, 3, "Delete", 6, 2);
		
		signup.click("Yes");
		log("Created Users 1 delete from Grid", ILogLevel.TEST);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user2_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user2_Email, 3, "Delete", 6, 2);
		signup.click("Yes");
		log("Created Users 2 delete from Grid", ILogLevel.TEST);
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Roles", "[Roloes] inner menu");
		dashboard.clickButton("Client Roles");
		signup.click("Action");
		dashboard.clickButton("New client role");
		system.clickOnArrowIconOfFields("Client Field", 1);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		signup.enterInput(1, RolePageObject.roleName);
		system.clickOnArrowIconOfFields("Role Type Field", 2);
		system.enterSearchString("User (for client user)", 1, "Role Type");
		system.pressEnterToSelectSearchItem(1);
		rolePage.checkAllTicketPermission("Ticket", 5, 2);
		signup.click("Create");
		
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		system.clickOnArrowIconOfFields("Client Field", 3);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.clickOnArrowIconOfFields("Role Filed", 4);
		system.enterSearchString(RolePageObject.roleName, 1, "Select Role");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "User 1", "User_1 Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.user1_Email, "User_1 Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User_1 Password");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User_1 Confirm Password");
		signup.clickWithScroll("Create");
		system.clickOnArrowIconOfFields("Client Field", 1);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", "User 1", 3), "Fail: New User doesn't created.");
		
		signup.click("Action");
		signup.click("Add user account");
		system.clickOnArrowIconOfFields("Client Field", 3);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.clickOnArrowIconOfFields("Role Filed", 4);
		system.enterSearchString(RolePageObject.roleName, 1, "Select Role");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "User 2", "User_2 Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.user2_Email, "User_2 Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User_2 Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User_2 Confirm Password");
		signup.clickWithScroll("Create");
		system.clickOnArrowIconOfFields("Client Field", 1);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user2_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", "User 2", 3), "Fail: New User doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, SystemPageObject.user1_Email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "User 1");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] present in left menu panel.");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		signup.click("New ticket");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		userPage.selectOption(1, "QaDepartmentForAutomationTest");
		userPage.selectOption(3, "QaLocationForAutomation_DND");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, "QaAssetForAutomation", "Asset");
		assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Suggestion list");
		signup.clickWithScroll("Done");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, SystemPageObject.user2_Email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "User 2");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		helpDesk.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: User 1 ticket present in User 2 Account");
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Edit", 10, "Tickets", 2);
		signup.click("Yes");
		log("Created Ticket delete from Grid", ILogLevel.TEST);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Roles", "[Roles] inner menu");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, RolePageObject.roleName, 3, "Delete", 4, 2);
		signup.click("Yes");
		log("Created Roles delete from Grid", ILogLevel.TEST);	
		
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user1_Email, 3, "Delete", 6, 2);
		signup.click("Yes");
		log("Created Users 1 delete from Grid", ILogLevel.TEST);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user2_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user2_Email, 3, "Delete", 6, 2);
		signup.click("Yes");
		log("Created Users 2 delete from Grid", ILogLevel.TEST);
	}
	
	@Test(priority=1)
	public void az_54_withoutViewOthersTicketsPermissions() throws AWTException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        rolePage = new RolePage(driver);
        ticketPage = new TicketPage(driver);
        system = new SystemPage(driver);
        userPage = new UsersPage(driver);
        assetPage = new AssetsPage(driver);
        helpDesk = new HelpDeskPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		
		
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user1_Email, 4, "Delete", 7, 2);
		signup.click("Yes");
		log("Created Users 1 delete from Grid", ILogLevel.TEST);
		
		dashboard.clickOnExpectedMenus("Roles", "[Roloes] inner menu");
		dashboard.clickButton("Client Roles");
		signup.click("Action");
		dashboard.clickButton("New client role");
		system.clickOnArrowIconOfFields("Client Field", 1);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		signup.enterInput(1, RolePageObject.roleName);
		system.clickOnArrowIconOfFields("Role Type Field", 2);
		system.enterSearchString("User (for client user)", 1, "Role Type");
		system.pressEnterToSelectSearchItem(1);
		rolePage.checkLimitedTicketsPermission("Ticket", "View Others Tickets", 5, 2);
		signup.click("Create");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		
		
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		system.clickOnArrowIconOfFields("Client Field", 3);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.clickOnArrowIconOfFields("Role Filed", 4);
		system.enterSearchString(RolePageObject.roleName, 1, "Select Role");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "User 1", "User_1 Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.user1_Email, "User_1 Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User_1 Password");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User_1 Confirm Password");
		signup.clickWithScroll("Create");
		system.clickOnArrowIconOfFields("Client Field", 1);
		system.enterSearchString("QATEST", 1, "Select Client");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", "User 1", 3), "Fail: New User doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, SystemPageObject.user1_Email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "User 1");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] present in left menu panel.");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		signup.click("New ticket");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		userPage.selectOption(2, "QaLocationForAutomation_DND");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, "QaAssetForAutomation", "Asset");
		assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Suggestion list");
		signup.clickWithScroll("Done");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", ConfigProperties.ticket, 2), "Fail: ["+ConfigProperties.ticket+"] Ticket doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		//Super Admin able to see all the tickets.
		
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		//Client Admin able to see all the tickets.
		
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(helpDesk.isItemAddedInGrid(SystemPageObject.searchTableId, "Ticket", HelpDeskTicketsPageObject.ticketName, 2), "Fail: Ticket doesn't created.");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketName, 3, "Edit", 10, "Tickets", 2);
		signup.click("Yes");
		log("Created Ticket delete from Grid", ILogLevel.TEST);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Roles", "[Roles] inner menu");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, RolePageObject.roleName, 3, "Delete", 4, 2);
		signup.click("Yes");
		log("Created Roles delete from Grid", ILogLevel.TEST);	
		
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.user1_Email, "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.user1_Email, 3, "Delete", 6, 2);
		signup.click("Yes");
		log("Created Users 1 delete from Grid", ILogLevel.TEST);

	}
}
