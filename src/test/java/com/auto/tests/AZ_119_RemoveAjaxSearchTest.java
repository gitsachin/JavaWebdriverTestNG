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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SubscriptionPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SubscriptionPage;

public class AZ_119_RemoveAjaxSearchTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	SubscriptionPage subscription;
	
	@Test
	public void  az_119_RemoveAjaxSearch(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        subscription = new SubscriptionPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        assertTrue(dashboard.verifyURL(),"Https not present in url");
        signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		/*dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply"); 
		assertTrue(dashboard.isExpectedButtonPresent("Awaiting Reply", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Awaiting Reply", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Awaiting Reply", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.awaitingTicket, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Awaiting Reply", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Awaiting Reply", ConfigProperties.awaitingTicket, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickButton("Ticket Fields");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Fields Manage Ticket Fields");
		assertTrue(dashboard.isExpectedButtonPresent("Ticket Fields", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Ticket Fields", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Ticket Fields", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.ticketFields, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Ticket Fields", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Ticket Fields", ConfigProperties.ticketFields, 4), "Fail: Search function doesn't perform.");
		*/
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assertTrue(dashboard.isExpectedButtonPresent("Assets", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Assets", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Assets", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.asset, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Assets", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Assets", ConfigProperties.asset, 3), "Fail: Search function doesn't perform.");
		
		dashboard.clickButton("Licenses");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		assertTrue(dashboard.isExpectedButtonPresent("Licenses", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Licenses", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Licenses", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.license, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Licenses", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Licenses", ConfigProperties.license, 3), "Fail: Search function doesn't perform.");
		
		dashboard.clickButton("Locations");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		assertTrue(dashboard.isExpectedButtonPresent("Locations", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Locations", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Locations", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.location, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Locations", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(assetPage.isItemPresentInLocationGrid(InventoryPageObject.searchTableId, ConfigProperties.location, 1), "Fail: Search operation is not perform.");
		
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Asset Categories Manage asset categories");
		assertTrue(dashboard.isExpectedButtonPresent("Asset Categories", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Asset Categories", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Asset Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.assetCat, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Asset Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Asset Categories", ConfigProperties.assetCat, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "License Catgories Manage license categories");
		assertTrue(dashboard.isExpectedButtonPresent("License Categories", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("License Categories", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("License Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.licenseCat, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("License Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "License Categories", ConfigProperties.licenseCat, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Labels");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Status Labels Manage status labels");
		assertTrue(dashboard.isExpectedButtonPresent("Status Labels", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Status Labels", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Status Labels", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.statusLabels, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Status Labels", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Status Labels", ConfigProperties.statusLabels, 2), "Fail: Search function doesn't perform.");
		

		dashboard.clickMenuTab("Projects");
		dashboard.clickMenuTab("All Projects");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Projects Manage projects");
		assertTrue(dashboard.isExpectedButtonPresent("Projects", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Projects", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Projects", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.projects, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Projects", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Projects", ConfigProperties.projects, 3), "Fail: Search function doesn't perform.");
		
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assertTrue(dashboard.isExpectedButtonPresent("Active Issues", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Active Issues", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Active Issues", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.activeIssue, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Active Issues", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Active Issues", ConfigProperties.activeIssue, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnSpecificSubmenu("Attributes", 3);
		dashboard.clickOnInnerSubmenu("Issues", "Attributes", "Issue Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issue Categories Manage issue categories");
		assertTrue(dashboard.isExpectedButtonPresent("Issue Categories", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Issue Categories", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Issue Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.issueCat, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Issue Categories", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Issue Categories", ConfigProperties.issueCat, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickMenuTab("Reports");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerTextReports_css)), "Reports Generare and view reports");
		assertTrue(dashboard.isExpectedButtonPresent("Reports", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Reports", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Reports", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		
		dashboard.clickMenuTab("Subscription");
		dashboard.clickOnSubscriptionInnerSubmenu("Subscription", "Plans");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Plans Subscription plans");
		assertTrue(dashboard.isExpectedButtonPresent("Plans", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Plans", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Plans", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.plans, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Plans", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchPlanTableId, "Plans", ConfigProperties.plans, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnSubscriptionInnerSubmenu("Subscription", "Features");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Features Subscription Features");
		assertTrue(dashboard.isExpectedButtonPresent("Features", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Features", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Features", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.features, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Features", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchFeaturesTableId, "Features", ConfigProperties.features, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickOnSubscriptionInnerSubmenu("Subscription", "Client");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Client Plan Plan Selected By Client");
		assertTrue(dashboard.isExpectedButtonPresent("Client Plan", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Client Plan", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Client Plan", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.clientPlan, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Client Plan", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchClientPlanTableId, "Client Plan", ConfigProperties.clientPlan, 2), "Fail: Search function doesn't perform.");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		assertTrue(dashboard.isExpectedButtonPresent("Clients", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Clients", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Clients", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.clientPlan, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Clients", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Clients", ConfigProperties.clientPlan, 2), "Fail: Client Search function doesn't perform.");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
		assertTrue(dashboard.isExpectedButtonPresent("Users", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Users", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Users", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assetPage.clickOnArrowIconOfFields("Select Client", 1);
		assetPage.enterSearchString("QATEST", 1, "Select Client");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.user, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Users", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Users", ConfigProperties.user, 3), "Fail: Users Search function doesn't perform.");
		
		dashboard.clickButton("Staff");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Staff Manage staff accounts");
		assertTrue(dashboard.isExpectedButtonPresent("Staff", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Staff", "Search Field", InventoryPageObject.textFieldSearch_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Staff", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.staff, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Staff", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Staff", ConfigProperties.staff, 2), "Fail: Staff Search function doesn't perform.");
		
		/*dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		dashboard.clickButton("Client Roles");
		assertTrue(dashboard.isExpectedButtonPresent("Roles", InventoryPageObject.buttonSearch_xpath,"Go"), "Fail: Go button doesn't present.");
		assertTrue(dashboard.isExpectedFieldPresent("Roles", "Search Field", SystemPageObject.roleSearchField_xpath), "Fail: Search field doesn't present.");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Roles", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.clientPlan, "Search string");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(dashboard.isLoadSpinnerAppear("Roles", DashboardPageObject.loadSpinner_xpath,"Load Spinner"), "Fail: Load Spinner doesn't present.");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.searchTableId, "Roles", ConfigProperties.clientPlan, 2), "Fail: Roles Search function doesn't perform.");
		*/
		dashboard.clickButton("Predefined Replies");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Predefined Replies Manage predefined replies");
		assertTrue(dashboard.isExpectedFieldPresent("Predefined Replies", "Search Field", SystemPageObject.roleSearchField_xpath), "Fail: Search field doesn't present.");
		dashboard.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, ConfigProperties.preDefReply, "Search string");
		assertTrue(dashboard.isSearchedItemPresentInGrid(InventoryPageObject.printAssetId, "Predefined Replies", ConfigProperties.preDefReply, 2), "Fail: Predefined Replies Search function doesn't perform.");
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
