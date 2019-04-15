package com.auto.pageobject;

import org.apache.commons.lang.RandomStringUtils;

public class SubscriptionPageObject {
	public static String plans = "Plan_"+RandomStringUtils.randomAlphabetic(5);
	public static String input_classname = "form-control";
	public static String priceInput_classname = "form-control priceInput";
	public static String textFieldSearch_xpath = "//label[@class='pull-right']/input";
	public static String buttonSearch_xpath = "//label[@class='pull-right']/button";
	public static String plan_name = "name";
	public static String freeAsset_name = "free_assets";
	public static String freeUser_name = "free_users";
	public static String cloudStorage_name = "cloud_storage";
	public static String planPrice_name = "price";
	public static String assrtPrice_name = "asset_price";
	public static String userPrice_name = "user_price";
	public static String buttonSavePlan_xapth = "//button[@form='addPlan']";

	public static String searchPlanTableId = "@id='planTable'";
	public static String searchFeaturesTableId = "@id='featuresTable'";
	public static String searchClientPlanTableId = "@id='clientPlanMap'";
	
	

}
