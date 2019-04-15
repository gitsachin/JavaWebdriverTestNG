package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.RolePageObject;
import com.auto.pageobject.SignUpPageObject;

public class RolePage extends BasePage{

	public RolePage(WebDriver driver) {
		super(driver);
	}

	public static String value = "";
	public static String actualName = "";
	public static String tagName = "";

	public void clickOnClient(){
		click(By.id(RolePageObject.client_id),"Client field click",3);
	}

	public void clickOnView(String _gridId, String _expectedName, int _buttonindex,int _index,String _buttonName,String _Name, int _columnNoForExpectedString){

		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"])"+"["+_index+"]"));
			if(actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				String saveLocator = "(//table/tbody["+_gridId+"]//a/i["+_buttonindex+"])";
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(saveLocator)));
				log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
				pause(2);
				break;
			}else{
				continue;
			}
		}
	}

	public void verfyRole(String _gridId, String _expectedName,int _columnNoForExpectedString){

		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
		System.out.print(2);

		for(int i=1; i<=size; i++){
			actualName = getText(By.xpath("(//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"])"));
			if(actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				log("["+actualName+"]"+ "is present on table.",ILogLevel.METHOD);
				break;
			}else{
				continue;
			}
		}
	}

	public void clickRoleDeletIcon(String _gridId,int _buttonindex,int _index ){
		click(By.xpath("(//table/tbody["+_gridId+"]//a/i["+_buttonindex+"])"+"["+_index+"]"));
	}

	public void getTable(String _gridId,int _index) {

		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).toString().length();
		value = Integer.toString(size);
		actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td["+_index+"]"));
	}
	
	public void getNumberOfRoles(String _gridId,int _index,String client) {

		boolean ele =  isElementPresent(By.xpath("//table["+_gridId+"]/tbody/tr/td"));
		int size = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr/td")).size();
		
		for(int i=1;i<=size;i++){
			String text = driver.findElement(By.xpath("//table["+_gridId+"]/tbody/tr/td["+i+"]")).getText();
			if(text.equals(client)){
				actualName = getText(By.xpath("//table["+_gridId+"]/tbody/tr/td["+_index+"]"));
			}
		}
	}

	public boolean verifyTable(String _gridId) {
		
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
		int name = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).toString().length();
		String s = Integer.toString(name);

		if(s.equals(value)) {
			log("Table length befor search the value" + "["+value+"]",ILogLevel.ASSERTS);
			fieldClear(By.xpath(InventoryPageObject.textFieldSearch_xpath));
			return true;
		}else {
			log("Table length befor search the value" + "["+s+"]",ILogLevel.ASSERTS);
			fieldClear(By.xpath(InventoryPageObject.textFieldSearch_xpath));
			return false;
		}

	}
	
public boolean verifyRolesPresentInUser() {
		
		int size = driver.findElements(By.xpath(RolePageObject.roles_xpath)).size();
		int roles = size-1;
		String s = Integer.toString(roles);

		if(actualName.contains(s)) {
			log("List of roles in dropdown list equal to client role",ILogLevel.ASSERTS);
			return true;
		}
		return false;

	}
	
	
	public void selectCheckbox(String checkboxName) {
        click(By.xpath(RolePageObject.checkbox_xpath+checkboxName+RolePageObject.checkbox_xpath1),"["+checkboxName+"] button clicked",2);
    }
	
	public void getTagAndEditAsset(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				tagName = getText(By.xpath("//table/tbody["+_gridId+"]/tr[1]/td[1]"));
				String saveLocator = "(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]";
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(saveLocator)));
				log("["+_buttonName+"] icon clicked for ["+_Name+"].", ILogLevel.METHOD);
				pause(2);
				break;
			}else{
				continue;
			}
		}
		
	}
	
	public boolean verifyFieldDisable(int _index,String expectedField){
		
		String text = driver.findElements(By.className(SignUpPageObject.input_classname)).get(_index).getAttribute("value");
		if(text.equals(tagName)){
			String value = driver.findElements(By.className(SignUpPageObject.input_classname)).get(_index).getAttribute("disabled");
			if(value.equals(expectedField)){
				log("["+value+"] Field is disable.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("Field is not disable.", ILogLevel.ASSERTS);
				return false;
			}
		}
		return false;
	}
	
	public void verifyTicketsPermission(String _ticketName){
		
		if(isElementPresent(By.xpath(RolePageObject.checkbox_xpath+_ticketName+RolePageObject.checkbox_xpath1))){
			
			if(driver.findElement(By.xpath("//label[contains(text(),'"+_ticketName+"')]/input")).isSelected()){
				click(By.xpath("//label[contains(text(),'"+_ticketName+"')]/input"), "["+_ticketName+"] select", 1);
			}
			
		}
	}
	
	
	public void checkAllTicketPermission(String _permissionName, int _rowNumber, int _columnNo){	
		int noOfCheckbox = driver.findElements(By.xpath("//div["+_rowNumber+"]/div["+_columnNo+"]/div/label/input")).size();
		for(int i=1; i<=noOfCheckbox; i++){
			WebElement ele = driver.findElement(By.xpath("(//div["+_rowNumber+"]/div["+_columnNo+"]/div/label/input)["+i+"]"));
			
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			//click(By.xpath("//div[4]/div[2]/div/label/input["+i+"]"));
		}
		log("Check all ["+_permissionName+"] permission to create a role", ILogLevel.METHOD);
	}
	
	public void checkLimitedTicketsPermission(String _permissionName,String skipPermission,  int _rowNumber, int _columnNo){	
		int noOfCheckbox = driver.findElements(By.xpath("//div["+_rowNumber+"]/div["+_columnNo+"]/div/label/input")).size();
		for(int i=1; i<=noOfCheckbox; i++){
			WebElement ele = driver.findElement(By.xpath("(//div["+_rowNumber+"]/div["+_columnNo+"]/div/label/input)["+i+"]"));
			String level = getText(By.xpath("(//div["+_rowNumber+"]/div["+_columnNo+"]/div/label)["+i+"]"));
			log_Method("Level: "+level);
			log_Method("Argu : "+skipPermission);
			if(!level.contains("View Others Tickets") || !level.equalsIgnoreCase("View Others Tickets")){
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			}else{
				log("Without check "+skipPermission+", check all ["+_permissionName+"] permission to create a role", ILogLevel.METHOD);
			}	
		}
		log("Check all ["+_permissionName+"] permission to create a role", ILogLevel.METHOD);
	}
	
	public void expectedFieldUncheckedIfChecked(String _fieldName, String _locator){
		boolean field = driver.findElement(By.xpath(_locator)).isSelected();
		if(field){
			click(By.xpath(_locator), "Click on ["+_fieldName+"] field to unchecked.", 1);
		}else {
			log("["+_fieldName+"] field already unchecked.", ILogLevel.METHOD);
		}
	}
	
	public boolean isExpectedFieldUnchecked(String _fieldName, String _locator){
		boolean field = driver.findElement(By.xpath(_locator)).isSelected();
		if(field){
			log("["+_fieldName+"] field Still Checked.", ILogLevel.METHOD);
			return true;
		}else {
			log("["+_fieldName+"] field Unchecked.", ILogLevel.METHOD);
			return false;
		}
	}
	
	public boolean verifyExpectedClientInDropdownList(String _expectedClientName){
		boolean ele = isElementPresent(By.xpath(RolePageObject.clientDropdown));
		
		if(ele){
			int size =  driver.findElements(By.xpath(RolePageObject.clientDropdown)).size();
			for(int i =1;i<=size;i++){
				String clientName = driver.findElement(By.xpath(RolePageObject.clientDropdown+"["+i+"]")).getText();
				if(clientName.equals(_expectedClientName)){
					log("["+clientName+"] present in drodown list",ILogLevel.ASSERTS);
					return true;
				}else{
					continue;
				}
			}
		}
		return false;
	}
	
}
