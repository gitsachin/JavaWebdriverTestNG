package com.auto.tests;

import static org.testng.Assert.assertEquals;
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
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_10_RelatedAssetsTest extends TestCore{
	
		SignUpPage signup;
		ConfigProperties configprop ;
		BasePage basepage;
		DashboardPage dashboard ;
		AssetsPage assetPage;
		TicketPage ticketPage;
		UsersPage userPage;
		
		@Test
		public void az_10_RelatedAssets(){
			signup = new SignUpPage(driver);
			configprop = new ConfigProperties();
			basepage = new BasePage(driver);
	        dashboard = new DashboardPage(driver);
	        assetPage = new AssetsPage(driver);
	        userPage = new UsersPage(driver);
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
			userPage.selectOption(2, "Category1_DND");
			signup.enterInput(1, InventoryPageObject.assetName);
			userPage.selectOption(3, "QaManufacturer_DND");
			userPage.selectOption(4, "QaAssetModelForAutomation_DND");
			userPage.selectOption(5, "QaSupplierForAutomation_DND");
			dashboard.enterExpectedValueInTextField(InventoryPageObject.textFieldSerialNo_xpath, 1, BasePage.autogenerateNumber(5), "Serial No");
			assetPage.clickOnArrowIconOfFields("Status", 2);
			assetPage.enterSearchString("QaStatusLabelsForAutomation_DND", 5, "Status");
			signup.clickWithScroll("Create");
			
			softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: ["+InventoryPageObject.sucessMessage+"] success aleart untracked.");
			
			assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: '"+InventoryPageObject.assetName+"'");
			assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
			assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 1, "Edit", 8, "Edit", 3);
			assertTrue(assetPage.isExpectedTabPresentWithExpectedPosition(InventoryPageObject.editPageTab_xpath,"Related Assets"), "Fail: [Related Assets] tab doesn't prtesent.");
			assetPage.clickOnExpectedTab(InventoryPageObject.editPageTab_xpath, 2, "Related Assets");
			assetPage.clickOnExpectedButton(InventoryPageObject.buttonNewRelatedAsset, 1, "NEW RELATED ASSET");
			assetPage.clickOnArrowIconOfFields("Assets", 9);
			assetPage.enterSearchString("QaAssetForAutomation", 5, "Assets");
			assetPage.clickOnExpectedButton(InventoryPageObject.buttonRelate_xpath, 1, "Relate");
			assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
			assetPage.clickOnExpectedTab(InventoryPageObject.editPageTab_xpath, 2, "Related Assets");
			assertTrue(assetPage.isItemAddedInTable("Related Assets", "QaAssetForAutomation"), "Fail: Related asset doesn't present in table.");
			dashboard.clickMenuTab("Inventory");
			dashboard.clickMenuTab("Inventory");
			dashboard.clickMenuListOption(0);
			assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, InventoryPageObject.assetName, "Search string: '"+InventoryPageObject.assetName+"'");
			assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
			
			assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, InventoryPageObject.assetName, 2, "Delete", 8, "Delete", 3);
			signup.click("Yes");
			softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: ["+InventoryPageObject.deleteSucessMessage+"] success aleart untracked.");
			log("Asset delete from Grid", ILogLevel.TEST);
		}

}
