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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_195_SearchRolesFromEntireTableTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	SystemPage  system;
	RolePage rolePage;
	IssuePage issuePage;


	@Test(priority=0)
	public void AZ_195_SearchRolesFromEntireTable() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
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
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		dashboard.clickButton("Client Roles");
		system.verifySearchItemAfterNavigateToPagination(RolePageObject.roleGridId, "Client Role: CLient", "QATEST", 2, SystemPageObject.roleSearchField_xpath, 2,  "Clients Role: Client Search Field" );
		system.clickOnExpectionIconsForRoles(RolePageObject.roleGridId,"QATEST",2,"Eye Ball", 4,1);
		system.verifySearchItemAfterNavigateToPagination(RolePageObject.searchRoleGridId, "Client Role: Role", ConfigProperties.role, 3, SystemPageObject.roleSearchField_xpath, 1,  "Clients Role: Role Search Field" );
	}

	@Test(priority=1)
	public void AZ_317_CheckClientInDropdownList(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		rolePage = new RolePage(driver);
		system = new SystemPage(driver);
		issuePage = new IssuePage(driver);

		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		dashboard.clickButton("Client Roles");
		issuePage.clickOnArrowIconOfFields("Client", 1);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.searchDepartmentField, 1, "QATEST", "Client Role");
		assertTrue(rolePage.verifyExpectedClientInDropdownList("QATEST"),"[QATEST] does not present in dropdown list");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.searchDepartmentField, 1, "Client 2 inc.", "Client Role");
		assertTrue(rolePage.verifyExpectedClientInDropdownList("Client 2 Inc."),"[Client 2 Inc.] does not present in dropdown list");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.searchDepartmentField, 1, "Client inc.", "Client Role");
		assertTrue(rolePage.verifyExpectedClientInDropdownList("Client Inc."),"[Client Inc.] does not present in dropdown list");
	}

}
