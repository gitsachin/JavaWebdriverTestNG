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
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TemplatePage;
import com.auto.pages.UsersPage;

public class AZ_99_StaffMembersCanSeeMoreThanOneClientTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	TemplatePage templatePage;
	AssetsPage assetPage;
	SystemPage system;
	
	@Test
	public void az_99_StaffMembersCanSeeMoreThanOneClient(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        userPage = new UsersPage(driver);
        templatePage = new TemplatePage(driver);
        assetPage = new AssetsPage(driver);
        system = new SystemPage(driver);
        
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnSpecificSubmenu("Staff", 1);
		
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ConfigProperties.staff, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		system.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ConfigProperties.staff, 2, "Edit", 6, 1);
		//system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, "QaTestStaff 01", 2, "Edit", 6, 1);
		assertTrue(system.isMultipleClientPresentInList(), "Fail: [Multiple CLient] doesn't present in Client field.");
		assertTrue(system.isAllClintSelectedInClientField("All Clients"), "Fail: [All Clients] doesn't appear in Client field.");
		
		
	}

}
