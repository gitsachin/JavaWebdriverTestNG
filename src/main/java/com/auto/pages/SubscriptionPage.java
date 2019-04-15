package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SubscriptionPageObject;

public class SubscriptionPage extends BasePage {

	public SubscriptionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSearchedItemPresentInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody/tr")); 

		  int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		  
		  for(int i=1; i<=gridSize; i++){
			  boolean row = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
			  if(row){
				  String name = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
				  if(name.equalsIgnoreCase(expectedItemName)){
					  log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					  return true;
				  }else if(i>=gridSize){
					  continue;
				  }
				  
			  }
		  }
		  log("["+expectedItemName+"] is doesn't present in ["+tableName+"] grid.", ILogLevel.ASSERTS);  
		return false;	  
			  
	}
	
	public boolean isItemDeleteFromGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber, boolean _expectedCondition){
		 boolean grid  = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr")); 
		 if(grid){
			 int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			 String name1 = getText(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  
			  for(int i=1; i<=gridSize; i++){
				  boolean rowForExpectedString = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]/a"));
				  if(rowForExpectedString){
				  String name2 = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]/a"));
				  if(name2.equals(expectedItemName)){
					  log("["+name2+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					  return true;
				  }
				  }
			 
			  }
			  log("["+name1+"] displaying when search deleted item: ["+expectedItemName+"].", ILogLevel.ASSERTS);
			  return _expectedCondition;
		}else{
			log("Item doesn't present in ["+tableName+"] when search deleted item: ["+expectedItemName+"].", ILogLevel.ASSERTS);
			return false;
		}
		
	}
	
	public void click(String _button){
		if(isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath))){
			click(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath), _button+" button clicked", 4);
		}

	}
	
	public void enterInput(String _locator, String _key, String _fieldname){
		fieldClear(By.name(_locator));
		sendKeys(By.name(_locator), _key, 1, _key+" entered in "+_fieldname);
	}
	
	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		        	waitForElementDisplayed(By.xpath(_locator));
		        	fieldClear(By.xpath(_locator));
		        	sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	pause(1);
		        	break;
		        case 2:
		        	waitForElementDisplayed(By.cssSelector(_locator));
		        	fieldClear(By.cssSelector(_locator));
		        	sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	pause(1);
		        	break;
		     }
	  }
	
	
	public void clickOnExpectedButton(String _locator, int _numForSwitch, String _buttonName){
		  
		  switch (_numForSwitch)
		     {
		     case 1:
		            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(4);
		        	break;
		        case 2:
		        	((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(3);
		        	//click(By.cssSelector(_locator), "["+_buttonName+"] button clicked.", 2);
		        	break;
		     }
	  }
	
	
	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _columnNoForExpectedString, String _buttonName,int columnNoForIcon, int _buttonindex){
		  waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody/tr")); 
		  boolean SearchTable = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  String emptyRecord = getText(By.xpath("//table["+_gridId+"]/tbody/tr[1]/td["+_columnNoForExpectedString+"]"));
		  if(SearchTable && !emptyRecord.equalsIgnoreCase("No Records Found") ){
			  int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			  
			  for(int i=1; i<=size; i++){
				  
				  String actualName = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
				  if(SearchTable == true && actualName.equalsIgnoreCase(_expectedName)){
					  click(By.xpath(("(//table["+_gridId+"]/tbody/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
					  break;
				  }else{
					  
					  continue;
				  }
			  }
		  }else{
			  log_Method("[No Records Found] message appears for enterd search string.");
		  }	  
	}
	
	
	public boolean isExpectedLabelPresent(String expectedLabel) {
		
		int size = driver.findElements(By.xpath("//label")).size();
		for(int i=1;i<=size;i++) {
			
			String text = driver.findElement(By.xpath("(//label)["+i+"]")).getText();
			if(text.equals(expectedLabel)) {
				log("["+text+"] present in add test plan",ILogLevel.ASSERTS);
				return true;
			}else {
				continue;
			}
		}
		return false;
	}

}
