package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.velocity.Template;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
import com.auto.pages.KnowledgeBasePage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TemplatePage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_83_HelpDeskTicketCustomFieldsNeedsToBePermissioned extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	UsersPage userPage;
	SystemPage systempage;
    KnowledgeBasePage knowledgePage;
    RolePage rolepage;
    SystemPage systemPage;
    HelpDeskPage helpPage;
	
	@Test()
	public void az_83_HelpDeskTicketCustomFieldsNeedsToBePermissioned(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        systempage = new SystemPage(driver); 
        knowledgePage = new KnowledgeBasePage(driver);
        rolepage = new RolePage(driver);
        systemPage = new SystemPage(driver);
        helpPage = new HelpDeskPage(driver);
        SoftAssert softAssertion= new SoftAssert();
        
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(3, "QATEST");
		userPage.selectOption(4, "SA1");
		signup.enterInput(5,SystemPageObject.userName);
		signup.enterInput(7,SystemPageObject.email);
		signup.enterInput(10,SystemPageObject.password);
		signup.clickWithScroll("Create");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage));
		dashboard.clickButton("Roles");
		dashboard.clickButton("Client Roles");
		systempage.click();
		signup.enterInput(0,"QATEST");
		knowledgePage.clickOnViewEditDeleteLocationIcon(SystemPageObject.roleGridSearchId,"View","QATEST", 2, 4,1);
		knowledgePage.clickOnViewEditDeleteLocationIcon(SystemPageObject.gridSearchId1,"Edit","SA1", 3, 4,1); 
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Edit Role");
		userPage.selectOption(2, "User (for client user)");
		rolepage.verifyTicketsPermission("Ticket Settings");
		rolepage.verifyTicketsPermission("View Ticket Fields");
		rolepage.verifyTicketsPermission("Add/Edit Ticket Fields");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreate_xpath, 1, "Save");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		systemPage.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		systemPage.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickMenuTab("Help Desk Tickets");
		assertFalse(dashboard.verifyPermission("Ticket Settings"),"Unchecked ticket permission is still present on page");
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		dashboard.clickButton("Client Roles");
		systempage.click();
		knowledgePage.clickOnViewEditDeleteLocationIcon(SystemPageObject.roleGridSearchId,"View","QATEST", 2, 4,1);
		knowledgePage.clickOnViewEditDeleteLocationIcon(SystemPageObject.gridSearchId1,"Edit","SA1", 3, 4,1); 
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Edit Role");
		userPage.selectOption(2, "User (for client user)");
		rolepage.selectCheckbox("Ticket Settings");
		rolepage.selectCheckbox("View Ticket Fields");
		rolepage.selectCheckbox("Add/Edit Ticket Fields");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreate_xpath, 1, "Save");
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		dashboard.clickMenuTab("Help Desk Tickets");
		assertTrue(dashboard.verifyPermission("Ticket Settings"),"Permission is does not  present on page");
		dashboard.clickButton("Ticket Settings");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Settings");
		dashboard.clickButton("NEW DEPARTMENT");
		signup.enterInput(1,DashboardPageObject.departmentName);
		signup.click("Create");
		assertTrue(helpPage.verifyDepartmentName(DashboardPageObject.departmentName)
				, "Department does not created");
		dashboard.logOut(ConfigProperties.logout_url);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "User search string:");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,"QATEST", 2, "Delete icon",6, "delete",2);
		signup.click("Yes");
		
		
	}
}
