package com.auto.pageobject;

import com.auto.base.BasePage;

public class AssetsCategriesPageObject {
	public static String assetName = "QaAssetForAutomation";
	public static String filePath = System.getProperty("user.dir")+"//src//main//resources//downloads//";
	public static String existingFilePath = System.getProperty("user.dir")+"//src//main//resources//files//";
	public static String locationName = "Location"+BasePage.currentDate();
	public static String expectedGrid_xpath = "//tbody[@id='originalTable']/tr";
	public static String searchGird_xpath = "//tbody[@id='searchTable']/tr";
	public static String ticketGrid_xpth = "//table[@id='ticketDetailsTable']/tbody/tr";
	public static String button_xpath = "(//i[@class='fa fa-check'])";
	public static String arrowIcon_css = ".fa-caret-down";
	public static String arrowIcon1_xpath = "//span[@class='filterSpan selectedFilter']";
	public static String assetCategories_id = "@id='assetCategoriesFullTable'";
	public static String licenseCategories_id = "@id='licenseCategoriesFullTable'";
	public static String assetModel_id = "@id='modelsTablesFull'";
	public static String supplier_id = "@id='supplierTablesFull'";
	public static String statusLabel_id = "@id='labelsFullTable'";
	public static String manufacturersTables_id = "@id='ManufacturersTablesFull'";
	public static String eyeBallIcon_css = ".fa.fa-eye";
	public static String applyButton = "(//button[@class='btn btn-flat btn-primary'])";
	public static String label_Xpath = "(//div[@class='form-group']/label)";
	
}
