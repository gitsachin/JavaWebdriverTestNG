package com.auto.mobilePages;

import org.openqa.selenium.By;

import com.auto.base.BaseMobilePage;
import com.auto.base.ILogLevel;
import com.auto.mobilePagesObject.MobileDashBoardPageObject;

import io.appium.java_client.AppiumDriver;

public class MobileDashBoardPage extends BaseMobilePage {

	public MobileDashBoardPage(AppiumDriver driver1) {
		super(driver1);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isExpectedElementPresentOnScreen(String _locatior, String _elementName) {
		boolean element = isElementPresent(By.xpath(_locatior));
		if(element) {
			log("["+_elementName+"] present.", ILogLevel.ASSERTS);
			return true;
		}else {
			log("["+_elementName+"] doesn't present.", ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public void tapOnExpectedElement(String _locatior, String _elementName, int wait) {
		if(isElementPresent(By.xpath(_locatior))){
			click(By.xpath(_locatior), _elementName+" clicked.", wait);
		}
	}
	
	public boolean isUserNamePresent(String _expectedUsername) {
		String name = getText(By.xpath(MobileDashBoardPageObject.userName_xpath));
		if(name.contains(_expectedUsername) || name.equalsIgnoreCase(_expectedUsername)) {
			log("User: ["+name+"] present in dashboard screen.", ILogLevel.ASSERTS);
			return true;
		}else {
			log("User: ["+name+"] present in dashboard screen.", ILogLevel.ASSERTS);
			return false;
		}
	}

}
