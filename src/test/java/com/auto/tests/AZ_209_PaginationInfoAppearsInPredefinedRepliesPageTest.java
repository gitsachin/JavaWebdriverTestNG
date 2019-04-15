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
import com.auto.pages.DashboardPage;
import com.auto.pages.PredefineRepliesPage;
import com.auto.pages.SignUpPage;

public class AZ_209_PaginationInfoAppearsInPredefinedRepliesPageTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	PredefineRepliesPage predefinePage;
	
	@Test
	public void az_209_PaginationInfoAppearsInPredefinedRepliesPage(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    predefinePage = new PredefineRepliesPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		dashboard.clickMenuTab("System");
		dashboard.clickButton("Predefined Replies");
		int gridSize = predefinePage.returnRowNoOfGrid();
		predefinePage.checkAndCreatePredefinedReplie();
		assertTrue(predefinePage.paginationInfoAppears(), "Fail: Pagination bar doesn't present.");
	}

}
