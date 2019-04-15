package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.UsersPage;

public class AZ_79_MakeAllTablesAbleToSortAndToggleBetweenAscAndDescTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	
	@Test
	public void AZ_101_MakeALLTablesWorkAndBehaveAsTheAssetsTable(){
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Inventory");
		assertEquals(basepage.getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css),0), "Assets");
		dashboard.clickButton("Assets");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Assets Manage assets");
		
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Tag", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Tag", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Tag column.");
		assertTrue(assetPage.isDescendingIconPresent("Tag", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in tag column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Category", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Category", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Category column.");
		assertTrue(assetPage.isDescendingIconPresent("Category", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Category column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Asset Name", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Asset Name", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Asset Name' column.");
		assertTrue(assetPage.isDescendingIconPresent("Asset Name", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Nsset name' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Model", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Model", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Model column.");
		assertTrue(assetPage.isDescendingIconPresent("Model", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Model column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Serial Number", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Serial Number", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Serial Number' column.");
		assertTrue(assetPage.isDescendingIconPresent("Serial Number", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Serial Number' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Related Entities", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Related Entities", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Related Entities' column.");
		assertTrue(assetPage.isDescendingIconPresent("Related Entities", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Related Entities' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Status", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Status", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Status column.");
		assertTrue(assetPage.isDescendingIconPresent("Status", "Ascending", InventoryPageObject.descSort), "Descending icon doesn't present in Status column.");
		
		
		dashboard.clickButton("Licenses");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Licenses Manage licenses");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Tag", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Tag", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Tag column.");
		assertTrue(assetPage.isDescendingIconPresent("Tag", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Tag column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Category", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Category", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Category column.");
		assertTrue(assetPage.isDescendingIconPresent("Category", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Catergory column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Name", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Name", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Name column.");
		assertTrue(assetPage.isDescendingIconPresent("Name", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Name column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Status", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Status", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Status column.");
		assertTrue(assetPage.isDescendingIconPresent("Status", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Status column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Assets", "Expiration Date", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Expiration Date", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Expiration Date' column.");
		assertTrue(assetPage.isDescendingIconPresent("Expiration Date", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Expiration Date' column.");
		
		
		dashboard.clickMenuTab("Help Desk Tickets");
		dashboard.clickButton("Awaiting Reply");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Help Desk Tickets Awaiting reply");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Ticket", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Ticket", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Ticket column");
		assertTrue(assetPage.isDescendingIconPresent("Ticket", "Descending", InventoryPageObject.descSort), "Decending icon icon doesn't present in Ticket column");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Subject", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Subject", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Subject column");
		assertTrue(assetPage.isDescendingIconPresent("Subject", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Subject column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Submitter", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Submitter", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Submitter column.");
		assertTrue(assetPage.isDescendingIconPresent("Submitter", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Submitter column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Assigned To", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Assigned To", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Assign To' column.");
		assertTrue(assetPage.isDescendingIconPresent("Assigned To", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Assign To' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Related Entities", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Related Entities ", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Related Entities' column.");
		assertTrue(assetPage.isDescendingIconPresent("Related Entities ", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Related Entities' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Department", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Department", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Department column.");
		assertTrue(assetPage.isDescendingIconPresent("Department", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Department column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Last Reply", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Last Reply", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Last Reply' column.");
		assertTrue(assetPage.isDescendingIconPresent("Last Reply", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Last Reply' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "Status", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Status", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Status column.");
		assertTrue(assetPage.isDescendingIconPresent("Status", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Status column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Help Desk Tickets Awaiting reply", "CC Recipients", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("CC Recipients", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'CC Recipient' column.");
		assertTrue(assetPage.isDescendingIconPresent("CC Recipients", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'CC Recipient' column.");
		
		dashboard.clickMenuTab("Issues");
		dashboard.clickButton("Active Issues");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Issues Active issues");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "ID", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("ID", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in ID column.");
		assertTrue(assetPage.isDescendingIconPresent("ID", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in ID column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "Name", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Name", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Name column.");
		assertTrue(assetPage.isDescendingIconPresent("Name", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Name column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "Assigned To", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Assigned To", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Assign To' column.");
		assertTrue(assetPage.isDescendingIconPresent("Assigned To", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Assign To' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "Related Entities", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Related Entities", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Related Entities' column.");
		assertTrue(assetPage.isDescendingIconPresent("Related Entities", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Related Entities' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "Due Date", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Due Date", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in 'Due Date' column.");
		assertTrue(assetPage.isDescendingIconPresent("Due Date", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in 'Due Date' column.");
		assetPage.clickOnExpectedSortIconFromTableHeader("Active issues", "Status", InventoryPageObject.iconSort);
		assertTrue(assetPage.isAscendingIconPresent("Status", "Ascending", InventoryPageObject.ascSort), "Ascending icon doesn't present in Status column.");
		assertTrue(assetPage.isDescendingIconPresent("Status", "Descending", InventoryPageObject.descSort), "Descending icon doesn't present in Status column.");
			
	}

}
