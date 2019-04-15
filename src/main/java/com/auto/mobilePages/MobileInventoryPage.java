package com.auto.mobilePages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.auto.base.BaseMobilePage;
import com.auto.base.ILogLevel;
import com.auto.mobilePagesObject.MobileInventoryPageObject;
import com.auto.pageobject.DashboardPageObject;


import io.appium.java_client.AppiumDriver;

public class MobileInventoryPage extends BaseMobilePage {

	public MobileInventoryPage(AppiumDriver driver1) {
		super(driver1);
		// TODO Auto-generated constructor stub
	}
	
	public void tapOnMenuTab(String _tabName){
		waitForElementDisplayed(By.xpath(MobileInventoryPageObject.menu_xpath+_tabName+MobileInventoryPageObject.menu1_xpath));
		
		WebElement _ele = driver1.findElement(By.xpath(DashboardPageObject.menu_xpath+_tabName+DashboardPageObject.menu1_xpath));
		 JavascriptExecutor js = (JavascriptExecutor) driver1;
		 js.executeScript("arguments[0].scrollIntoView();", _ele);
		
		 ((JavascriptExecutor)driver1).executeScript("arguments[0].click();", driver1.findElement(By.xpath(MobileInventoryPageObject.menu_xpath+_tabName+MobileInventoryPageObject.menu1_xpath)));
     	log("["+_tabName+"] Menu tab clicked", ILogLevel.METHOD);
     	pause(3);
		
	}
	
	public void tapButton(String _subMenu){
		waitForElementDisplayed(By.xpath(MobileInventoryPageObject.ButtonTagA_xpath+_subMenu+MobileInventoryPageObject.menu1_xpath));
		click(By.xpath(MobileInventoryPageObject.ButtonTagA_xpath+_subMenu+MobileInventoryPageObject.menu1_xpath), "["+_subMenu+"] clicked", 4);
	}
	
	public boolean verifyTabs(String _value) {
		boolean ele = isElementPresent(By.xpath(MobileInventoryPageObject.tab_xpath));
		if(ele) {
			int size =  driver1.findElements(By.xpath(MobileInventoryPageObject.tab_xpath)).size();
			for(int i=1;i<=size;i++) {
		    String text = driver1.findElement(By.xpath(MobileInventoryPageObject.tab_xpath+"["+i+"]")).getText();
		    if(text.equals(_value)){
		    	log("["+_value+"] is present on tab",ILogLevel.ASSERTS);
		    	return true;
		    }
		}
	}
		return false;
		
  }
}
