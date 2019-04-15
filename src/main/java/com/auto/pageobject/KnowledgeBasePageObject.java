package com.auto.pageobject;

import com.auto.base.BasePage;

public class KnowledgeBasePageObject {
	
	public static String articalName = "Artical"+BasePage.currentDate();
	
	public static String knowledgeTable_id = "@id='kbDataTablesFull'";
	public static String uploadedFile_xpath = "//ul[@id='fileslist']/li//div[@class='col-xs-10']";
	public static String fileButton_xpath = "//ul[@id='fileslist']/li//div[@class='pull-right']//a";
	public static String uploadFileButton_xpath ="(//input[@type='file'])";

}
