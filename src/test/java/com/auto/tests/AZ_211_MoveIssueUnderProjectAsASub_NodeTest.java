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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;


public class AZ_211_MoveIssueUnderProjectAsASub_NodeTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	
	
	@Test
	public void az_211_MoveIssueUnderProjectAsASub_Node(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        assertTrue(dashboard.verifyURL(),"Https not present in url");
        signup.enterInput(0, ConfigProperties.superAdmin_email01);
		signup.enterInput(1, ConfigProperties.superAdmin_pass01);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		assertFalse(dashboard.isExpectedMenuDisplayedInLeftPlanel(DashboardPageObject.menu_xpath+"Issues"+DashboardPageObject.menu1_xpath, "Issues", "in Menu List"), "Fail: Issues menu present in Menu list.");
		dashboard.clickMenuTab("Projects");
		assertTrue(dashboard.isExpectedMenuDisplayedInLeftPlanel(DashboardPageObject.menu_xpath+"Issues"+DashboardPageObject.menu1_xpath, "Issues", "under Projects Menu"), "Fail: Issues doesn't present under menu list.");
	}

}
