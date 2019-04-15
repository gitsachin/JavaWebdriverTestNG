package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.auto.base.TestCoreMobileDevices;
import com.auto.configproperties.ConfigProperties;
import com.auto.mobilePages.MobileDashBoardPage;
import com.auto.mobilePages.MobileInventoryPage;
import com.auto.mobilePages.MobileSignUp;
import com.auto.mobilePagesObject.MobileDashBoardPageObject;
import com.auto.mobilePagesObject.MobileInventoryPageObject;
import com.auto.mobilePagesObject.MobileSignUpPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;


public class AZ_15_AssetScreenResponsiveDesignTest extends TestCoreMobileDevices {
	
	MobileSignUp signup;
	MobileDashBoardPage mobileDash;
	MobileInventoryPage inventory;
	AssetsPage assetPage;
	
	@Test
	public void az_15_AssetScreenResponsiveDesign(){
		signup = new MobileSignUp(driver1);
		mobileDash = new MobileDashBoardPage(driver1);
		inventory = new MobileInventoryPage(driver1);
		assetPage = new AssetsPage(driver1);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(MobileSignUpPageObject.textFieldEmail_xpath, ConfigProperties.email, "Email Id");
		signup.enterInput(MobileSignUpPageObject.textFieldPassword_xpath, ConfigProperties.pass, "Password");
		signup.clickSubmit();
		assertTrue(mobileDash.isExpectedElementPresentOnScreen(MobileDashBoardPageObject.menuIcon_xpath, "Menu Icon"), "Fail: [Menu Icon] missing from dashboard screen.");
		mobileDash.tapOnExpectedElement(MobileDashBoardPageObject.menuIcon_xpath, "Menu Icon", 2);
		assertTrue(mobileDash.isUserNamePresent("SA"), "Fail: User name doesn't display in screen.");
		inventory.tapOnMenuTab("Inventory");
		inventory.tapButton("Assets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, MobileInventoryPageObject.assetName, "Search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.editAssets(InventoryPageObject.searchTableId, MobileInventoryPageObject.assetName,1);
		assertTrue(inventory.verifyTabs("Details"),"[Details] does not present in tab table");
		assertTrue(inventory.verifyTabs("Related Assets"),"[Related Assets] does not present in tab table");
		assertTrue(inventory.verifyTabs("Issues"),"[Issues] does not present in tab table");
		assertTrue(inventory.verifyTabs("Tickets"),"[Tickets] does not present in tab table");
		assertTrue(inventory.verifyTabs("Files"),"[Files] does not present in tab table");
		assertTrue(inventory.verifyTabs("History"),"[History] does not present in tab table");
		assertTrue(inventory.verifyTabs("Credentials"),"[Credentials] does not present in tab table");
		assertTrue(inventory.verifyTabs("Licenses"),"[Licenses] does not present in tab table");
	}
	
	

}
