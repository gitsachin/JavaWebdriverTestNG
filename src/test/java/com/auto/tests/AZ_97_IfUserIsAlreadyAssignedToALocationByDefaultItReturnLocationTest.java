package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
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

public class AZ_97_IfUserIsAlreadyAssignedToALocationByDefaultItReturnLocationTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	RolePage rolePage;
	SystemPage system;
	UsersPage userPage;
	HelpDeskPage helpPage;
	
	@Test
	public void az_97_IfUserIsAlreadyAssignedToALocationByDefaultItReturnLocationTest() throws AWTException{
	
	signup = new SignUpPage(driver);
	basepage = new BasePage(driver);
    dashboard = new DashboardPage(driver);
    ticketPage = new TicketPage(driver);
    assetPage = new AssetsPage(driver);
    rolePage = new RolePage(driver);
    helpPage= new HelpDeskPage(driver);
    userPage = new UsersPage(driver);
    SoftAssert softAssertion= new SoftAssert();
  
    assertEquals(driver.getTitle(), SignUpPageObject.title);
	signup.enterInput(0, ConfigProperties.superAdmin_email);
	signup.enterInput(1, ConfigProperties.superAdmin_pass);
	system = new SystemPage(driver);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	signup.click("Action");
	signup.click("Add user account");
	assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
	system.clickOnArrowIconOfFields("Client", 3);
	system.enterSearchString("QATEST", 1, "Client");
	system.pressEnterToSelectSearchItem(1);
	system.clickOnArrowIconOfFields("Role", 4);
	system.enterSearchString("QaRolesForAutomation_DND", 1, "Role");
	system.pressEnterToSelectSearchItem(1);
	signup.enterInput(5, SystemPageObject.userName);
	system.clickOnArrowIconOfFields("Location", 5);
	system.enterSearchString("QaLocationForAutomation_DND", 1, "Location");
	system.pressEnterToSelectSearchItem(1);
	signup.enterInput(7,SystemPageObject.email);
	signup.enterInput(10,SystemPageObject.password);
	signup.click("Create");
	assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
	dashboard.clickMenuTab("Help Desk Tickets");
	dashboard.clickButton("All Tickets");
	signup.click("Action");
	dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
	userPage.selectOption(3,"QATEST");
	
	system.enterSearchString(SystemPageObject.userName, 1, "User or Email Address");
	system.downAndpressEnterToSelectSearchItem(1);
	
	assertTrue(helpPage.verifyDataCreatelist(HelpDeskTicketsPageObject.locationFieldId,"QaLocationForAutomation_DND"));
	system.clickOnArrowIconOfFields("Return Location", 5);
	assertTrue(helpPage.verifyIsLocationPresent(HelpDeskTicketsPageObject.locationid,"QaLocationForAutomation_DND"));
	driver.navigate().to(ConfigProperties.site_url+"?route=people/users&section=users");
	assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string: ");
	assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	system.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, SystemPageObject.email, 4, "Delete", 6, 2);
	signup.click("Yes");
	
	softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
	
	log("User delete from Grid", ILogLevel.TEST);
	}
}
