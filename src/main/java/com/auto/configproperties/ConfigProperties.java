package com.auto.configproperties;

public class ConfigProperties {


	// Select crossbrowser (local, crossbrowser)
	public static String crossBrowser = "crossbrowser";
	
	// Select os: linux, windows, mac, android
	public static String OS = "windows";

	// Environment variables for web:
	public static String site_url = "https://app.aszet.com/";
	public static String logout_url = "https://app.aszet.com?route=signout";
	public static String signin_url = "https://app.aszet.com/?route=signin";
	public static String dashBoardpage_url = "https://app.aszet.com/?route=dashboard";
	public static String signOut_Url = "https://app.aszet.com/?route=signin#stop";
	public static String forceRestPass = "https://app.aszet.com/?route=forcepasswordreset";
	
	public static String error404_url = "https://app.aszet.com/?route=404";
	//public static String error404_url = "https://app2.aszet.com/?route=404";
	public static String tested_url = "https://app.aszet.com/qatest";
	
	
	//browserStack credential
		public static final String USERNAME = "mark%40infoco.com";
		public static final String AUTOMATE_KEY = "ub78717d649def24";
	

	// Select browser (firefox,chrome,IE,Safari,edge) for web for mobile iPhone,iPad
	public static String browser_name = "chrome";
	
	public static String device_name = "Nexus 6";
	public static String device_version = "5.0";
	
	
	//Select resulution (Windows: 1366x768/ mac: 1600x1200)
	public static String screenResulution = "1366x768";
	
	
	//Select browser version chrome(63)
		public static String browser_version ="63";

		//Select operating system version for windows like (7,8,8.1,10) and for mac like (Yosemite/Mavericks/Mountain Lion)
		// Select OS: (Windows /linux/ mac)
		public static String platform = "Windows 7 64-Bit";
	
	
   //Login credential client admin
	public static String email = "satest@mailinator.com";
	public static String clientAdmin = "saclientadmin01@mailinator.com";
	public static String pass = "Testing@123a";  

	
	
	public static String email01 = "satest01@mailinator.com";
	public static String pass01 = "Testing@123b";
	
	public static String email03 = "clientuseradmin@mailinato.com";

	//Login credential super admin
	public static String superAdmin_email = "jason@bavasoft.com";
	public static String superAdmin_pass = "aszet";	
	
	public static String superAdmin_email01 = "satest02@mailinator.com";
	public static String superAdmin_pass01 = "Testing@123c";
	
	public static String stuffUser_email = "stuffuser01@mailinator.com";
	public static String stuffUser_pass = "Testing@123a";
	
	//client user 
	
	public static String clientUser_email = "clientuser01@mailinator.com";
	public static String clientUser_pass = "Testing@123a";	
	
	
	//New Account For testing
	//user Name = "SA_TEST"
	//Emil Id = satest004@mailinator.com/satest06@mailinator.com
	//Password = Testing@123a
	
	
	//Excel Files
	
	public static String assetBatchFile = "uploadAsset.xlsx";
	public static String assetCsvFile = "Sample.csv";
	public static String wrongFileToUpload = "SampleVideo.mp4";
	public static String licenseBatchFile = "licenseBatchUploadSampleFile.xlsx";
	
	public static String assetCatBatchFile = "assetCategoriesBatchUploadSampleFile.xlsx";
	public static String licenseCatBatchFile = "licenseCategoriesBatchUploadSampleFile.xlsx";
	public static String statusLabelsBatchFile = "statusLabelBatchUploadSampleFile.xlsx";
	public static String manufacturesBatchFile = "manufacturersBatchUploadSampleFile.xlsx";
	public static String assetModelsBatchFile = "assetModelsBatchUploadSampleFile.xlsx"; 
	public static String suppliersBatchFile = "suppliersBatchUploadSampleFile.xlsx";
	public static String userBatchFile = "userBatch.xlsx";
	public static String fileUpload_AZ_108 = "BatchUpload AZ_128.xlsx";
	public static String locationBatch = "locationBatchUploadFormat.xlsx";
	public static String Image = "avatar.jpg";
	
	public static String awaitingTicket = "QaAwaitingTicket_DND";
	public static String ticket = "QaTicketForAutomation";
	public static String ticketFields = "QaTicketFieldsForAutomation";
	public static String asset = "QaAssetForAutomation";
	public static String license = "QaLicensesForAutomation_DND";
	public static String location = "QaLocationForAutomation_DND";
	public static String assetCat = "QaAssetCategoryForAutomation_DND";
	public static String licenseCat = "QaLicenseCategoryForAutomation_DND";
	public static String statusLabels = "QaStatusLabelsForAutomation_DND";
	public static String activeIssue = "QaIssueForAutomation";
	public static String issueCat = "IssueCatForAutomation_DND";
	public static String projects = "QaProjectForAutomation";
	public static String plans = "QaPlanForAutomation_DND";
	public static String features = "QaFeatureForAutomation_DND";
	public static String clientPlan = "QATEST";
	public static String user = "User1_DND";
	public static String staff = "StaffUser_DND";
	public static String preDefReply = "QaPreDefReply_DND";
	public static String manufacturer = "QaManufacturer_DND";
	public static String role = "QaRolesForAutomation_DND";
	public static String departName = "QaDepartmentForAutomationTest";
	public static String assetModel = "QaAssetModelForAutomation_DND";
	public static String supplier = "QaSupplierForAutomation_DND";
	public static String category = "Category1_DND";
	public static String assetModels = "QaAssetModelForAutomation_DND";
	public static String assetSerialNo = "SerialNumber12345";
	public static String ticketRules = "QaRuleForAutomationTest_DND";
	
	
	
}
