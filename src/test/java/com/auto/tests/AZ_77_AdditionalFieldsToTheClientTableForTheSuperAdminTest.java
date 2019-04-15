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
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_77_AdditionalFieldsToTheClientTableForTheSuperAdminTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	SystemPage system;
	
	@Test
	public void az_77_AdditionalFieldsToTheClientTableForTheSuperAdmin(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        system = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "jason");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertTrue(system.isExpectedSecreenAppear(SystemPageObject.headerText_xpath, "Clients"), "Fail: [Clients] page doesn't open.");
		
		assertTrue(system.isExpectedTableHeaderPresent(SystemPageObject.tableHeader_xpath, "Created On"), "Fail: [Created] column doesn't present.");
		assertTrue(system.isExpectedTableHeaderPresent(SystemPageObject.tableHeader_xpath, "Subscription"), "Fail: [Subscription] column doesn't present.");
	}

}
