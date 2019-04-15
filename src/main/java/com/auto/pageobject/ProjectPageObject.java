package com.auto.pageobject;

import com.auto.base.BasePage;

public class ProjectPageObject {
	public static String buttonSearch_xpath = "//label[@class='pull-right']/button";
	public static String textFieldSearch_xpath = "//label[@class='pull-right']/input";
	public static String buttonNewIssue_xpath = "(//a[contains(text(), 'New Issue')])[2]";
	public static String fieldProject_xpath = "//span/span[@id='select2-projectid-container']";
	//public static String buttonEdit_xpath = "//div/a[@data-original-title='Edit Project']/i";
	public static String exactLoc = "https://app.aszet.com/?route=projects/manage&id=17";
	public static String dashBoardLoc = "https://app.aszet.com/?route=dashboard";
	public static String buttonEdit_css = ".fa.fa-edit";
	public static String textFieldIssueName_xapth = "//input[@id='name']";
	public static String projectName = "Qa_Project"+BasePage.currentDate(); 
	public static String projectHead_xpath = "//h1/small";
	
	
	public static String searchTableId = "@id='searchTable'";

}
