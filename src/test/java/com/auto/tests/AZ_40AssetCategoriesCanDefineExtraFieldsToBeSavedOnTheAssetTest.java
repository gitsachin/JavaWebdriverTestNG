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
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.IssuePage;
import com.auto.pages.SignUpPage;

public class AZ_40AssetCategoriesCanDefineExtraFieldsToBeSavedOnTheAssetTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	AssetsPage assetPage;

	@Test
	public void az_40AssetCategoriesCanDefineExtraFieldsToBeSavedOnTheAssetTest(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        issuePage = new IssuePage(driver);
        assetPage = new AssetsPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("Issues");
		dashboard.clickOnSpecificSubmenu("Attributes", 3);
		dashboard.clickOnInnerSubmenu("Issues", "Attributes", "Issue Categories");
		signup.click("Action");
		signup.click("New issue category");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "New Issue Category");
		signup.enterInput(1,IssuePageObject.categoriesName);
		signup.enterInput(2,"#8c8caa");
		issuePage.clickNewButton();
		signup.enterInput(3,IssuePageObject.AttributeName);
		issuePage.selectValueFromDropdown("Boolean");
		signup.click("Add");
		issuePage.clickNewButton();
		signup.enterInput(3,IssuePageObject.dateName);
		issuePage.selectValueFromDropdown("Date");
		signup.click("Add");
		issuePage.clickCreate();
		dashboard.clickButton("Active Issues");
		signup.click("Action");
		signup.click("New issue");
		signup.enterInput(3,IssuePageObject.issueName);
		assetPage.clickOnArrowIconOfFields("Type", 1);
		assetPage.enterSearchString(IssuePageObject.categoriesName, 1, "Type");
		assertEquals(assetPage.getLabelText(IssuePageObject.warrantyLabelId), "Warranty");
		assertEquals(assetPage.getLabelText(IssuePageObject.datelabelId), "Date");
		assertTrue(issuePage.verifyDataCreatelist(IssuePageObject.warrantyFiledId,"TRUE"));
		assertTrue(issuePage.verifyDataCreatelist(IssuePageObject.dateFileId,basepage.currentOnlyDate()));
		signup.click("Cancel");
		dashboard.clickOnSpecificSubmenu("Attributes", 3);
		dashboard.clickOnInnerSubmenu("Issues", "Attributes", "Issue Categories");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, IssuePageObject.categoriesName, "Location search string");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, IssuePageObject.categoriesName, 3, "Delete", 4, "Model", 2);
		signup.click("Yes");
	}

}
