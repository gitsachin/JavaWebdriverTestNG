package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.IssuePageObject;

public class IssuePage extends BasePage {

	public IssuePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public static String numberOfIssueCategories="";

	public String returnCountNo(String _gridId, String _expectedClientName){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr[1]"));
		int rowCount = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr/td")).size();
		for(int i = 1; i<=rowCount; i++){
			String clientName = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[2]/span")).getText();
			if(clientName.equals(_expectedClientName)){
				String countValue = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[3]")).getText();
				log("["+countValue+"] count value appears under ["+_expectedClientName+"] client", ILogLevel.METHOD);
				return countValue;
			}
		}
		return null;
	}


	public boolean isExpectedColumnPresent(String _headerId, String _expectedColumnName){
		int size = driver.findElements(By.xpath("//table["+_headerId+"]/thead/tr/th")).size();
		for(int i=1; i<=size; i++){
			String columnName = getText(By.xpath("//table["+_headerId+"]/thead/tr/th["+i+"]"));

			if(columnName.contains(_expectedColumnName)){
				log("Expected column heading ["+columnName+"] present in ["+i+"] no position.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue; 
			}
		}
		return false;
	}

	public boolean isEyeIconPresent(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _gridName, int _columnNoForExpectedString){
		// clickOnViewEditDelIcons(Grid locator id, Searched String, icond index(view/edit/delete icon position), perform Action(view/edit/delete), Icon's column no, Grid Name, searched String column No.) 
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]/span"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				boolean eyeIcon = isElementPresent(By.xpath(("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i[@class='fa fa-eye'])["+_buttonindex+"]")));
				if(eyeIcon){
					log("["+_buttonName+"] present in ["+_gridName+"] table", ILogLevel.ASSERTS);
					// click(By.xpath(("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i[@class='fa fa-eye'])["+_buttonindex+"]")), "[Eye Ball] icon clicked", 2);
					return true;
				}else{
					log("[Eye Icon] doesn't present in ["+_gridName+"] table", ILogLevel.ASSERTS);
					return false;
				}
			}else{
				continue;
			}
		}
		return false;
	}

	public boolean isGridSizeSameAccordingToCountNumber(String _gridId, int _countValue){
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int gridSize = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
		if (grid && gridSize == _countValue) {
			log("["+gridSize+"] record present in [Issue Categories]  grid", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		// clickOnViewEditDelIcons(Grid locator id, Searched String, icond index(view/edit/delete icon position), perform Action(view/edit/delete), Icon's column no, performed function Name, searched String column No.) 
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				String saveLocator = "(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]";
				//click(By.xpath((saveLocator)), "["+_buttonName+"] icon clicked for ["+_Name+"].", 2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(saveLocator)));
				log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
				pause(2);
				break;
			}else{
				continue;
			}
		}

	}
	public boolean verifyColumn(String columnName){
		boolean column = isElementPresent(By.xpath(IssuePageObject.column_xpath));
		int size = driver.findElements(By.xpath(IssuePageObject.column_xpath)).size();

		for(int i=1; i<=size; i++){
			String columnValue=  driver.findElement(By.xpath(IssuePageObject.column_xpath+"["+i+"]")).getText();
			System.out.print(columnValue);
			if(columnValue.equals(columnName)){
				log("["+columnValue+"]"+ "Present in column",ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return false;
	}

	public void clickOnExpectedFilterArrowIcon(String columnName, int index){
		click(By.xpath(IssuePageObject.filterArrowIcon_xpath+"["+index+"]"),"Click on ["+columnName+"] arrow icon", 2);
	}

	public boolean verifyDueDateField(){
		boolean dateField = isElementPresent(By.id(IssuePageObject.dateRangeField_id));

		if(dateField){
			log("[Date Range] Present in due date",ILogLevel.ASSERTS);
			return true;
		}else{
			log("[Date Range] not Present in due date",ILogLevel.ASSERTS);
			return false;
		}

	}
	
	public void countNumberOfIssueCategories(int _index){
		waitForElementDisplayed(By.xpath("(//div[@class='info'])["+_index+"]"));
		String value = driver.findElement(By.xpath("(//div[@class='info'])["+_index+"]")).getText();
		String [] parts = value.split("of ");
		String part1 = parts[1];
		String [] s = part1.split(" entries");
		numberOfIssueCategories = s[0];
		log("Total size of issueCategories is ["+numberOfIssueCategories+"]",ILogLevel.METHOD);
	}
	
	public boolean verifyNumberOfCategories(String _gridId,String _number){
		int numberOfIssueCategoriesInTypeField = driver.findElements(By.xpath("//ul["+_gridId+"]/li")).size();
		String numberOfIssueInType = Integer.toString(numberOfIssueCategoriesInTypeField);
		if(numberOfIssueInType.equals(_number)){
			log("["+numberOfIssueInType+"] is equal to Issue Category",ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+numberOfIssueInType+"] is not equal to Issue Category",ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public void clickCreate(){
		click(By.id(IssuePageObject.CreateButtonId),"Click on create button",3);
	}
	
	public void clickNewButton(){
		click(By.id(IssuePageObject.new_Id),"Click on [New] button",2);
	}
	
	public void selectValueFromDropdown(String _value){
	  dropdownSelect(By.id(IssuePageObject.attributeType_id),_value);
	}
	
	public boolean verifyDataCreatelist(String fieldId,String value){
		boolean ele = isElementPresent(By.id(fieldId));
		String text = driver.findElement(By.id(fieldId)).getAttribute("value");
		if(text.equals(value)){
			log("["+text+"] present on field",ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+text+"] not present on field",ILogLevel.ASSERTS);
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
	
	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
		  log_Method("Test 0");
		  for(int i=1; i<=gridSize; i++){
			  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]/a"));
			  log_Method("Test 1"+name);
			  if(name.equals(expectedItemName) || name.contains(expectedItemName)){
				  log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
				 return true;
			  }
		  }
		  log_Method("Test 2");
		  return false;
	  }
	
	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _columnNoForExpectedString, String _buttonName,int columnNoForIcon, int _buttonindex){
		 
		  boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		  String emptyRecord = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]"));
		  if(grid && !emptyRecord.equalsIgnoreCase("No Records Found") ){
			  int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
			  
			  for(int i=1; i<=size; i++){
				  
				  String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]/a"));
				  if(grid == true && actualName.equalsIgnoreCase(_expectedName)){
					  click(By.xpath(("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
					  break;
				  }else{
					  
					  continue;
				  }
			  }
		  }else{
			  log_Method("[No Records Found] message appears for enterd search string.");
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
	
	public boolean isExpectedFiledPresent(String _screenName, String _fieldname){
		boolean field = isElementPresent(By.xpath("//label[contains(text(), '"+_fieldname+"')]"));
		if(field){
			log("["+_fieldname+"] field is presnt on "+_screenName+".", ILogLevel.ASSERTS);
			return true;
		}
		log("["+_fieldname+"] field removed from "+_screenName+".", ILogLevel.ASSERTS);
		return false;
	}
	
	public String returnExpectedFieldValues(String _filedname, String _locator){
		String fieldValue = driver.findElement(By.xpath(_locator)).getAttribute("value");
		if(!fieldValue.equals("")){
			log("Field value of "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return fieldValue;
		}else{
			log("Field value of "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return fieldValue;
		}
	}
	

}
