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
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SubscriptionPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_295_297_298_302_306_320_310_312_RenameMenusAndDesignChangeTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	SystemPage  system;
	HelpDeskPage helpDeskPage;
	TicketPage ticketPage;
	AssetsPage assetPage;
	SubscriptionPage subscriptionPage;
	
	@Test(priority=0)
	public void az_295_RenameIssuesToTasks(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    issuePage = new IssuePage(driver);
	    system = new SystemPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Project");
		assertFalse(dashboard.isExpectedSubMenuPresentInLeftMenuPlanel("Projects", "Issues"), "Fail: 'Issue' sub-menu still present under menu panel.'");
		assertTrue(dashboard.isExpectedSubMenuPresentInLeftMenuPlanel("Projects", "Tasks"), "Fail: 'Tasks' sub-menu doesn't present under menu panel.'");
		dashboard.clickMenuTab("Tasks");
		assertFalse(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks", "Active Issue"), "Fail: 'Active Issue' inner menu still present under menu panel.'");
		assertFalse(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks", "All Issue"), "Fail: 'All Issue' inner menu still present under menu panel.'");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks", "Active Tasks"), "Fail: 'Active Issue' inner menu doesn't present under menu panel.'");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks", "All Tasks"), "Fail: 'All Tasks' inner menu doesn't present under menu panel.'");
		dashboard.clickButton("All Tasks");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Tasks Active Tasks");
		signup.click("Action");
		assertFalse(dashboard.isExpectedButtonAvailableInPage("New Issue", "Projects>Tasks>All Tasks>Action"), "Fail: 'New Issue' button present.");
		assertTrue(dashboard.isExpectedButtonAvailableInPage("New Task", "Projects>Tasks>All Tasks>Action"), "Fail: 'New Tasks' button missing.");
		signup.click("New Task");
		assertFalse(dashboard.isExpectedPopupOpen("Add Issue"), "Fail: 'Add Issue' pop-up opens");
		assertTrue(dashboard.isExpectedPopupOpen("Add Task"), "Fail: 'Add Task' pop-up doesn't open");
		issuePage.clickOnExpectedButton(IssuePageObject.buttonCancel_xpath, 1, "Cancel");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		assertFalse(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks>Attributes", "Issue Categories"), "Fail: 'Issue Categories' inner menu still present under menu panel.'");
		assertTrue(dashboard.isExpectedInnerMenuPresentInLeftSideMenuPlanel("Projects>Tasks>Attributes", "Task Categories"), "Fail: 'Task Categories' inner menu doesn't present under menu panel.'");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QATEST", 2, "Eye Ball", 6, 1);
		assertTrue(dashboard.isExpectedButtonPresent("Client Details", IssuePageObject.buttonNewTasksCreate_xpath, "New Task"), "'New Task' button missing.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	
	@Test(priority=1)
	public void az_297_MoveSectionsToTabsOnTheTicketSettingsPage(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		driver.navigate().to("https://app.aszet.com/?route=tickets/settings");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("General"), "Fail: 'General' tab doesn't present.");
		assertTrue(dashboard.isExpectedPopupOpen("Auto Import Tickets & Auto Close Tickets"), "Fail: 'Auto Import Tickets & Auto Close Tickets' screen doesn't open.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Support Departments"), "Fail: 'Support Departments' tab doesn't present."); 
		signup.clickButton("Support Departments");
		assertTrue(dashboard.isExpectedButtonPresent("Service Request>Basic Settings>Support Departments Tab", HelpDeskTicketsPageObject.buttonNewDepartment_xpath, "NEW DEPARTMENT"), "fail: 'NEW DEPARTMENT' missing from page.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	
	@Test(priority=2)
	public void az_298_BreakEditRulesIntoATabbedView(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonTicketSetting_xpath, 1, "Settings");
		dashboard.clickOnInnerSubmenu("tickets", "Attributes", "Rules");
		signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewRule_xpath, 1, "New Rule");
		assertTrue(dashboard.isExpectedPopupOpen("Add Ticket Rule"),"Fail: 'Add Ticket Rule' pop-up doesn't open.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Basic"), "Fail: 'Basic' tab doesn't present.");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Rule Name", 1), "Fail: 'Rule Name' filed missing from Basic tab.");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Client", 1), "Fail: 'Rule Name' filed missing from Basic tab.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Conditions"), "Fail: 'Conditions' tab doesn't present.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Actions"), "Fail: 'Actions' tab doesn't present.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Notifications"), "Fail: 'Notifications' tab doesn't present.");
		
		signup.clickButton("Conditions");
		assertTrue(dashboard.isExpectedPopupOpen("Conditions"), "Fail: 'Conditions' screen doesn't open.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Status", 1), "Fail: 'Status' filed missing from Conditions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Priority", 1), "Fail: 'Priority' filed missing from Conditions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Time Elapsed (minutes)", 1), "Fail: 'Time Elapsed (minutes)' filed missing from Conditions tab.");
		
		signup.clickButton("Actions");
		assertTrue(dashboard.isExpectedPopupOpen("Actions"), "Fail: 'Actions' screen doesn't open.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Status", 2), "Fail: 'Status' filed missing from Actions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Priority", 2), "Fail: 'Priority' filed missing from Actions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Assign To", 1), "Fail: 'Assign To' filed missing from Actions tab.");
		
		signup.clickButton("Notifications");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Reply", 1), "Fail: 'Reply' filed missing from Notifications tab.");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel");
		
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticketRules, "Search string");		
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		ticketPage.clickOnViewEditDelIcons(HelpDeskTicketsPageObject.searchTableId, ConfigProperties.ticketRules, 1, "Edit", 4, "Update", 3);
		assertTrue(dashboard.isExpectedPopupOpen("Edit Ticket Rule"),"Fail: 'Edit Ticket Rule' pop-up doesn't open.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Basic"), "Fail: 'Basic' tab doesn't present.");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Rule Name", 1), "Fail: 'Rule Name' filed missing from Basic tab.");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Client", 1), "Fail: 'Rule Name' filed missing from Basic tab.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Conditions"), "Fail: 'Conditions' tab doesn't present.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Actions"), "Fail: 'Actions' tab doesn't present.");
		assertTrue(ticketPage.isExpectedTabPresentWithExpectedPosition("Notifications"), "Fail: 'Notifications' tab doesn't present.");
		signup.clickButton("Conditions");
		assertTrue(dashboard.isExpectedPopupOpen("Conditions"), "Fail: 'Conditions' screen doesn't open.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Status", 1), "Fail: 'Status' filed missing from Conditions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Priority", 1), "Fail: 'Priority' filed missing from Conditions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Time Elapsed (minutes)", 1), "Fail: 'Time Elapsed (minutes)' filed missing from Conditions tab.");
		
		signup.clickButton("Actions");
		assertTrue(dashboard.isExpectedPopupOpen("Actions"), "Fail: 'Actions' screen doesn't open.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Status", 2), "Fail: 'Status' filed missing from Actions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Priority", 2), "Fail: 'Priority' filed missing from Actions tab.");
		//assertTrue(ticketPage.isExpectedFieldLabelPresent("Assign To", 1), "Fail: 'Assign To' filed missing from Actions tab.");
		
		signup.clickButton("Notifications");
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Reply", 1), "Fail: 'Reply' filed missing from Notifications tab.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=3)
	public void az_302_RenameLicenseToContract(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		assertFalse(dashboard.isExpectedSubMenuPresentInLeftMenuPlanel("Inventory", "License", 2), "Fail: 'License' sub-menu still present under menu panel.'");
		assertTrue(dashboard.isExpectedSubMenuPresentInLeftMenuPlanel("Inventory", "Contract", 2), "Fail: 'Contract' sub-menu doesn't present under menu panel.'");
		dashboard.clickOnExpectedButton(InventoryPageObject.subMenuContract_xpath, 1, "Contract");
		assertEquals(basepage.getText(By.cssSelector(".content-header>h1.pull-left")), "Contract Manage Contract");
		signup.click("Action");
		assertFalse(dashboard.isExpectedButtonLinkAvailableInPage("New License", "Inventory>Contract>Action"), "Fail: 'New License' button present.");
		assertTrue(dashboard.isExpectedButtonLinkAvailableInPage("New Contract", "Inventory>Contract>Action"), "Fail: 'New Contract' button missing.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewContract_xpath, 1, "New Contract");
		assertFalse(dashboard.isExpectedHeadertextPresent("Create New License"), "Fail: 'Create New License' form opens");
		assertTrue(dashboard.isExpectedHeadertextPresent("Create New Contract"), "Fail: 'Create New Contract' form doesn't open");
		assertFalse(assetPage.isExpectedFiledPresent("License Tag *", "License"), "Fail: 'License Tag' field still available.");
		assertTrue(assetPage.isExpectedFiledPresent("Contract Tag *", "Contract"), "Fail: 'Contract Tag' field doesn't available.");
		assertFalse(assetPage.isExpectedFiledPresent("License Name *", "License"), "Fail: 'License Name' field still available.");
		assertTrue(assetPage.isExpectedFiledPresent("Contract Name *", "Contract"), "Fail: 'Contract Name *' field doesn't available.");
		dashboard.clickOnExpectedButton(InventoryPageObject.subMenuContract_xpath, 1, "Contract");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.license, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.license, 1, "Edit", 7, "Update", 3);
		assertFalse(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"License Info"), "Fail: [License Info] tab doesn't present.");
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract Info"), "Fail: [Contract Info] tab doesn't present.");
		signup.clickButton("Assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.asset, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.asset, 1, "Edit", 8, "Update", 3);
		assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Contract"), "Fail: [Contract] tab doesn't present.");
		dashboard.logOut(ConfigProperties.logout_url);
		
	}
	
	@Test(priority=4)
	public void az_306_ReArrangeTicketSettingOptions(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver); 
	    assetPage = new AssetsPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		
		String[] ticketSubMenu = {"Awaiting Reply", "Active Tickets", "All Tickets", "Basic Settings", "Settings"};
		int length = ticketSubMenu.length;
		assertTrue(dashboard.verrifySubMenu("Service Request", 2, length, ticketSubMenu), "Fail: Expected sub-menu missing Under Ticket list");
		assertFalse(dashboard.isExpectedSubMenuPresentInExpectedMenuInLeftMenuPlanel("Service Request", 2, "Attributes"), "Fail: 'Basic Settings' sub-menu doesn't present under menu panel.'");
		dashboard.clickOnSpecificSubmenu("Basic Settings", 1);
		assertTrue(ticketPage.isExpectedPageOpen("Ticket Settings"), "Fail: Ticket Setting page doesn't open.");
		assertFalse(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Ticket Rules"), "Fail: [Ticket Rules] tab doesn't present.");
		dashboard.clickOnSpecificSubmenu("Settings", 2);
		String[] ticketInnerSubMenu = {"Ticket Fields", "Ticket Rules"};
		int innerListlength = ticketInnerSubMenu.length;
		assertTrue(dashboard.verrifyInnerSubMenu("Service Request", 2, innerListlength, "Settings", ticketInnerSubMenu), "Fail: Expected inner sub-menu missing Under Ticket list");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	

	@Test(priority=5)
	public void az_320_AddStatusesToServiceRequests(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver); 
	    assetPage = new AssetsPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
		ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticket, "Search string: ");
		ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,ConfigProperties.ticket, 2, "Delete", 10, "Ticket", 2);
		assertTrue(ticketPage.isExpectedFieldLabelPresent("Status *", 1), "Fail: 'Status' filed missing from 'Edit Ticket' pop-up.");
		ticketPage.clickOnArrowIconOfFields("Client Field", 9);
		//String[] statusOption = {"OVERDUE", "RECEIVED", "TO THIRD PARTY", "QUOTE NEEDED", "QUOTE SENT", "AUTHORIZED", "IN REPAIR", "READY TO SHIP", "SHIPPED"};
		//int size = statusOption.length;
		//for(int i=0; i<=size-1; i++){
		//	assertTrue(ticketPage.isExpectedOptionAvailableInDropdownList(statusOption[i],"'Status' field", "'Edit Ticket' Pop-up"), "Fail: Status : "+statusOption[i]+" option missing from 'Edit Ticket' pop-up.");
		//}
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=6)
	public void az_310_ChangeHelpDeskTicketToServiceRequest(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver); 
	    assetPage = new AssetsPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		assertFalse(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Help Desk Tickets"), "Fail: [Help Desk Tickets] doesn't present in left menu panel.");
		assertTrue(dashboard.isExpectedMenuPresentInLeftSideMenuPlanel(DashboardPageObject.leftSidePanel_xpath, "Service Request"), "Fail: [Service Request] present in left menu panel.");
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("Awaiting Reply");
		assertTrue(ticketPage.isExpectedPageOpen("Service Request"), "Fail: 'Service Request Awaiting reply' page missing.");
		dashboard.clickButton("Active Tickets");
		assertTrue(ticketPage.isExpectedPageOpen("Service Request"), "Fail: 'Service Request Awaiting reply' page missing.");
		dashboard.clickButton("All Tickets");
		assertTrue(ticketPage.isExpectedPageOpen("Service Request"), "Fail: 'Service Request Awaiting reply' page missing.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
	
	@Test(priority=7)
	public void az_312_ChangeLicenceManagementToContract(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskPage = new HelpDeskPage(driver); 
	    assetPage = new AssetsPage(driver);
	    subscriptionPage = new SubscriptionPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
	    
	    assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Subscription");
		dashboard.clickMenuTab("Plans");
		assertTrue(ticketPage.isExpectedPageOpen("Plans"), "Fail: 'Plans' page missing.");
		dashboard.clickButton("NEW PLAN");
		assertTrue(ticketPage.isExpectedPageOpen("Add Plan"), "Fail: 'Add Plan' page missing.");
		assertTrue(subscriptionPage.isExpectedLabelPresent("Contract Management"), "Fail: 'Contract Management' does not present in add new plan pop up.");
		assertTrue(subscriptionPage.isExpectedLabelPresent("Service Request Feature"), "Fail: 'Service Request Feature' does not present in add new plan pop up.");
		assertFalse(subscriptionPage.isExpectedLabelPresent("License Management"), "Fail: 'License Management' present on add new plan pop up.");
		assertFalse(subscriptionPage.isExpectedLabelPresent("Help Desk Feature"), "Fail: 'Help Desk Feature' present on add new plan pop up.");
		dashboard.logOut(ConfigProperties.logout_url);
	}
		
}
