package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.UsersPage;

public class AZ_194_AllTableDataAppearsInSinglePageTest extends TestCore {
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	SystemPage  system;
	RolePage rolePage;
	
	
	@Test
	public void az_194_AllTableDataAppearsInSinglePage() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        system = new SystemPage(driver);

        driver.navigate().to(ConfigProperties.signin_url);
      
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName );
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		int gridSize = system.returnRowNoOfGrid(SystemPageObject.roleGridSearchId);
		assertTrue(system. verifyGridSize(SystemPageObject.roleGridSearchId, "Roles", gridSize), "Fail: Roles grid size iz not 10");
		assertTrue(system.isPaginationInfoAppears(SystemPageObject.roleGridSearchId), "Fail: Pagination bar doesn't present.");
		
		
	}

}
