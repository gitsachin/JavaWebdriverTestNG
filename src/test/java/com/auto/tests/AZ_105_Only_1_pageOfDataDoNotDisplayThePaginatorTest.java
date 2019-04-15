package com.auto.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

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


public class AZ_105_Only_1_pageOfDataDoNotDisplayThePaginatorTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	
	
	@Test
	public void az_105_Only_1_pageOfDataDoNotDisplayThePaginator() throws AWTException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/assets");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/assets",ILogLevel.TEST);
		int assetGridSize  = assetPage.returnOriginalGridSize("assetTable ",2, "Asset", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Asset", assetGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/licenses");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/licenses",ILogLevel.TEST);
		int licenseGridSize  = assetPage.returnOriginalGridSize("licenseTable", 2,  "Licenses", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("License", licenseGridSize);
		
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
	
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=inventory/attributes/manufacturers");
		log("URL: "+ConfigProperties.site_url+"?route=inventory/attributes/manufacturers",ILogLevel.TEST);
		int manufacturerGridSize  = assetPage.returnOriginalGridSize("ManufacturersTablesFull", 2, "Client", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Manufacturer", manufacturersGridSize);
		
		
		
		/*basepage.navigateUrl(ConfigProperties.site_url+"?route=tickets/ar");
		log("URL: "+ConfigProperties.site_url+"?route=tickets/ar",ILogLevel.TEST);
		assetPage.verifyPagination();
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=tickets/active");
		log("URL: "+ConfigProperties.site_url+"?route=tickets/active",ILogLevel.TEST);
		assetPage.verifyPagination();
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=tickets/all");
		log("URL: "+ConfigProperties.site_url+"?route=tickets/all",ILogLevel.TEST);
		assetPage.verifyPagination();
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=tickets/attributes/fields");
		log("URL: "+ConfigProperties.site_url+"?route=tickets/attributes/fields",ILogLevel.TEST);
		assetPage.verifyPagination();*/
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=issues/active");
		log("URL: "+ConfigProperties.site_url+"?route=issues/active",ILogLevel.TEST);
		int activeIssueGridSize  = assetPage.returnOriginalGridSize("issuesTableactive",1,  "Active Issues", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Active Issue", activeIssueGridSize);
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=issues/all");
		log("URL: "+ConfigProperties.site_url+"?route=issues/all",ILogLevel.TEST);
		int allIssuesGridSize  = assetPage.returnOriginalGridSize("issuesTableactive", 2,  "All Issues", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("All Issue", allIssuesGridSize);
		
		/*basepage.navigateUrl(ConfigProperties.site_url+"?route=issues/attributes/issuecategories");
		log("URL: "+ConfigProperties.site_url+"?route=issues/attributes/issuecategories",ILogLevel.TEST);
		assetPage.verifyPagination();
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=projects");
		log("URL: "+ConfigProperties.site_url+"?route=projects",ILogLevel.TEST);
		assetPage.verifyPagination();
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=templates/printassets");
		log("URL: "+ConfigProperties.site_url+"?route=templates/printassets",ILogLevel.TEST);
		assetPage.verifyPagination();*/
		
		basepage.navigateUrl(ConfigProperties.site_url+"?route=people/users");
		log("URL: "+ConfigProperties.site_url+"?route=people/users",ILogLevel.TEST);
		int usersGridSize  = assetPage.returnOriginalGridSize("userDataTablesFull", 2, "Users", 1, InventoryPageObject.infoPagination_xpath, 1);
		assetPage.verifyPagination("Users", usersGridSize);
		
		
		
	}

}
