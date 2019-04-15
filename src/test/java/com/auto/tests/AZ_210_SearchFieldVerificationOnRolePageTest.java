package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;


public class AZ_210_SearchFieldVerificationOnRolePageTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	RolePage rolePage;
	SystemPage system;
	

	@Test
	public void az_210_SearchFieldVerificationOnRolePage(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        rolePage = new RolePage(driver);
        system = new SystemPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		dashboard.clickButton("Client Roles");
		system.enterExpectedValueInTextField("("+SystemPageObject.roleSearchField_xpath+")[2]", 1, "Client 2 Inc.", "Roles for search");
		system.clickOnExpectionIconsForRoles(RolePageObject.roleGridId,"Client 2 Inc.",2,"Eye Ball", 4,1);
		assertTrue(system.isExpectedFieldPresent(SystemPageObject.roleSearchField_xpath, "Role Search Field", "SuperAdmin: Role Page"), "Fail: Search filed dpesn't present in Role page.");
			
	}
}
