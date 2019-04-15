package com.auto.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_16_LeftHandMenuChangesTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_16_LeftHandMenuChanges(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		Assert.assertTrue(dashboard.verifyUpdatedText(By.xpath(DashboardPageObject.menu_xpath+"Help Desk Tickets"+DashboardPageObject.menu1_xpath), "Tickets"), "Assert Fail: Text doesn't changes to [Help Desk Tickets].");
		Assert.assertTrue(dashboard.verifyUpdatedText(By.xpath(DashboardPageObject.helpDeskSectionHeader), "Active Tickets"), "Fail: Text doesn't changes to [Active Help Desk Tickets].");
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Active Tickets");
		Assert.assertTrue(dashboard.verifyUpdatedText(By.cssSelector(DashboardPageObject.headerText_css), "Tickets"), "Fail: Text doesn't changes to [Active Help Desk Tickets].");
		dashboard.clickButton("All Tickets");
		Assert.assertTrue(dashboard.verifyUpdatedText(By.cssSelector(DashboardPageObject.headerText_css), "Tickets"), "Fail: Text doesn't changes to [Active Help Desk Tickets].");
		dashboard.clickMenuTab("Help Desk Tickets");
		Assert.assertFalse(dashboard.isExpectedMenuPresentInLeftPlanel(SignUpPageObject.ButtonTagA_xpath+"Manage Client Info"+DashboardPageObject.menu1_xpath,"Manage Client Info", "under left menu panel"), "Fail: [Manage Client Info] present in left menu panel.");
		dashboard.clickMenuTab("System");
		Assert.assertTrue(dashboard.isExpectedMenuPresentInLeftPlanel(DashboardPageObject.menu_xpath+"Manage Client Info"+SignUpPageObject.ButtonTagA1_xpath,"Manage Client Info", "under [System] menu in left menu panel."), "Fail: [Manage Client Info] doesn't present under [System] menu in left menu panel.");
	}

}
