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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.KnowledgeBasePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_131_ClientUserBatchUploadAddLocationFieldToBatchUploadTemplateTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	KnowledgeBasePage knowledgePage;
	SystemPage systemPage;
	
	@Test(priority=0)
	public void az_131_ClientUserBatchUploadAddLocationFieldToBatchUploadTemplateTest() throws Exception{
	signup = new SignUpPage(driver);
	configprop = new ConfigProperties();
	basepage = new BasePage(driver);
    dashboard = new DashboardPage(driver);
    assetPage = new AssetsPage(driver);
    knowledgePage = new KnowledgeBasePage(driver);
    systemPage = new SystemPage(driver);
    
	assertEquals(driver.getTitle(), SignUpPageObject.title);
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
	
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	signup.click("Action");
	assetPage.clickOnExpectedButton(SystemPageObject.buttonBatchUploadWithOutIndex_xpath, 1, "Batch upload");
	systemPage.writeDataInExcelSheet(SignUpPageObject.email);
	assetPage.sendFile(ConfigProperties.userBatchFile);
	signup.click("Upload");
	assertTrue(assetPage.isExpectedContentTextPresentInScreen(InventoryPageObject.uploadSuccessMessage_xpath, InventoryPageObject.uploadSuccessMessage), "Fail: [Upload Success Message] doesn't present.");
	signup.click("Close");
	systemPage.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	systemPage.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, SignUpPageObject.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)),"UserName");
	systemPage.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	systemPage.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
	
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,SignUpPageObject.email, "Search string: ");
	assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assertTrue(assetPage.isItemAddedInGrid(InventoryPageObject.searchTableId, "User", SignUpPageObject.email, 3), "Assert Fail: [Licenses] doesn't create.");
	systemPage.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	systemPage.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, ConfigProperties.superAdmin_email);
	signup.enterInput(1, ConfigProperties.superAdmin_pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
	
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,SignUpPageObject.email, "Search string: ");
	assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId,SignUpPageObject.email,2,"Delete", 6,"User",4);
	signup.click("Yes");
	systemPage.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
	systemPage.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	
	signup.enterInput(0, SignUpPageObject.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(SystemPageObject.alertCss)), SystemPageObject.authenticationMessage);
	
	signup.enterInput(0, ConfigProperties.email);
	signup.enterInput(1, ConfigProperties.pass);
	signup.clickSubmit();
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
	
	dashboard.clickMenuTab("System");
	dashboard.clickMenuTab("People");
	dashboard.clickButton("Users");
	assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
	assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,SignUpPageObject.email, "Search string: ");
	assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
	}

}
