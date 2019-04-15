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
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;

public class AZ_115_IssueCategoriesTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	AssetsPage assetPage;

	@Test
	public void az_115_VerifyNumberOfIssueCategories(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        issuePage = new IssuePage(driver);
        assetPage = new AssetsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickOnSpecificSubmenu("Attributes", 3);
		dashboard.clickOnInnerSubmenu("Issues", "Attributes", "Issue Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issue Categories Manage issue categories");
		issuePage.countNumberOfIssueCategories(1);
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		signup.click("Action");
		signup.click("New issue");
		assetPage.clickOnArrowIconOfFields("Type", 1);
		assertTrue(issuePage.verifyNumberOfCategories(IssuePageObject.typedropdown_id,IssuePage.numberOfIssueCategories));
	}
	

}
