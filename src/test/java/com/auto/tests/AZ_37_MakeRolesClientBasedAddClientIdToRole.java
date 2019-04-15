package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
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
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_37_MakeRolesClientBasedAddClientIdToRole extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	RolePage rolePage;
	SystemPage system;
	
	@Test
	public void az_37_MakeRolesClientBasedAddClientIdToRole() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        SoftAssert softAssertion= new SoftAssert();
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		system = new SystemPage(driver);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		signup.click("Action");
		dashboard.clickButton("New client role");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Client Role");
		rolePage.clickOnClient();
		ticketPage.enterSearchString(RolePageObject.clientName);
		signup.enterInput(1, RolePageObject.roleName);
		dashboard.clickButton("Check All");
		signup.click("Create");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		dashboard.clickButton("Client Roles");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.clientName, "Roles for search");
		rolePage.clickOnView(InventoryPageObject.printAssetId, RolePageObject.clientName, 1,2,"View",  "Role", 2);
		rolePage.verfyRole(InventoryPageObject.printAssetId, RolePageObject.clientName,3);
		rolePage.clickRoleDeletIcon(InventoryPageObject.printAssetId,1,2);
		signup.click("Yes");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: Delete Success aleart untracked.");
	
	}

}
