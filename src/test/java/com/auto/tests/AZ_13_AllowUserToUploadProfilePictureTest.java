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
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_13_AllowUserToUploadProfilePictureTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	
	@Test(priority=0)
	public void az_13_UploadAvtarImage() throws Exception{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		dashboard.clickOnUser();
		dashboard.clickButton("Profile");
		dashboard.clickOnBrowserButton();
		assetPage.uploadFile("avatar.jpg");
		signup.enterInput(6, ConfigProperties.pass);
		signup.click("Save");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		assertTrue(dashboard.CheckImage(),"Image is not present");
		dashboard.clickOnUser();
		dashboard.clickButton("Profile");
		dashboard.clickButton("Remove Avatar Image");
		signup.enterInput(6, ConfigProperties.pass);
		signup.click("Save");
		assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage);
		assertFalse(dashboard.CheckImage(),"Image is still present");
	}

}
