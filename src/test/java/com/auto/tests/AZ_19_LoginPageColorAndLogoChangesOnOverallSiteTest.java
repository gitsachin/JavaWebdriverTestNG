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

public class AZ_19_LoginPageColorAndLogoChangesOnOverallSiteTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	
	@Test
	public void az_19_LoginPageColorAndLogoChangesOnOverallSite(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
	
		assertTrue(dashboard.isLogoPresent(DashboardPageObject.beforeLoginLogo_xpath ), "Fail: Logo doesn't present in login page.");
		assertTrue(dashboard.verifyBackGroundColor(DashboardPageObject.loginPage_xpath), "Fail: Back gorund color doesn't RGB Hex blu.");
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		assertTrue(dashboard.isLogoPresent(DashboardPageObject.afterLoginLogo_xpath), "Fail: Logo doesn't present in dashboard page.");
		assertTrue(dashboard.verifyBackGroundColor(DashboardPageObject.logoSection_xpath), "Fail: Back gorund color doesn't RGB Hex blu.");
		assertTrue(dashboard.verifyAltTextOfLogo("Aszet"), "Fail: [Aszet] text doesn't appear as ALt text of logo image.");
	}

}
