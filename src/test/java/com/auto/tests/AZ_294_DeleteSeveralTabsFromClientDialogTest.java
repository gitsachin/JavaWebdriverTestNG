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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_294_DeleteSeveralTabsFromClientDialogTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SystemPage  system;
	
	
	@Test
	public void az_295_RenameIssuesToTasks(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    system = new SystemPage(driver);
	    
	    driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QATEST", "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QATEST", 2, "Eye Ball", 6, 1);
		assertTrue(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Summary"), "Fail: [Summary] tab doesn't present.");
		assertTrue(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Users"), "Fail: [Users] tab doesn't present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Assets"), "Fail: [Assets] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"License"), "Fail: [License] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Projects"), "Fail: [Projects] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Issues"), "Fail: [Issues] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Tickets"), "Fail: [Tickets] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Credentials"), "Fail: [Credentials] tab present.");
		assertFalse(system.isExpectedTabPresentWithExpectedPosition(SystemPageObject.tabClientDetails_xpath,"Files"), "Fail: [Files] tab present.");
		
	}

}
