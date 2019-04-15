package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.MonitoringPageObject;
import com.auto.pageobject.ProjectPageObject;
import com.auto.pageobject.ReportsPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.IssuePage;
import com.auto.pages.MonitoringPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.ReportsPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class SmokeTest extends TestCore {
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
	IssuePage issuePage;
	ProjectPage projectPage;
	MonitoringPage monitoring;
	ReportsPage reportsPage;
	
	@Test(priority=0)
	public void createEditDeleteClient(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	    
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		signup.click("Actions");
		system.clickOnExpectedButton(SystemPageObject.buttonAddClient_xpath, 1, "Add Client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient, "Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAssetTag_xpath, 1, SystemPageObject.assetTag, "Asset Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsLicensetag_xpath, 1, SystemPageObject.licenseTag, "License Tag Prefix");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminName_xpath, 1, "Qa", "Admin Name");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminEmail_xpath, 1, SystemPageObject.admienEmail, "Admin Email");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsAdminPass_xpath, 1, SystemPageObject.adminPassword, "Admin Passsword");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.adminPassword, "Admin Confirm Password");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient, 2), "Fail: Client doesn't created.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient, 2, "Edit", 6, 2);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Edit Client");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.nameOfClient+"Test", "Name");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Save");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient,2), "Fail: Searched Operation.");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Clients", SystemPageObject.nameOfClient+"Test",2), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient+"Test", 2, "Edit", 6, 2);
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Edit Client");
		assertFalse(system.isDataUpdateInExpectedField(SystemPageObject.textFieldsName_xpath, "Client Name", SystemPageObject.nameOfClient), "Fail: Edit Operation.");
		system.clickOnExpectedButton(SystemPageObject.buttonClientCreate_xpath, 1, "Create");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.nameOfClient+"Test", 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.nameOfClient+"Test", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SystemPageObject.nameOfClient+"Test", 1, false), "Fail: Searched item present in client grid.");
		
		dashboard.logOut(ConfigProperties.logout_url);
	}
	 

	@Test(priority=1)
	public void createEditDeleteUser() throws InterruptedException{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	    
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
		Thread.sleep(2000);
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
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.userName, 3, "Edit", 7, 1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "QaTestUser 01", "name");	
		signup.clickWithScroll("Save");
		dashboard.clickButton("Users");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User Name", SystemPageObject.userName, 3), "Fail: Searched Operation.");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "User Name", "QaTestUser 01", 3), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QaTestUser 01", 3, "Edit", 7, 1);
		assertFalse(system.isDataUpdateInExpectedField(SystemPageObject.textFieldsName_xpath, "User Name", SystemPageObject.userName), "Fail: Edit Operation.");
		dashboard.clickButton("Users");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QaTestUser 01", 3, "Delete", 7, 2);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "User", "QaTestUser 01", 1, false), "Fail: Searched item present in User grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	

	@Test(priority=2)
	public void createEditDeleteAndLoginStaff(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    userPage = new UsersPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    system = new SystemPage(driver);
	    ticketPage = new TicketPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Staff");
		signup.click("Action");
		signup.click("Add saff account");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Add Staff Account");
		userPage.selectOption(2, "QATEST");
		userPage.selectOption(3, "Super Administrator");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, SystemPageObject.staffName, "Staff Name");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsEmail_xpath, 1, SystemPageObject.staff_Email, "Staff Email");	
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsPassword_xpath, 1, SystemPageObject.password, "Staff Password");
		system.enterExpectedValueInTextField(SystemPageObject.textFieldRePass_xpath, 1, SystemPageObject.password, "Staff Confirm Password");
		signup.clickWithScroll("Create");
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staff_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(SystemPageObject.searchTableId, "Staff", SystemPageObject.staffName, 2), "Fail: New staff doesn't created.");
		
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SystemPageObject.staffName, 2, "Edit", 6, 1);
		system.enterExpectedValueInTextField(SystemPageObject.textFieldsName_xpath, 1, "QaTestStaff 01", "Name");	
		signup.clickWithScroll("Save");
		dashboard.clickButton("Staff");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staff_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Staff Name", SystemPageObject.staffName, 2), "Fail: Searched Operation.");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staff_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isSearchedItemPresentInGrid(SystemPageObject.searchTableId, "Staff Name", "QaTestStaff 01", 2), "Fail: Searched Operation.");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QaTestStaff 01", 2, "Edit", 6, 1);
		assertFalse(system.isDataUpdateInExpectedField(SystemPageObject.textFieldsName_xpath, "Staff Name", SystemPageObject.staffName), "Fail: Edit Operation.");
		dashboard.clickButton("Staff");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		driver.navigate().to(ConfigProperties.site_url);
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, SystemPageObject.staff_Email);
		signup.enterInput(1, SystemPageObject.password);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "QaTestStaff 01");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
		driver.navigate().to(ConfigProperties.site_url);    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Staff");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.staff_Email, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QaTestStaff 01", 2, "Delete", 6, 2);
		signup.click("Yes");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Staff", "QaTestStaff 01", 1, false), "Fail: Searched item present in Staff grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	

	@Test(priority=3)
	public void createEditDeleteRoles() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        system = new SystemPage(driver);
        SoftAssert softAssertion= new SoftAssert();
        

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
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePageObject.clientName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		rolePage.clickOnView(InventoryPageObject.printAssetId, RolePageObject.clientName, 1,2,"View",  "Client Role", 2);
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePageObject.roleName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(system.isItemAddedInGrid(InventoryPageObject.searchTableId, "Role", RolePageObject.roleName, 3), "Fail: Created role missing from role grid.");
		rolePage.clickRoleDeletIcon(InventoryPageObject.searchTableId,1,2);
		signup.click("Yes");
		dashboard.clickButton("Client Roles");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePageObject.roleName, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isSearchedItemPresentInRoleGrid(RolePageObject.searchRoleGridId, "Role", RolePageObject.roleName, 3), "Fail: Created role still present in role grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}

	
	
	@Test(priority=4)
	public void createEditDeleteAsset(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        SoftAssert softAssertion= new SoftAssert();
		
        driver.navigate().to(ConfigProperties.signin_url);
 
		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		userPage.selectOption(2, ConfigProperties.category);
		signup.enterInput(1, InventoryPageObject.assetName);
		userPage.selectOption(3, ConfigProperties.manufacturer);
		userPage.selectOption(4, ConfigProperties.assetModel);
		userPage.selectOption(5, ConfigProperties.supplier);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, BasePage.autogenerateNumber(5), "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 2);
		assetPage.enterSearchString(ConfigProperties.statusLabels, 5, "Status");
		signup.clickWithScroll("Create");
		
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		String serialNumber = assetPage.returnSerialNo(InventoryPageObject.searchTableId, InventoryPageObject.assetName);
		assetPage.editAssets(InventoryPageObject.searchTableId, InventoryPageObject.assetName,1);
		assetPage.enterSerialNo();
		assetPage.clickOnSaveButton();
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertFalse(assetPage.isAssetUpdate(InventoryPageObject.searchTableId, InventoryPageObject.assetName, serialNumber), "Assert Fail: Asset doesn't update");
		
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Delete", 3);
		                              
		signup.click("Yes");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
		
		
		log("["+InventoryPageObject.assetName+"] Asset delete from Grid", ILogLevel.TEST);
		dashboard.logOut(ConfigProperties.logout_url);
			
	}
	

	@Test(priority=5)
	public void createEditDeleteIssue(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        issuePage = new IssuePage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Tasks");
		dashboard.clickButton("All Tasks");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Tasks Active Tasks");
		signup.click("Action");
		signup.click("New Task");
		issuePage.enterExpectedValueInTextField(IssuePageObject.textFieldIssueName_xapth, 1, IssuePageObject.issueNameGenerate, "Issue Name");
		issuePage.clickOnArrowIconOfFields("Client", 4);
		issuePage.enterSearchString("QATEST", 1, "Client");
		issuePage.clickOnArrowIconOfFields("Type", 3);
		issuePage.enterSearchString(ConfigProperties.issueCat, 1, "Type");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonCreate_xpath, 1, "Create");
		issuePage.enterExpectedValueInTextField(IssuePageObject.textFieldSearch_xpath, 1, IssuePageObject.issueNameGenerate, "Issue Search Field");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(issuePage.isItemAddedInGrid(IssuePageObject.searchTableId, "Tasks", IssuePageObject.issueNameGenerate, 2), "Fail: Issue doesn't create");
		
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, IssuePageObject.issueNameGenerate, 2, "Edit", 7, 1);
		issuePage.enterExpectedValueInTextField(IssuePageObject.textFieldIssueName_xapth, 1, "QaTest Issue 01", "Name");	
		signup.clickWithScroll("Save");
		
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, IssuePageObject.issueNameGenerate, "Search string");
		issuePage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(issuePage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Issue Name", IssuePageObject.issueNameGenerate, 2), "Fail: Searched Operation.");
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaTest Issue 01", "Search string");
		issuePage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(issuePage.isSearchedItemPresentInGrid(IssuePageObject.searchTableId, "Issue Name", "QaTest Issue 01", 2), "Fail: Searched Operation.");
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, "QaTest Issue 01", 2, "Edit", 7, 1);
		assertFalse(issuePage.isDataUpdateInExpectedField(IssuePageObject.textFieldIssueName_xapth, "Issue Name", IssuePageObject.issueNameGenerate), "Fail: Edit Operation.");
		signup.clickWithScroll("Save");
		
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaTest Issue 01", "Search string");
		issuePage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, "QaTest Issue 01", 2, "Delete", 7, 2);
		signup.click("Yes");
		issuePage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaTest Issue 01", "Search string");
		issuePage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(issuePage.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Issue", "QaTest Issue 01", 2, false), "Fail: Searched item present in Issue grid.");
		dashboard.logOut(ConfigProperties.logout_url);
			
	}

	@Test(priority=6)
	public void viewCreateEditDeleteProject() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("All Projects");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Projects Manage projects");
		signup.click("Actions");
		signup.click("New project");
		assertTrue(dashboard.isExpectedPopupOpen("Add Project"), "Fail: [Add Project] pop-up doesn't open.");
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldIssueName_xapth, 1, ProjectPageObject.projectName, "Project Name");
		projectPage.clickOnArrowIconOfFields("Client", 3);
		projectPage.enterSearchString("QATEST", 1, "Client");
		projectPage.clickOnExpectedButton(IssuePageObject.buttonCreate_xpath, 1, "Create");
		
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1,  ProjectPageObject.projectName, "Project Search Field");
		projectPage.clickOnExpectedButton(IssuePageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(projectPage.isItemAddedInGrid(ProjectPageObject.searchTableId, "Project", ProjectPageObject.projectName, 3), "Fail: Project doesn't create");
		
		projectPage.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, ProjectPageObject.projectName, 2, "Edit", 6, "Edit", 3);
		projectPage.enterExpectedValueInTextField(IssuePageObject.textFieldIssueName_xapth, 1, "QaTest Project 01", "Name");	
		signup.clickWithScroll("Save");
		
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1,  ProjectPageObject.projectName, "Project Search Field");
		projectPage.clickOnExpectedButton(IssuePageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(projectPage.isSearchedItemPresentInGrid(ProjectPageObject.searchTableId, "Project Name", ProjectPageObject.projectName, 3), "Fail: Searched Operation.");
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1, "QaTest Project 01", "Search string");
		projectPage.clickOnExpectedButton(ProjectPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(projectPage.isSearchedItemPresentInGrid(ProjectPageObject.searchTableId, "Project Name", "QaTest Project 01", 3), "Fail: Searched Operation.");
		projectPage.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, "QaTest Project 01", 2, "Edit", 6, "Edit", 3);
		assertFalse(projectPage.isDataUpdateInExpectedField(ProjectPageObject.textFieldIssueName_xapth, "Project Name", ProjectPageObject.projectName), "Fail: Edit Operation.");
		signup.clickWithScroll("Save");
		
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1, "QaTest Project 01", "Search string");
		projectPage.clickOnExpectedButton(ProjectPageObject.buttonSearch_xpath, 1, "Go");
		projectPage.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, "QaTest Project 01", 1, "View", 6, "Review", 3);
		assertTrue(projectPage.isExpectedProjectOpen("QaTest Project 01"), "Fail: User unable to preview Project.");
		
		dashboard.clickMenuTab("All Projects");
		
		
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1, "QaTest Project 01", "Search string");
		projectPage.clickOnExpectedButton(ProjectPageObject.buttonSearch_xpath, 1, "Go");
		projectPage.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, "QaTest Project 01", 3, "Delete", 6, "Remove", 3);
		signup.click("Yes");
		projectPage.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1, "QaTest Project 01", "Search string");
		projectPage.clickOnExpectedButton(ProjectPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(projectPage.isItemDeleteFromGrid(ProjectPageObject.searchTableId, "Project", "QaTest Project 01", 3, false), "Fail: Searched item present in Project grid.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	
	@Test(priority=7)
	public void viewCreateEditDeleteMonitoring() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        monitoring = new MonitoringPage(driver);
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Monitoring");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerTextReports_css)), "Monitoring");
		
		monitoring.clickOnExpectedButton(MonitoringPageObject.buttonHost_xapth, 1, "New Host");
		monitoring.clickOnArrowIconOfFields("Client", 1);
		monitoring.enterSearchString("QATEST", 1, "Client");
		monitoring.enterExpectedValueInTextField(MonitoringPageObject.textFieldIssueName_xapth, 1, MonitoringPageObject.hostName, "Host Name");
		monitoring.enterExpectedValueInTextField(MonitoringPageObject.textFieldAddress_xapth, 1, MonitoringPageObject.hostAddress, "Host Address");
		monitoring.clickOnExpectedButton(IssuePageObject.buttonCreate_xpath, 1, "Create");
		
		assertTrue(monitoring.isItemAddedInGrid(MonitoringPageObject.monitoringTableId, "Monitoring", MonitoringPageObject.hostName, 1), "Fail: Host doesn't create");
		
		monitoring.clickOnViewEditDelIcons(MonitoringPageObject.monitoringTableId, MonitoringPageObject.hostName, 2, "Edit", 3, "Edit", 1);
		monitoring.enterExpectedValueInTextField(MonitoringPageObject.textFieldIssueName_xapth, 1, "QaTest Monitoring 01", "Name");	
		signup.clickWithScroll("Save");
		Thread.sleep(1000);
		monitoring.clickOnViewEditDelIcons(MonitoringPageObject.monitoringTableId, MonitoringPageObject.hostName, 2, "Edit", 3, "Edit", 1);
		assertFalse(monitoring.isDataUpdateInExpectedField(MonitoringPageObject.textFieldIssueName_xapth, "Host Name", MonitoringPageObject.hostName), "Fail: Edit Operation.");
		signup.clickWithScroll("Save");
		Thread.sleep(1000);
		monitoring.clickOnViewEditDelIcons(MonitoringPageObject.monitoringTableId, "QaTest Monitoring 01", 1, "View", 3, "Review", 1);
		assertTrue(monitoring.isExpectedProjectOpen("QaTest Monitoring 01"), "Fail: User unable to preview Project.");
		dashboard.clickMenuTab("Monitoring");
		
		monitoring.clickOnViewEditDelIcons(MonitoringPageObject.monitoringTableId, "QaTest Monitoring 01", 3, "Delete", 3, "Remove", 1);
		signup.click("Yes");
		assertFalse(monitoring.isItemDeleteFromGrid(MonitoringPageObject.monitoringTableId, "Project", "QaTest Project 01", 3), "Fail: Searched item present in Project grid.");
		dashboard.logOut(ConfigProperties.logout_url);
		
	}
	
	@Test(priority=8)
	public void generateAndViewReports() {
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        reportsPage = new ReportsPage(driver);
        
        
        driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Reports");
		assertEquals(basepage.getText(By.cssSelector(ReportsPageObject.headerText_css)), "Reports Generare and view reports");
		
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		assertTrue(reportsPage.expectedSectionPresent("Timesheet"), "Fail: [Timesheet] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Timesheet With Summary"), "Fail: [Timesheet With Summary] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Client Summary"), "Fail: [Client Summary] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("Other Reports"), "Fail: [Other Reports] section doesn't present at 'Report You Can Run' tab.");
		assertTrue(reportsPage.expectedSectionPresent("User Summary"), "Fail: [User Summary] section doesn't present at 'Report You Can Run' tab.");
		
		//Time sheet Block
		reportsPage.clickOnArrowIconOfFields("Client", 1);
		reportsPage.enterSearchString("QATEST", 1, "Client");
		reportsPage.enterStartAndEndDate(ReportsPageObject.startDate_xapth, 0, "Start date", basepage.currentOnlyDate());
		reportsPage.enterStartAndEndDate(ReportsPageObject.endDate_xapth, 0, "End Date", basepage.addDateToCurrentDate(5));
		reportsPage.clickOnGenerateButton(1, "Generate");
		basepage.switchToNewTab();
		assertTrue(reportsPage.isTimeSheetReportCreated("TIMESHEET", "QATEST", basepage.currentOnlyDate()+" - "+basepage.addDateToCurrentDate(5)), "Fail: Data not matched.");
		driver.close();
		basepage.switchToParentTab();
		
		//Time sheet With Summary Block
		reportsPage.clickOnArrowIconOfFields("Client", 2);
		reportsPage.enterSearchString("QATEST", 1, "Client");
		reportsPage.enterStartAndEndDate(ReportsPageObject.startDate_xapth, 1, "Start date", basepage.currentOnlyDate());
		reportsPage.enterStartAndEndDate(ReportsPageObject.endDate_xapth, 1, "End Date", basepage.addDateToCurrentDate(3));
		reportsPage.clickOnGenerateButton(2, "Generate");
		basepage.switchToNewTab();
		assertTrue(reportsPage.isTimeSheetReportCreated("TIMESHEET WITH SUMMARRY", "QATEST", basepage.currentOnlyDate()+" - "+basepage.addDateToCurrentDate(3)), "Fail: Data not matched.");
		driver.close();
		basepage.switchToParentTab();
		
		//Client Summary Block
		reportsPage.clickOnArrowIconOfFields("Client", 3);
		reportsPage.enterSearchString("QATEST", 1, "Client");
		reportsPage.clickOnGenerateButton(3, "Generate");
		basepage.switchToNewTab();
		assertTrue(reportsPage.isTimeSheetReportCreated("ASSETS & LICENSES SUMMARY", "QATEST", " "), "Fail: Data not matched.");
		driver.close();
		basepage.switchToParentTab();
		
		//User Summary Block
		reportsPage.clickOnArrowIconOfFields("User", 4);
		reportsPage.enterSearchString("QATEST", 1, "Client");
		reportsPage.clickOnGenerateButton(4, "Generate");
		basepage.switchToNewTab();
		assertTrue(reportsPage.isTimeSheetReportCreated("ASSETS SUMMARY", " " , " "), "Fail: Data not matched.");
		driver.close();
		basepage.switchToParentTab();
		
		//Verify Asset(Detailed List), Licenses (Detailed List) and Warranty Expiration Report (Assets) button functionality
		
		String assetButton = reportsPage.expectedButtonName(ReportsPageObject.buttonAsset_linkText);
		String contractButton = reportsPage.expectedButtonName(ReportsPageObject.buttonLicense_linkText);
		String warentyExpButton = reportsPage.expectedButtonName(ReportsPageObject.buttonWarentyExp_linkText);
		
		reportsPage.clickOnExpectedButton(ReportsPageObject.buttonAsset_linkText, 3, "Assets");
		assertTrue(reportsPage.isExpectedPageOpen(DashboardPageObject.headerText_css, assetButton), "Fail: ["+assetButton+"] doesn't open.");
		basepage.navigateBack();
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		
		reportsPage.clickOnExpectedButton(ReportsPageObject.buttonLicense_linkText, 3, contractButton);
		assertTrue(reportsPage.isExpectedPageOpen(DashboardPageObject.headerText_css, "Contract"), "Fail: ["+contractButton+"] doesn't open.");
		basepage.navigateBack();
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");
		
		reportsPage.clickOnExpectedButton(ReportsPageObject.buttonWarentyExp_linkText, 3, warentyExpButton);
		assertTrue(reportsPage.isExpectedPageOpen(DashboardPageObject.headerText_css, "Warrenty Expires"), "Fail: ["+warentyExpButton+"] doesn't open.");
		basepage.navigateBack();
		reportsPage.clickOnExpectedTab(ReportsPageObject.reportCanRunTab_xpath, "Report You Can Run");	
			
	}
	
	
	
	
	
	// Reset password: AZ-100 need to add Delete code to delete created a/c
		//PasswordGenerator: AZ-186
		//Location and nested location create and delete: AZ-171
		//Plan create, edit, delete: AZ-214
		//Create new account: AZ-125
		// User password reset - 126
		// Create New Customer- AZ-12
		//Ticket create/delete - AZ-59, AZ-94,84, 91, 134 
		//When Staff adds a Client, he should be linked to that client: AZ-254
		//Forgot and Reset Password: AZ-35, 178
		//Client Can Not Be Added With Existing Email Test: AZ_221
		//Duplicate Client Or User Name Check Should Be Case Insensitive Test: AZ_76
		//Reset password using ref link: AZ-216
		//AZ-54
		//AZ-190: AZ_190_ResetPasswordFromUserEditScreenTest
		//AZ-232: AZ_232_LocationPageIsTooSlowTest
		//AZ-265: AZ_265_256_ExportFileRecordTest
		//AZ-170: AZ_170_RefreshGridAfterDeleteReordsTest
		//AZ-272: AZ_272_264_JSOverhaulOnClientPageTest
		//AZ-267: AZ_267_AssetWarrantyExpirationReportRecordTest
		//AZ-154: AZ_154_AbilityToRe_RunAReportTest
		//AZ-165: AZ_165_StatusChangeWhenUserReplyOnTicketTest
		//AZ_266_MultipleTicketsAddedInGridTest extends TestCore
		//AZ-84
		//AZ-301
		//AZ-314
		
		
		
		
		
		
		
		
		
		
		
		//165*, 266*: Create Ticket with client
}
