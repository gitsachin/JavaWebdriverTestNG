package com.auto.mobilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.auto.base.BaseMobilePage;
import com.auto.base.ILogLevel;
import com.auto.mobilePagesObject.MobileSignUpPageObject;
import com.auto.pageobject.SignUpPageObject;

import io.appium.java_client.AppiumDriver;

public class MobileSignUp extends BaseMobilePage {

	public MobileSignUp(AppiumDriver driver1) {
		super(driver1);
		// TODO Auto-generated constructor stub
	}
	
	public void enterInput(String _locator, String _key, String _log){
		sendKeys(By.xpath(_locator), _key, 1, _log+": "+_key);	
	}
	
	public void clickSubmit(){
		click(By.xpath(SignUpPageObject.submit_xpath),"Submit button",2);
	}
	
	public boolean isExpectedFieldPresent(String _locator, String _filedName, String _pageName){
		boolean field = isElementPresent(By.xpath(_locator));
		if(field){
			log("["+_filedName+"] is present in "+_pageName, ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean ScrollingUpPage(String _pageName){
		JavascriptExecutor js = (JavascriptExecutor) driver1;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		waitForElementDisplayed(By.xpath(MobileSignUpPageObject.logoImg_xpath));
		boolean logo = driver1.findElement(By.xpath(MobileSignUpPageObject.logoImg_xpath)).isDisplayed();
		if(logo){
			log("Scroll up the page and Logo Image is visible in "+_pageName, ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean ScrollingDownPage(String _filedName, String _pageName){
		JavascriptExecutor js = (JavascriptExecutor) driver1;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		waitForElementDisplayed(By.xpath(MobileSignUpPageObject.signUpLink_xpath));
		boolean passwordField = driver1.findElement(By.xpath(MobileSignUpPageObject.textFieldPassword_xpath)).isDisplayed();
		boolean signUpLink = driver1.findElement(By.xpath(MobileSignUpPageObject.signUpLink_xpath)).isDisplayed();
		if(passwordField && signUpLink){
			log("Scroll down the page and ["+_filedName+"] is visible in "+_pageName, ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	

}
