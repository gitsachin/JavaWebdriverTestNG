package com.auto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SystemPageObject;

public class HelpDeskPage extends BasePage {

	public HelpDeskPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static String name = "";
	public static String ticketName = "";
	public static String countOfComment = "";

	public void enterSearchString(String _key, int _indexOfSearchField, String _fieldName){
		sendKeys(By.xpath("("+HelpDeskTicketsPageObject.assetName_xpath+")["+_indexOfSearchField+"]"), _key, 2, "value ["+_key+"] for field ["+_fieldName+"].");
		driver.findElement(By.xpath(HelpDeskTicketsPageObject.assertdropdown_xpath)).click();

	}

	public void clickButton(String buttonName){
		click(By.linkText(buttonName),"["+buttonName+"] clicked",2);
	}

	public void getTicketNumber(String _gridId, String _expectedName,int columnNoForIcon, String _Name, int _columnNoForExpectedString){ 
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				name = "//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]";
				System.out.println(name);
			}else{
				continue;
			}
		}
	}

	public boolean verifyIsLocationPresent(String _gridId,String _number){
		int numberOfIssueCategoriesInTypeField = driver.findElements(By.xpath("//ul["+_gridId+"]/li")).size();
		for(int i=1;i<=numberOfIssueCategoriesInTypeField;i++){
			String value = driver.findElement(By.xpath("//ul["+_gridId+"]/li["+i+"]")).getText();
			if(value.equals(_number)){
				log("["+value+"] present in return location",ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}

		return false;
	}

	public boolean verifyDataCreatelist(String fieldId,String value){
		boolean ele = isElementPresent(By.id(fieldId));
		String text = driver.findElement(By.id(fieldId)).getText();
		if(text.equals(value)){
			log("["+text+"] present on field",ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+text+"] not present on field",ILogLevel.ASSERTS);
			return false;
		}
	}

	public String getTicketNumber(){
		boolean check = isElementPresent(By.cssSelector(DashboardPageObject.headerTextlog_css));
		if(check){
			String text = getText(By.cssSelector(DashboardPageObject.headerTextlog_css));
			String[] ticket = text.split(" -");
			ticketName = ticket[0];
			log_Method("Ticket Number is: ["+ticketName+"]");
			return ticketName;
		}
		return null;
	}

	public void selectTicket(String value){
		waitForElementDisplayed(By.xpath("//div[@class='all_message-min_text all_message-min_text-3']"));
		int size = driver.findElements(By.xpath("//div[@class='all_message-min_text all_message-min_text-3']")).size();
		for(int i=1;i<=size;i++){
			String text = getText(By.xpath("(//div[@class='all_message-min_text all_message-min_text-3'])["+i+"]"));
			if(text.equals(value)){
				click(By.xpath("//i[@class='fa fa-square-o fa-lg']["+i+"]"));
				break;
			}else{
				continue;
			}
		}
	}

	public void selectText(String _value){
		dropdownSelect(By.id(HelpDeskTicketsPageObject.typeId),_value);
	}

	public boolean verifyDepartmentName(String departmentName){

		List<WebElement> ele = driver.findElements(By.xpath("//table/tbody/tr/td"));

		for(int i=1 ; i<ele.size(); i++){

			String text = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[3]")).getText();
			if(text.equals(departmentName)){
				return true;
			}
		}
		return false;

	}

	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){

		switch (_numForSwitch)
		{
		case 1:
			fieldClear(By.xpath(_locator));
			sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			pause(1);
			break;
		case 2:
			fieldClear(By.cssSelector(_locator));
			sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		}
	}

	public boolean isSearchResultPresent(String _gridId, String _expectedName, int _columnNoForExpectedString){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && size==1 && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				log("No of searched result for search string ["+actualName+"] is: ["+size+"]", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return false;
	}

	public boolean isRolePermissonSet(String _role){
		boolean table = isElementPresent(By.xpath("//table[@id='rolesTable']/tbody/tr"));
		String role = getText(By.xpath("//table[@id='rolesTable']/tbody/tr/td[1]"));
		if(table && role.contains(_role)){
			log("Role permission set", ILogLevel.ASSERTS);
			return true;
		}else{
			log("Role permission doesn't set.", ILogLevel.ASSERTS);
			return false;
		}
	}

	public void selectItemFrmDropdownList(String _role){
		dropdownSelect(By.xpath(HelpDeskTicketsPageObject.selectRole_xpath), _role);
		log("Role: ["+_role+"] selected.", ILogLevel.METHOD);
	}

	public boolean isExpectedOptionPresentInList(String _expectedPermission){
		click(By.xpath(HelpDeskTicketsPageObject.dropdownPermission_xpath));
		int listSize = driver.findElements(By.xpath(HelpDeskTicketsPageObject.noOfItemInPermissionList_xpath)).size();
		for(int i=1; i<=listSize; i++){
			String _permission = getText(By.xpath(HelpDeskTicketsPageObject.noOfItemInPermissionList_xpath+"["+i+"]")).trim();
			if (_permission.equalsIgnoreCase(_expectedPermission)) {
				log("["+_permission+"] available in permission list.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return true;      
	}

	public boolean isExpectedFieldPresent(String _fieldName, String _locator){
		boolean field = isElementPresent(By.xpath(_locator));
		if(field){
			log("["+_fieldName+"] field present in ticket pop-up.", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_fieldName+"] field doesn't present in ticket pop-up.", ILogLevel.ASSERTS);
			return false;
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

	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		boolean ele = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr"));
		if(ele){

			String rowText = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td"));

			if(!rowText.equalsIgnoreCase("No Records Found")){
				int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
				for(int i=1; i<=gridSize; i++){
					String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));

					if(name.equals(expectedItemName)){
						log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
						return true;
					}else{
						continue;
					}
				}

			}else if(rowText.equalsIgnoreCase("") ){
				log_Method("Not any text appears in row for enterd search string "+expectedItemName+".");
				return false;
			}else{
				log_Method("["+rowText+"] message appears for enterd search string "+expectedItemName+"."); 
				return false;
			}

		}else{
			log_Method("Not any records appears for enterd search string.");
			return false;
		}	
		return ele;	
	}

	public void clickCloseTicket(){
		click(By.cssSelector(HelpDeskTicketsPageObject.closeTicket_css),"Click on close ticket x icon",0);
	}

	public void countNumberOfComment(){
		boolean ele = isElementPresent(By.cssSelector(HelpDeskTicketsPageObject.timelineHeader_css));
		int getSize = driver.findElements(By.cssSelector(HelpDeskTicketsPageObject.timelineHeader_css)).size();
		countOfComment = Integer.toString(getSize);
	}

	public boolean verifyImageNotUploadInTicket(){
		boolean ele = isElementPresent(By.cssSelector(HelpDeskTicketsPageObject.timelineHeader_css));
		int size = driver.findElements(By.cssSelector(HelpDeskTicketsPageObject.timelineHeader_css)).size();

		String value = Integer.toString(size); 

		if(countOfComment.equals(value)){
			log("Image does not upload in ticket",ILogLevel.ASSERTS);
			return true;

		}
		return false;

	}



}
