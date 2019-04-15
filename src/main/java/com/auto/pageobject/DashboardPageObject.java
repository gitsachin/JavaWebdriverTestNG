package com.auto.pageobject;

import com.auto.base.BasePage;

public class DashboardPageObject {
	
	public static String clientAdminName = "SA";
	public static String superAdminName = "jason";
	public static String clientUserName = "ClientUser_DND";
	public static String browserButton_Xpath = "//input[@name='avatar']";
	public static String userIcon_css = ".hidden-xs";
	public static String image_Xpath = "//img[@class='img-circle']";
	public static String alertMessage_xpath = "//div[@class='alert alert-danger alert-auto']";
	public static String message = "Unauthorized Access";
	public static String departmentName = "Department"+BasePage.autogenerateNumber(7);
	
	
	public static String user_css = ".pull-left.info > p";
	public static String menu_xpath = "//span[contains(text(),'";
	public static String menu1_xpath = "')]";
	public static String subMenu_css = ".treeview-menu.menu-open>li>a";
	public static String headerText_css = "h1.pull-left";
	public static String headerTextReports_css = ".content-header>h1";
	public static String headerTextlog_css = ".content-header>h1";
	public static String menuIcon_css = "i.fa-cogs";
	public static String assetsCategories_xapth = "//div[@id='js-legend']/ul/li";
	public static String userTab_id = "go-users";
	public static String locatorInnerSubmenus_xpath = "//li/a[@href='?route=";
	public static String submenuFirstPart_xpath = "(//a[contains(text(),'";
	public static String submenuLastPart_xpath = "')])";//[1]";
	public static String helpDeskSectionHeader = "(//h3[@class='box-title'])[2]";
	public static String listActiveHelpDeskTickets_xpath = "//div/ul/li/span[@class='text']/a";
	public static String listAssetByCat_xpath = "//div[@id='js-legend']/ul/li";
	public static String buttonViewMore_xpath = "//a[contains(text(), 'VIEW MORE')]";
	
	public static String innerMenuList_xpath = "html/body/div[2]/aside[1]/section/ul/li[3]/ul/li[4]/ul/li";
	public static String subMenuList_xpath = "html/body/div[2]/aside[1]/section/ul/li[3]/ul/li";
	
	public static String cuntryList_xpath = "//ul[@id='select2-country-results']/li";
	public static String arrowIcon_xpath = "//span[@class='select2-selection__arrow']";
	public static String innerMenu1_xpath = "//a[contains(text(),'";
	public static String innerMenu2_xpath = "')]";
	public static String leftSidePanel_xpath = "//ul[@class='sidebar-menu']/li/a/span";
	public static String leftSideSubMenuPanel_xpath = "//ul[@class='treeview-menu menu-open']/li/a";
	public static String leftSideInnerMenuPanel1_xpath = "//li/a[@href][contains(text(), '";
	public static String optionList_xpath = "//li[contains(text(),'";
	public static String optionList1_xpath = "')]";
	public static String dropdown = "(//span[@role='combobox'])";
	public static String removeChoice_xpath = "(//span[@class='select2-selection__choice__remove'])";
	public static String leftSideSubMenuPanel1_xpath = "//a/span[contains(text(), '";
	public static String summarySection_xpath  = "//div[@class='box-md-2 col-xs-6']//div/p";
	public static String userName_xpath = "//span[@class='hidden-xs']";
	public static String buttonProfile_xpath = "//a[text()='Profile']";
	public static String textFieldChangePassword_xpath = "//input[@name='password']";
	public static String textFieldConfirmPassword_xpath = "//input[@name='confirmpassword']";
	public static String buttonSave_xapth = "//button[contains(text(), 'Save')]";
	
	
	public static String loginPage_xpath = "//body[@class='login-page']";
	public static String altText_xpath = "//img[@alt='aszet']";
	public static String beforeLoginLogo_xpath  = "//div/img[@class='fixed-logo']";
	public static String afterLoginLogo_xpath  = "//a[@class='logo']/img";
	public static String logoSection_xpath = "//a[@class='logo']";//header[@class='main-header']/a;
	public static String loadSpinner_xpath = "//img[@alt='loader']";
	public static String serviceRequest_xpath = "(//li[@class='treeview']//span)[1]";
	
	
	
	


}
