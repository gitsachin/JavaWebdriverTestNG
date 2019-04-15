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
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SubscriptionPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SubscriptionPage;

public class AZ_214_CreateEditDeletePlansTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	SubscriptionPage subscription;
	
	@Test(priority=0)
	public void  az_214_createEditDeletePlan(){
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    subscription = new SubscriptionPage(driver);
	    
	    assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Subscription");
		dashboard.clickMenuTab("Plans");
		dashboard.clickButton("NEW PLAN");
		subscription.enterInput(SubscriptionPageObject.plan_name,  SubscriptionPageObject.plans, "Plan Name");
		subscription.enterInput(SubscriptionPageObject.freeAsset_name, "10", "Free Assets");
		subscription.enterInput(SubscriptionPageObject.freeUser_name, "10", "Free Users");
		subscription.enterInput(SubscriptionPageObject.cloudStorage_name, "20", "Cloud Storage");
		subscription.enterInput(SubscriptionPageObject.planPrice_name, "10", "Plan Price");
		subscription.enterInput(SubscriptionPageObject.assrtPrice_name, "10", "Asset Price");
		subscription.enterInput(SubscriptionPageObject.userPrice_name, "10", "User Price");
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSavePlan_xapth, 1, "Save");
		subscription.enterExpectedValueInTextField(SubscriptionPageObject.textFieldSearch_xpath, 1, SubscriptionPageObject.plans, "Search string");	
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchPlanTableId, "Plans", SubscriptionPageObject.plans, 2), "Fail: Search function doesn't perform.");
		subscription.clickOnViewEditDelIcons(SubscriptionPageObject.searchPlanTableId, SubscriptionPageObject.plans, 2, "Edit", 4, 1);
		subscription.enterInput(SubscriptionPageObject.plan_name, "My Plan 001", "Plan Name");
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSavePlan_xapth, 1, "Save");
		subscription.enterExpectedValueInTextField(SubscriptionPageObject.textFieldSearch_xpath, 1, SubscriptionPageObject.plans, "Search string");	
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchPlanTableId, "Plans", SubscriptionPageObject.plans, 2), "Fail: Item doesn't updated in grid.");
		subscription.enterExpectedValueInTextField(SubscriptionPageObject.textFieldSearch_xpath, 1, "My Plan 001", "Search string");	
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(subscription.isSearchedItemPresentInGrid(SubscriptionPageObject.searchPlanTableId, "Plans", "My Plan 001", 2), "Fail: Item doesn't delete from grid.");
		subscription.clickOnViewEditDelIcons(SubscriptionPageObject.searchPlanTableId, "My Plan 001", 2, "Delete", 4, 2);
		signup.click("Yes");
		subscription.enterExpectedValueInTextField(SubscriptionPageObject.textFieldSearch_xpath, 1, "My Plan 001", "Search string");	
		subscription.clickOnExpectedButton(SubscriptionPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(subscription.isItemDeleteFromGrid(SubscriptionPageObject.searchPlanTableId, "Plan", "My Plan 001", 1, false), "Fail: Searched item present in User grid.");	
	}

}
