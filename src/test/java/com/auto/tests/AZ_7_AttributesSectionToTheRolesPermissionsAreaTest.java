package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_7_AttributesSectionToTheRolesPermissionsAreaTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	SystemPage system;
	
	@Test
	public void az_7_AttributesSectionToTheRolesPermissionsArea(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        system = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Roles", "[Roles] inner menu");
		
		system.enterExpectedValueInTextField(SystemPageObject.roleSearchField_xpath, 1, ConfigProperties.role, "Roles for search");
		system.clickOnExpectionIconsForRoles(SystemPageObject.roleGridSearchId, ConfigProperties.role, 3, "Edit", 4, 1);
		assertTrue(system.isExpectedSectionPresent("Roles"), "[Roles] doesn't present in attributes permissions area.");
		assertTrue(system.isExpectedSectionHavePermissionArea("Roles", 15), "[Roles] doesn't have accesss permission attributes.");
	}

}
