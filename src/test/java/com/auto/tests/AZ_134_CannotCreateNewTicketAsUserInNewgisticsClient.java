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
import com.auto.pages.AssetsPage;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

import common.Assert;

public class AZ_134_CannotCreateNewTicketAsUserInNewgisticsClient extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	HelpDeskPage helpDeskPage;
	SystemPage  system;
	TicketPage ticketPage;
	
	@Test
	public void az_134_CannotCreateNewTicketAsUserInNewgisticsClient() {
	
	signup = new SignUpPage(driver);
	configprop = new ConfigProperties();
	basepage = new BasePage(driver);
    dashboard = new DashboardPage(driver);
    assetPage = new AssetsPage(driver);
    userPage = new UsersPage(driver);
    helpDeskPage = new HelpDeskPage(driver);
    system = new SystemPage(driver);
    ticketPage = new TicketPage(driver);
    SoftAssert softAssertion= new SoftAssert();
    
    
    assertEquals(driver.getTitle(), SignUpPageObject.title);
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	signup.click("Action");
	signup.click("Add user account");
	assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
	system.clickOnArrowIconOfFields("Role Field", 1);
	system.enterSearchString("SA1", 1, "Role name");
	system.pressEnterToSelectSearchItem(1);
	signup.enterInput(2,SystemPageObject.clienName);
	signup.enterInput(4,SignUpPageObject.email);
	system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Password");
	system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "Repassword");
	signup.clickWithScroll("Create");
	softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: Create Success aleart untracked.");
	
	dashboard.clickButton("Roles");
	signup.enterInput(0,"SA1");
	system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId,"SA1",3,"Edit", 4,1);
	userPage.selectOption(1,"User (for client user)");
	dashboard.clickButton("Uncheck All");
	signup.click("Save");
	softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: Add Success aleart untracked.");
	system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0,SignUpPageObject.email);
	signup.enterInput(1,SystemPageObject.password);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.clienName);
	assertFalse(dashboard.isExpectedMenuPresentInLeftPlanel(SignUpPageObject.ButtonTagA_xpath+"Help Desk Tickets"+DashboardPageObject.menu1_xpath,"Help Desk Tickets", "under left menu panel"), "Fail: [Help Desk Tickets] present in left menu panel.");
	system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Roles");
	system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId,"SA1",3,"Edit", 4,1);
	userPage.selectOption(1,"User (for client user)");
	dashboard.clickButton("Check All");
	signup.click("Save");
	softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: Add Success aleart untracked.");
	system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0,SignUpPageObject.email);
	signup.enterInput(1,SystemPageObject.password);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.clienName);
	dashboard.clickMenuTab("Help Desk Tickets");
	dashboard.clickButton("All Tickets");
	signup.click("Action");
	dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
	assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
	ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
	userPage.selectOption(3, ConfigProperties.location);
	signup.clickWithScroll("Done");
	system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,SignUpPageObject.email, "Search string ");
	assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,SignUpPageObject.email,2,"Delete", 6,"User",3);
	signup.click("Yes");
	log("Created user: ["+SignUpPageObject.email+"] deleted and test finished.", ILogLevel.TEST);
	
	
	
	}
	
}
