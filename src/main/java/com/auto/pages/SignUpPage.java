package com.auto.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.SignUpPageObject;


public class SignUpPage extends BasePage{

	public SignUpPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLink(String _text){
		driver.findElement(By.partialLinkText(_text)).click();
		log("[Signup link] clicked ",ILogLevel.TEST);
	}
	

	public void enterInput(int _index, String _key){
		fieldClears(By.className(SignUpPageObject.input_classname), _index);
		sendKey(By.className(SignUpPageObject.input_classname), _key, _index, 1, _key+" entered");
		
	}
	
	public void clickSubmit(){
		click(By.xpath(SignUpPageObject.submit_xpath),"Submit button",5);
	}
	
	public void click(String _button){
		if(isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath))){
			click(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath), _button+" button clicked", 4);
		}

	}
	
	public void selectClientFromdropdownList(){
		dropdownSelect(By.xpath(SignUpPageObject.dropdownClientList_xpath), "QATEST");
	}
	
	public void clickWithScroll(String _button){
		if(isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath))){
			pause(2);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath)));
			click(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath), _button+" button",4);
		}

	}
	
	public void selectMail(String _mail){
		click(By.xpath(SignUpPageObject.mail_xpath+_mail+SignUpPageObject.mail_xpath1),"[Mail] Open", 4);
	}
	
	public void clickButton(String _button){

		if(isElementPresent(By.xpath(SignUpPageObject.ButtonTagA_xpath+_button+ SignUpPageObject.ButtonTagA1_xpath))){
			pause(3);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(SignUpPageObject.ButtonTagA_xpath+ _button+ SignUpPageObject.ButtonTagA1_xpath)));
			pause(3);
			click(By.xpath(SignUpPageObject.ButtonTagA_xpath+ _button+ SignUpPageObject.ButtonTagA1_xpath), "Click on button/tab ["+_button+"]", 3);
		}

	}

	public void deleteMail(){
		click(By.cssSelector(SignUpPageObject.deleteMail_css),"[Mail] deleted", 3);
	}
	
	public void enterTestedUrl(String _url){
		pause(5);
		driver.get(_url);
		log("Test ["+_url+"] launched.", ILogLevel.METHOD);	
	}
	
	public boolean isErrorUrlLaunched(String _expectedUrl){
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.equalsIgnoreCase(_expectedUrl)){
			log("Error page appears with url: ["+currentUrl+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOopsMessageAppears(String _expectedText){
		waitForElementDisplayed(By.xpath(SignUpPageObject.pageInfo_xpath));
		String text = getText(By.xpath(SignUpPageObject.pageInfo_xpath));
		if(text.equalsIgnoreCase(_expectedText)){
			log("["+text+"] text appearx in error page", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyLaunchedPageUrl(String _expectedUrl){
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.equalsIgnoreCase(_expectedUrl)){
			log("Launched page url: ["+currentUrl+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public void broswerBack(){
		driver.navigate().back();
		log("Clicdk on Broser back button", ILogLevel.METHOD);
		pause(1);
	}
	
	public void broswerForward(){
		driver.navigate().forward();
		log("Clicdk on Broser Forward button", ILogLevel.METHOD);
		pause(1);
	}
	
	public String returnBodyColorOfLoginPage(){
		String loginPageBodyColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
		log("Login page Body Color: ["+loginPageBodyColor+"]", ILogLevel.METHOD);
		return loginPageBodyColor;
	}
	
	public void launchExpectedUrl(String _expectedUrl){
		driver.navigate().to(_expectedUrl);
		driver.navigate().to(ConfigProperties.error404_url);
		//driver.get(_expectedUrl);
		pause(4);
	}
	
	public boolean isErrorPageAppear(String _urlForErrorPage){
		String errorUrl = driver.getCurrentUrl();
		if(errorUrl.equalsIgnoreCase(_urlForErrorPage)){
			log("User navigate to error page: "+errorUrl, ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isErrorPageColorIsSameLikeLoginPage(String _loginPageColor){
		String errorPageBodyColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
		if(errorPageBodyColor.equalsIgnoreCase(_loginPageColor)){
			log("Error page color is same like Aszet login page color.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isUserNavigateToExpectedPage(String _url){
		String recentPageUrl = driver.getCurrentUrl();
		if(recentPageUrl.equalsIgnoreCase(recentPageUrl)){
			log("User navigate to expected page: "+recentPageUrl, ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpectedTextAppearInErrorPage(String _text){
		boolean ele = isElementPresent(By.xpath(SignUpPageObject.oopsText_xpath));
		String text = getText(By.xpath(SignUpPageObject.oopsText_xpath));
		if(ele==true && text.equals(_text)){
			log("["+text+"] present in page.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isValidationAlertAppearsDuringSignup(String _expectedValidationMessage, String _expectedThankYouMessage){
		boolean errorMessage = isElementPresent(By.cssSelector(SignUpPageObject.errorMessage_css));
		boolean thankYouMessage = isElementPresent(By.cssSelector(SignUpPageObject.thankyouMessage_css));
		if(errorMessage){
			String message = getText(By.cssSelector(SignUpPageObject.errorMessage_css));
			if(message.equalsIgnoreCase(_expectedValidationMessage)){
				log("["+message+"] message appears so navigate back to Login page", ILogLevel.METHOD);	
				return false;
			}
		}else if(thankYouMessage){
			String thankMessage = getText(By.cssSelector(SignUpPageObject.thankyouMessage_css));
			if(thankMessage.equalsIgnoreCase(_expectedThankYouMessage)){
				log("["+thankMessage+"] message appears.", ILogLevel.METHOD);
				return true;
			}
		
		}else{
			return false;
		}
		return thankYouMessage;

	}
	
	public boolean isValidationMessageAppears(){
		boolean validationMessage = isElementPresent(By.xpath(SignUpPageObject.emailValidationMessage_xpath));
		if(validationMessage){
			String messsage = getText(By.xpath(SignUpPageObject.emailValidationMessage_xpath));
			log("["+messsage+"] appears for entering wrong input.", ILogLevel.ASSERTS);
			return validationMessage;
		}else{
			return false;
		}
	}
	
	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		String key = _key.trim();
		switch (_numForSwitch)
		{
		case 1:
			fieldClear(By.xpath(_locator));
			sendKeys(By.xpath(_locator), key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		case 2:
			fieldClear(By.cssSelector(_locator));
			sendKeys(By.cssSelector(_locator), key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		}
	}
	
	
	public boolean isAuthenticationAlertAppersDuringSignup(String _expectedValidationMessage){
		boolean errorMessage = isElementPresent(By.xpath(SignUpPageObject.atthenticationAlert_xapth));
		if(errorMessage){
			String message = getText(By.xpath(SignUpPageObject.atthenticationAlert_xapth));
			if(message.equalsIgnoreCase(_expectedValidationMessage)){
				log("["+message+"] message appears during login. ", ILogLevel.ASSERTS);	
				return true;
			}
		}
		log_Method("Locator doesn't found and ["+ _expectedValidationMessage+"] message doesn't appear.");
		return false;
	}
	
	
	public boolean isClientValidationAlertAppears(String _expectedValidatioMessage){
		boolean alert = driver.findElement(By.cssSelector(SignUpPageObject.clientNameAlert_css)).isDisplayed();
		if(alert){
			String alertText = getText(By.cssSelector(SignUpPageObject.clientNameAlert_css));
			if (alertText.equalsIgnoreCase(_expectedValidatioMessage)) {
				log("["+alertText+"] validation message appears for invalid enter.", ILogLevel.ASSERTS);
				return true;
			}
		}
		log("["+_expectedValidatioMessage+"] validation message doesn't appears.", ILogLevel.ASSERTS);
		return false;
	}
	
	public void pressBackSpaceButton(String _locator){
		driver.findElement(By.xpath(_locator)).sendKeys(Keys.BACK_SPACE);
		pause(1);
		driver.findElement(By.xpath(_locator)).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(_locator)).sendKeys(Keys.BACK_SPACE);
		
	}
	
	public boolean isSignUpformPresent(){
		boolean signup = isElementPresent(By.xpath(SignUpPageObject.formSignup_xpath));
		if(signup){
			boolean fields = isElementPresent(By.className(SignUpPageObject.input_classname));
			if(fields){
				log("Signup form present in Sign Up page.", ILogLevel.ASSERTS);
				return signup;
			}	
		}
		return false;
	}
	
	public boolean isExpectedTextAppearInExpectedSection(String _locator, String _thankMessage, String _screenName){
		boolean ele = isElementPresent(By.xpath(_locator));
		if(ele){
			String text = getText(By.xpath(_locator));
			if(text.equalsIgnoreCase(_thankMessage)){
				log("["+text+"] message appears/text in "+_screenName+" page", ILogLevel.ASSERTS);
				return true;
			}
		}
		log("["+_thankMessage+"] message/text doesn't appears in "+_screenName+" page", ILogLevel.ASSERTS);
		return false;
	}
	
	public String mailHeader(){
		waitForElementDisplayed(By.xpath(SignUpPageObject.mailHeader_xpath));
		String ticketMail = getText(By.xpath(SignUpPageObject.mailHeader_xpath));
		return ticketMail;
		
	}
	
	public String fetchUrl(){
		String text = getText(By.xpath("html/body/p"));	
		String[] parts = text.split("\n"); 
		int length = parts.length;	
		for(int i=0; i<=(length-1); i++){
			String subText= parts[i];
			if(subText.startsWith("https://")){
				log_Method("Reset Password Reference Link: "+subText);
				return subText;
			}
		}
		return null;
		
	    }
}
	


