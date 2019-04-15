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
import com.auto.pageobject.ProjectPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;


public class AZ_113_SessionTimeOutAndKeepUserAtExactLocationAfterReLoginTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage projectsPage;
	
	
	@Test
	public void az_113_SessionTimeOutAndKeepUserAtExactLocationAfterReLogin(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        projectsPage = new ProjectPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Projects");
		dashboard.clickMenuTab("All Projects");
		
		projectsPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaProjectForAutomation", "Search string: 'QaProjectForAutomation'");
		projectsPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		projectsPage.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, "QaProjectForAutomation", 1, "Eye", 6, "View", 3);
		
		assertTrue(projectsPage.isCookiesDelete(ProjectPageObject.exactLoc), "Cookies doesn't deleted.");
		
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		
		assertTrue(projectsPage.navigeteToExpectedpageAfterStartNewSession(ProjectPageObject.exactLoc, ProjectPageObject.dashBoardLoc), "New session doesn't starts.");
		
		
	}

}
