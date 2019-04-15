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


public class AZ_173_IssueCategoriesListHaveCountColumnAndShowNumberOfIssueTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	IssuePage issuePage;
	AssetsPage assetPage;

	@Test
	public void AZ_173_IssueCategoriesListHaveCOUNTColumnAndShowNumberOfIssue(){
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
		
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "IssueCatForAutomation_DND", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		
		assertTrue(issuePage.isExpectedColumnPresent(IssuePageObject.tableHeader_xpath, "Count"), "Fail: [Count]  column doesnot present.");
		
		int countNumber = Integer.parseInt(issuePage.returnCountNo(IssuePageObject.searchTableId, "IssueCatForAutomation_DND"));
		
		assertTrue(issuePage.isEyeIconPresent(IssuePageObject.searchTableId, "IssueCatForAutomation_DND", 1, "Eye Ball", 4, "Issue", 2), "Fail: [Eye Ball] icon doesnot present.");
		
		issuePage.clickOnViewEditDelIcons(IssuePageObject.searchTableId, "IssueCatForAutomation_DND", 1, "Eye Ball", 4, "View", 2);
		
		assertTrue(issuePage.isGridSizeSameAccordingToCountNumber(IssuePageObject.searchTableId, countNumber), "Fail: Issue Category grid size doesn't same as per Count value.");
		
	}
	
	
	
	
}	

