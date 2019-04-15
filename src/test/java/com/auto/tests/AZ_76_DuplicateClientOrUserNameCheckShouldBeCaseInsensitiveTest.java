package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_76_DuplicateClientOrUserNameCheckShouldBeCaseInsensitiveTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	SystemPage system;
	
	@Test(priority=0)
	public void az_76_DuplicateUserNameCheckShouldBeCaseInsensitive(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        system = new SystemPage(driver);
         
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		signup.click("Action");
		system.clickOnExpectedButton(SystemPageObject.buttonAddUser_xpath, 1, "Add user account");
		
		system.clickOnArrowIconOfFields("Roles", 1);
		system.enterSearchString(ConfigProperties.role, 1, "Roles");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "Test Case Sensitive".toUpperCase(), "UpperCase User Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.email, "User Email Address");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonCreate_xpath, 1, "Create");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Test Case Sensitive".toUpperCase(), "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Users Table", "Test Case Sensitive".toUpperCase(), 2), "Fail: ["+"Test Case Sensitive".toUpperCase()+"] doesn't added in Users grid.");
		
		signup.click("Action");
		system.clickOnExpectedButton(SystemPageObject.buttonAddUser_xpath, 1, "Add user account");
		system.clickOnArrowIconOfFields("Roles", 1);
		system.enterSearchString(ConfigProperties.role, 1, "Roles");
		system.pressEnterToSelectSearchItem(1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "Test Case Sensitive".toLowerCase(), "LowerCase User Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, "qaUser_"+system.returnRandomNumber(1000)+"@mailinator.com", "User Email Address");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "User Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "User Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonCreate_xpath, 1, "Create");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Test Case Sensitive".toLowerCase(), "Search string: ");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Users Table", "Test Case Sensitive".toLowerCase(), 2), "Fail: ["+"Test Case Sensitive".toLowerCase()+"] doesn't added in Users grid.");
		assertTrue(system.searchResult(SystemPageObject.searchTableId, "Users Table", "Test Case Sensitive".toUpperCase(), 2), "fail: [Duplicate] user doesn't created.");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Test Case Sensitive".toUpperCase(), "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "Test Case Sensitive", 2, "Delete", 6, 2);
		signup.click("Yes");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Test Case Sensitive".toLowerCase(), "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "Test Case Sensitive", 2, "Delete", 6, 2);
		signup.click("Yes");
			    
	}
	
	
	@Test(priority=1)
	public void az_76_DuplicateClientCheckShouldBeCaseInsensitive(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        system = new SystemPage(driver);
        
   
        	
    	driver.navigate().to(ConfigProperties.signin_url);
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertTrue(system.isExpectedSecreenAppear(SystemPageObject.headerText_xpath, "Clients"), "Fail: [Clients] page doesn't open.");
		
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients", "QATEST", 2),"Fail: [QATEST] doesn't present in grid");
		signup.click("Actions");
		signup.click("Add client");
		
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "qatest", "Cient Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsClientName_xpath, 1, "Admin_"+BasePage.autogenerateNumber(4), "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsClientEmail_xpath, 1, SystemPageObject.email, "Admin Email Address");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
	
		assertTrue(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] pop-up doesn't close.");
		assertTrue(system.isErrorPressent(SystemPageObject.errorMessage_xpath, SystemPageObject.errorMessage), "Fail: [Error Message] doesn't appear for entering duplicate client name.");
		system.clickOnExpectedButton(SystemPageObject.button_Cancel, 1, "Cancel");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "qatest", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedClientPresentInGrid(SystemPageObject.searchTableId, "Clients", "qatest", 2),"Fail: [qatest] doesn't present in grid");
		
		// Create Clients with number
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.clienwithNumberName, "Client Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "Admin_"+BasePage.autogenerateNumber(4), "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		
		assertFalse(system.verifyResponseMessage("Clients", SystemPageObject.errorMessage_xpath, "Error Message", SystemPageObject.errorMessageForEmail), "Fail: [Error Message For Existing Email] doesn't appear for entering duplicate client name.");
		assertFalse(system.isExpectedPopupOpen("Create New Client"), "Fail: [Create New Client] pop-up doesn't close.");
	
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.clienwithNumberName, "Search string");	
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.clienwithNumberName, 2), "Fail: Client doesn't created.");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.clienwithNumberName, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.clienwithNumberName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.clienwithNumberName, 1, false), "Fail: Searched item present in client grid.");
				
	}

}
