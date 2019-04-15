package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SettingPageObject;
import com.auto.pageobject.SystemPageObject;

public class UsersPage extends BasePage{

	public UsersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	public void selectOption(int _index,String _text){
		
		driver.findElement(By.xpath(SettingPageObject.dropdown+"["+_index+"]")).click();
		
		log("Dropdown Clicked", ILogLevel.TEST);
		//clicks(By.xpath(SettingPageObject.optionList_xpath+_text+SettingPageObject.optionList1_xpath), _index,_text+" Selected", 3);
		click(By.xpath(SettingPageObject.optionList_xpath+_text+SettingPageObject.optionList1_xpath), _text+" Selected", 3);
	}
	
	public boolean isRetrunLocationPresent(String _expectedLocation){
		int rowCount = driver.findElements(By.xpath(AssetsCategriesPageObject.ticketGrid_xpth)).size();
		for(int i=1; i<=rowCount; i++){
		String returnLocation = getText(By.xpath("//table[@id='ticketDetailsTable']/tbody/tr["+i+"]/td[2]"));
		if(returnLocation.equals(_expectedLocation)){
			log("["+_expectedLocation+"] is present on Ticket", ILogLevel.ASSERTS);
			return true;
		}
	  }return false;
	}
	
	public boolean verifyConfirmPasswordField() {
		boolean confirmPassword = isElementPresent(By.xpath(SystemPageObject.confirmPassword_Xpath));
		
		if(confirmPassword) {
			String text = driver.findElement(By.xpath(SystemPageObject.confirmPassword_Xpath)).getText();
			log("["+text+"] field present",ILogLevel.ASSERTS);
			return true;
		}else {
			return false;
		}
	}
}
