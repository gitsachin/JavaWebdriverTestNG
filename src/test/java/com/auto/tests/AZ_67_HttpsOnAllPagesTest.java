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

public class AZ_67_HttpsOnAllPagesTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	
	@Test
	public void az_67_HttpsOnAllPagesTest(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        assertTrue(dashboard.verifyURL(),"Https not present in url");
        signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)),DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		dashboard.clickButton("Active Tickets");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		dashboard.clickButton("All Tickets");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		dashboard.clickButton("Ticket Settings");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Settings");
		dashboard.clickOnSpecificSubmenu("Attributes", 1);
		dashboard.clickButton("Ticket Fields");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Ticket Fields Manage Ticket Fields");
		
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Assets");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		dashboard.clickButton("Licenses");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		dashboard.clickButton("Locations");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Locations Manage locations");
		dashboard.clickOnSpecificSubmenu("Attributes", 2);
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Asset Categories");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Asset Categories Manage asset categories");
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "License Categories");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "License Catgories Manage license categories");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Manufacturers");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Manufacturers Manage manufacturers");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickOnInnerSubmenu("Inventory", "Attributes", "Suppliers");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Suppliers Manage suppliers");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		dashboard.clickButton("All Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		dashboard.clickOnSpecificSubmenu("Attributes", 3);
		dashboard.clickButton("Issue Categories");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issue Categories Manage issue categories");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("All Projects");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Projects Manage projects");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Monitoring");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Reports");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Templates");
		dashboard.clickMenuTab("Print Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Asset TemplatesView templates to print assets barcode");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Subscription");
		dashboard.clickMenuTab("Plans");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Plans Subscription plans");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Features");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Features Subscription Features");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Client Plan");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Client Plan Plan Selected By Client");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("Knowledge Base");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Knowledge Base");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickMenuTab("People");
		dashboard.clickButton("Users");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Users Manage user accounts");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickButton("Staff");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Staff Manage staff accounts");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickButton("Roles");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Roles Manage user Roles");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickButton("Contacts");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Contacts Manage contacts");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickButton("Predefined Replies");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Predefined Replies Manage predefined replies");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
		dashboard.clickButton("Logs");
		assertTrue(dashboard.verifyURL(),"Https not present in url");
		
	}

}
