package com.auto.pageobject;

import java.util.Stack;

import com.auto.base.BasePage;

public class InventoryPageObject {
	
	public static String assetName = "Assets_"+BasePage.currentDate();
	public static String sucessMessage = "Item has been added successfully!";
	public static String deleteSucessMessage = "Item has been deleted successfully!";
	public static String addedSucessMessage = "Item has been saved successfully!";
	public static String batchUploadFormatDownloadLinkText = "Click here to download batch upload format";
	public static String sizeUploadNoteMessage = "Note: Max upload size is 10k records.";
	public static String fileAttachedMessage = "File ready to upload";
	public static String uploadSuccessMessage = "Batch sucessfully uploaded";
	public static String uploadSuccessMessages = "Csv sucessfully uploaded";
	public static String searchName = "Laptop";
	public static String uploadErrorMessage = "File is not valid.Please upload excel file only";
	public static String uploadErrorMessage_xpath = "//span[@id='upload_error_message']";
	public static String uploadSuccessMessage_xpath = "//span[@id='upload_success_message']";
	
	public static String boxTitle_css = "h3.box-title";
	public static String popTitle_css = "h4.modal-title";
	public static String batchFormatLink_css = ".upload_btn_box>div>p>a";
	public static String sizeNote_css = ".help-block";
	public static String labelText_css = "label[for='";
	public static String labelText1_css = "']";
	public static String uploadIcon_css = "i.fa-upload";
	public static String uploadFileButton_xpath ="//input[@type='file']";
	public static String viewFileName_css = "#viewFileName";
	public static String uploadSuccessMessage_css = "#upload_success_message";
	public static String tableRow_xpath = "//table/tbody/tr";
	public static String tableInfo_xpath = "//div[@class='info']";
	public static String fieldSerialNo_xpath = "//input[@id='serial']";
	public static String buttonSave_css = "#azt_asset_edit_btn";
	public static String existingFilePath = System.getProperty("user.dir")+"//src//main//resources//files//";
	public static String labelModel_xpath = "(//label[contains(text(),'";
	
	public static String licenseName = "Qa License_"+BasePage.currentDate();
	
	public static String assetsTableHeader_xpath = "//table[@id='assetTable ']/thead";
	public static String licenseTableHeader_xpath = "//table[@id='licenseTable']/thead";
	public static String filterColumnName_xpath = "/a[@class='textSpan']";
	public static String filterArrowIcon_xpath = "/span[@class='filterSpan']";
	public static String filterArrowIcons_xpath = "/span[@class='filterSpan hideFilter']";
	public static String filterArrowIcon_css = ".filterSpan.hideFilter";
	public static String columnName1stPart_xpath = "//div[@id='";
	public static String columnName2ndPart_xpath = "FilterBox']";
	
	public static String editAssetTab = "//div[@class='av-tabs-custom']/ul/li";
	public static String editAssetTab1stPart_xpath = "//ul/li/a[contains(text(), '";
	public static String editAssetTab2ndPart_xpath = "')]";
	public static String licenseHeaderText_css = ".content-header>h1";
	public static String buttonSave_xpath = "//button[contains(text(), 'Save')]";
	public static String buttonCancel_xpath = "//button[contains(text(), 'Cancel')]";
	public static String buttonNewCateAttr_xpath = "//button[@id='newAttr']";
	public static String textFieldLabel_xpath = "//input[@id='attributeLabels']";
	public static String buttonAddCat_xpath = "//button[@id='addAttr']";
	public static String textFieldAttrName_xpath = "//input[@id='attributeName']";
	public static String attrName = "Qa Cate Attributes "+BasePage.currentDate();
	public static String selectCoptTo_xpath = "//select[@id='copyToCat']";
	public static String buttonCopyCat_xpath = "//button[@id='copyAttr']";
	
	public static String buttonApplyFilter_xpath = "//div[@id='modelFilterBox']/div[2]/button[2]";
	public static String buttonCancelFilter_xpath = "//div[@id='modelFilterBox']/div[2]/button[1]";
	public static String textFieldSearch_xpath = "//label[@class='pull-right']/input";
	public static String buttonSearch_xpath = "//label[@class='pull-right']/button";
	public static String buttonCSV_xpath = "//button[@id='asset-csv']";
	public static String buttonExcel_xpath = "//button[@id='asset-excel']";
	public static String linkButtonExcel_xpath = "//a[@id='asset-excel']";
	public static String urlAssetAfterClickCSVExcelButton = "https://app.aszet.com/?route=inventory/assets";
	public static String gridHeader_xpath = "//div[@class='box-header']/h3";
	public static String editPageTab_xpath = "//div[@class='row']/div/div/ul/li";
	public static String buttonNewRelatedAsset = "//a[contains(text(), 'NEW RELATED ASSET')]";
	public static String arrowIconDepartmentField = "//span[@class='select2-selection__arrow']";
	public static String searchDepartmentField = "//input[@class= 'select2-search__field']";
	public static String buttonRelate_xpath = "//button[contains(text(), 'Relate')]";
	public static String popupName_xpath1 = "//h4[contains(text(), '";
	public static String popupName_xpath2 = "')]";
	public static String headerText_xpath1 = "//h3[contains(text(), '";
	public static String headerText_xpath2 = "')]";
	public static String pageheaderText_xpath1 = "//h1[contains(text(), '";
	public static String pageheaderText_xpath2 = "')]";
	public static String iconPinForAssignLicense_xpath = "//div[@class='btn-group pull-right']/a";
	public static  String contentText_xpath = "//div[@class='modal-body']/h5";
	public static String contentText = "Please select from the existing licenses below. If you do not see your license, please go to the menu Inventory->Licenses and add a new license. Then come back to the asset to assign.";
	public static String buttonAssign_xpath = "//button[contains(text(), 'Assign')]";
	public static String tabLicense_xpath = "//a[@data-toggle='tab'][contains(text(), 'License')]";
	public static String textFieldExpirationDate_xapth = "//input[@id='license_expire_date']";
	public static String enterDate = BasePage.currentOnlyDate();
	public static String label_xapth = "//label[@for]";
	public static String textFieldName_xpath = "//input[@id='name']";
	public static String model = "Qa Model_"+BasePage.currentDate();
	public static String buttonCreate_xpath = "//button[@type='submit']";
	//public static String buttonBatchUpload_xpath = "//button[contains(text(), 'Batch upload')]";
	public static String buttonBatchUploadWithOutIndex_xpath = "(//button[@class='dropdown-item modal-button'])";
	public static String actiondropdown_xpath = "//a[@class='btn btn-primary btn-sm btn-flat dropdown-item']";
	public static String actionDropdown_Xpath = "(//div[@class='dropdown-menu dropdown-menu-right action-dropdown-menu']/div/button)";
	public static String buttonBatchUploadWithIndex_xpath = "//button[@class='dropdown-item modal-button'][2]";
	public static String helpText_xpath = "//p[@class='help-block']";
	public static String buttonUpload_xpath = "//button[contains(text(),'Upload')]";
	public static String buttonClose_xpath = "//button[contains(text(),'Close')]";
	public static String buttonCreateInpopup_xpath = "//button[contains(text(), 'Create')]";
	public static String buttonNewFields_xpath = "//button[contains(text(), 'New field')]";
	public static String locationPageClientField_xapath = "//span[@id='select2-clientFilter-container']";
	public static String listClient_css = ".select2-dropdown.select2-dropdown--below";
	
	public static String locationName = "Location_"+BasePage.currentDate();
	public static String subLocationName = "Sub Location_"+BasePage.currentDate();
	public static String rootLocation = "Root_"+BasePage.currentDate();
	public static String textFieldLocationName_xpath = "//input[@name='Locations[name]']";
	public static String textFieldLocationType_xpath = "//input[@id='location-type']";
	public static String textFieldLocationCity_xpath = "//input[@id='city']";
	public static String textFieldLocationZip_xpath = "//input[@id='zip']";
	public static String textFieldLocationLat_xpath = "//input[@id='latitude']";
	public static String textFieldLocationLong_xpath = "//input[@id='longitude']";
	public static String linklSetLatLog_xpath = "//a[@id='setLatLong']";
	public static String mapPin_xpath = "//map/area[@log='miw']";
	
	public static String popupNameAssetCat_xpath = "Upload Batch Asset Categories";
	public static String popupNameLicenseCat_xpath = "Upload Batch License Categories";
	public static String popupNameStatusLabels_xpath = "Upload Batch Status Labels";
	public static String popupNameManufacturers_xpath = "Upload Batch Manufacturers";
	public static String popupNameAssetModel_xpath = "Upload Batch Asset Models";
	public static String popupNameSuppliers_xpath = "Upload Batch Suppliers";
	public static String popupNameLocation_xpath = "Upload Batch Location";
	
	public static String NestedArrowIcon_xpath = "//div[@id='originalTable']/div/ul/li/a";
	public static String circleIcon_xpath = "//div[@id='originalTable']/div/ul/li/span";
	public static String nestedCircleIcon_xpath = "//div[@class='location_tabs']/div/ul/li/span";
	public static String tabPagination_xpath = "(//ul[@class='pagination'])";
	public static String infoPagination_xpath = "//div[@class='info']";
	public static String infoAllPagination_xpath = "//div[@class='dataTables_info']";
	public static String pageNo1_xpath = "//ul[@class='pagination'][1]/li/a[contains(text(), '";
	public static String pageNo2_xpath = "')]";
	public static String blankGrid_xpath = "//div[@id='searchTable']/ul/li";
	public static String noRecordGrid_xpath = "//table/tbody[@id='searchTable']/tr/td";
	public static String blankGridtext = "location not found!";
	public static String blankRecords = "No Records Found";
	public static String buttonActionDropDown_xpath = "//button[@id='dropdownMenu2']";
	public static String buttonNewLocation = "//button[contains(text(), 'New location')]";
	
	public static String loader = "//img[@alt='loader']";
	public static String tabLocalization_xpath = "//a[contains(text(), 'Localization')]";
	
	public static String searchTableId = "@id='searchTable'";
	public static String modelFilterId = "@id='modelFilterBox'";
	public static String originalGridId = "@id='originalTable'";
	public static String userTableId = "@id='originalUserTable'";
	public static String printAssetId = "'dataTablesFull'";
	public static String categoriesId = "@id='categoryTable'";
	public static String licenseTable = "@id='licenseTable'";
	public static String assettable = "@id='assetTable '";
	public static String assetReportTable = "@id='assetTable'";
	public static String assetDataTable    = "@id='assetDataTablesFull'";
	public static String assetCatTable    = "@id='assetCategoriesFullTable'";
	public static String locationTableId = "@id='searchTable'";
	public static String locationChildTable_class = "@class='location_tabs childs'";
	public static String urlLocationpage= "https://app.aszet.com/?route=inventory/attributes/locations";
	public static String urlActiveLocationpageOfPagination = "https://app.aszet.com/?route=inventory/attributes/locations&offset=10&page=";
	public static String selectClient_xpath = "//select[@id='dd_client']";
	public static String clientIdSelectField_xpath = "//select[@name='clientid']";
	public static String selectClientField_xpath = "//label[text()='Select Client']";
	public static String selectedClient = "QATEST";
	
	public static String filePath = System.getProperty("user.dir")+"//src//main//resources//files//";
	public static String excelOption_xpath = "//button[@data-filetype='excel']";
	public static String csvOption_xpath = "//button[@data-filetype='csv']";
	public static String filterBox1_xpath = "//div[@id='";
	public static String filterBox2_xpath = "FilterBox']";
	
	public static String iconSort = "@class='fa fa-sort'";
	public static String ascSort = "@class='fa fa-sort-asc'";
	public static String descSort = "@class='fa fa-sort-desc'";
	
	public static String assetCatName = "All Client Asset Cat_"+BasePage.currentDate();
	public static String licenseCat = "All Client License Cat_"+BasePage.currentDate();
	public static String statusLab = "All Client Status Labels_"+BasePage.currentDate();
	public static String manufacturer = "All Client Manufacturer_"+BasePage.currentDate();
	public static String assetModel = "All Client Asset Model_"+BasePage.currentDate();
	public static String color = "#019d9b";
	
	public static String buttonNewAssetcat_xpath = "//button[contains(text(), 'New asset category')]";
	public static String buttonNewLicensecat_xpath = "//button[@data-modal='licensecategories/add']";
	public static String buttonNewStatusLab_xpath = "//button[@data-modal='labels/add']";
	public static String buttonNewManufact_xpath = "//button[@data-modal='manufacturers/add']";
	public static String buttonNewModel_xpath = "//button[contains(text(), 'New model')]";
	public static String inputFieldColr_xpath = "//input[@id='color']";
	public static String buttonSaveCat_xpath = "//button[@id='saveCat']";
	public static String breadCrumbAssCat_xpath = "//a[text()='Asset Categories']";
	public static String buttonCreateCatgories_xpath = "//button[@class='btn btn-primary']";
	public static String breadCrumbStatusLabels_xpath = "//a[text()='Status Labels']";
	public static String breadCrumbManufacturer_xpath = "//a[text()='Manufacturers']";
	public static String selectmanufacturer_xpath = "//li[text()='QaManufacturer_DND']";
	public static String breadCrumbAssModel_xpath = "//a[text()='Asset Models']";
	public static String uploadDescrip_xpath = "//div[@class='filename_discripient']";
	public static String uploadDescrip = "There are some invalid records. Please click here to download";
	public static String textFieldSerialNo_xpath = "//input[@id='serial']";
	public static String buttonMoreFilter_xpath = "//button[contains(text(), 'More Filters')]";
	public static String availableFilter_xpath = "//div[@class='moreFilter-div']/label";
	public static String textFieldFilterSearch_xpath = "//div[@class='moreFilter-div']/span/input";
	public static String appliedFileterSection_xpath = "//div[@class='selectedFilter-Span']";
	public static String clientfield_css = "#select2-clientid-container";
	public static String clientFiled_xapth = "//span[@id='select2-clientid-container']";
	public static String assignToFiled_xapth = "//span[@id='select2-adminid-container']";
	public static String statusFiled_xapth = "//span[@id='select2-statusid-container']";
	public static String assignAsset_xpath = "//a[text()='Assign Asset']";
	
	
	public static String assignToFiledSuggestionList_xapth = "//ul[@id='select2-adminid-results']/li";
	public static String statusResult_xpath = "//ul[@id='select2-statusid-results']/li";
	public static String fieldsReadOnly_xpath = "//input[@readonly]";
	public static String ticketTab_xpath = "//a[@href='#tab-tickets']";
	public static String ticketSearch_css= ".form-control.input-sm";
	public static String parentLocField_xapth = "//span[@id='select2-dd_parent_loc_edit-container']";
	public static String locationScreenArrowIcon_xpath = "//span[@class='select2-selection__rendered']";
	public static String textFieldStartDate_xpath = "//input[@id='startDatew']";
	public static String textFieldEndDate_xpath = "//input[@id='endDatew']";
	public static String statusColumn_xpath = "//span[@class='filterSpan']";
	public static String pinIconForAssignContract_xpath = "//a[@data-toggle='tooltip' and @data-original-title='Assign Contract']/i";
	public static String subMenuContract_xpath = "(//a[contains(text(), 'Contract')])[2]";
	public static String buttonNewContract_xpath = "(//a[contains(text(),'New Contract')])[2]";
	public static String clientDropdown_xpath = "//span//li[@role='treeitem']";
	
}
