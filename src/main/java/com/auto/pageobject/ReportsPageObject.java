package com.auto.pageobject;

public class ReportsPageObject {
	public static String reportRunHisTab_xpath = "//a[@href='#tab-runningReports']";
	public static String reportCanRunTab_xpath = "//a[@href='#tab-runReports']";
	public static String tabSelect_true_xpath = "//a[@aria-expanded='true']"; 
	public static String tabSelect_false_xpath = "//a[@aria-expanded='false']";
	public static String reportsTable = "@id='report_table_view'";
	public static String boxTitle_xpath = "(//h3[@class='box-title'])";
	public static String headerText_css = ".content-header>h1";
	
	public static String startDate_xapth = "(//input[@id='startDate'])";
	public static String endDate_xapth = "(//input[@id='endDate'])";
	public static String buttonGenrate_xapth = "(//button[text()='Generate'])";
	public static String buttonAsset_linkText = "Assets (Detailed List)";
	public static String buttonLicense_linkText = "Contract (Detailed List)";
	public static String buttonWarentyExp_linkText = "Warranty Expiration Report (Assets)";
	public static String buttonExportType_xpath = "//span[@class='export_type']";
	public static String tabReport_xpath = "//div[@class='row']//div/ul/li";
	
	public static String tableId = "@id='report_table_view'";
}
