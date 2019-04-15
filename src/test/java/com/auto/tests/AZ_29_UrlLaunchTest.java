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



public class AZ_29_UrlLaunchTest extends TestCore {

	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	
	@Test
	public void az_29_UrlLaunch(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		assertTrue(signup.verifyLaunchedPageUrl(ConfigProperties.signin_url), "Fail: [Login] page doesn't open.");
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		signup.broswerBack();
		assertTrue(signup.verifyLaunchedPageUrl(ConfigProperties.signin_url), "Fail: [Login] page doesn't open.");
		signup.enterTestedUrl(ConfigProperties.site_url);
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
	}

}
