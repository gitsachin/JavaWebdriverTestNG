package com.auto.pageobject;

import com.auto.base.BasePage;

public class PredefineRepliesPageObject {
	public static String gridId_xpath = "//table[@id='dataTablesFull']/tbody/tr";
	public static String paginationTab_xpath = "(//ul[@class='pagination'])[1]";
	public static String paginationInfo_xpath = "(//div[@class='dataTables_info'])[1]";
	public static String buttonPredefine_xapth = "//a[contains(text(), 'NEW PREDEFINED REPLY')]";
	public static String name = "PredefineReply_"+BasePage.autogenerateNumber(3);
}
