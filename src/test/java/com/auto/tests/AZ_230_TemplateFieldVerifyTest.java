package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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

public class AZ_230_TemplateFieldVerifyTest extends TestCore{
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	TemplatePage templatePage;
	AssetsPage assetPage;
	
	@Test
	public void az_230_TemplateFieldVerify(){
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
		signup.enterInput(0, TemplatePageObject.templateName);
		userPage.selectOption(1, "QATEST");
		signup.clickWithScroll("New");
		basepage.dropdownSelect(By.id(TemplatePageObject.availbleField_id), "Model");
		basepage.dropdownSelect(By.id(TemplatePageObject.displaysAS_id), "Alphanumeric");
		signup.enterInput(13, "1");
		signup.enterInput(14, "1");
		basepage.dropDownGetSelectedOptionText(By.id(TemplatePageObject.displayValue_id));
		signup.click("Add");
		assertTrue(templatePage.isCreateTemplatePresent(TemplatePageObject.fieldTable_id, "Template Fields", "Model"), "Fail: [Model] Template filed doesn't present.");
		templatePage.clickOnViewEditDelIcons("Model", 2, "Delete", 6, "Template Field", 1);
		assertFalse(templatePage.isCreateTemplatePresent(TemplatePageObject.fieldTable_id, "Template Fields", "Model"), "Fail: [Model] Template filed doesn't present.");
		signup.clickWithScroll("SAVE");
		
		templatePage.enterExpectedValueInTextField(TemplatePageObject.searchTextField_xpath, 1, TemplatePageObject.templateName, "Search string: '"+TemplatePageObject.templateName+"'");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		templatePage.clickOnViewEditDelIcons(TemplatePageObject.templateName, 1, "Edit", 6, "Templates: Print Asset", 1);
		assertFalse(templatePage.isCreateTemplatePresent(TemplatePageObject.fieldTable_id, "Template Fields", "Model"), "Fail: [Model] Template filed doesn't present.");
		
		signup.clickWithScroll("New");
		basepage.dropdownSelect(By.id(TemplatePageObject.availbleField_id), "Serial Number");
		basepage.dropdownSelect(By.id(TemplatePageObject.displaysAS_id), "Alphanumeric");
		signup.enterInput(13, "1");
		signup.enterInput(14, "1");
		basepage.dropDownGetSelectedOptionText(By.id(TemplatePageObject.displayValue_id));
		signup.click("Add");
		assertTrue(templatePage.isCreateTemplatePresent(TemplatePageObject.fieldTable_id, "Template Fields", "Serial Number"), "Fail: [Model] Template filed doesn't present.");
		signup.clickWithScroll("SAVE");
		
		templatePage.enterExpectedValueInTextField(TemplatePageObject.searchTextField_xpath, 1, TemplatePageObject.templateName, "Search string: '"+TemplatePageObject.templateName+"'");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(templatePage.isCreateTemplatePresent(TemplatePageObject.seaqrchTable_id, "Template", TemplatePageObject.templateName), "Create template doesn't present on Template page.");
		templatePage.clickOnViewEditDelIcons(TemplatePageObject.templateName, 1, "Edit", 6, "Templates: Print Asset", 1);
		
		assertTrue(templatePage.isCreateTemplatePresent(TemplatePageObject.fieldTable_id, "Template Fields", "Serial Number"), "Fail: [Model] Template filed doesn't present.");
		signup.clickWithScroll("SAVE");
		
		templatePage.enterExpectedValueInTextField(TemplatePageObject.searchTextField_xpath, 1, TemplatePageObject.templateName, "Search string: '"+TemplatePageObject.templateName+"'");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		templatePage.clickOnViewEditDelIcons(TemplatePageObject.templateName, 2, "Delete", 6, "Templates: Print Asset", 1);
		signup.click("Yes");
		log("Asset Template delete from Grid", ILogLevel.TEST);
		
	}

}
