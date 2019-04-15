package com.auto.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_55_HelpDesk_TicketSettingTest extends TestCore{
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	
	@Test(priority=0)
	public void verifyTicketSettingButtonInHelpDesk(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Help Desk Tickets");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),3), "Ticket Settings");
		
	}
	
	@Test(priority=1)
	public void verifyTicketSettingPageContents(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
		
		dashboard.clickMenuTab("Help Desk Tickets");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),3), "Ticket Settings");
		dashboard.clickButton("Ticket Settings");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Settings");
		dashboard.logOut(ConfigProperties.logout_url);
		
		
	}

}
