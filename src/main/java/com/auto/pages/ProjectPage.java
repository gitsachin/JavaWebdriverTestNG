package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.ProjectPageObject;

public class ProjectPage extends BasePage{

	public ProjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		        	waitForElementDisplayed(By.xpath(_locator));
		        	fieldClear(By.xpath(_locator));
		        	sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	break;
		        case 2:
		        	waitForElementDisplayed(By.cssSelector(_locator));
		        	fieldClear(By.cssSelector(_locator));
		        	sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
		        	break;
		     }
	  }
	
	public void clickOnExpectedButton(String _locator, int _numForSwitch, String _buttonName){
		  
		  switch (_numForSwitch)
		     {
		     case 1:
		            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(5);
		        	break;
		        case 2:
		        	((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(5);
		        	break;
		     }
	  }
	
	
	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		  boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		  pause(2);
		  int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
		  
		  for(int i=1; i<=size; i++){
			  String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			  if(grid == true && actualName.equals(_expectedName)){
				  click(By.xpath(("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_Name+"].", 3);
				
				  break;
			  }else{
				  continue;
			  }
		  }
	  }
	
	public boolean isProjecSelected(String _expectedPrejectName) {
		waitForElementDisplayed(By.xpath(ProjectPageObject.fieldProject_xpath));
		pause(4);
		String projectName = getText(By.xpath(ProjectPageObject.fieldProject_xpath));
		log("Get Project Name:"+projectName, ILogLevel.METHOD);
		
		if(projectName.equals(_expectedPrejectName)){
			log("Project Name: ["+projectName+"] is automatically selected.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isCookiesDelete(String _expectedUrl){
		
	   driver.manage().deleteAllCookies();
	   
	   driver.get(_expectedUrl);
	   pause(2);
	   String currntPageUrl = driver.getCurrentUrl();
	   if (currntPageUrl.equals(ConfigProperties.signin_url)) {
		   log("[Cookies] deleted and Current page url is: ["+currntPageUrl+"].", ILogLevel.ASSERTS);
		   return true;
	   }else{
		   return false;
	   }   
	}
	
	
	public boolean navigeteToExpectedpageAfterStartNewSession(String _expectedUrl, String _dashboardUrl ){
	   String currntPageUrl = driver.getCurrentUrl();
		
	   if (currntPageUrl.equalsIgnoreCase(_expectedUrl)) {
		   log("["+currntPageUrl+"] page opens after re-Login.", ILogLevel.ASSERTS);
		   clickOnExpectedButton(ProjectPageObject.buttonEdit_css, 2,  "Edit");
		   isExpectedPopupOpen("Edit Project");
		   return true;
	   }else if (currntPageUrl.equalsIgnoreCase(_dashboardUrl)){
		   log("["+currntPageUrl+"] page opens after re-Login.", ILogLevel.ASSERTS);
		   
	   		return true;
	   }else{
		   return false;
	   }
	  
	}
	
	public boolean isExpectedPopupOpen(String _expectedText){
		  boolean content = isElementPresent(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
		  String text = getText(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
		  if(content && text.equalsIgnoreCase(_expectedText)){
			  log("["+text+"] pop-up opens.", ILogLevel.ASSERTS);
			  return true;
		  }else{
			  log("["+text+"] pop-up Close.", ILogLevel.ASSERTS);
			  return false;
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
	
	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
		  
		  for(int i=1; i<=gridSize; i++){
			  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]/a"));
			  
			  if(name.equals(expectedItemName) || name.contains(expectedItemName)){
				  log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
				 return true;
			  }
		  }
		 
		  return false;
	}
	
	public boolean isSearchedItemPresentInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		  
		  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
		  
		  for(int i=1; i<=gridSize; i++){
			  boolean row = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
			  if(row){
				  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]/a"));
				  if(name.equals(expectedItemName)){
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
	
	public boolean isItemDeleteFromGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber, boolean _expectedCondition){
		 boolean grid  = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr")); 
		 if(grid){
			 int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
			 String name1 = getText(By.xpath("//tbody["+_gridId+"]/tr"));
		  
			  for(int i=1; i<=gridSize; i++){
				  boolean rowForExpectedString = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]/a"));
				  if(rowForExpectedString){
				  String name2 = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]/a"));
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
	

	public boolean isExpectedProjectOpen(String _expectedProject){
		  boolean header = isElementPresent(By.xpath(ProjectPageObject.projectHead_xpath));
		  String text = getText(By.xpath(ProjectPageObject.projectHead_xpath));
		  if(header && text.equalsIgnoreCase(_expectedProject)){
			  log("["+text+"] opens to review.", ILogLevel.ASSERTS);
			  return true;
		  }else{
			  log("["+text+"] doesn't opent to review.", ILogLevel.ASSERTS);
			  return false;
		  }
	}
	
}
