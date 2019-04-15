package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.TemplatePageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TemplatePage;

public class AZ_117_SearchBrokenWhenSearchingForAnAssetTagTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	TemplatePage templatePage;
	AssetsPage assetPage;
	
	@Test
	public void az_117_SearchBrokenWhenSearchingForAnAssetTag(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        templatePage = new TemplatePage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickMenuListOption(0);
		templatePage.enterExpectedValueInTextField(TemplatePageObject.searchTextField_xpath, 1, InventoryPageObject.searchName, "Search string: '"+InventoryPageObject.searchName+"'");
		signup.click("Go");
		assertTrue(assetPage.isGridPresentAfterSearch(), "Grid doesn't present after search asset");
		
	}

}
