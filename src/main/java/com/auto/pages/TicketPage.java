package com.auto.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SettingPageObject;
import com.auto.pageobject.SignUpPageObject;

public class TicketPage extends BasePage{

	public TicketPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//public static String ticketMail = "";

	public void clickSearchOption(){
		click(By.cssSelector(HelpDeskTicketsPageObject.assetName_css), "Option selected", 2);
	}

	public void deleteAllMail(){
		List<WebElement> els = driver.findElements( By.cssSelector(HelpDeskTicketsPageObject.mailCheckbox_css) );
		for ( WebElement el : els ) {
			if ( !el.isSelected() ) {
				el.click();
			}
		}
		click(By.cssSelector(SignUpPageObject.deleteMail_css),"[Mail] deleted", 3);
	}

	public void enterInput(String _key){

		sendKeys(By.xpath(HelpDeskTicketsPageObject.textFieldName_xpath), _key, 1, "department name: ["+_key+"]");

	}

	public void clickButton(String _subMenu){

		waitForElementDisplayed(By.xpath(SignUpPageObject.ButtonTagA_xpath+_subMenu+SignUpPageObject.ButtonTagA1_xpath));
		click(By.xpath(SignUpPageObject.ButtonTagA_xpath+_subMenu+SignUpPageObject.ButtonTagA1_xpath), "["+_subMenu+"] clicked", 3);
	}

	public void clickWithScroll(String _button){
		if(isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath))){
			pause(3);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath)));
			click(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath), _button+" button", 1);
		}

	}

	public void createDeartmentIfNotPresentInGrid(String _expectedDeptName, String _buttonName, String _buttonCreate){
		int gridSize = driver.findElements(By.xpath(HelpDeskTicketsPageObject.departmentGrid)).size();
		for(int i=1; i<=gridSize; i++){
			String deptName = getText(By.xpath("(//div[@class='box-body'])[1]/div/table/tbody/tr["+i+"]/td[3]"));
			if(deptName.equals(_expectedDeptName)){
				log("Expected Department: ["+deptName+"] is present in grid.", ILogLevel.METHOD);
				break;
			}else if(i!=gridSize){
				continue;
			}else{

				clickButton(_buttonName);
				enterInput(_expectedDeptName);
				clickWithScroll(_buttonCreate);
			}
		}

	}


	public void clickOnArrowIconForDepartmentField(){

		click(By.xpath(HelpDeskTicketsPageObject.arrowIconDepartmentField), "Department arrow icon.", 2);
	}

	public void enterSearchString(String _departName) throws AWTException{
		sendKeys(By.xpath(HelpDeskTicketsPageObject.searchDepartmentField), _departName, 2, "department name: ["+_departName+"].");

		driver.findElement(By.xpath(HelpDeskTicketsPageObject.searchDepartmentField)).sendKeys(Keys.ENTER);
		pause(2);

	}


	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){

		switch (_numForSwitch) //For xpath: enter 1, For cssSelector: Enter 2
		{
		case 1:
			fieldClear(By.xpath(_locator));
			sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			pause(1);
			break;
		case 2:
			fieldClear(By.xpath(_locator));
			sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			pause(1);
			break;
		}
	}

	public boolean isExpectedPopupOpen(String _expectedText, int _index){
		boolean content = isElementPresent(By.xpath("("+InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2+")["+_index+"]"));
		log_Method("Pop-up name: ("+InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2+")["+_index+"]");
		if(content){
			String text = getText(By.xpath("("+InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2+")["+_index+"]"));
			if(text.equalsIgnoreCase(_expectedText)){
				log("["+_expectedText+"] pop-up opens.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+text+"] pop-up Close.", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("["+_expectedText+"] pop-up Close.", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean isSuggestionListAppear(String _expectdAssetName, String _notFoundText){
		boolean suggestionList = isElementPresent(By.xpath(HelpDeskTicketsPageObject.listAsset_xpath));

		if(suggestionList){
			int noOfItemInList = driver.findElements(By.xpath(HelpDeskTicketsPageObject.listAsset_xpath+"/li")).size();

			if(noOfItemInList>1){
				String list = getText(By.xpath(HelpDeskTicketsPageObject.listItem_xpath));
				if(list.contains(_expectdAssetName)){
					log("Asset suggestion list is displaying according enterd string and ["+noOfItemInList+"] items present in list.", ILogLevel.ASSERTS);
					return true;
				}

			}else{
				String text = getText(By.xpath(HelpDeskTicketsPageObject.notFound_xpath));
				if(suggestionList && text.equals(_notFoundText)) {
					log("["+text+"] is displaying according to enterd invalid string.", ILogLevel.ASSERTS);
					//click(By.xpath(HelpDeskTicketsPageObject.notFound_xpath), "[No Asset Found] clicked", 2);
					return true;	
				}
			}
		}else{
			return false;
		}
		return suggestionList;	
	}



	public boolean isFieldValidate(){
		boolean suggestionList = isElementPresent(By.xpath(HelpDeskTicketsPageObject.textFieldAsset_xpath));
		String PlaceHolder = driver.findElement(By.xpath(HelpDeskTicketsPageObject.textFieldAsset_xpath)).getAttribute("placeholder");
		if(suggestionList && PlaceHolder.equals("Select Asset First")){
			log("Asset is [Required field].", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}

	}

	public void enterSerialNo(String _key, String _fieldName, int _index){
		sendKeys(By.xpath(HelpDeskTicketsPageObject.textFieldAsset_xpath), _key, 1, "Asset: ["+_key+"]");
		clicks(By.cssSelector(HelpDeskTicketsPageObject.assetName_css), _index, "Select: ["+_fieldName+"]", 3);
	}

	public void deleteItemFromGrid(int _tableIndex, String _searchingItemName, int _searchingItemColumnNo, String _operation, String _operationButtonName, int _operationButtonColumnNo, int _buttonIndex){

		boolean grid = isElementPresent(By.xpath("(//table)["+_tableIndex+"]/tbody/tr"));
		int size = driver.findElements(By.xpath("(//table)["+_tableIndex+"]/tbody/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("(//table)["+_tableIndex+"]/tbody/tr["+i+"]/td["+_searchingItemColumnNo+"]"));
			if(grid == true && actualName.equals(_searchingItemName) || actualName.contains(_searchingItemName)){
				String saveLocator = "((//table)["+_tableIndex+"]/tbody/tr["+i+"]/td["+_operationButtonColumnNo+"]//a/i)["+_buttonIndex+"]";

				((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(saveLocator)));
				log("["+_operationButtonName+"] icon clicked for ["+_operation+"].", ILogLevel.METHOD);
				pause(2);
				break;
			}else{
				continue;
			}
		}
	}

	public boolean isButtonPresent(String _button) {
		waitForElementEnabled(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath));
		boolean actionButton = isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath));

		if(actionButton) {
			driver.findElement(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath)).click();
			return true;
		}else {
			return false;
		}
	}

	public boolean verifyElementOnActionDropdown(int _index,String _value) {


		boolean dropdown = isElementPresent(By.xpath(InventoryPageObject.actionDropdown_Xpath));
		String text = driver.findElements(By.xpath(InventoryPageObject.actionDropdown_Xpath)).get(_index).getText();
		String action = driver.findElement(By.xpath(InventoryPageObject.buttonBatchUploadWithOutIndex_xpath)).getText();

		if(text.equals(_value)) {
			log("["+text+"]"+ "is present in action dropdown", ILogLevel.ASSERTS);
			return true;
		}else if(action.equals(_value)){
			log("["+action+"]" +"is present in action dropdown", ILogLevel.ASSERTS);
			return true;
		}else{
			log("search data not present in action dropdown", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean verifyFieldInActionDropdown(String _value){
		boolean dropdown = isElementPresent(By.xpath(InventoryPageObject.actiondropdown_xpath));
		String button = driver.findElement(By.xpath(InventoryPageObject.actiondropdown_xpath)).getText();

		if(button.equals(_value)){
			log("["+button+"]"+ "is present in action dropdown", ILogLevel.ASSERTS);
			return true;
		}else{
			log("search data not present in action dropdown", ILogLevel.ASSERTS);
			return false;
		}
	}

	public void verifyMail(){
		boolean present = isElementPresent(By.xpath("//b"));
		int size = driver.findElements(By.xpath("//b")).size();
		if(present){
			for(int i=1;i<=size;i++){
				String name = driver.findElement(By.xpath("//b["+i+"]")).getText();
				log("["+name+"] present on mail",ILogLevel.METHOD);
			}
		}
	}

	public boolean verifyStatusOfTicket( String tableName, String expectedItemName, int _columnNumber){
		waitForElementDisplayed(By.xpath("//table[@id='ticketDetailsTable']/tbody")); 
		int gridSize = driver.findElements(By.xpath("//table[@id='ticketDetailsTable']/tbody/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//table[@id='ticketDetailsTable']/tbody/tr["+i+"]/td["+_columnNumber+"]/b"));
			System.out.println(name);
			if(name.equals(expectedItemName)){
				String getStatus = getText(By.xpath("//table[@id='ticketDetailsTable']/tbody/tr[1]/td[2]/span"));
				log("["+name+"] is ["+getStatus+"] grid.", ILogLevel.ASSERTS);
				break;
			}else{
				continue;
			}
		}
		return true;
	}

	public String getTicket(String _locator){
		waitForElementDisplayed(By.xpath(_locator));
		String ticketMail = getText(By.xpath(_locator));
		return ticketMail;

	}

	public boolean isExpectedCheckboxFieldAppears(String _checkFieldName, String _locator, int _index){
		if(isElementPresent(By.xpath("("+_locator+")["+_index+"]"))){
			String field = getText(By.xpath("("+_locator+")["+_index+"]"));
			if(field.equalsIgnoreCase(_checkFieldName)){
				log("["+field+"] field present.", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;
	}

	public void clickOnExpectedCheckbox(String _locator, String _checkBoxName, int _index){	
		if(isElementPresent(By.xpath("//input[@name='fieldRequired']"))){
			boolean fieldChecked = driver.findElement(By.xpath("//input[@name='fieldRequired']")).isSelected();
			if(fieldChecked){
				log_Method("["+_checkBoxName+"] field already checked.");
			}else{
				click(By.xpath("//input[@name='fieldRequired']"), "["+_checkBoxName+"] field checked.", 3);	
			}	
		}else{
			log_Method("["+_checkBoxName+"] checkbox field doesn't present.");
		}
	}

	public void clickOnExpectedCheckboxToUncheck(String _locator, String _checkBoxName, int _index){	
		if(isElementPresent(By.xpath("//input[@name='fieldRequired']"))){
			boolean fieldChecked = driver.findElement(By.xpath("//input[@name='fieldRequired']")).isSelected();
			if(fieldChecked){
				click(By.xpath("//input[@name='fieldRequired']"), "["+_checkBoxName+"] field unchecked.", 3);				
			}else{
				log_Method("["+_checkBoxName+"] field already unchecked.");
			}	
		}else{
			log_Method("["+_checkBoxName+"] checkbox field doesn't present.");
		}
	}

	public boolean isExpectedFiledPresent(String _screenName, String _fieldname){
		boolean field = isElementPresent(By.xpath("//label[contains(text(), '"+_fieldname+"')]"));
		if(field){
			log("["+_fieldname+"] field is presnt on "+_screenName+".", ILogLevel.ASSERTS);
			return true;
		}
		return false;
	}

	public boolean isExpectedFieldRequiredField(String _fieldname, int _index){	
		boolean field = isElementPresent(By.xpath("//input[@required]"));
		if(field){
			fieldClear(By.xpath("(//input[@required])["+_index+"]"));
			log("["+_fieldname+"] field works as a Required field.", ILogLevel.ASSERTS);
			return true;
		}
		return false;

	}

	public boolean isExpectedFieldPresentInDetailViewPage(String _locator,String _expectedFieldName){
		int size = driver.findElements(By.xpath("//table["+_locator+"]/tbody/tr//td[1]")).size();
		for(int i=1; i<=size; i++){
			String fieldName = getText(By.xpath("//table["+_locator+"]/tbody/tr["+i+"]//td[1]"));
			if(fieldName.contains(_expectedFieldName)){
				log("Expected field ["+fieldName+"] present in ["+i+"] no position.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue; 
			}
		}
		log("Expected field ["+_expectedFieldName+"] doesn't present.", ILogLevel.ASSERTS);
		return false;
	}

	public boolean isSearchedItemPresentInTicketGrid(String tableName, String expectedItemName, int _columnNumber){
		waitForElementDisplayed(By.xpath("(//table/tbody/tr)[2]")); 

		int gridSize = driver.findElements(By.xpath("(//table/tbody/tr)[2]")).size();

		for(int i=1; i<=gridSize; i++){
			boolean row = isElementPresent(By.xpath("(//tbody/tr["+i+"]/td["+_columnNumber+"])[2]"));
			if(row){
				String name = getText(By.xpath("(//tbody/tr["+i+"]/td["+_columnNumber+"]/a)[2]"));
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

	public void clickOnViewEditDelIconsofTicket(String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		waitForElementDisplayed(By.xpath("(//table/tbody/tr)[2]"));
		boolean grid = isElementPresent(By.xpath("(//table/tbody/tr)[2]"));
		int size = driver.findElements(By.xpath("(//table/tbody/tr)[2]")).size();
		if(grid){
			for(int i=1; i<=size; i++){
				String actualName = getText(By.xpath("(//table/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"])[2]"));
				if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
					WebElement icon = driver.findElement(By.xpath("(//table/tbody/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].click();", icon);
					log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
					pause(2);
					break;
				}else{
					continue;
				}
			}
		}else{
			log("No date present in grid to perform ["+_Name+"]", ILogLevel.METHOD);
		}
	}

	public void searchTicket(String _locator, String _key,int _index){
		sendKey(By.cssSelector(_locator), _key, _index, 1, "Enter Ticket: "+_key);

	}

	public boolean isEdxpectedFieldsReadOnlyField(String _fieldName, int _index){
		boolean readOnlyField = isElementPresent(By.xpath("("+HelpDeskTicketsPageObject.fieldsReadOnly_xpath+")["+_index+"]"));
		if(readOnlyField){
			log("["+_fieldName+"] is Read Only field.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public void mouseOverOnElement(String _locator){
		waitForElementDisplayed(By.xpath(_locator));
		WebElement element = driver.findElement(By.xpath(_locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		pause(2);
	}

	public boolean isTooltipAppearForExpectedElement(String _locator, String _tooltipName, String _screenName){
		boolean tooltip = isElementPresent(By.xpath(_locator));
		if(tooltip){
			String tooltipName = driver.findElement(By.xpath(_locator)).getAttribute("data-original-title");
			if(tooltipName.equalsIgnoreCase(_tooltipName)){
				log("["+tooltipName+"] tooltip displayed in "+_screenName+" screen.", ILogLevel.ASSERTS);
				return true;
			}else{
				return false;
			}
		}
		log("["+_tooltipName+"] tooltip doesn't displayed in "+_screenName+" screen.", ILogLevel.ASSERTS);
		return tooltip;
	}

	public boolean verifyAssignee(String _clientField, int _clientFieldIndex, String _clientName, String _assigneeToField, int _assigneeToFieldIndex, String _assigneeName){
		String client = getText(By.xpath(HelpDeskTicketsPageObject.fieldsTickets_xapth+"["+_clientFieldIndex+"]")); 
		if(client.equalsIgnoreCase(_clientName)){
			String assignee = getText(By.xpath(HelpDeskTicketsPageObject.fieldsTickets_xapth+"["+_assigneeToFieldIndex+"]"));
			if(assignee.equalsIgnoreCase(_assigneeName)){
				log("When client is ["+client+"], Assign To is: ["+assignee+"]", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;
	}

	public boolean verifyAssetBySerialNumber(String expectedSerialNumber, String assets[]){
		int assetListSize = driver.findElements(By.cssSelector(HelpDeskTicketsPageObject.serialAssetOptions_css)).size();
		log_Method("0 list size: "+assetListSize);
		for(int i=0; i<=assetListSize-1; i++){
			log_Method("1");
			List<WebElement> ele = driver.findElements(By.cssSelector(HelpDeskTicketsPageObject.serialAssetOptions_css));
			String options = ele.get(i).getText();
			log_Method("2 Option: "+options);
			if(assetListSize ==2 && options.contains(assets[i])){
				log("Multiple Asset: ["+options+"] available when Serial Number is same: ["+expectedSerialNumber+"]", ILogLevel.TEST);
			}
		}
		return true;
	}

	public void clickOnArrowIconOfFields(String _fieldName, int _index){	
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.arrowIconDepartmentField+")["+_index+"]"));
		click(By.xpath("("+InventoryPageObject.arrowIconDepartmentField+")["+_index+"]"), "["+_fieldName+"] arrow icon clicked.", 1);
	}

	public void enterSearchString(String _key, int _indexOfSearchField, String _fieldName){
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"));
		driver.findElement(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(_key);
		pause(1);
		driver.findElement(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]")).sendKeys(Keys.ENTER);
		log("Enter value ["+_key+"] for field ["+_fieldName+"].", ILogLevel.METHOD);
		pause(3);
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
			//click(By.cssSelector(_locator), "["+_buttonName+"] button clicked.", 2);
			pause(4);
			break;
		}
	}

	public boolean isSuggestionListAppearOnStatus(String locator,String _expectdStatusName){
		boolean suggestionList = isElementPresent(By.xpath(locator));
		int noOfItemInList = driver.findElements(By.xpath(locator)).size();

		for(int i=1;i<=noOfItemInList;i++){
			String list = getText(By.xpath(locator+"["+i+"]"));

			if(list.equals(_expectdStatusName)){
				log("["+list+"] present in status dropdown", ILogLevel.TEST);
				return true;
			}else{
				continue;
			}
		}
		return false;
	}

	public void clickOnStausColumn(){
		click(By.xpath(InventoryPageObject.statusColumn_xpath),"Click on [Status] column",2);
	}

	public void clickOnStatusFilterIcon(String _filterIconName){
		click(By.xpath(HelpDeskTicketsPageObject.status_xpath+"[contains(text(),'"+_filterIconName+"')]"),"Click on ["+_filterIconName+"]",2);

	}

	public void clickOnApplyButton(){
		click(By.xpath(HelpDeskTicketsPageObject.applyButton_xpath),"Apply button does not clickable",0);
	}

	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
		if(grid){
			for(int i=1; i<=size; i++){
				String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
				if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
					WebElement icon = driver.findElement(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].click();", icon);
					log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
					pause(2);
					break;
				}else{
					continue;
				}
			}
		}else{
			log("No date present in grid to perform ["+_Name+"]", ILogLevel.METHOD);
		}
	}

	public String returnTicketStatus(String _gridId, String _expectedName, int _ticketNameColumn, int _statusColumn){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		if(grid){
			int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
			for(int i=1; i<=size; i++){
				String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_ticketNameColumn+"]"));
				if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){

					String searchedTicketStatus = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_statusColumn+"]/span"));
					log("Current Status: ["+searchedTicketStatus+"]", ILogLevel.METHOD);
					return searchedTicketStatus;
				}else{
					continue;
				}
			}
		}else{
			log("No date present in grid.", ILogLevel.METHOD);
			return null;
		}
		return null;
	}

	public String returnTextFromExpectedBlock(String _locator){
		boolean block = isElementPresent(By.xpath(_locator));
		if(block){
			String text = getText(By.xpath(_locator));
			return text;
		}
		return null;
	}

	public boolean isTextUpdateInExpectedBlock(String _locator, String _blockName, String _oldText){
		waitForElementDisplayed(By.xpath(_locator));
		boolean block = isElementPresent(By.xpath(_locator));
		if(block){	
			String text = getText(By.xpath(_locator));
			if(!text.equalsIgnoreCase(_oldText)){
				log(_blockName+": Updated from ["+_oldText+"] to ["+text+"]", ILogLevel.ASSERTS);
				return true;
			}
		}
		return block;
	}

	public void selectOption(int _index,String _text){

		driver.findElement(By.xpath(SettingPageObject.dropdown+"["+_index+"]")).click();

		log("Dropdown Clicked", ILogLevel.TEST);
		//clicks(By.xpath(SettingPageObject.optionList_xpath+_text+SettingPageObject.optionList1_xpath), _index,_text+" Selected", 3);
		click(By.xpath(SettingPageObject.optionList_xpath+_text+SettingPageObject.optionList1_xpath), _text+" Selected", 3);
	}

	public String returnvalueOfExpectedField(String _filedname, String _locator, String _initialOrSet){
		String fieldValue = getText(By.xpath(_locator));
		if(!fieldValue.equals("")){
			log(_initialOrSet+" "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return fieldValue;
		}else{
			log(_initialOrSet+" "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return fieldValue;
		}
	}

	public boolean isFiledvalueCarried(String _filedname, String _locator, String _expectedValue){
		waitForElementDisplayed(By.xpath(_locator));
		String fieldValue = getText(By.xpath(_locator));
		if(fieldValue.equalsIgnoreCase(_expectedValue)){
			log("["+fieldValue+"] value present in "+_filedname+" field.", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+fieldValue+"] value present in "+_filedname+" field.", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean isAssetInfoCarriedOver(String _filedname, String _locator, String _expectedValue){
		waitForElementDisplayed(By.xpath(_locator));
		String fieldValue = driver.findElement(By.xpath(_locator)).getAttribute("value");
		log_Method("Get Text: "+fieldValue);
		log_Method("Argument: "+_expectedValue);
		if(_expectedValue.contains(fieldValue)){
			//fieldValue.equalsIgnoreCase(_expectedValue) || 
			log(_filedname+" field value ["+fieldValue+"] carry out to another page.", ILogLevel.ASSERTS);
			return true;
		}
		return false;
	}

	public boolean verifyEnterLastMessagePresentInMessageHistory(String _expectedMessage){
		boolean messageBlocak = isElementPresent(By.xpath("//div[@class='timeline-body']//p"));
		if(messageBlocak){
			int historySize = driver.findElements(By.xpath("//div[@class='timeline-body']//p")).size();
			for(int i=1; i<=historySize; i++){
				String actualMessage = getText(By.xpath("(//div[@class='timeline-body']//p)["+i+"]"));
				if(actualMessage.equalsIgnoreCase(_expectedMessage) || actualMessage.contains(_expectedMessage)){
					log("Last entered message ["+actualMessage+"] appears in meaasge history.", ILogLevel.ASSERTS);
					return true;
				}else{
					return false;
				}
			}

		}
		log("Ticket Meaasge history empty.", ILogLevel.ASSERTS);
		return messageBlocak;
	}

	public boolean isExpectedButtonPresent(String _locator, String _buttonName, String _screenName){
		boolean button = isElementPresent(By.xpath(_locator));
		boolean buttonDisplay = driver.findElement(By.xpath(_locator)).isDisplayed();
		if(button && buttonDisplay){
			log("["+_buttonName+"] button present in "+_screenName+".", ILogLevel.ASSERTS);
			return button;
		}else{
			log("["+_buttonName+"] button doesn't present in "+_screenName+".", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean verifyGridRecord(String _locator, String _searchItem, int _expectedGridSize){
		int size = driver.findElements(By.xpath("//table/tbody["+_locator+"]/tr")).size();
		for(int i=1; i<=size; i++){
			String ticket = getText(By.xpath("//table/tbody["+_locator+"]/tr["+i+"]/td[2]"));
			String newString = ticket.replace("_", " ");
			if(newString.contains(_searchItem.replace("_", " ")) || newString.equals(_searchItem.replace("_", " "))){
				log("Search Result: ["+ticket+"] present in ticket grid.", ILogLevel.ASSERTS);
				if(i==_expectedGridSize){
					log("Grid Size: ["+size+"] after search.", ILogLevel.ASSERTS);
					return true;
				}
			}else{
				continue;
			}
		}
		return false;
	}

	public void clickOndDelIconsIfItemPresentInGrid(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		pause(1);
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		if(grid){
			String text = getText(By.xpath("//table/tbody["+_gridId+"]/tr"));
			if(!text.equals("No Records Found")){
				int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
				for(int i=1; i<=size; i++){
					String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
					if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
						WebElement icon = driver.findElement(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
						JavascriptExecutor js = (JavascriptExecutor)driver;
						js.executeScript("arguments[0].click();", icon);
						log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
						click(By.xpath(SignUpPageObject.Button_xpath +"Yes"+ SignUpPageObject.Button1_xpath), "[Yes] button clicked", 5);
						pause(2);
						log("["+actualName+"]: Ticket delete from Grid", ILogLevel.TEST);
						break;
					}
				}
			}else{
				log("No date present in grid to perform ["+_Name+"]", ILogLevel.METHOD);
			}
		}
	}

	public boolean isExpectedTabPresentWithExpectedPosition(String _tabName){
		//List<WebElement> _ele = driver.findElements(By.xpath(HelpDeskTicketsPageObject.pathA1_xpath+_tabName+HelpDeskTicketsPageObject.pathA2_xpath));
		List<WebElement> _ele = driver.findElements(By.cssSelector(HelpDeskTicketsPageObject.tabTicketSetting_css));
		int size = _ele.size();
		for(int i=0; i<=size; i++){
			String tabName = _ele.get(i).getText();
			if(tabName.equalsIgnoreCase(_tabName)){
				log("["+tabName+"] tab present in tab bar at position ["+(i+1)+"]", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}  
		}
		return false;
	}

	public boolean isExpectedPageOpen(String _expectedText){
		waitForElementDisplayed(By.xpath(InventoryPageObject.pageheaderText_xpath1+_expectedText+InventoryPageObject.pageheaderText_xpath2));
		boolean content = isElementPresent(By.xpath(InventoryPageObject.pageheaderText_xpath1+_expectedText+InventoryPageObject.pageheaderText_xpath2));
		if(content){
			String text = getText(By.xpath(InventoryPageObject.pageheaderText_xpath1+_expectedText+InventoryPageObject.pageheaderText_xpath2));
			if(text.equalsIgnoreCase(_expectedText) || text.contains(_expectedText)){
				log("["+text+"] page opens/appears.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+text+"] page doesn't open.", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("["+_expectedText+"] page Close.", ILogLevel.ASSERTS);
			return false;
		}
	}
	
	
	public boolean verifyCreateTicketThreeStepWizard(String _locator, String _popupName){
		boolean popup = isElementPresent(By.xpath(_locator));
		if(popup){
			String text = getText(By.xpath(_locator+"/h4"));
			if(text.equalsIgnoreCase(_popupName)){
				String step[] = getText(By.xpath(_locator)).split("\n");
				String header = step[1]+" : "+step[2];
				log("["+header+"] pop-up opens.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+text+"] pop-up doesn't open.", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("["+_popupName+"] pop-up Close.", ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public boolean isExpectedFieldLabelPresent(String _expectedLabel, int _index){

		waitForElementDisplayed(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])["+_index+"]"));

		boolean expectedField = isElementPresent(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));
		String label = getText(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])["+_index+"]"));

		if(expectedField){

			if(label.equals(_expectedLabel)){
				log("["+label+"] is present on Create New Asset form", ILogLevel.ASSERTS);
				return true;
			}
		}else{
			log("["+label+"] doesn't present on Create New Asset form", ILogLevel.ASSERTS);
			return false;
		}

		return expectedField;

	}
	
	public boolean isExpectedOptionAvailableInDropdownList(String _itemName, String _fieldName, String _screenName){
		boolean dorpdownList = isElementPresent(By.xpath(InventoryPageObject.clientDropdown_xpath));
		if(dorpdownList){
			int listSize = driver.findElements(By.xpath(InventoryPageObject.clientDropdown_xpath)).size();
			for(int i=1; i<=listSize; i++){
				String item = getText(By.xpath("("+InventoryPageObject.clientDropdown_xpath+")["+i+"]"));
				if(item.equalsIgnoreCase(_itemName)){
					log("["+item+"] option availabel in "+_fieldName+" at ["+i+"th] position on "+_screenName+".", ILogLevel.ASSERTS);
					return true; 
				}
			}
		}
		return false;
	}
	
	public void clickOnExpectedTab(String _tabName){
		waitForElementDisplayed(By.xpath(HelpDeskTicketsPageObject.tabTicketPage1_xpath+_tabName+HelpDeskTicketsPageObject.pathA2_xpath));
		click(By.xpath(HelpDeskTicketsPageObject.tabTicketPage1_xpath+_tabName+HelpDeskTicketsPageObject.pathA2_xpath), "Click on ["+_tabName+"] tab", 1);
	}
	
	public boolean isCommentAdded(String _expectedComment){
		boolean comment = isElementPresent(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath));
		if(comment){
			int elementSize = driver.findElements(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath)).size();
			for(int i=1; i<=elementSize; i++){
				String actualComment = getText(By.xpath("("+HelpDeskTicketsPageObject.addedComment_xpath+")["+i+"]"));
				if(actualComment.equalsIgnoreCase(_expectedComment)){
					log("["+actualComment+"] added in Comment history", ILogLevel.ASSERTS);
					return true;
				}
			}
		}
		return comment;
	}
	
	public boolean isEditDeleteButtonApperInPostedComment(String _expectedComment, String _buttonName){
		boolean comment = isElementPresent(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath));
		if(comment){
			int elementSize = driver.findElements(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath)).size();
			for(int i=1; i<=elementSize; i++){
				String actualComment = getText(By.xpath("("+HelpDeskTicketsPageObject.addedComment_xpath+")["+i+"]"));
				if(actualComment.equalsIgnoreCase(_expectedComment)){
					boolean expectedButon = isElementPresent(By.xpath("("+HelpDeskTicketsPageObject.posetdCommentButtons_xpath+")["+i+"]/a[contains(text(), '"+_buttonName+"')]"));
					if(expectedButon){
						String button = getText(By.xpath("("+HelpDeskTicketsPageObject.posetdCommentButtons_xpath+")["+i+"]/a[contains(text(), '"+_buttonName+"')]"));
						log("["+button+"] button appears in "+actualComment+" section.", ILogLevel.ASSERTS);
						return true;
					}else{
						log("["+_buttonName+"] button doesn't appear in '"+actualComment+"' section.", ILogLevel.ASSERTS);
						return false;
					}
				}else if(i==elementSize){
					log("["+actualComment+"] comment doesn't appear under comment tab.", ILogLevel.ASSERTS);
					return false;
				}
			}
		}
		return comment;
	}
	
	public void enterComment(String _comment){
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(HelpDeskTicketsPageObject.commentField_xpath));
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(_comment);
		actions.build().perform();
		log("Enter Comment "+_comment, ILogLevel.METHOD);
	}
	
	public void clickOnEdit_DeleteCommentButton(String _expectedComment, String _buttonName){
		boolean comment = isElementPresent(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath));
		if(comment){
			int elementSize = driver.findElements(By.xpath(HelpDeskTicketsPageObject.addedComment_xpath)).size();
			for(int i=1; i<=elementSize; i++){
				String actualComment = getText(By.xpath("("+HelpDeskTicketsPageObject.addedComment_xpath+")["+i+"]"));
				if(actualComment.equalsIgnoreCase(_expectedComment)){
					boolean expectedButon = isElementPresent(By.xpath("("+HelpDeskTicketsPageObject.posetdCommentButtons_xpath+")["+i+"]/a[contains(text(), '"+_buttonName+"')]"));
					if(expectedButon){
						String button = getText(By.xpath("("+HelpDeskTicketsPageObject.posetdCommentButtons_xpath+")["+i+"]/a[contains(text(), '"+_buttonName+"')]"));
						click(By.xpath("("+HelpDeskTicketsPageObject.posetdCommentButtons_xpath+")["+i+"]/a[contains(text(), '"+_buttonName+"')]"));
						log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
						pause(2);
					}
				}else if(i==elementSize){
					log("["+actualComment+"] comment doesn't appear under comment tab.", ILogLevel.ASSERTS);
					
				}
			}
		}
	}

	


}

