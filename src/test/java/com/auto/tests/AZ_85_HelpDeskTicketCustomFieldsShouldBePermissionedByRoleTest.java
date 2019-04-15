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

public class AZ_85_HelpDeskTicketCustomFieldsShouldBePermissionedByRoleTest extends TestCore {
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
	public void az_85_HelpDeskTicketCustomFieldsShouldBePermissionedByRole() throws AWTException {
		
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
		signup.click("Create");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickOnInnerSubmenu("Tickets", "Attributes", "Fields");
		signup.click("Action");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewFields_xpath, 1, "New field");
		ticketPage.clickOnArrowIconForDepartmentField();
		ticketPage.enterSearchString(ConfigProperties.departName);
		assertEquals(basepage.getText(By.xpath(HelpDeskTicketsPageObject.containerDepartmentField)), ConfigProperties.departName);
		dashboard.enterExpectedValueInTextField(HelpDeskTicketsPageObject.ticketFieldName_xpath, 1, HelpDeskTicketsPageObject.ticketFieldName, "Ticket Field: Name");
		assertFalse(helpdesk.isRolePermissonSet(RolePageObject.roleName), "Fail: Role permission is set. ");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewRole, 1, "NEW");
		helpdesk.selectItemFrmDropdownList(RolePageObject.roleName);
		assertTrue(helpdesk.isExpectedOptionPresentInList("View Only"), "Fail: [View Only] option doesn't present in table.");
		assertTrue(helpdesk.isExpectedOptionPresentInList("Hidden"), "Fail: [Hidden] option doesn't present in table.");
		assertTrue(helpdesk.isExpectedOptionPresentInList("Editable"), "Fail: [Editable] option doesn't present in table.");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonAddRole_xpath, 1,"ADD");
		assertTrue(helpdesk.isRolePermissonSet(RolePageObject.roleName), "Fail: Role permission doesn't set.");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.saveTicketField_xpath, 1, "Create");
		
		helpdesk.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, HelpDeskTicketsPageObject.ticketFieldName, "Search string: ");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, HelpDeskTicketsPageObject.ticketFieldName, 2, "Delete", 6, "Remove", 3);
		signup.click("Yes");
		log("Ticket Field delete from Grid", ILogLevel.TEST);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, RolePageObject.roleName, 3, "Delete", 4, 2);
		signup.click("Yes");
		log("Created Roles delete from Grid", ILogLevel.TEST);	
	
}

}
