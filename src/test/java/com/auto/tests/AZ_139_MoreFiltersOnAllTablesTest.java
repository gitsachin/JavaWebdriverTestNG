package com.auto.tests;

import static org.testng.Assert.assertEquals;
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
import com.auto.pages.SignUpPage;

public class AZ_139_MoreFiltersOnAllTablesTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void  az_139_MoreFiltersOnAllTables(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickMenuListOption(0);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		assertTrue(assetPage.isExpectedButtonPresent(InventoryPageObject.buttonMoreFilter_xpath, "More Filters", "Asset Page"), "Fail: [More Filters] buton doesn't present in Asset screen.");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonMoreFilter_xpath, 1, "More Filters");
		assertTrue(assetPage.isExpectedFiltersAvailable(InventoryPageObject.availableFilter_xpath), "Fail: [Available Filter] doesnot present inside More Filter.");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldFilterSearch_xpath, 1, "Location", "Search string");
		assertTrue(assetPage.isUserAbleToSearchFilter(InventoryPageObject.availableFilter_xpath, "Location", InventoryPageObject.textFieldFilterSearch_xpath), "Fail: Search functionality not working.");
		int availableAssetFilter = assetPage.clickOnAvailableItemChekBox(InventoryPageObject.availableFilter_xpath);
		assertTrue(assetPage.isExpectedNoOfFilterApplied(InventoryPageObject.appliedFileterSection_xpath, availableAssetFilter), "Fail: Filter doesn't applyed Asset Page.");
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assertTrue(assetPage.isExpectedButtonPresent(HelpDeskTicketsPageObject.buttonMoreFilter_xpath, "More Filters", "Help Desk Tickets Page"), "Fail: [More Filters] buton doesn't present in Help Desk Tickets screen.");
		assetPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonMoreFilter_xpath, 1, "More Filters");
		assertTrue(assetPage.isExpectedFiltersAvailable(HelpDeskTicketsPageObject.availableFilter_xpath), "Fail: [Available Filter] doesnot present inside More Filter.");
		assetPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldFilterSearch_xpath, 1, "Return Location", "Search string");
		assertTrue(assetPage.isUserAbleToSearchFilter(HelpDeskTicketsPageObject.availableFilter_xpath, "Return Location", InventoryPageObject.textFieldFilterSearch_xpath), "Fail: Search functionality not working.");
		int availableHelpDeskFilter = assetPage.clickOnAvailableItemChekBox(HelpDeskTicketsPageObject.availableFilter_xpath);
		assertTrue(assetPage.isExpectedNoOfFilterApplied(HelpDeskTicketsPageObject.appliedFileterSection_xpath, availableHelpDeskFilter), "Fail: Filter doesn't applyed at Help Desk Tickets Page.");
	}

}
