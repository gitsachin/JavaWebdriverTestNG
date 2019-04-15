package com.auto.pageobject;

import com.auto.base.BasePage;

public class MonitoringPageObject {
	public static String buttonHost_xapth = "//a[text()='NEW HOST']";
	public static String textFieldIssueName_xapth = "//input[@id='name']";
	public static String hostName = "Qa_Host_"+BasePage.currentDate(); 
	public static String textFieldAddress_xapth = "//input[@id='address']";
	public static String hostAddress = "http://"+hostName; 
	public static String breadcrumb_xpath = "//ol[@class='breadcrumb']/li[@class='active']";
	
	public static String monitoringTableId = "@id='hostsTable'";
}
