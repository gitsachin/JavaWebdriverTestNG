package com.auto.pageobject;

import com.auto.base.BasePage;

public class HelpDeskTicketsPageObject {
	
	public static String ticketName = "Ticket_"+BasePage.currentDate();
	public static String assetTag = "QA-673085";
	public static String mailName = "Aszet";
	
	public static String ticketRuleName = "TicketRule_"+BasePage.currentDate();
	
	public static String saveButton_xapth = "//button[@id='mailBtn']";
	
	public static String assetName_css = ".ui-menu-item";
	public static String assetName_xpath = "//input[@id='assetInput']";
	public static String assetField_xpath = "//input[@id='assetName']";
	public static String assertdropdown_xpath="//ul[@id='ui-id-1']";
	public static String mailName_css = "div[title='FROM']";
	public static String mailCheckbox_css = "i.fa.fa-square-o.fa-lg";
	//public static String departName = "QA_Department"+BasePage.currentDate();
	public static String ticketFieldName = "QA_TicketFiled"+BasePage.currentDate(); 
	public static String ticketMessage = "My Message "+BasePage.autogenerateNumber(5); 
	public static String updatetTicketMessage = "Update Message "+BasePage.autogenerateNumber(5); 
	
	public static String ticketFieldName_xpath = "//input[@name='name']";
	public static String buttonNewRole = "//button[@id='newRole']";
	public static String selectRole_xpath = "//select[@id='role']";
	public static String buttonAddRole_xpath = "//button[@id='addRole']";
	public static String saveTicketField_xpath = "//button[@id='saveTicketField']";
	public static String dropdownPermission_xpath = "//select[@id='permission']";
	public static String noOfItemInPermissionList_xpath = "//select[@id='permission']/option";
	

	public static String departmentGrid = "(//div[@class='box-body'])[1]/div/table/tbody/tr";
	public static String textFieldName_xpath = "//input[@id='name']";
	public static String arrowIconDepartmentField = "//span[@class= 'select2-selection__arrow']";
	public static String searchDepartmentField = "//input[@class= 'select2-search__field']";
	                                            
	public static String containerDepartmentField = "//*[@id='select2-departmentid-container']";
	public static String textFieldAsset_xpath = "//input[@id='assetInput']";
	public static String listAsset_xpath = "//ul[@id= 'ui-id-1']";
	public static String listStatus_xpath  = "//ul[@id='select2-status-results']/li";
	public static String status_xpath = "(//div[@class='filterBy-box'])[7]/label";
	public static String notFound_xpath = "//ul/li[contains(text(), 'No Asset Found')]";
	public static String listItem_xpath = "//ul[@id= 'ui-id-1']/li[1]";
	public static String buttonCreate_xpath = "//button[@id='create_ticket']";
	public static String textFieldSubject_xpath = "//input[@id='subject']";
	public static String buttonNewTicket = "//button[contains(text(), '	New ticket')]";
	public static String locationid = "@id='select2-locationid-results'";
	public static String locationFieldId = "select2-locationid-container";
	//public static String ticketXpath = "(//div[@id='msgpane']/div/div[@class='ng-binding'])[1]";
	public static String adminTicketXpath = "//li//div[contains(text(), 'New Support Ticket QATEST')]";
	public static String userTicketXpath = "//li//div[contains(text(), 'Ticket')]";
	public static String typeId = "type";
	public static String suggestionList_xpath = "//li[@tabindex='-1']";
	public static String fieldCC_xpath = "//label[text()='CC Recipients']";
	public static String fieldTime_xpath = "//label[text()='Time Spent (minutes)']";
	public static String buttonMoreFilter_xpath = "//button[contains(text(), 'More Filters')]";
	public static String availableFilter_xpath = "//div[@class='moreFilter-div']/label";
	public static String textFieldFilterSearch_xpath = "//div[@class='moreFilter-div']/span/input";
	public static String appliedFileterSection_xpath = "//div[@class='selectedFilter-Span']";
	public static String checkBoxLebelTicketField_xpath = "//div[@class='checkbox']/label";
	public static String checkBoxTicketField_xpath = "//input[@name='fieldRequired']";
	public static String messageField_xpath = "//div[@class='note-editable panel-body']";
	public static String replymMessageField_xpath = "(//div[@class='note-editable panel-body'])[1]";
	public static String buttonReply_xpath = "(//button[contains(text(),'Reply')])[2]";
	public static String userChoice_xpath = "//li[@class='select2-selection__choice']";
	
	public static String buttonCancel_xpath = "(//button[@data-dismiss='modal'])[2]";
	public static String buttonUpdate_xpath = "//button[@id='updateTicketField']";
	public static String buttonSave_xpath = "(//button[contains(text(), 'Save')])[1]";
	public static String fieldsReadOnly_xpath = "//input[@readonly]";
	public static String tooltip_xpath = "//div[@class='modal-content']//button[@data-toggle='tooltip']"; 
	public static String butonEdit_xpath = "(//button[@type='button'])[2]";
	public static String fieldsTickets_xapth = "(//span[@class='select2-selection__rendered'])";
	public static String serialAssetOptions_css = ".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content>li";
	public static String buttonTicketExcel_xpath = "//button[@id='ticket-excel']";
	public static String buttonTicketCsv_xpath = "//button[@id='ticket-csv']";
	public static String textFieldSearch_xpath = "//label[@class='pull-right']/input";
	public static String textFieldClientTicketSearch_xpath = "(//label[@class='pull-right']/input)[5]";
	public static String textFieldClientTicketGoButton_xpath = "(//label[@class='pull-right']/button)[5]";
	public static String applyButton_xpath = "(//button[@class='btn btn-flat btn-primary'])[8]";
	public static String fieldAssignTo_xapth = "//span[@id='select2-adminid-container']";
	public static String fieldReturnLocation_xapth = "//span[@id='select2-locationid-container']";
	public static String statusOnTickDetails_xpath = "//ul[@id='ticketDetailsTable']/li[1]/div[2]/span";
	public static String closeTicket_css = ".fa-close";
	public static String timelineHeader_css = ".timeline-header";
	public static String  buttonDone_xpath = "(//button[contains(text(),'Done')])";
	public static String  buttonNextTicket_xpath = "(//button[contains(text(),'Next Ticket')])";
	public static String pathA1_xpath = "//a[contains(text(),'";
	public static String pathA2_xpath = "')]";
	public static String buttonNewDepartment_xpath = "//a[contains(text(), 'NEW DEPARTMENT')]";
	public static String tabTicketSetting_css = ".nav.nav-tabs>li";
	public static String buttonTicketSetting_xpath = "(//a[contains(text(), 'Settings')])[2]";
	public static String buttonNextInBasicTab_xpath = "//button[@id='btnbasic']";
	public static String buttonNextInConditionTab_xpath = "//button[@id='btncondition']";
	public static String buttonNextInActionTab_xpath = "//button[@id='btnaction']";
	public static String buttonCreateRules_xpath = "//button[@class='btn btn-primary']";
	public static String buttonNextStep1_xpath  = "//button[@id='next_step']";
	public static String buttonNextStep2_xpath  = "//button[@id='next_step2']";
	public static String createTicketPopup1_xpath  = "//div[@id='step1']";
	public static String createTicketPopup2_xpath  = "//div[@id='step2']";
	public static String createTicketPopup3_xpath  = "//div[@id='step3']";
	public static String buttonNewRule_xpath = "//Button[contains(text(),'New Rule')]";
	public static String tabTicketPage_xpath = "//div[@class='nav-tabs-custom']/ul/li";
	public static String tabTicketPage1_xpath = "//div[@class='nav-tabs-custom']/ul/li/a[contains(text(), '";
	public static String iconComment_xpath = "//a[@title='New Comment']";
	public static String commentField_xpath  = "(//div[@class='note-editing-area'])[3]";
	public static String addedComment_xpath = "(//div[@id='tab-comments']/ul/li/div/div[@class='timeline-body'])";
	public static String posetdCommentButtons_xpath = "(//div[@id='tab-comments']/ul/li/div/div[@class='timeline-footer'])";
	
	
	public static String searchTableId = "@id='searchTable'";
	public static String ticketDetailTable = "@id='ticketDetailsTable'";
	public static String ticketTableHeader_id = "@id='ticketTablear'";
	public static String searchClientTicketTableId = "@id='originalTicketTable'";
	

}
