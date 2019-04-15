package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.MonitoringPageObject;
import com.auto.pageobject.ProjectPageObject;

public class MonitoringPage extends BasePage {

	public MonitoringPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickOnExpectedButton(String _locator, int _numForSwitch, String _buttonName){ // xpath: 1, cssSelector: 2
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(3);
		        	break;
		        case 2:
		        	 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(_locator)));
		        	log("["+_buttonName+"] button clicked.",ILogLevel.METHOD);
		        	pause(3);
		        	break;
		     }
	}
	
	public void clickOnArrowIconOfFields(String _fieldName, int _index){	
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.arrowIconDepartmentField+")["+_index+"]"));
		click(By.xpath("("+InventoryPageObject.arrowIconDepartmentField+")["+_index+"]"), "["+_fieldName+"] arrow icon clicked.", 2);
	}

	public void enterSearchString(String _key, int _indexOfSearchField, String _fieldName){
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"));
		sendKeys(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"), _key, 2, "value ["+_key+"] for field ["+_fieldName+"].");
		driver.findElement(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.ENTER);
		pause(3);
	}
	
	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		        	fieldClear(By.xpath(_locator));
		        	sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	break;
		        case 2:
		        	fieldClear(By.cssSelector(_locator));
		        	sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	break;
		     }
	  }
	

	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody/tr")); 
		  int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		  for(int i=1; i<=gridSize; i++){
			  String name = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
			
			  if(name.equals(expectedItemName) || name.contains(expectedItemName)){
				  log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
				 return true;
			  }
		  }
		  log_Method("1");
		  return false;
	}
	
	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		  boolean grid = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  pause(2);
		  int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		  for(int i=1; i<=size; i++){ 
			  String actualName = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			  if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){	  
				  click(By.xpath(("(//table["+_gridId+"]/tbody/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_Name+"].", 3);
				  break;
			  }else{
				  continue;
			  }
		  }
	  }
	
	public boolean isDataUpdateInExpectedField(String _locator, String _fieldName, String _oldData){
		if(isElementPresent(By.xpath(_locator))){
			String fieldValue = driver.findElement(By.xpath(_locator)).getAttribute("value");
			if(fieldValue.equalsIgnoreCase(_oldData)){
				log("["+_fieldName+"] Field data doesn't updated.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+_fieldName+"] Field data updated from ["+_oldData+"] to ["+fieldValue+"].", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("Expected filed: ["+_fieldName+"] doesn't present in screen.", ILogLevel.ASSERTS);
			return false;
		}
		
	}
	
	public boolean isExpectedProjectOpen(String _expectedProject){
		  boolean header = isElementPresent(By.xpath(MonitoringPageObject.breadcrumb_xpath));
		  String text = getText(By.xpath(MonitoringPageObject.breadcrumb_xpath));
		  if(header && text.equalsIgnoreCase(_expectedProject) || text.contains(_expectedProject)){
			  log("["+_expectedProject+"] opens to review.", ILogLevel.ASSERTS);
			  return true;
		  }else{
			  log("["+_expectedProject+"] doesn't opent to review.", ILogLevel.ASSERTS);
			  return false;
		  }
	}
	
	/*public boolean isItemDeleteFromGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber, boolean _expectedCondition){
		 boolean grid  = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr")); 
		 if(grid){
			 int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			 String name1 = getText(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  
			  for(int i=1; i<=gridSize; i++){
				  boolean rowForExpectedString = isElementPresent(By.xpath("//["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
				  if(rowForExpectedString){
				  String name2 = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
				  if(name2.equals(expectedItemName) || name2.contains(expectedItemName) ){
					  log("["+name2+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					  return true;
				  }
				  }
			 
			  }
			  
		}else{
			log("Item doesn't present in ["+tableName+"] when search deleted item: ["+expectedItemName+"].", ILogLevel.ASSERTS);
			return false;
		}
		
	}*/
	
	public boolean isItemDeleteFromGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		boolean grid  = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr")); 
		 if(grid){
			 int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			 String name1 = getText(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  
			  for(int i=1; i<=gridSize; i++){
				  boolean rowForExpectedString = isElementPresent(By.xpath("//["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
				  if(rowForExpectedString){
				  String name2 = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNumber+"]"));
				  if(name2.equals(expectedItemName) || name2.contains(expectedItemName) ){
					  log("["+name2+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					  return true;
				  }
				  }
			  }
			  return false;
	}else{
		log("Item doesn't present in ["+tableName+"] when search deleted item: ["+expectedItemName+"].", ILogLevel.ASSERTS);
		return false;
	}
		
	}
	
	
}
