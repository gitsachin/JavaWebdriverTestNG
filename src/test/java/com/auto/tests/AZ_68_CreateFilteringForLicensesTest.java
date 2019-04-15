package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_68_CreateFilteringForLicensesTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	UsersPage userPage;
	SystemPage system;
	IssuePage issuePage;	
	@Test
	public void az_68_CreateFilteringForLicensesTest() throws IOException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        system = new SystemPage(driver);
        issuePage= new IssuePage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Licenses");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		assetPage.isFilterArrowIconPresent(InventoryPageObject.licenseTableHeader_xpath);
		assetPage.clickOnExpectedFilterArrowIcon("Category",1);
		assetPage.clickOnLabel(30,1,"license");
		assetPage.clickOnApplyButton(1);
		assetPage.clickOnExpectedFilterArrowIcon("Status",1);
		assetPage.clickOnLabel(40,1,"Status Label");
		assetPage.clickOnApplyButton(2);
		issuePage.countNumberOfIssueCategories(2);
		signup.click("Action");
		assetPage.clickActionButtonDropdown(2,"Export Excel Button");
	    assertTrue(assetPage.fileExist(".xlsx"));
	    assertTrue(assetPage.countRowInExcel(IssuePage.numberOfIssueCategories),"");
	    assetPage.deleteFile();
	    system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
	    signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Licenses");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
	    userPage.selectOption(1,"QATEST");
	    issuePage.countNumberOfIssueCategories(2);
		signup.click("Action");
		assetPage.clickActionButtonDropdown(2,"Export Excel Button");
	    assertTrue(assetPage.fileExist(".xlsx"));
	    assertTrue(assetPage.countRowInExcel(IssuePage.numberOfIssueCategories),"");
	    assetPage.deleteFile();
		
	}

}
