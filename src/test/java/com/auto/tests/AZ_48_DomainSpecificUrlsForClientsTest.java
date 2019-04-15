package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_48_DomainSpecificUrlsForClientsTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	RolePage rolePage;
	SystemPage system;
	
	@Test
	public void az_48_DomainSpecificUrlsForClientsTest() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		system = new SystemPage(driver);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		signup.click("Actions");
		signup.click("Add client");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Client");
		signup.enterInput(1,SystemPageObject.clienName);
		signup.enterInput(2,SystemPageObject.domainName);
		signup.enterInput(3,InventoryPageObject.assetName);
		signup.enterInput(4,InventoryPageObject.assetName);
		signup.enterInput(5,SystemPageObject.name);
		signup.enterInput(6,SystemPageObject.email);
		signup.enterInput(7,SystemPageObject.password);
		signup.enterInput(8,SystemPageObject.password);
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonCreate_xpath, 1, "Create");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		system.clickOnExpectedButton(SignUpPageObject.userIcon_xpath, 1, "Header section User Down Arrow icon");
		system.clickOnExpectedButton(SignUpPageObject.buttonLogout_xpath, 1, "Logout");
		driver.navigate().to("https://"+SystemPageObject.domainName+".aszet.com");
		signup.enterInput(0,ConfigProperties.superAdmin_email);
		signup.enterInput(1,ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(SystemPageObject.alertCss)), SystemPageObject.message);
		signup.enterInput(0,SystemPageObject.email);
		signup.enterInput(1,SystemPageObject.password);
		signup.clickSubmit();
		signup.click("ACTIVE");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SystemPageObject.name);
		driver.navigate().to(ConfigProperties.signin_url);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		system = new SystemPage(driver);
		signup.clickSubmit();
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SystemPageObject.clienName, "Search string: '"+SystemPageObject.clienName+"'");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, SystemPageObject.clienName, 3, "Delete", 6, "Client", 2);
        signup.click("Yes");
		
		log("Client delete from Grid", ILogLevel.TEST);
	}

}
