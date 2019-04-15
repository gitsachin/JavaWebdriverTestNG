package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_178_ForgotPasswrdRestTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard;
	
	@Test(priority=0)
	public void az_178_ForgotPasswrdRest() throws InterruptedException{
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
		dashboard = new DashboardPage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		dashboard.clickOnExpectedButton(SignUpPageObject.linkForgetPassword_xpath, 1, "Forgot your password?");
		assertTrue(dashboard.isExpectedFieldPresent("Forgot Password", "Emial Id", SignUpPageObject.textFieldEmail_xpath), "fail: [Email Id] field doesn't located.");
		assertTrue(dashboard.isExpectedButtonPresent("Forgot Password", SignUpPageObject.buttonContinue_xpath, "Continue"), "Fail: [Continue] button doesn't located.");
		dashboard.enterExpectedValueInTextField(SignUpPageObject.textFieldEmail_xpath, 1, ConfigProperties.email, "Email Id"); // Invalid email id
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonContinue_xpath, 1, "Continue");
		Thread.sleep(2000);
		assertFalse(dashboard.isExpectedButtonPresent("Forgot Password", SignUpPageObject.buttonContinue_xpath, "Continue"), "Fail: [Continue] button present.");
		assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)), SignUpPageObject.resetLinkConfirmation);
		driver.navigate().to("https://www.mailinator.com/");
		signup.enterInput(0, ConfigProperties.email);
		signup.click("Go!");
		signup.selectMail("Reset Account Password");
		String mailHeader = signup.mailHeader();
		assertEquals(mailHeader,"Reset Account Password");
		signup.deleteMail();
		
	}

}
