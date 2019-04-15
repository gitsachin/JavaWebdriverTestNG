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
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_216_ErrorOccurredValidationAppearsWhileResetPasswordTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	SystemPage  system;
	
	@Test
	public void az_216_ErrorOccurredValidationAppearsWhileResetPassword(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    system = new SystemPage(driver);
	    
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		signup.click("Action");
		signup.click("Add user account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add User Account");
		userPage.selectOption(5, "QATEST");
		userPage.selectOption(6, ConfigProperties.role);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.userName, "User Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User Confirm Password");
		signup.clickWithScroll("Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 3), "Fail: New User doesn't created.");
		
		system.clickOnResetPasswordIcons(SystemPageObject.searchTableId, SystemPageObject.email, 4, "Reset Password", 7, 2);
		assertTrue(system.isConfirmationMessageAppear(SystemPageObject.passwordResetAlert), "Fail: ["+SystemPageObject.passwordResetAlert+"] success aleart untracked.");
		dashboard.logOut(ConfigProperties.logout_url);
		
		signup.enterInput(0, SystemPageObject.email);
		signup.enterInput(1,  SystemPageObject.password);
		signup.clickSubmit();
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.forceRestPass), "Fail: [Force Password Reset] url doesnot open.");
		
		basepage.navigateUrl(SignUpPageObject.mailinator_url);
		signup.enterInput(0, SystemPageObject.email);
		signup.click("Go!");
		signup.selectMail("Password Reset");
		basepage.switchFrame("msg_body");
		String refLink = signup.fetchUrl();
		
		driver.navigate().to(refLink);
		assertTrue(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.resetPasswrdBox_xapth, SignUpPageObject.resetPasswrdBox, "Reset Password"), "Fail: Password Reset screen doesn't open.");
		
		signup.enterInput(0, SignUpPageObject.resetPassword);
	    signup.enterInput(1, SignUpPageObject.resetPassword);
	    signup.click("Change Password");
	    assertFalse(signup.isExpectedTextAppearInExpectedSection(SignUpPageObject.resetPasswrdBox_xapth, SignUpPageObject.resetPasswrdBox, "Reset Password"), "Fail: Password Reset screen still present.");
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    signup.enterInput(0, SystemPageObject.email);
	    signup.enterInput(1, SignUpPageObject.resetPassword);
	    signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.userName);

		dashboard.logOut(ConfigProperties.logout_url);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Delete", 7, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", SystemPageObject.userName, 1, false), "Fail: Searched item present in User grid.");
		dashboard.logOut(ConfigProperties.logout_url);
			
	}

}
