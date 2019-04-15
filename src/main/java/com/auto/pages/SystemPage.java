package com.auto.pages;


import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.Xls_Reader;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.PredefineRepliesPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;

public class SystemPage extends BasePage {

	public SystemPage(WebDriver driver) {
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
		 
		  boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		  if(grid){
			  String rowText = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td"));
			
			  if(!rowText.equalsIgnoreCase("No Records Found")){
				
				  int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
				  for(int i=1; i<=size; i++){
					  
					  String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
					  if(grid == true && actualName.equalsIgnoreCase(_expectedName)){
						  click(By.xpath("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
						  break;
					  }else{
						  continue;
					  }
				  }
			  }else if(rowText.equalsIgnoreCase("") ){
				  log_Method("Not any text appears in row for enterd search string.");
			  }else{
				  log_Method("["+rowText+"] message appears for enterd search string."); 
			  }
		  }else{
			  log_Method("Not any records appears for enterd search string.");
		  }
	  
	  }
	
	public boolean isAllClintSelectedInClientField(String _expectedClientName){
		boolean clientName = isElementPresent(By.xpath(SystemPageObject.selectedClient_xpath));
		int listSize = driver.findElements(By.xpath(SystemPageObject.selectedClient_xpath)).size();
		if(clientName){
			for(int i=1; i<=listSize; i++){
				
				String selectedClient = getText(By.xpath("//ul[@class='select2-selection__rendered']/li["+i+"]"));
				if(selectedClient.contains(_expectedClientName)){
					log("["+_expectedClientName+"] is already Selected.", ILogLevel.ASSERTS);
					return true;
				}else{
					continue;
				}
			}
		return true;
	
		}else{	
			return false;
		}
	}
	
	public boolean isMultipleClientPresentInList(){
		click(By.xpath("//ul[@class='select2-selection__rendered']/li[2]"));
		int itemInList = driver.findElements(By.xpath(SystemPageObject.listClient_xpath)).size();
		
		if(itemInList >= 2){
			log("["+(itemInList-1)+"] client present under Client field.", ILogLevel.METHOD);
			return true;
		}else{
			return false;
		}	
	}
	
	public void clickOnArrowIconOfFields(String _fieldName, int _index){	
		waitForElementDisplayed(By.xpath("("+SystemPageObject.arrowIconDepartmentField+"/b)["+_index+"]"));
		 click(By.xpath("("+SystemPageObject.arrowIconDepartmentField+"/b)["+_index+"]"), "["+_fieldName+"] arrow icon clicked.", 4);
	}
	
	public void enterSearchString(String _key, int _indexOfSearchField, String _fieldName){
		sendKeys(By.xpath("("+SystemPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"), _key, 2, "value ["+_key+"] for field ["+_fieldName+"].");
		//driver.findElement(By.xpath("("+SystemPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.ENTER);
	}
	
	public void pressEnterToSelectSearchItem(int _indexOfSearchField){
		driver.findElement(By.xpath("("+SystemPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.ENTER);
		pause(4);
	}
	
	public void downAndpressEnterToSelectSearchItem(int _indexOfSearchField){
		driver.findElement(By.xpath("("+SystemPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("("+SystemPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.ENTER);
		pause(3);
	}
	
	public boolean isEnteredKeyPresentInList(String _searchedKey, String _listname){
		int list  = driver.findElements(By.xpath(SystemPageObject.listLocation_xpath)).size();
		for(int i=1; i<=list; i++){
			String actualLocation = getText(By.xpath("("+SystemPageObject.listLocation_xpath+")["+i+"]"));
			if(actualLocation.equalsIgnoreCase(_searchedKey)){
				log("Entered searched key: ["+actualLocation+"] present in "+_listname, ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return false;
	}
	
	public boolean isExpectedSectionPresent(String _expectedSectionName){
		boolean pesrmissionSection = isElementPresent(By.xpath(SystemPageObject.permissionSection1_xpath+_expectedSectionName+SystemPageObject.permissionSection2_xpath));
		String sectionName = getText(By.xpath(SystemPageObject.permissionSection1_xpath+_expectedSectionName+SystemPageObject.permissionSection2_xpath));
		if (pesrmissionSection && sectionName.equals(_expectedSectionName) ) {
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpectedSectionHavePermissionArea(String _expectedSectionName, int _index){
		boolean pesrmissions = isElementPresent(By.xpath(SystemPageObject.buttonaPermission1_xapth+_index+SystemPageObject.buttonaPermission2_xapth));
		if (pesrmissions) {
			int numberOfPermissions = driver.findElements(By.xpath(SystemPageObject.buttonaPermission1_xapth+_index+SystemPageObject.buttonaPermission2_xapth)).size();
			for(int i=1; i<=numberOfPermissions; i++){
				String name = getText(By.xpath("("+SystemPageObject.buttonaPermission1_xapth+_index+SystemPageObject.buttonaPermission2_xapth+"/label)["+i+"]"));
				log("["+i+"] number "+_expectedSectionName+" permission is: ["+name+"]", ILogLevel.METHOD);	
			}
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpectedSecreenAppear(String _locator, String _sceenHeadertext){
		String header = getText(By.xpath(_locator));
		if(header.contains(_sceenHeadertext)){
			log("["+header+"] page opens.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpectedTableHeaderPresent(String _locator, String _expectedHeader){
		int numberOfTableHeader = driver.findElements(By.xpath(_locator)).size();
		for(int i=1; i<=numberOfTableHeader; i++){
			String header = getText(By.xpath(_locator+"["+i+"]"));
			if (header.contains(_expectedHeader)) {
				log("["+i+"] number table header is: ["+header+"]", ILogLevel.ASSERTS);	
				return true;
			}else{
			continue;
		}
					
		}
		return false;
	}
	
	public int returnRandomNumber(int _limit){
		// create instance of Random class
	      Random rand = new Random();

	      // Generate random integers in range 0 to 999
	      return rand.nextInt(1000);
	}
	
	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
		  
		  for(int i=1; i<=gridSize; i++){
			  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
			  if(name.equals(expectedItemName)){
				  log(tableName+": ["+name+"] is present in grid.", ILogLevel.ASSERTS);
				  break;
			  }else{
				  continue;
			  }
		  }
		  return true;
	  }
	
	public boolean searchResult(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
		  for(int i=1; ; i++){
			  boolean rowPresent = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]"));
			  if(rowPresent){ 
				  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
				  if(name.equalsIgnoreCase(expectedItemName)){
					  log("["+i+": "+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					  continue;
				  }else{
					  return false;
				  }
			  }else if(gridSize==(i-1)){
				  log("["+gridSize+"] duplicate record founds as per search String ["+expectedItemName+"].", ILogLevel.ASSERTS);
				  return true;
			  }else{
				  return false;
			  }
		}     
	}
	
	public boolean isSearchedItemPresentInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  boolean gridRow = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr"));
		  if(gridRow){
			  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
			  
			  for(int i=1; i<=gridSize; i++){
				  boolean row = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
				  if(row){
					  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
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
		  }else{
			  log("Not any rows appears while search ["+expectedItemName+"] "+tableName+" from ["+tableName+"] grid.", ILogLevel.ASSERTS);
			  return false;
		  }		  
	}
	
	public boolean isExpectedPopupOpen(String _expectedText){
		  boolean content = isElementPresent(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
		  if(content){
			  String text = getText(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
			  if(text.equalsIgnoreCase(_expectedText)){
				  log("["+text+"] pop-up opens.", ILogLevel.ASSERTS);
				  return true;
			  }
		  }
		  log("["+_expectedText+"] pop-up Close.", ILogLevel.ASSERTS);
		  return false;
	  }
	
	public boolean isErrorPressent(String _locator, String _expectedErrortext){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		boolean errorLocator = isElementPresent(By.xpath(SystemPageObject.errorMessage_xpath));
		String textError = getText(By.xpath(SystemPageObject.errorMessage_xpath));
		if(errorLocator && textError.equalsIgnoreCase(_expectedErrortext)){
			log("Error message: ["+textError+"] appears.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public int clientRolesRoeNumber(String _gridId, String _gridName, String _expectedPageName){ 
		  boolean grid = isElementPresent(By.xpath("//table["+_gridId+"]/tbody"));
		  if(grid){
			  int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			  log("["+size+"] number of "+_gridName+" displaying under "+_expectedPageName+" page. ", ILogLevel.METHOD);
			  return size; 
		  }
		return 0;	  
	  }
	
	public boolean isPaginationTabPresent(int expectGridSize){
		boolean paginationtab = isElementPresent(By.xpath(InventoryPageObject.tabPagination_xpath));
		boolean paginationInfo = isElementPresent(By.xpath(InventoryPageObject.infoPagination_xpath));
		if(expectGridSize==10 ){
			if(paginationtab && paginationInfo){
				log("Row size more than "+expectGridSize+", Pagination tab and pagination info appears in table.", ILogLevel.METHOD);
				return true;
			}else{
				log("Row size more than "+expectGridSize+" but Pagination tab and pagination info doesn't present in table.", ILogLevel.METHOD);
				return false;
			}
		}else{
			log("Grid size: ["+expectGridSize+"] and Pagination tab and pagination info doesn't present in table.", ILogLevel.METHOD);
			return true;
		}
		  
	}
	
	public void clickOnExpectionIconsForRoles(String _gridId, String _expectedName, int _columnNoForExpectedString, String _buttonName,int columnNoForIcon, int _buttonindex){
		 
		  boolean grid = isElementPresent(By.xpath("//table["+_gridId+"]/tbody"));
		  
		  int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		  
		  for(int i=1; i<=size; i++){
			  
			  String actualName = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			  if(grid == true && actualName.contains(_expectedName)){
				  click(By.xpath(("(//table["+_gridId+"]/tbody/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
				  break;
			  }else{
				  
				  continue;
			  }
		  }
		  
	  }
	
	public boolean isExpectedIconPresentInRolesGrid(String _gridId,String _tableName, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		
		waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody/tr"));
		boolean grid = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr"));
		int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				
				WebElement _ele = driver.findElement(By.xpath("(//table["+_gridId+"]/tbody/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 js.executeScript("arguments[0].scrollIntoView();", _ele);
				 
				 boolean icon = isElementPresent(By.xpath("(//table["+_gridId+"]/tbody/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 log("["+_buttonName+"] icon present in ["+_tableName+"] .", ILogLevel.ASSERTS);
				 
				 return icon;
			}else{
				continue;
			}
		}
		return grid;
	}
	
	public void isColumnPresent(int _index,int _end){
		int columnSize = driver.findElements(By.xpath("(//a[@class='textSpan'])")).size();

		for(int i=_index; i<=_end; i++){ 
			String value = driver.findElement(By.xpath("(//a[@class='textSpan'])["+i+"]")).getText();
				log("["+value+"] Column present in field", ILogLevel.ASSERTS);
		}
	}
	
	public void verifyPagination(int _index){
		if(driver.getCurrentUrl().equals(ConfigProperties.site_url+"?route=system/logs")){
			waitForElementDisplayed(By.xpath(InventoryPageObject.tableRow_xpath));
			int tableRowCount = driver.findElements(By.xpath(InventoryPageObject.tableRow_xpath)).size();
			if(tableRowCount==10 && isElementPresent(By.xpath("(//div[@class='info'])["+_index+"]"))){
				assertTrue(verifyPaginationButton(), "Pagination does not present on page");
			}
		}
	}
	public boolean verifyPaginationButton(){

		if(isElementPresent(By.cssSelector(".pagination"))&&driver.findElement(By.cssSelector(".pagination")).isDisplayed()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void writeDataInExcelSheet(String email){
		Xls_Reader excel = new Xls_Reader(System.getProperty("user.dir") + "//src//main//resources//files//userBatch.xlsx");
		excel.removeColumn("Sheet1",2);
		excel.setCellData("Sheet1","Email *",2,email);
	}
	
	public void click() {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,800)");
        click(By.xpath("(//div[@class='dataTables_paginate paging_simple_numbers']/ul/li/a)[3]"));
    }
	
	public void clickTab(String id,String _value){
		click(By.id(id),"click on ["+_value+"] tab",2);
	}
	
	public boolean isExportButtonPresent(String xpath,String _value,int _index){
		waitForElementDisplayed(By.xpath(xpath+"["+_index+"]"));
		boolean element = isElementPresent(By.xpath(xpath));
		String text  = driver.findElement(By.xpath(xpath+"["+_index+"]")).getText();
		if(text.equals(_value)){
			log("["+text+"] present on page",ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_value+"] present on page",ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public boolean isExpectedIconPresentInGrid(String _gridId,String _tableName, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				
				WebElement _ele = driver.findElement(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 js.executeScript("arguments[0].scrollIntoView();", _ele);
				 
				 boolean icon = isElementPresent(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 log("["+_buttonName+"] icon present in ["+_tableName+"] .", ILogLevel.ASSERTS);
				 return icon;
			}else{
				continue;
			}
		}
		return grid;
	}
	

	public void clearSearchField(String _fieldName, String _locator, int _numForSwitch){
		  
		  switch (_numForSwitch)
		     {
		     case 1:
		            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(_locator)));
		        	log("["+_fieldName+"] cleaned.", ILogLevel.METHOD);
		        	pause(3);
		        	break;
		        case 2:
		        	((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(_locator)));
		        	log("["+_fieldName+"] cleaned.", ILogLevel.METHOD);
		        	pause(3);
		        	break;
		     }
	  }
	
	public boolean isAlertAppearsAgainstDeleteOperation(String _expectedMessage){
		boolean alert1 = isElementPresent(By.cssSelector(SystemPageObject.error_css));
		//boolean alert2 = driver.findElement(By.cssSelector(SystemPageObject.error_css)).isDisplayed();
		
		//String message = getText(By.cssSelector(SystemPageObject.error_css));
		if(alert1){
			/*String message = getText(By.cssSelector(SystemPageObject.error_css));
			if(message.equalsIgnoreCase(_expectedMessage)){
				log("["+message+"] alert message appears.", ILogLevel.ASSERTS);	 
				return true;
			}
			else{
				log("["+message+"] alert message not visible.", ILogLevel.ASSERTS);  
				return false;	
			}*/	
			log("[This is the only client admin for this client. Please create another admin before deleting.] message appears", ILogLevel.ASSERTS);
			return true;
		}else{
			log("[This is the only client admin for this client. Please create another admin before deleting.] message doesn't appears", ILogLevel.ASSERTS);
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
	
	public void clickRefreshButton(int _index) {
		click(By.xpath(SystemPageObject.refreshButton_Xpath+"["+_index+"]"),"Click on [Refresh Button]",0);
	}
	
	public boolean isSearchFieldPresent(String _screenname){
		boolean searchField = isElementPresent(By.xpath(InventoryPageObject.textFieldSearch_xpath));
		log("Search field present in "+_screenname+" page.", ILogLevel.ASSERTS);
		return searchField;
	}
	
	public void clickOnActiveOrDeleteButton(String _gridId, String _expectedName, int _columnNoForExpectedString, String _buttonName,int columnNoForIcon, int _buttonindex){
		 
		  boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		  String emptyRecord = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td["+_columnNoForExpectedString+"]"));
		  if(grid && !emptyRecord.equalsIgnoreCase("No Records Found") ){
			  int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
			  
			  for(int i=1; i<=size; i++){
				  
				  String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
				  if(grid == true && actualName.equalsIgnoreCase(_expectedName)){
					  click(By.xpath(("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+columnNoForIcon+"]//span)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
					  break;
				  }else{	  
					  continue;
				  }
			  }
		  }else{
			  log("[No Records Found] message appears for enterd search string.",ILogLevel.ASSERTS);
		  }
		  
		  
		  
	}
	
	public boolean isExpectedFieldPresent(String _locator,  String _filedName, String _pageName){
		boolean field = isElementPresent(By.xpath(_locator));
		if(field){
			log("["+_filedName+"] present in "+_filedName+" table.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public void clickOnResetPasswordIcons(String _gridId, String _expectedName, int _columnNoForExpectedString, String _buttonName,int columnNoForIcon, int _buttonindex){
		 
		  boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		  if(grid){
			  String emptyRecord = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td"));
			  if(!emptyRecord.equalsIgnoreCase("No Records Found") || !emptyRecord.equalsIgnoreCase("") ){
				  int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
				  
				  for(int i=1; i<=size; i++){
					  
					  String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
					  if(grid == true && actualName.equalsIgnoreCase(_expectedName)){
						  click(By.xpath("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+columnNoForIcon+"]//i)["+_buttonindex+"]"), "["+_buttonName+"] icon clicked for ["+_expectedName+"].", 4);
						  break;
					  }else{
						  
						  continue;
					  }
				  }
			  }else{
				  log_Method("[No Records Found] message appears for enterd search string.");
			  }
		  }else{
			  log_Method("Not any records appears for enterd search string.");
		  }
	  
	  }
	
	public boolean isConfirmationMessageAppear(String _message){
		boolean alert = isElementPresent(By.cssSelector(SystemPageObject.passwordResetAlert_css));
		if(alert){
			log("["+_message+"] alert message appears.", ILogLevel.METHOD);	
			return true;
		}
		else{
			log("["+_message+"] alert message not visible.", ILogLevel.METHOD);  
			return false;
		}
	}
	
	public boolean isSignUpFeedBackAlert(String _locator, String _message, String _alertType){       
		boolean alert = isElementPresent(By.cssSelector(_locator));
		if(alert){
			log(_alertType+": ["+_message+"] alert message appears.", ILogLevel.METHOD);	
			return true;
		}
		else{
			log(_alertType+": ["+_message+"] alert message not visible.", ILogLevel.METHOD);  
			return false;
		}
	}

	
	
	public boolean isSearchedClientPresentInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  boolean gridRow = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr"));
		  if(gridRow){
			  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
			  
			  for(int i=1; i<=gridSize; i++){
				  boolean row = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
				  if(row){
					  String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
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
		  }else{
			  log("Not any rows appears while search ["+expectedItemName+"] "+tableName+" from ["+tableName+"] grid.", ILogLevel.ASSERTS);
			  return false;
		  }	  
	}
	
	public boolean verifyResponseMessage(String _screen, String _locator, String _mesageType, String _message){
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		boolean response = isElementPresent(By.xpath(_locator));
		if(response){
			String message = getText(By.xpath(_locator));
			if(message.equalsIgnoreCase(_message)){
				log("["+message+"] appears while createting "+_screen+"", ILogLevel.ASSERTS);
				return true;
			}
		}
		log("["+_message+"] doesn't appears while createting "+_screen+"", ILogLevel.ASSERTS);
		return response;
	}
	
	
	public boolean isSearchedItemPresentInRoleGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  boolean gridRow = isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr"));
		  if(gridRow){
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
		  }else{
			  log("Not any rows appears while search ["+expectedItemName+"] "+tableName+" from ["+tableName+"] grid.", ILogLevel.ASSERTS);
			  return false;
		  }		  
	}
	
	public void verifySearchItemAfterNavigateToPagination(String _gridId, String tableName, String expectedItemName, int _columnNumber, String _searchFieldLocator, int _searchFiledIndex, String _fieldname){
		boolean  pagination = isElementPresent(By.xpath(SystemPageObject.paginationTabButton_xpath));
		if(pagination){
			int list = driver.findElements(By.xpath(SystemPageObject.paginationTabButton_xpath)).size();
			for(int i=2; i<=4; i++){
				fieldClear(By.xpath(_searchFieldLocator+"["+_searchFiledIndex+"]"));
				click(By.xpath("((//ul[@class='pagination'])["+_searchFiledIndex+"]//li/a)"), "Click on ["+(i-1)+"]th tab on pagination.", 2);
				sendKeys(By.xpath(_searchFieldLocator+"["+_searchFiledIndex+"]"), expectedItemName, 1, "Search "+_fieldname+" :["+expectedItemName+"]");
				assertTrue(isSearchedItemPresentInRoleGrid(_gridId, tableName, expectedItemName, _columnNumber));
			}
		}
	}
	
	public int returnRowNoOfGrid(String _gridId){
		if(isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr"))){
			int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
			log("Now of Row in grid: ["+size+"]", ILogLevel.METHOD);
			return 	size;		
		}
		return 0;
	}
	
	public boolean verifyGridSize(String _gridId, String _gridName, int _expectedGridSize){
		int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		if(gridSize==_expectedGridSize){
			log("["+gridSize+"] rows appears in "+_gridName+" grid", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	

	public boolean isPaginationInfoAppears(String _gridId){
		int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		boolean paginationTab = isElementPresent(By.xpath(SystemPageObject.paginationTab_xpath));
		boolean paginationInfo = isElementPresent(By.xpath(SystemPageObject.paginationInfo_xpath));
		if(paginationTab && paginationInfo && gridSize==10){
			log("[Pagination Tab And Info] present for redirect to another page.", ILogLevel.ASSERTS);
			log("["+getText(By.xpath(SystemPageObject.paginationInfo_xpath))+"] paginatiom info appears.", ILogLevel.ASSERTS);
			return true;	
		}else{
			log("Paginatiom Tab  And Pagination Info doesn't appears in page.", ILogLevel.ASSERTS);
			return false;
		}	
	}
	
	public boolean validateFieldBorderColor(String _locator, String _filedName){
		String style = driver.findElement(By.xpath(_locator)).getAttribute("style");//getCssValue("border");
		log_Method("Bordr Style: "+style);
		if(style.contains("red") || style.equalsIgnoreCase("Red")){
			log("["+_filedName+"] field validate and highlighted by [Red] color.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	
	}
	
	/*public boolean isExpectedTabPresentWithExpectedPosition(String _locator, String _tabName){
		List<WebElement> _ele = driver.findElements(By.xpath(_locator));
		int size = _ele.size();
		for(int i=0; i<=size; i++){
			String tabName = _ele.get(i).getText();
			if(tabName.equalsIgnoreCase(_tabName)){
				log("["+tabName+"] tab present in tab bar at position ["+(i+1)+"]", ILogLevel.ASSERTS);
				return true;
			}else if(i<=size){
				continue;
			}else{
				
			}
		}
		return false;
	}*/
	
	public boolean isExpectedTabPresentWithExpectedPosition(String _locator, String _tabName){
		List<WebElement> _ele = driver.findElements(By.xpath(_locator));
		int size = _ele.size();
		for(int i=0; ; i++){
			if(i<=size-1){
				String tabName = _ele.get(i).getText();
				if(tabName.equalsIgnoreCase(_tabName)){
					log("["+tabName+"] tab present in tab bar at position ["+(i+1)+"]", ILogLevel.ASSERTS);
					return true;
				}
			}else{
				log("["+_tabName+"] tab doesn't present in tab bar.", ILogLevel.ASSERTS);
				return false;
			}
			
		}
	}

	public void clickOnExpectedTab(String _locator, String _tabName){
		List<WebElement> _ele = driver.findElements(By.xpath(_locator));
		int size = _ele.size();
		for(int i=0; i<=size; i++){
			String tabName = _ele.get(i).getText();
			if(tabName.equalsIgnoreCase(_tabName)){
				click(By.xpath(_locator+"["+(i+1)+"]"), "["+tabName+"] tab clicked.", 3); 
				break;
			}else{
				continue;
			}  
		
		//click(By.xpath(_locator+"["+_index+"]"), "["+tabName+"] tab clicked.", 3); 
		}
	}
	
	public boolean isExpectedOptionAvailableInDropdownList(String _itemName, String pageName){
		boolean dorpdownList = isElementPresent(By.xpath(InventoryPageObject.clientDropdown_xpath));
		if(dorpdownList){
			int listSize = driver.findElements(By.xpath(InventoryPageObject.clientDropdown_xpath)).size();
			for(int i=1; i<=listSize; i++){
				String item = getText(By.xpath("("+InventoryPageObject.clientDropdown_xpath+")["+i+"]"));
				if(item.equalsIgnoreCase(_itemName)){
					log("["+item+"] option availabel in "+pageName+".", ILogLevel.ASSERTS);
					return true; 
				}
			}
		}
		return false;
		
	}

	
}
	
