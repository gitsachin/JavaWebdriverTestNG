package com.auto.pageobject;

import org.apache.commons.lang.RandomStringUtils;

import com.auto.base.BasePage;

public class SignUpPageObject {
	
	public static String companyName = "ASZET"+BasePage.currentDate();
	public static String companyNames = "ASZET"+RandomStringUtils.randomAlphabetic(6);
	public static String Name = "Sachin_"+BasePage.currentDate();
	public static String email = "test_"+BasePage.currentDate()+"@mailinator.com";
	public static String password = "Testing@123a";
	public static String buttonNewLocation = "//button[contains(text(), 'New location')]";
	public static String captcha = "test";
	public static String title = "aszet";
	public static String resetPassword = "Resetpass@123a";
	public static String signup_url= "https://app.aszet.com/?route=signup";
	public static String mailinator_url = "http://www.mailinator.com";
	public static String registrationMessage = "Thank you for signing up!";
	public static String accountActivationMessage = "Your account is successfully activated";
	public static String existingEmailValidation = "A customer account already exists, please contact your administrator to create a login.";
	public static String resetLinkConfirmation = "Reset link has been sent to you on your registered email address.";
	public static String accountNotActiveMessage = "Client is not active.Please do verification first.";
	
	public static String input_classname = "form-control";
	public static String successMessage_css = ".alert-success";
	public static String alertWarrning_css = ".alert-warning";
	public static String errorMessage_css = ".error-message";
	public static String submit_xpath = "//button[@type='submit']";
	public static String Button_xpath = "(//button[contains(text(),'";
	public static String Button1_xpath = "')])";
	public static String ButtonTagA_xpath = "//a[contains(text(),'";
	public static String ButtonTagA1_xpath = "')]";
	public static String deleteMail_css = ".fa-trash";
	public static String mail_xpath = "//div[contains(text(),'";
	public static String mail_xpath1 = "')]";
	public static String dropdownClientList_xpath = "//select[@id='dd_client']";
	public static String linkForgetPassword_xpath = "//a[text()='Forgot your password?']";
	public static String thankyouMessage_css = ".message h2";
	public static String buttonContinue_xpath = "//button[text()= 'Continue']";
	public static String emailValidationMessage_xpath = "//div[text()='Email Does not exist!']";
	
	public static String rootDirectoryUrl = "https://app.aszet.com/aszet/?route=signin";
	public static String errorUrl  = "https://app.aszet.com/?route=404";
	public static String pageInfo_xpath = "//div[@class='info']/h1";
	public static String userIcon_xpath = "//span[@class='hidden-xs']";
	public static String buttonLogout_xpath = "//a[contains(text(), 'Sign Out')]";
	public static String buttonActionDropDown_xpath = "//button[@id='dropdownMenu2']";
	public static String textFieldEmail_xpath = "//input[@placeholder='Email']";
	
	public static String buttonGoHome_xpath = "//a[contains(text(), 'Go Home')]";
	public static String oopsText_xpath = "//h1[contains(text(), 'Oops!')]";
	public static String oopsText = "Oops!";
	public static String atthenticationAlert_xapth  = "//div[@role='alert']";
	public static String clientNameAlert_css = ".alert.alert-danger.companyNameErrorDiv";
	public static String validationTextForInvalidClient = "Please use only letters in company name.";
	public static String companyName_xpath = "//input[@id='companyName']";
	public static String formSignup_xpath = "//div[@class='login-box-body']";
	public static String textThanksSignup_xpath = "//div[@class='message']/h2";
	public static String textThanksSignup = "Thank you for signing up!";
	public static String mailHeader_xpath = "(//div[contains(text(), 'Reset Account Password')])[1]";
	public static String resetPasswrdBox_xapth = "//p[@class='login-box-msg']";
	public static String resetPasswrdBox = "Reset your password";
}
