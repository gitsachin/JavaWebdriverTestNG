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
import com.auto.pages.SignUpPage;


public class AZ_304_AddHelpDeskTicketSummaryOnDashboardTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	
	@Test
	public void az_304_AddHelpDeskTicketSummaryOnDashboard(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	      
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		String expectedSummary[] = {"Service Request", "Assets", "Contract", "Projects", "Clients"};
		int size = expectedSummary.length;
		for(int i=0; i<=size-1; i++){
			assertTrue(dashboard.isExpectedSummaryAvailableOnDashboard(expectedSummary[i], "Dashboard Page"), "Fail: Expected summary: "+expectedSummary[i]+" missing from Dashboard page.");
		}
		
		
	}

}
