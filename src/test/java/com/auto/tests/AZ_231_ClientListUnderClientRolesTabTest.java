package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_231_ClientListUnderClientRolesTabTest extends TestCore{
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
	
	@Test
	public void az_231_ClientListUnderClientRolesTab() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    assetPage = new AssetsPage(driver);
	    rolePage = new RolePage(driver);
	    system = new SystemPage(driver);
	   
	    driver.navigate().to(ConfigProperties.signin_url);
	  
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		dashboard.clickButton("Client Roles");
		int clientRoleSize = system.clientRolesRoeNumber(RolePageObject.roleGridId, "Clients", "Role Clients");
		assertTrue(system.isPaginationTabPresent(clientRoleSize), "Fail: Pagination tab abd pagination Info doesn't present in page.");
	}
}
