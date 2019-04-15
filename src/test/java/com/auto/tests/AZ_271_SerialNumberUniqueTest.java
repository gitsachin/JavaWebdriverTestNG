package com.auto.tests;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_271_SerialNumberUniqueTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	TicketPage ticketPage;
	
	@Test
	public void az_271_SerialNumberUnique(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        ticketPage = new TicketPage(driver);
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
		String asset1 = "Asset_"+basepage.getCurrentDate();
		userPage.selectOption(2, ConfigProperties.category);
		signup.enterInput(1, asset1);
		userPage.selectOption(3, ConfigProperties.manufacturer);
		userPage.selectOption(4, ConfigProperties.assetModel);
		userPage.selectOption(5, ConfigProperties.supplier);
		String serialNumber = "SL"+BasePage.autogenerateNumber(5);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, serialNumber, "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 2);
		assetPage.enterSearchString(ConfigProperties.statusLabels, 5, "Status");
		signup.clickWithScroll("Create");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
		assertTrue(assetPage.isItemAddedInTable("Asset", asset1), "Fail: ["+asset1+"] doesnt created.");
		
		signup.click("Actions");
		dashboard.clickButton("New asset");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.boxTitle_css)), "Create New Asset");
		String asset2 = "Asset_"+basepage.getCurrentDate();
		userPage.selectOption(2, ConfigProperties.category);
		signup.enterInput(1, asset2);
		userPage.selectOption(3, ConfigProperties.manufacturer);
		userPage.selectOption(4, ConfigProperties.assetModel);
		userPage.selectOption(5, ConfigProperties.supplier);
		dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, serialNumber, "Serial No");
		assetPage.clickOnArrowIconOfFields("Status", 2);
		assetPage.enterSearchString(ConfigProperties.statusLabels, 5, "Status");
		signup.clickWithScroll("Create");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
		assertTrue(assetPage.isItemAddedInTable("Asset", asset2), "Fail: ["+asset2+"] doesnt created.");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("All Tickets");
		signup.click("Action");
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, HelpDeskTicketsPageObject.ticketName, "Subject");
		ticketPage.enterSerialNo(ConfigProperties.assetSerialNo, "Serial Number", 0);
		ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.assetName_xpath, 1, serialNumber, "Serial Number");
		
		String[] assets = {asset1, asset2};
		assertTrue(ticketPage.verifyAssetBySerialNumber(serialNumber, assets), "Fail: Serial Number is Unique.");
		
		dashboard.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonCancel_xpath, 1, "Cancel");
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, asset1, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, asset1, 2, "Delete", 8, "Delete", 3);                             
		signup.click("Yes");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, asset1, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
		assertTrue(assetPage.isItemPresentInGridAfterDelete(InventoryPageObject.noRecordGrid_xpath, asset1, "No Records Found"), "Fail: ["+InventoryPageObject.assetName+"] doesn't delete from Asset grid.");
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, asset2, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, asset2, 2, "Delete", 8, "Delete", 3);                             
		signup.click("Yes");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, asset1, "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
		assertTrue(assetPage.isItemPresentInGridAfterDelete(InventoryPageObject.noRecordGrid_xpath, asset2, "No Records Found"), "Fail: ["+InventoryPageObject.assetName+"] doesn't delete from Asset grid.");
			
	}

}
