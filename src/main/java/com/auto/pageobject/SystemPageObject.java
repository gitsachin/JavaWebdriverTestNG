package com.auto.pageobject;

import org.apache.commons.lang.RandomStringUtils;

import com.auto.base.BasePage;

public class SystemPageObject {
	public static String clienName = "clientAdmin"+RandomStringUtils.randomAlphabetic(6);
	public static String clienwithNumberName = BasePage.autogenerateNumber(10);
	public static String userName = "QaUser_"+RandomStringUtils.randomAlphabetic(6);
	public static String domainName = "Test"+BasePage.autogenerateNumber(3);
	public static String nameOfClient = "QaClient"+RandomStringUtils.randomAlphabetic(6);
	public static String name = "QaUser_"+BasePage.currentDate();
	public static String email = "qaUser_"+BasePage.currentDate()+"@mailinator.com";
	public static String password = "Testing@123a";//+BasePage.autogenerateNumber(7)+"a";
	public static String assetTag = "QaAssetTag_"+RandomStringUtils.randomAlphabetic(6);
	public static String licenseTag = "QaLicenseTag_"+RandomStringUtils.randomAlphabetic(6);
	public static String user1_Email = "qaUser_1"+"@mailinator.com";
	public static String user2_Email = "qaUser_2"+"@mailinator.com";
	public static String staffName = "QaStaff_"+BasePage.autogenerateNumber(6);
	public static String staff_Email = "qaStaff_"+BasePage.currentDate()+"@mailinator.com";
	public static String admienEmail = "qaAdmin_"+BasePage.currentDate()+"@mailinator.com";
	public static String adminPassword = "Testing@123a";
	
	public static String message = "Unauthorized Access";
	public static String authenticationMessage = "Authentication Failed!";
	public static String alertCss = ".alert.alert-danger.alert-auto";
	public static String selectedClient_xpath = "//ul[@class='select2-selection__rendered']/li";
	public static String listClient_xpath = "//span[@class='select2-results']/ul/li";
	public static String buttonAddUser_xpath = "//button[contains(text(),'Add user account')]";
	public static String arrowIconDepartmentField = "//span[@class= 'select2-selection__arrow']";
	public static String searchDepartmentField = "//input[@class= 'select2-search__field']";
	public static String listLocation_xpath = "//ul[@id='select2-locationid-results']/li";
	public static String roleSearchField_xpath = "//input[@type='search']";
	public static String permissionSection1_xpath = "//div/h4[contains(text(), '";
	public static String permissionSection2_xpath  = "')]";
	public static String buttonaPermission1_xapth = "(//div[@class='col-md-3'])[";
	public static String buttonaPermission2_xapth = "]/div";
	public static String textFieldsName_xpath = "//input[@id='name']";
	public static String textFieldsEmail_xpath = "//input[@id='email']";
	public static String textFieldsPassword_xpath = "//input[@id='password']";
	public static String buttonCreate_xpath = "//button[contains(text(), 'Create')]";
	public static String headerText_xpath = "//h1[@class='pull-left']"; 
	public static String tableHeader_xpath = "//table/thead/tr/th";
	public static String buttonNewClient_xpath = "//a[contains(text(),'NEW CLIENT')]";
	public static String textFieldsClientName_xpath = "//input[@id='clientName']";
	public static String textFieldsClientEmail_xpath = "//input[@id='clientEmail']";
	public static String errorMessage_xpath = "//div/span[@id='upload_error_message']";
	public static String errorMessage = "Client with same name already exist";
	public static String errorMessageForEmail = "Client with same email already exist";
	public static String buttonBatchUploadWithOutIndex_xpath = "(//button[@class='dropdown-item modal-button'])[2]";
	public static String addStaffOption_xpath = "//button[@data-modal='staff/add']";
	public static String addUserOption_xpath = "//button[@data-modal='users/add']";
	public static String addRoleOption_css = ".btn.btn-primary.btn-sm.btn-flat.dropdown-item";
	public static String buttonAddClient_xpath = "//button[@data-modal='clients/add']";
	public static String textFieldsAssetTag_xpath = "//input[@id='asset_tag_prefix']";
	public static String textFieldsLicensetag_xpath = "//input[@id='license_tag_prefix']";
	public static String textFieldsAdminName_xpath = "//input[@id='clientName']";
	public static String textFieldsAdminEmail_xpath = "//input[@id='clientEmail']";
	public static String textFieldsAdminPass_xpath = "//input[@id='password']";
	public static String textFieldRePass_xpath  = "//input[@id='confirmpassword']";
	public static String buttonClientCreate_xpath = "//button[@id='create_client_button']";
	public static String sleclectClientChoice_xpath = "//li[@class='select2-selection__choice']";
	public static String tabDeleteUsers_xpath = "//a[@href='#tab-deletedUsers']";
	public static String passwordResetAlert_css = ".alert.alert-success.success-message";
	public static String passwordResetAlert = "User is successfully forced to reset password!!!";
	public static String tabClientDetails_xpath = "//ul[@class='nav nav-tabs']/li/a";
	
	
	
	//public static String gridSearchId = "@id='dataTablesFull'";
	public static String roleGridSearchId = "@id='dataTablesRole'";
	public static String gridSearchId1= "@id='dataTablesFull1'";
	public static String searchTableId = "@id='searchTable'";
	public static String deleteSearchTableId = "@id='deletedUserSearchTable'";
	public static String tableId = "@id='originalTable'";
	public static String userGridHeader_Id = "@id='userDataTablesFull'";
	public static String roleClientSearchId = "@id='clientSearchTable'";
	
	public static String assetTabId = "go-assets";
	public static String licensesTabId = "go-licenses";
	public static String ticketsTabId = "go-tasks";
	public static String userTabId = "go-users";
	public static String IssuesTabId = "go-issues";
	public static String filesTabId = "go-licenses";
	public static String credentialTabId = "go-credentials";
	public static String projectsTabId = "go-projects";
	public static String assetCsvXpath = "(//a[@id='asset-csv']/span)";
	public static String assetExcelXpath = "(//a[@id='asset-excel']/span)";
	public static String licensesCsvXpath = "(//a[@id='license-csv']/span)";
	public static String licensesExcelXpath = "(//a[@id='license-excel']/span)";
	public static String projectsCsvXpath = "(//a[@id='projects-csv']/span)";
	public static String projectsExcelXpath = "(//a[@id='projects-excel']/span)";
	public static String issuesCsvXpath = "(//a[@id='issues-csv']/span)";
	public static String issuesExcelXpath = "(//a[@id='issues-excel']/span)";
	public static String ticketsCsvXpath = "(//a[@id='ticket-csv']/span)";
	public static String ticketsExcelXpath = "(//a[@id='ticket-excel']/span)";
	public static String credentialsCsvXpath = "(//a[@id='credentials-csv']/span)";
	public static String credentialsExcelXpath = "(//a[@id='credentials-excel']/span)";
	public static String usersCsvXpath = "(//a[@id='users-csv']/span)";
	public static String usersExcelXpath = "(//a[@id='users-excel']/span)";
	public static String button_Cancel = "//button[@id='client_dismiss_button']";
	
	public static String error = "This is the only client admin for this client. Please create another admin before deleting.";
	public static String error_css = ".alert.alert-danger.alert-auto";
	
	
	public static String ccRecipient_xpath = "//input[@value='viewCCRecipientField']";
	public static String timeSpent_xpath = "//input[@value='viewTimeSpentField']";
	public static String refreshButton_Xpath = "(//button[@type='button']/i)";
	public static String confirmPassword_Xpath = "//label[@for='confirmpassword']";
	public static String aleartError_Xpath = "(//div[@class='alert alert-error'])[2]";
	public static String paginationTabButton_xpath = "((//ul[@class='pagination'])[2]//li/a)";
	public static String paginationTab_xpath = "(//ul[@class='pagination'])[1]";
	public static String paginationInfo_xpath = "(//div[@class='dataTables_info'])[1]";
	public static String clientTab_xpath = "//div/ul[@class='nav nav-tabs']/li";
	
	
		

}
