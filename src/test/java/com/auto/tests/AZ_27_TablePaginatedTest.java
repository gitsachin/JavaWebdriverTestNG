package com.auto.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;


public class AZ_27_TablePaginatedTest extends TestCore{
	
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	@Test
	public void az_27_TablePaginated(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)),DashboardPageObject.superAdminName);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/assets");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/assets",ILogLevel.TEST);
		int assetGridSize  = assetPage.returnOriginalGridSize("assetTable ",2, "Asset", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Asset", assetGridSize);
		
		
		/*basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/locations");
		log("Location URL: "+ConfigProperties.site_url+"?route=inventory/attributes/locations",ILogLevel.TEST);
		int locationGridSize = assetPage.returnLocationGridSize(InventoryPageObject.originalGridId, 1,  InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Location",locationGridSize);*/
						
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/licenses");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/licenses",ILogLevel.TEST);
		int licenseGridSize  = assetPage.returnOriginalGridSize("licenseTable", 2,  "Licenses", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("License", licenseGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=projects");
		log("URL: "+ConfigProperties.site_url+"?route=projects",ILogLevel.TEST);
		int ProjectGridSize  = assetPage.returnOriginalGridSize("projectsDataTablesFullDesc", 2, "Project", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Projects", ProjectGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=issues/active");
		log("URL: "+ConfigProperties.site_url+"?route=issues/active",ILogLevel.TEST);
		int activeIssueGridSize  = assetPage.returnOriginalGridSize("issuesTableactive",1,  "Active Issues", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Active Issue", activeIssueGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=issues/all");
		log("URL: "+ConfigProperties.site_url+"?route=issues/all",ILogLevel.TEST);
		int allIssuesGridSize  = assetPage.returnOriginalGridSize("issuesTableactive", 2,  "All Issues", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("All Issue", allIssuesGridSize);
		
		/*basepage.navigateUrl(ConfigProperties.site_url+"?route=monitoring");
		log("URL: "+ConfigProperties.site_url+"?route=monitoring",ILogLevel.TEST);
		int licenseGridSize  = assetPage.returnOriginalGridSize("licenseTable", "Asset", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Monitoring");*/
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=people/users");
		log("URL: "+ConfigProperties.site_url+"?route=people/users",ILogLevel.TEST);
		int usersGridSize  = assetPage.returnOriginalGridSize("userDataTablesFull", 2, "Users", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Users", usersGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=people/staff");
		log("URL: "+ConfigProperties.site_url+"?route=people/staff",ILogLevel.TEST);
		int staffGridSize  = assetPage.returnOriginalGridSize("staffDataTablesFull", 2, "Staff", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Staff", staffGridSize);
		
		/*basepage.navigateUrl(ConfigProperties.site_url+"?route=people/roles");
		log("URL: "+ConfigProperties.site_url+"?route=people/roles",ILogLevel.TEST);
		dashboard.clickButton("Client Roles");
		assetPage.verifyPagination("Roles");*/
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=people/contacts");
		log("URL: "+ConfigProperties.site_url+"?route=people/contacts",ILogLevel.TEST);
		int contactGridSize  = assetPage.returnOriginalGridSize("contactsDataTablesFull", 2, "Contact", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Contacts", contactGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=clients");
		log("URL: "+ConfigProperties.site_url+"?route=clients",ILogLevel.TEST);
		int clientGridSize  = assetPage.returnOriginalGridSize("clientDataTablesFull", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Clients", clientGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/assetcategories");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/assetcategories",ILogLevel.TEST);
		int assetCatGridSize  = assetPage.returnOriginalGridSize("assetCategoriesFullTable", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Asset Categories", assetCatGridSize );
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/licensecategories");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/licensecategories",ILogLevel.TEST);
		int licenseCatGridSize  = assetPage.returnOriginalGridSize("licenseCategoriesFullTable", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("License Categories", licenseCatGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/labels");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/labels",ILogLevel.TEST);
		int labelsGridSize  = assetPage.returnOriginalGridSize("labelsFullTable", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Assets Labels", labelsGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/manufacturers");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/manufacturers",ILogLevel.TEST);
		int manufacturersGridSize  = assetPage.returnOriginalGridSize("labelsFullTable", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Manufacturers", manufacturersGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/models");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/models",ILogLevel.TEST);
		int modelsGridSize  = assetPage.returnOriginalGridSize("modelsTablesFull", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Asset Model", modelsGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/suppliers");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/suppliers",ILogLevel.TEST);
		int suppliersGridSize  = assetPage.returnOriginalGridSize("supplierTablesFull", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Supplier", suppliersGridSize);
	
	}

}
