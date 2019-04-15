package com.auto.pageobject;

import com.auto.base.BasePage;

public class TemplatePageObject {
	
	public static String templateName = "Template_"+BasePage.currentDate();
	public static String availbleField_id = "availableFields";
	public static String displaysAS_id = "displayAs";
	public static String displayValue_id = "displayValue";
	
	public static String barcodeText_css = ".col-xs-12.childDiv>p";
	public static String barCodeClose_css = ".close";
	
	//public static String templateGrid_xpath = "//table[@id='dataTablesFull']/tbody/tr/td[1]";
	//public static String textFieldSearch_xpath = "//label/input[@type='text']";
	public static String buttonSearch_xpath = "//label[@class='pull-right']/button";
	public static String searchTextField_xpath = "//input[@placeholder='Search...']";
	public static String dispalyOptions_xpath = "(//select[@id='displayAs']/option)";
	
	public static String seaqrchTable_id = "@id='AssetTemplates'";
	public static String fieldTable_id = "@id='fieldTable'";
	
}
