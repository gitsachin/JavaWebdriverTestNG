package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.RolePage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;

public class AZ_96_EditingExistingUsersDoesNotWorkTest extends TestCore {
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	RolePage rolePage;
	SystemPage system;
	
	@Test
	public void az_96_EditingExistingUsersDoesNotWorkTest() throws AWTException{
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        ticketPage = new TicketPage(driver);
        assetPage = new AssetsPage(driver);
        rolePage = new RolePage(driver);
        SoftAssert softAssertion= new SoftAssert();
      
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		system = new SystemPage(driver);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1,"QATEST", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, "22", 1, "View", 6, "Client", 1);
		dashboard.clickOnUserTab();
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.userTableId, ConfigProperties.email, 1, "View", 4, "Client", 3);
		assetPage.clickOnArrowIconOfFields("Location", 3);
		assetPage.enterSearchString("asset loc", 1, "Location");
		signup.click("Save");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: ["+InventoryPageObject.addedSucessMessage+"] Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)), InventoryPageObject.addedSucessMessage);
		assetPage.clickOnArrowIconOfFields("Location", 3);
		assetPage.enterSearchString("None", 1, "Location");
		signup.click("Save");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: ["+SignUpPageObject.successMessage_css+"] success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)), InventoryPageObject.addedSucessMessage);
	}

}
