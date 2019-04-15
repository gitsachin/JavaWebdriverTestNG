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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TemplatePage;
import com.auto.pages.UsersPage;

public class AZ_108_LocationsMenuShouldnotBeUnderAssetAttributesTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	TemplatePage templatePage;
	AssetsPage assetPage;
	
	@Test
	public void az_108_LocationsMenuShouldnotBeUnderAssetAttributes(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        userPage = new UsersPage(driver);
        templatePage = new TemplatePage(driver);
        assetPage = new AssetsPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Inventory");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		assertFalse(dashboard.isExpectedItemPresentInList(DashboardPageObject.innerMenuList_xpath, "Locations", false, "Attributes"), "Fail: [Locations] present under Attributes inner menu list");
		assertTrue(dashboard.isExpectedItemPresentInList(DashboardPageObject.subMenuList_xpath, "Locations", true, "Inventory"), "Fail: [Locations] doesn't present under Inventory menu list");
		
	}

}
