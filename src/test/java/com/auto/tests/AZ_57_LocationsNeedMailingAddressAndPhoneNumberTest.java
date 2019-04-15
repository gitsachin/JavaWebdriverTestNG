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
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TemplatePage;
import com.auto.pages.UsersPage;

public class AZ_57_LocationsNeedMailingAddressAndPhoneNumberTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	UsersPage userPage;
	TemplatePage templatePage;
	AssetsPage assetPage;
	
	@Test
	public void az_57_LocationsNeedMailingAddressAndPhoneNumber(){
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
		dashboard.clickButton("Locations");
		signup.click("Action");
		dashboard.clickOnExpectedButton(InventoryPageObject.buttonNewLocation,1, "New location");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Street Address 1"), "Fail: [Street Address 1] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Street Address 2"), "Fail: [Street Address 2] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("City"), "Fail: [City] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("State"), "Fail: [State] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Zip"), "Fail: [Zip] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Country"), "Fail: [Country] field doesn't present in 'New Location' pop-up");
		dashboard.clickOnArrowIcon(1);
		assertTrue(dashboard.veryfyCountryList("USA"), "Fail: [USA] doesn't appear under Country list");
		assertTrue(dashboard.veryfyCountryList("Canada"), "Fail: [Canada] doesn't appear under Country list");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Latitude"), "Fail: [Latitude] field doesn't present in 'New Location' pop-up");
		assertTrue(dashboard.verifyExpectedFieldLabelPresent("Longitude"), "Fail: [Longitude] field doesn't present in 'New Location' pop-up");
	}

}
