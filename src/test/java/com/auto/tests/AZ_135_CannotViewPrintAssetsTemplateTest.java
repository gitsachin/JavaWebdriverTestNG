package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
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
import com.auto.pages.UsersPage;

public class AZ_135_CannotViewPrintAssetsTemplateTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	TemplatePage templatePage;
	AssetsPage assetPage;
	
	@Test
	public void az_135_CannotViewPrintAssetsTemplate(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        userPage = new UsersPage(driver);
        templatePage = new TemplatePage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("Templates");
		dashboard.clickMenuListOption(0);
		dashboard.clickButton("NEW TEMPLATE");
		signup.enterInput(0, InventoryPageObject.assetName);
		userPage.selectOption(1, "Newgistics");
		signup.clickWithScroll("New");
		basepage.dropdownSelect(By.id(TemplatePageObject.availbleField_id), "Model");
		basepage.dropdownSelect(By.id(TemplatePageObject.displaysAS_id), "Alphanumeric");
		signup.enterInput(13, "1");
		signup.enterInput(14, "1");
		basepage.dropDownGetSelectedOptionText(By.id(TemplatePageObject.displayValue_id));
		signup.click("Add");
		signup.clickWithScroll("Generate Preview");
		assertEquals(basepage.getText(By.cssSelector(".childDiv>p")), "MODEL");
		dashboard.clickOnExpectedButton(TemplatePageObject.barCodeClose_css, 2, "Close");
		signup.clickWithScroll("SAVE");
		
		//Search Template Name
		templatePage.enterExpectedValueInTextField(TemplatePageObject.searchTextField_xpath, 1, InventoryPageObject.assetName, "Search string: '"+InventoryPageObject.assetName+"'");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(templatePage.isCreateTemplatePresent(TemplatePageObject.seaqrchTable_id, "Template", InventoryPageObject.assetName), "Fail: Create template doesn't presentin [Asset Template] grid.");
		templatePage.clickOnViewEditDelIcons(InventoryPageObject.assetName, 2, "Delete", 6, "Templates: Print Asset", 1);
		signup.click("Yes");
		log("Asset Template delete from Grid", ILogLevel.TEST);
			
	}

}
