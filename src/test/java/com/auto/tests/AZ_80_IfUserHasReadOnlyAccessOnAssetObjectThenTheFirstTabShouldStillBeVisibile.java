package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_80_IfUserHasReadOnlyAccessOnAssetObjectThenTheFirstTabShouldStillBeVisibile extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	RolePage rolePage;
	SystemPage system;
	
	@Test
	public void az_80_IfUserHasReadOnlyAccessOnAssetObjectThenTheFirstTabShouldStillBeVisibile() {
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        rolePage = new RolePage(driver);
        system = new SystemPage(driver);
        
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
		rolePage.selectCheckbox("View Assets");
		signup.click("Create");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assetPage.clickOnArrowIconOfFields("Roles", 1);
		assetPage.enterSearchString(RolePageObject.roleName, 1, "Role");
		signup.enterInput(2,SystemPageObject.userName);
		signup.enterInput(4,SystemPageObject.email);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Password");
		signup.click("Create"); 
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, AssetsCategriesPageObject.assetName,"Search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,AssetsCategriesPageObject.assetName, 1, "Edit", 8, "Edit", 3);
		assertEquals(basepage.getText(By.xpath(DashboardPageObject.alertMessage_xpath)),DashboardPageObject.message);
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.printAssetId,RolePageObject.roleName, 1, "Edit", 4, "Edit", 3);
		rolePage.selectCheckbox("Manage");
		signup.click("Save");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, AssetsCategriesPageObject.assetName,"Search string: '"+InventoryPageObject.assetName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		rolePage.getTagAndEditAsset(InventoryPageObject.searchTableId,AssetsCategriesPageObject.assetName,1,"Edit",8,"Edit", 3);
		assertTrue(rolePage.verifyFieldDisable(0,"true"));
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, RolePageObject.roleName, "Roles for search");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.printAssetId,RolePageObject.roleName, 2, "Delete", 4, "Delete", 3);
		signup.click("Yes");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage);
		
		dashboard.clickButton("Users");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.userName,"Search string: '"+InventoryPageObject.assetName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,SystemPageObject.userName, 2, "Edit", 5, "Edit", 2);
		signup.click("Yes");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage);
	}

}
