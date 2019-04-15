package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.SignUpPage;

public class AZ_123_404PagesTemplateTest extends TestCore {
	SignUpPage signup;
	DashboardPage dashboard;
	BasePage basepage;
	
	@Test
	public void az_123_404PagesTemplate(){
		signup = new SignUpPage(driver);
		dashboard = new DashboardPage(driver);
		basepage = new BasePage(driver);
		
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		String loginPageBodyColor = signup.returnBodyColorOfLoginPage();
		signup.launchExpectedUrl(ConfigProperties.tested_url);
		assertTrue(signup.isErrorPageAppear(ConfigProperties.error404_url), "Fail: Error page doesn't open.");
		assertTrue(signup.isExpectedTextAppearInErrorPage(SignUpPageObject.oopsText), "["+SignUpPageObject.oopsText+"] text doesn't present in page.");
		assertTrue(signup.isErrorPageColorIsSameLikeLoginPage(loginPageBodyColor), "Fail: Error page body color doesn't same with login page.");
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonGoHome_xpath, 1, "Go Home");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.signin_url), "Fail: User doesn't redirected to Signin page.");
		
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User doesn't redirected to Dashboard page.");
		signup.launchExpectedUrl(ConfigProperties.tested_url);
		assertTrue(signup.isErrorPageAppear(ConfigProperties.error404_url), "Fail: Error page doesn't open.");
		assertTrue(signup.isExpectedTextAppearInErrorPage(SignUpPageObject.oopsText), "["+SignUpPageObject.oopsText+"] text doesn't present in page.");
		dashboard.clickOnExpectedButton(SignUpPageObject.buttonGoHome_xpath, 1, "Go Home");
		assertTrue(signup.isUserNavigateToExpectedPage(ConfigProperties.dashBoardpage_url), "Fail: User doesn't redirected to Dashboard page.");
	
	}

}
