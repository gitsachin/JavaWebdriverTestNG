package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;
import com.auto.pages.SystemPage;
import com.auto.pages.TicketPage;
import com.auto.pages.UsersPage;

public class AZ_35_ForgotPasswordFunctionalityIsBrokenTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop ;
	BasePage basepage;
	DashboardPage dashboard ;
	AssetsPage assetPage;
	TicketPage ticketPage;
	UsersPage userPage;
	SystemPage  system;
	
	@Test
	public void az_35_ForgotPasswordFunctionalityIsBrokenTest() throws InterruptedException{
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        userPage = new UsersPage(driver);
        system = new SystemPage(driver);
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.clickLink("Sign Up");
		assertEquals(driver.getCurrentUrl(),SignUpPageObject.signup_url);
		signup.enterInput(0, SignUpPageObject.companyNames);
		signup.enterInput(1, SignUpPageObject.Name);
		signup.enterInput(2, SignUpPageObject.email);
		signup.enterInput(3, SignUpPageObject.password);
		signup.enterInput(4, SignUpPageObject.password);
		signup.enterInput(5,SignUpPageObject.captcha);
		signup.clickSubmit();
		
		boolean validationMessage = basepage.isElementPresent(By.cssSelector(SignUpPageObject.errorMessage_css));
		boolean thankYouMessage = basepage.isElementPresent(By.cssSelector(SignUpPageObject.thankyouMessage_css));
		
		if(validationMessage){
			String message = basepage.getText(By.cssSelector(SignUpPageObject.errorMessage_css));
			if(message.equalsIgnoreCase(SignUpPageObject.existingEmailValidation)){
				log("["+message+"] message appears so Test finish here.", ILogLevel.METHOD);	
				
			}
		}else if(thankYouMessage){
			String thankMessage = basepage.getText(By.cssSelector(SignUpPageObject.thankyouMessage_css));
			if(thankMessage.equalsIgnoreCase(SignUpPageObject.registrationMessage)){
				log("["+thankMessage+"] message appears.", ILogLevel.METHOD);
				driver.navigate().to(ConfigProperties.signin_url);
				signup.enterInput(0, SignUpPageObject.email);
				signup.enterInput(1, SignUpPageObject.password);
				signup.clickSubmit();
				assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.alertWarrning_css)), SignUpPageObject.accountNotActiveMessage);
				
				driver.get(SignUpPageObject.mailinator_url);
				signup.enterInput(0, SignUpPageObject.email);
				signup.click("Go!");
				signup.selectMail("Please Activate Your Aszet Account");
				basepage.switchFrame("msg_body");
				signup.clickButton("Activate Your Account");
				ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			    driver.switchTo().window(tabs2.get(1));
			    assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)), SignUpPageObject.accountActivationMessage);
			    driver.close();
			    driver.switchTo().window(tabs2.get(0));
			    signup.deleteMail();
			    
			    // forget password mail
			    basepage.navigateUrl(ConfigProperties.site_url+"?route=forgot");
				log("URL: "+ConfigProperties.site_url+"/?route=system/logs",ILogLevel.TEST);
				signup.enterInput(0,"test12345678@mailinator.com");
				signup.click("Continue");
				assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.errorMessage_css)), "Email Does not exist!");
				basepage.navigateUrl(ConfigProperties.site_url+"?route=forgot");
				signup.enterInput(0, SignUpPageObject.email);
				signup.click("Continue");
				assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)), SignUpPageObject.resetLinkConfirmation);
				driver.get(SignUpPageObject.mailinator_url);
				signup.enterInput(0, SignUpPageObject.email);
				signup.click("Go!");
				signup.selectMail("Reset Account Password");
				basepage.switchFrame("msg_body");
				signup.clickButton("Reset Your Password");
				ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
			    driver.switchTo().window(tabs3.get(1));
			    signup.enterInput(0, SignUpPageObject.resetPassword);
			    signup.enterInput(1, SignUpPageObject.resetPassword);
			    signup.click("Change Password");
			    driver.navigate().to(ConfigProperties.signin_url);
			    signup.enterInput(0, SignUpPageObject.email);
			    signup.enterInput(1, SignUpPageObject.resetPassword);
			    signup.clickSubmit();
				assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), SignUpPageObject.Name);
				
			}
		
		}
		
		driver.navigate().to(ConfigProperties.signin_url);
        
        assertEquals(driver.getTitle().toLowerCase(), SignUpPageObject.title.toLowerCase());
		signup.enterInput(0, ConfigProperties.superAdmin_email);
		signup.enterInput(1, ConfigProperties.superAdmin_pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.superAdminName);
		dashboard.clickMenuTab("System");
		dashboard.clickMenuTab("Clients");
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.headerText_css)), "Clients Manage clients");
		signup.click("Actions");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SignUpPageObject.companyNames, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		system.clickOnViewEditDelIcons(SystemPageObject.searchTableId, SignUpPageObject.companyNames, 2, "Delete", 6, 3);
		signup.click("Yes");
		system.enterExpectedValueInTextField(InventoryPageObject.textFieldSearch_xpath, 1, SignUpPageObject.companyNames, "Search string");
		system.clickOnExpectedButton(InventoryPageObject.buttonSearch_xpath, 1, "Go");
		assertFalse(system.isItemDeleteFromGrid(SystemPageObject.searchTableId, "Client", SignUpPageObject.companyNames, 1, false), "Fail: Searched item present in client grid.");
		
		dashboard.logOut(ConfigProperties.logout_url);
		
	}

}
