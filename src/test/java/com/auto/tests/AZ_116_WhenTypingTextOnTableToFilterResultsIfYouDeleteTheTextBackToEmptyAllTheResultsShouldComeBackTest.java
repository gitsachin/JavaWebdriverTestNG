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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;

public class AZ_116_WhenTypingTextOnTableToFilterResultsIfYouDeleteTheTextBackToEmptyAllTheResultsShouldComeBackTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	RolePage rolePage;


	@Test
	public void az_116_WhenTypingTextOnTableToFilterResultsIfYouDeleteTheTextBackToEmptyAllTheResultsShouldComeBackTest(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		rolePage = new RolePage(driver);

		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		rolePage.getTable(InventoryPageObject.originalGridId,2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePage.actualName, "Search string: '"+HelpDeskTicketsPageObject.ticketName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(rolePage.verifyTable(InventoryPageObject.searchTableId));
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		
		dashboard.clickButton("Active Tickets");
		rolePage.getTable(InventoryPageObject.originalGridId,2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePage.actualName, "Search string: '"+HelpDeskTicketsPageObject.ticketName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(rolePage.verifyTable(InventoryPageObject.searchTableId));
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		
		dashboard.clickButton("All Tickets");
		rolePage.getTable(InventoryPageObject.originalGridId,2);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePage.actualName, "Search string: '"+HelpDeskTicketsPageObject.ticketName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(rolePage.verifyTable(InventoryPageObject.searchTableId));
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		rolePage.getTable(InventoryPageObject.originalGridId,3);
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePage.actualName, "Search string: '"+HelpDeskTicketsPageObject.ticketName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(rolePage.verifyTable(InventoryPageObject.searchTableId));
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		rolePage.getTable(InventoryPageObject.originalGridId,2);
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, RolePage.actualName, "Search string: '"+HelpDeskTicketsPageObject.ticketName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(rolePage.verifyTable(InventoryPageObject.searchTableId));
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(rolePage.verifyTable(InventoryPageObject.originalGridId));

	}

}