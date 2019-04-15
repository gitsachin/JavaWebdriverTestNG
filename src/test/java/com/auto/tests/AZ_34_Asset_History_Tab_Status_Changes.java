package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_34_Asset_History_Tab_Status_Changes extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	SystemPage system;
	

	@Test
	public void az_34_Asset_History_Tab_Status_Changes(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		assetPage = new AssetsPage(driver);
		userPage = new UsersPage(driver);
		system = new SystemPage(driver);
		SoftAssert softAssertion= new SoftAssert();
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");

		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		signup.click("Action");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		userPage.selectOption(2, ConfigProperties.category);
		signup.enterInput(1, InventoryPageObject.assetName);
		userPage.selectOption(3, ConfigProperties.manufacturer);
		userPage.selectOption(4, ConfigProperties.assetModel);
		userPage.selectOption(5, ConfigProperties.supplier);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, BasePage.autogenerateNumber(5), "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 2);
		//assetPage.enterSearchString("QaStatusLabelsForAutomation_DND", 5, "Status");
		assetPage.enterSearchString("status label", 5, "Status");
		signup.clickWithScroll("Create");
		
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
		//assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);

		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Edit", 3);
		dashboard.clickButton("History");
		assertTrue(assetPage.verifyHistoryTabRow("Date",1),"[Date] not present in history tab");
		assertTrue(assetPage.verifyHistoryTabRow("User",2),"[User] not present in history tab");
		assertTrue(assetPage.verifyHistoryTabRow("Changed",3),"[Changed] not present in history tab");
		assertTrue(assetPage.verifyHistoryTabRow("Before",4),"[Before] not present in history tab");
		assertTrue(assetPage.verifyHistoryTabRow("After",5),"[After] not present in history tab");
		assertFalse(assetPage.verifyHistoryFieldPresent(),"[Field] present on history table.");
		
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Edit", 3);
		assetPage.clickOnArrowIconOfFields("Status", 3);
		assetPage.enterSearchString(ConfigProperties.statusLabels, 5, "Status");
		signup.click("Action");
		signup.click("Save");
		
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");

		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Edit", 3);
		dashboard.clickButton("History");
		assertTrue(assetPage.verifyHistoryFieldDataAfterChange("jason",2,"User"));
		assertTrue(assetPage.verifyHistoryFieldDataAfterChange("Status",3,"Changed"));
		assertTrue(assetPage.verifyHistoryFieldDataAfterChange("status label",4,"Before"));
		assertTrue(assetPage.verifyHistoryFieldDataAfterChange(ConfigProperties.statusLabels,5,"After"));
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Delete", 3);
		signup.click("Yes");
		log("["+InventoryPageObject.assetName+"] Asset delete from Grid", ILogLevel.TEST);

	}

}
