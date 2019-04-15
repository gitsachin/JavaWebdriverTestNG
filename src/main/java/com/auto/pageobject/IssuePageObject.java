package com.auto.pageobject;

import com.auto.base.BasePage;

public class IssuePageObject {
	public static String tableHeader_xpath = "//table[@id='assetCategoriesFullTable']/thead/tr/th";
	public static String issueName = "QaIssueForAutomation";
	public static String issueNameGenerate = "Qa_Task"+BasePage.currentDate(); 
	public static String column_xpath = "(//a[@class='textSpan'])";
	public static String filterArrowIcon_xpath = "(//span[@class='filterSpan hideFilter'])";
	public static String dateRangeField_id = "dueDateRange";
	public static String categoriesName = "Categories"+BasePage.currentDate();
	public static String AttributeName = "Warranty";
	public static String dateName = "Date";
	public static String CreateButtonId = "saveCat";
	public static String new_Id = "newAttr";
	public static String attributeType_id = "attributeType";
	public static String color = "color";
	public static String warrantyFiledId = "Warranty";
	public static String dateFileId = "Date";
	public static String datelabelId = "purchase_date";
	public static String warrantyLabelId = "value";
	public static String textFieldIssueName_xapth = "//input[@id='name']";
	public static String fieldAssetName_xapth = "//input[@id='assetInput']";
	public static String buttonCreate_xpath = "//button[@type='submit']";
	public static String textFieldSearch_xpath = "//label[@class='pull-right']/input";
	public static String buttonSearch_xpath = "//label[@class='pull-right']/button";
	public static String clientFiled_xapth = "//span[@id='select2-clientid-container']";
	public static String assignToFiled_xapth = "//span[@id='select2-adminid-container']";
	public static String assignToFiledSuggestionList_xapth = "//ul[@id='select2-adminid-results']/li";
	public static String projectFiledSuggestionList_xapth = "//ul[@id='select2-projectid-results']/li";
	public static String buttonCancel_xpath = "(//button[@data-dismiss='modal'])[2]";
	public static String buttonCreateTicket_xpath = "//a[text()='Create New Ticket']";
	public static String buttonNewTasksCreate_xpath = "(//a[contains(text(), 'New Task')])[2]";
	
	
	public static String searchTableId = "@id='searchTable'";
	public static String tableHeader = "@id='assetCategoriesFullTable'";
	public static String typedropdown_id = "@id='select2-issuetype-results'";

}
