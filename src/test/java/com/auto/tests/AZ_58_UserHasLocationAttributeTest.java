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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;

public class AZ_58_UserHasLocationAttributeTest extends TestCore {


	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	SystemPage system;
	
	@Test
	public void az_58_UserHasLocationAttribute(){
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        system = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickMenuTab("Inventory");
		dashboard.clickButton("Locations");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "QaLocationForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(assetPage.isLocationPresentInGrid("QaLocationForAutomation_DND"), "Fail: [QaLocationForAutomation_DND] doesn't present in Location grid.");
		
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("People");
		dashboard.clickOnExpectedMenus("Users", "[Users] inner menu");
		signup.click("Action");
		system.clickOnExpectedButton(SystemPageObject.buttonAddUser_xpath, 1, "Add user account");
		system.clickOnArrowIconOfFields("Location", 2);
		system.enterSearchString("QaLocationForAutomation_DND", 1, "Location");
		assertTrue(system.isEnteredKeyPresentInList("QaLocationForAutomation_DND", "Location"), "Fail: Entered location [QaLocationForAutomation_DND] doesn't present in list.");
		
		
		
	}
}
