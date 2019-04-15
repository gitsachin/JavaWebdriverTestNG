package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.HelpDeskPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_266_MultipleTicketsAddedInGridTest extends TestCore{
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	UsersPage userPage;
	TicketPage ticketPage;
	HelpDeskPage helpDeskTicket;
	
	@Test(priority=4)
	public void az_266_MultipleTicketsAddedInGrid() throws InterruptedException{
	
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
	    dashboard = new DashboardPage(driver);
	    assetPage = new AssetsPage(driver);
	    ticketPage = new TicketPage(driver);
	    helpDeskTicket = new HelpDeskPage(driver);
	   
	    driver.navigate().to(ConfigProperties.signin_url);
	
		assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		
		dashboard.clickMenuTab("Service Request");
		dashboard.clickButton("All Tickets");
			
		String sl[] = {"SerialNumber12345"};
		String ticket[] = {"Ticket_1111_0", "Ticket_1111_1", "Ticket_1111_2", "Ticket_1111_3"};
		int size = ticket.length;
		
		//Ticket Delete if present in grid
		for(int i=0; i<=size-1; i++){
			ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ticket[i], "Search string: ");
			ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
			ticketPage.clickOndDelIconsIfItemPresentInGrid(InventoryPageObject.searchTableId, ticket[i], 3, "Delete", 10, "Ticket", 2);
		}
		
		//Ticket Create
		signup.click("Action");
		ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNewTicket,1, "New ticket");
		for(int i=0; i<=size-1; i++){
			if(i>=0){
				assertTrue(ticketPage.isExpectedPopupOpen("Add Ticket", 1), "Fail: [Add Ticket] pop-up disappear.");
				
				assetPage.clickOnArrowIconOfFields("Client Field", 3);
				assetPage.enterSearchString("QATEST", 2, "Select Client");
				ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, sl[0], "Asset Serial No");
				ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Serial Suggestion list");
				ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep1_xpath, 1, "1st Screen Next");
				assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Serial Number", 1), "Fail: 'Serial Number' field missing.");
				assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Asset Name", 2), "Fail: 'Asset Name' field missing.");
				assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Manufacturer", 3), "Fail: 'Manufacturer' field missing.");
				assertTrue(ticketPage.isEdxpectedFieldsReadOnlyField("Model", 4), "Fail: 'Model' field missing.");
				ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.buttonNextStep2_xpath, 1, "2nd Screen Next");
				ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, ticket[i], "Subject");
				ticketPage.selectOption(8, "QaLocationForAutomation_DND");
				if(!basepage.isElementPresent(By.xpath(HelpDeskTicketsPageObject.userChoice_xpath))){
					assetPage.enterSearchString("user_"+BasePage.autogenerateNumber(5)+"@mailinator.com", 1, "User or Email Address");
				}
				signup.clickWithScroll("Next Ticket");
				Thread.sleep(7000);
			}else if(i>0){		
				assertTrue(ticketPage.isExpectedPopupOpen("Add Ticket", 1), "Fail: [Add Ticket] pop-up doisappear.");
				ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldSubject_xpath, 1, ticket[i], "Subject");		
				ticketPage.enterExpectedValueInTextField(HelpDeskTicketsPageObject.textFieldAsset_xpath, 1, sl[0], "Asset Serial No");		
				ticketPage.clickOnExpectedButton(HelpDeskTicketsPageObject.suggestionList_xpath, 1, "Asset Serial Suggestion list");			
				if(i<=size-2){
					signup.clickWithScroll("Next Ticket");
					Thread.sleep(7000);
				}else if(i==size-1){
					signup.clickWithScroll("Done");
					Thread.sleep(2000);
					assertFalse(assetPage.isExpectedPopupOpen("Add Ticket"), "Fail: [Add Ticket] pop-up still appear.");
				}	
			}
			
		}
		dashboard.clickButton("All Tickets");
		assetPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, "Ticket_1111", "Search string: ");
		assetPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertTrue(ticketPage.verifyGridRecord(HelpDeskTicketsPageObject.searchTableId,"Ticket_1111", size), "Fail: Search item doesn't present in grid.");
		
		for(int i=0; i<=size-1; i++){
			ticketPage.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, ticket[i], "Search string: ");
			ticketPage.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
			assetPage.clickOnViewEditDelIcons(InventoryPageObject.searchTableId, ticket[i], 3, "Delete", 10, "Ticket", 2);
			signup.click("Yes");
			log("["+ticket[i]+"]: Ticket delete from Grid", ILogLevel.TEST);
		}
		
	}

}
