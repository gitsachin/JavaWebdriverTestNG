package com.auto.pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.base.Xls_Reader;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.HelpDeskTicketsPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.PredefineRepliesPageObject;
import com.auto.pageobject.SettingPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import au.com.bytecode.opencsv.CSVReader;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AssetsPage extends BasePage{

	public AssetsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static String fileName = "";
	public static String assetName = "";
	public int rowCount ;
	public static String Name = "";
	public String getLabelText(String _tagName){

		String labelName =  getText(By.cssSelector(InventoryPageObject.labelText_css+_tagName+InventoryPageObject.labelText1_css));
		return labelName;
	}


	public void clickOnViewEditDelIcons(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		pause(1);
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		
		if(grid){
			int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();
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

	public boolean isSaveButtonPresent(String _editScreenName){
		boolean saveButton = isElementPresent(By.xpath(InventoryPageObject.buttonSave_xpath));
		log("[Save] button present in ["+_editScreenName+"] page.", ILogLevel.ASSERTS);
		return saveButton;
	}


	public boolean isLoaderPresent(String _screenName){
		boolean loader = driver.findElement(By.xpath(InventoryPageObject.loader)).isDisplayed();
		log("[Loader] doesn't dispalying in ["+_screenName+"] page.", ILogLevel.ASSERTS);
		return loader;
	}



	public void editAssets(String _gridId, String _expectedName,int _buttonindex){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr[1]"));
		int rowCount = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr/td")).size();
		for(int i = 1; i<=rowCount; i++){
			String networkName = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[3]/a")).getText();
			if(networkName.equals(_expectedName)){
				driver.findElement(By.xpath("(//table/tbody["+_gridId+"]/tr["+i+"]/td[8]//a/i)["+_buttonindex+"]")).click();
				log("Click on Edit button", ILogLevel.METHOD);
				break;
			}else{
				continue;
			}
		}
	}

	public String returnSerialNo(String _gridId, String _expectedName){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int rowCount = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr/td")).size();
		for(int i = 1; i<=rowCount; i++){
			String networkName = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[3]/a")).getText();
			if(networkName.equals(_expectedName)){
				String serialNo = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[5]")).getText();
				return serialNo;
			}
		}
		return null;
	}

	public String returnClientnameFromGrid(String _gridId, String _expectedName){
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int rowCount = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr/td")).size();
		for(int i = 1; i<=rowCount; i++){
			String networkName = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[3]")).getText();
			if(networkName.equals(_expectedName)){
				String client = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[2]")).getText();
				return client;
			}
		}
		return null;
	}



	public String returnClientName(){
		waitForElementDisplayed(By.cssSelector(InventoryPageObject.clientfield_css));
		log_Method("Selected client is: ["+getText(By.cssSelector(InventoryPageObject.clientfield_css))+"]");
		return getText(By.cssSelector(InventoryPageObject.clientfield_css));

	}

	public boolean isAssetUpdate(String _gridId, String _expectedName, String _expectedSerialsNo){
		waitForElementDisplayed(By.xpath("//table/tbody/tr[1]"));
		int rowCount = driver.findElements(By.xpath("//table//tr/td")).size();
		for(int i = 1; i<=rowCount; i++){
			String networkName = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[3]/a")).getText();
			if(networkName.equals(_expectedName)){
				String serialNo = driver.findElement(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td[5]")).getText();
				if(serialNo.equals(_expectedSerialsNo)){
					log("Asset Doesn't Updated", ILogLevel.ASSERTS);
					return true;
				}else{
					log("Asset Updated", ILogLevel.ASSERTS);
					return false;
				}
			}else{
				continue;
			}
		}
		return false;
	}


	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {

			setClipboardData(System.getProperty("user.dir")+"\\src\\main\\resources\\files\\"+fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public void verifyPagination(String _screenName, int _gridSize){

		if(driver.getCurrentUrl().equals(ConfigProperties.site_url+"?route=inventory/attributes/locations")){

			waitForElementDisplayed(By.xpath("//div[@class='location_tabs']"));
			int tableLocationRow = _gridSize;
			if(tableLocationRow==10 && isElementPresent(By.cssSelector(".custom-paginate .dataTables_info"))){
				assertTrue(verifyPaginationButton(_screenName), "Pagination does not present on page");
			}

			else if(tableLocationRow==0||tableLocationRow<10||(tableLocationRow==10 && !isElementPresent(By.cssSelector(".custom-paginate .dataTables_info")))){
				assertFalse(verifyPaginationButton(_screenName), "Pagination present on page");
			}
		}

		else if(driver.getCurrentUrl().equals(ConfigProperties.site_url+"?route=people/roles")){

			waitForElementDisplayed(By.xpath("//table[@id='dataTablesRole3']/tbody/tr"));
			int clientRoleCount = driver.findElements(By.xpath("//table[@id='dataTablesRole3']/tbody/tr")).size();
			if(clientRoleCount==10 && isElementPresent(By.cssSelector(".custom-paginate .info"))){
				assertTrue(verifyPaginationButton(_screenName), "Pagination does not present on page");
			}

			else if(clientRoleCount==0||clientRoleCount<10||(clientRoleCount==10 && !isElementPresent(By.cssSelector(".custom-paginate .info")))){
				assertFalse(verifyPaginationButton(_screenName), "Pagination present on page");
			}
		}

		else{

			waitForElementDisplayed(By.xpath(InventoryPageObject.tableRow_xpath));
			int tableRowCount = driver.findElements(By.xpath(InventoryPageObject.tableRow_xpath)).size();
			if(tableRowCount==10 && isElementPresent(By.cssSelector(".custom-paginate .info"))){
				assertTrue(verifyPaginationButton(_screenName), "Pagination does not present on page");
			}

			else if(tableRowCount==0||tableRowCount<10||(tableRowCount==10 && !isElementPresent(By.cssSelector(".custom-paginate .info")))){
				assertFalse(verifyPaginationButton(_screenName), "Pagination present on page");
			}
		}

	}

	public boolean verifyPaginationButton(String _screenName){
		
		boolean paginationTab = isElementPresent(By.cssSelector(".pagination"));
		//boolean pageinationTabDisplay = driver.findElement(By.cssSelector(".pagination")).isDisplayed();
		boolean pageSelection = isElementPresent(By.xpath("//span[@id='select2-selectLimit-container']"));

		if(paginationTab){
			if(pageSelection) {
				log("[Pagination] tab and [Page Selection] limit tab appears on '"+_screenName+"' page", ILogLevel.ASSERTS);
				return true;
			}else {
				log("[Pagination] tab present but [Page Selection] limit tab doesn't appears on '"+_screenName+"' page", ILogLevel.ASSERTS);
				return false;
			}
		}else if(pageSelection) {
			if(paginationTab==false) {
				log("[Pagination] tab doesn't present but [Page Selection] limit tab appears on '"+_screenName+"' page", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			return false;
		}
		return pageSelection;
	}

	public void sendFile(String _fileName){
		pause(2);
		fileUpload(By.xpath(InventoryPageObject.uploadFileButton_xpath),System.getProperty("user.dir") + "\\src\\main\\resources\\files\\"+_fileName, 3, "File");
		log("Uploaded file: "+System.getProperty("user.dir") + "\\src\\main\\resources\\files\\"+_fileName, ILogLevel.METHOD);
	}

	public void totalAssetBeforeUploadBatch(){

		rowCount = driver.findElements(By.xpath(InventoryPageObject.tableRow_xpath)).size();
		log("Total asset before upload batch file : " +rowCount, ILogLevel.TEST);

	}

	public void totalAssetAfterUploadBatch(){	  
		rowCount = driver.findElements(By.xpath(InventoryPageObject.tableRow_xpath)).size();
		log("Total asset after upload batch file : " +rowCount, ILogLevel.TEST);
	}

	public int returnExpectedGridSize(){

		boolean grid = isElementPresent(By.xpath(AssetsCategriesPageObject.expectedGrid_xpath));
		if(grid){
			int numberOfRow = driver.findElements(By.xpath(AssetsCategriesPageObject.expectedGrid_xpath)).size();
			log("Gride size before added new Item: ["+numberOfRow+"]", ILogLevel.METHOD);
			return numberOfRow; 
		}else{
			log("Item grid doesn't present.", ILogLevel.METHOD);
			return 0;
		}
	}

	public boolean isItemAddedInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
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
		return false;
	}


	public  void findAndClickOnNewLicense(String _gridId, String item, String expectedItemName){
		waitForElementDisplayed(By.xpath(AssetsCategriesPageObject.searchGird_xpath));
		int gridSize = driver.findElements(By.xpath(AssetsCategriesPageObject.searchGird_xpath)).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td[3]"));
			if(name.equals(expectedItemName)){
				driver.findElement(By.xpath("//*["+_gridId+"]/tr["+i+"]/td[1]/a")).click();
				log("New ["+item+"] is clicked.", ILogLevel.ASSERTS);
				break;
			}else{
				continue;
			}
		}
	}

	public boolean isLicenseOpen(){
		waitForElementDisplayed(By.cssSelector(InventoryPageObject.licenseHeaderText_css));
		boolean licenseText = isElementPresent(By.cssSelector(InventoryPageObject.licenseHeaderText_css));
		if(licenseText){
			log("License text present on header", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public void enterSerialNo(){
		fieldClear(By.xpath(InventoryPageObject.fieldSerialNo_xpath));
		sendKeys(By.xpath(InventoryPageObject.fieldSerialNo_xpath), autogenerateNumber(6), 2, "[Serial No.]");
	}

	public void clickOnSaveButton(){
		click(By.cssSelector(InventoryPageObject.buttonSave_css), "Click on [Save] button to save edited Asset", 4);
	}

	public boolean isExpectedFieldLabelPresent(String _expectedLabel){

		waitForElementDisplayed(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));

		boolean expectedField = isElementPresent(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));
		String label = getText(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));

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

	public boolean isFilterArrowIconPresent(String _locator){
		waitForElementDisplayed(By.xpath(_locator));
		//InventoryPageObject.assetsTableHeader_xpath
		int columnSize = driver.findElements(By.xpath("//th/div")).size();

		for(int i=1; i<=columnSize; i++){ 
			boolean arrowIcon = isElementPresent(By.xpath("//th["+i+"]/div"+InventoryPageObject.filterArrowIcon_xpath));
			if(arrowIcon){
				log("Filter arrow icon is present for column ["+i+"th:"+getText(By.xpath("//th["+i+"]/div"+InventoryPageObject.filterColumnName_xpath))+"]", ILogLevel.ASSERTS);
				continue;
			}else{ 
				continue;
			}
		} 
		return true;

	}

	public boolean isFilterArrowIconsPresent(){
		int columnSize = driver.findElements(By.xpath("//th/div")).size();

		for(int i=1; i<=columnSize; i++){ 
			boolean arrowIcon = isElementPresent(By.xpath("//th["+i+"]/div"+InventoryPageObject.filterArrowIcons_xpath));
			if(arrowIcon){
				log("Filter arrow icon is present for column ["+i+"th:"+getText(By.xpath("//th["+i+"]/div"+InventoryPageObject.filterColumnName_xpath))+"]", ILogLevel.ASSERTS);
				continue;
			}else{ 
				continue;
			}
		} 
		return true;

	}

	public void clickOnExpectedFilterArrowIcon(String columnName, int index){
		waitForElementDisplayed(By.xpath("(/"+InventoryPageObject.filterArrowIcon_xpath+")["+index+"]"));
		click(By.xpath("(/"+InventoryPageObject.filterArrowIcon_xpath+")["+index+"]"), "Click on ["+columnName+"] arrow icon", 2);
	}

	public void clickOnExpectedFilterArrowIcons(String columnName,int index){
		waitForElementDisplayed(By.cssSelector(InventoryPageObject.filterArrowIcon_css));
		driver.findElements(By.cssSelector(InventoryPageObject.filterArrowIcon_css)).get(index).click();
	}

	public boolean isFilterPanelOpen(String _columnName){
		boolean filterPanel = isElementPresent(By.xpath(InventoryPageObject.columnName1stPart_xpath+_columnName.toLowerCase()+InventoryPageObject.columnName2ndPart_xpath));
		if(filterPanel){
			log("Apply filter panel is opens for : ["+_columnName+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public boolean isUpdatedTabPresent(String _expectedTabName){
		boolean filterPanel = isElementPresent(By.xpath(InventoryPageObject.editAssetTab1stPart_xpath+_expectedTabName+InventoryPageObject.editAssetTab2ndPart_xpath));
		if(filterPanel){
			log("Expected tab : ["+_expectedTabName+"] tab is present in edit screen", ILogLevel.ASSERTS);
			return true;
		}else{
			log("Expected tab : ["+_expectedTabName+"] tab doesn't present in edit screen", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean isDetailsInFirstPOsition(String _tabName, int _expectedPosition){
		int tabSize = driver.findElements(By.xpath(InventoryPageObject.editAssetTab)).size();
		for(int i=1; i<=tabSize; i++){
			String tabName = getText(By.xpath(InventoryPageObject.editAssetTab+"["+i+"]"));
			if(tabName.equals(_tabName) && i==_expectedPosition){
				return true;
			}else{
				continue;
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
		pause(3);
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


	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){

		switch (_numForSwitch)
		{
		case 1:
			fieldClear(By.xpath(_locator));
			sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		case 2:
			sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		}
	}

	public void clickOnCopyIcon(String _expectAtt){
		int size = driver.findElements(By.xpath("//table[@id='categoryTable']/tbody/tr")).size();
		for(int i=1; i<=size; i++){
			String attrname = getText(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[1]"));
			if(attrname.equals(_expectAtt)){
				click(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[5]/div/a[1]"), "[Copy] icon clicked.", 2);
				break;
			}else{
				continue;
			}
		}
	}

	public void clickOnDeleteIcon(String _expectAtt){
		int size = driver.findElements(By.xpath("//table[@id='categoryTable']/tbody/tr")).size();
		for(int i=1; i<=size; i++){
			String attrname = getText(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[1]"));
			if(attrname.equals(_expectAtt)){
				click(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[5]/div/a[3]"), "[Delete] icon clicked.", 2);
				break;
			}else{
				continue;
			}
		}
	}

	public void selectCopyTo(){
		dropdownSelect(By.xpath(InventoryPageObject.selectCoptTo_xpath), "Category1_DND");
	}

	public boolean isAttributesCatgoriesCopied(String _expectAtt, String _expectLabel){
		int size = driver.findElements(By.xpath("//table[@id='categoryTable']/tbody/tr")).size();
		for(int i=1; i<=size; i++){
			String attrname = getText(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[1]"));
			String labelValue = getText(By.xpath("//table[@id='categoryTable']/tbody/tr["+i+"]/td[4]"));

			if(attrname.equals(_expectAtt) && labelValue.equals(_expectLabel)){
				log("[Category Attributes] copied successfully.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return true;
	}

	public void clickOnFilterArrowIcon(int _columnNo){ // "_columnNo" is the int number denotes which column arrow icon access.
		waitForElementDisplayed(By.xpath(InventoryPageObject.assetsTableHeader_xpath));
		boolean arrowIcon = isElementPresent(By.xpath("//th["+_columnNo+"]/div"+InventoryPageObject.filterArrowIcon_xpath));
		if(arrowIcon){
			click(By.xpath("//th[4]/div"+InventoryPageObject.filterArrowIcon_xpath), "Model arrow icon clicked", 2);
		} 
	}

	public void clickOnBlankCheckbox(String _columnId){
		int list = driver.findElements(By.xpath("//div["+_columnId+"]/div[1]/label")).size();

		for(int i=1; i<=list; i++){

			String name = getText(By.xpath("//div["+_columnId+"]/div[1]/label["+i+"]"));
			if (name.equals("Blank")) {

				click(By.xpath("//div["+_columnId+"]/div[1]/label["+i+"]/input"), "[Blank] checkbox clicked", 3);
				break;
			}else{

				continue;
			}
		}

	}

	public boolean isFilterApplied(String _locator, int _columnNo, String _appliedFilter){
		int size = driver.findElements(By.xpath("//tbody["+_locator+"]/tr")).size();
		for(int i=1; i<=size; i++){
			if(i!=size){
				String filterString = getText(By.xpath("//table/tbody/tr["+i+"]/td["+_columnNo+"]"));
				if(filterString.equals("")){
					continue;
				}else{
					return false;
				}	  
			}else{
				log("["+_appliedFilter+"] is apply.", ILogLevel.ASSERTS);
				return true;
			} 

		}
		return true;
	}

	public boolean isGridPresentAfterSearch(){
		boolean grid = isElementPresent(By.xpath(AssetsCategriesPageObject.searchGird_xpath));
		int numberOfRows = driver.findElements(By.xpath(AssetsCategriesPageObject.searchGird_xpath)).size();
		if(grid){
			log("Grid present after Assets Search and Row Size: ["+numberOfRows+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public boolean isConfirmationMessageAppear(String _message){
		// waitForElementDisplayed(By.cssSelector(SignUpPageObject.successMessage_css));
		boolean alert = isElementPresent(By.cssSelector(SignUpPageObject.successMessage_css));
		//String message = getText(By.cssSelector(SignUpPageObject.successMessage_css));
		if(alert){
			log("["+_message+"] alert message appears.", ILogLevel.METHOD);	
			return true;
		}
		else{
			log("["+_message+"] alert message not visible.", ILogLevel.METHOD);  
			return false;
		}
	}

	public boolean isEyeBallIconPresent(){
		boolean eyeIcon = isElementPresent(By.cssSelector(AssetsCategriesPageObject.eyeBallIcon_css));
		if(eyeIcon){
			log("Eye Icon present under Location attribute", ILogLevel.ASSERTS);

			return true;
		}else{
			return false;
		}
	}

	public boolean isLocationPresentInGrid(String expectedItemName){
		waitForElementDisplayed(By.xpath("//ul[@data-label='"+expectedItemName+"']/li")); 
		String location = getText(By.xpath("//ul[@data-label='"+expectedItemName+"']/li[1]"));
		if(location.contains(expectedItemName)){
			log("[expectedItemName] location pooresent in Location Grid.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		} 
	}

	public boolean isExpectedUrlPresent(String _expectedUrl){
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.equalsIgnoreCase(_expectedUrl)){
			log("Current page url is: ["+currentUrl+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
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

	public void clickOnExpectedTab(String _locator, int _index, String tabName){
		click(By.xpath(_locator+"["+_index+"]"), "["+tabName+"] tab clicked.", 3); 
	}

	public boolean isExpectedPopupOpen(String _expectedText){
		boolean content = isElementPresent(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
		if(content){
			String text = getText(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
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

	public boolean isExpectedContentTextPresentInScreen(String _locator, String _expectedText){
		waitForElementDisplayed(By.xpath(_locator));
		boolean content = isElementPresent(By.xpath(_locator));
		String text = getText(By.xpath(_locator));
		if(content && text.equalsIgnoreCase(_expectedText)){
			log("["+text+"] present on screen.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	  
	}

	public boolean isItemAddedInTable(String tableName, String expectedItemName){
		//waitForElementDisplayed(By.xpath("//tbody/tr")); 
		int gridSize = driver.findElements(By.xpath("//tbody/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//tbody/tr["+i+"]/td[3]"));
			if(name.equals(expectedItemName)){
				log("["+name+"] is present in ["+tableName+"] table.", ILogLevel.ASSERTS);
				break;
			}else{
				continue;
			}
		}
		return true;
	}

	public void clickOnExpectedIcon(String _locator, int _index, String _iconName){
		List<WebElement> _ele = driver.findElements(By.xpath(_locator));
		_ele.get(_index).click();
		log("["+_iconName+"] clicked.", ILogLevel.METHOD);
	}

	public boolean isLicenseAddedInTable(String _tableName, String _expectedItemName){
		waitForElementDisplayed(By.xpath("//div[@id='tab-licenses']/div/table/tbody/tr/td")); 
		int gridSize = driver.findElements(By.xpath("//tbody/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div[@id='tab-licenses']/div/table/tbody/tr["+i+"]/td[3]"));
			log("Name: ["+name+"] and argu: ["+_expectedItemName+"]", ILogLevel.METHOD);
			if(name.equals(_expectedItemName)){
				log("["+name+"] is present in ["+_tableName+"] table.", ILogLevel.ASSERTS);
				break;
			}else{
				continue;
			}
		}
		return true;
	}

	public void deleteAddedLicenseFromTable(String _tableName, String _expectedItemName){
		waitForElementDisplayed(By.xpath("//div[@id='tab-licenses']/div/table/tbody/tr/td")); 
		int gridSize = driver.findElements(By.xpath("//tbody/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div[@id='tab-licenses']/div/table/tbody/tr["+i+"]/td[3]"));
			log("Name: ["+name+"] and argu: ["+_expectedItemName+"]", ILogLevel.METHOD);
			if(name.equals(_expectedItemName)){
				click(By.xpath("//div[@id='tab-licenses']/div/table/tbody/tr["+i+"]/td[5]/div/a"), "Click on [Delete] icon to delete "+_tableName+"", 2);
				break;
			}else{
				continue;
			}
		} 
	}

	public void totalRowAfterUploadBatch(){	  
		rowCount = driver.findElements(By.xpath(InventoryPageObject.tableRow_xpath)).size();
		log("Total asset after upload batch file : " +rowCount, ILogLevel.TEST);
	}

	public String expirationDate = null;
	public String returnExpirationDate(String _gridId, String tableName, String expectedItemName){
		waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td[3]"));
			if(name.equals(expectedItemName)){
				String expirationDate = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td[5]"));
				log("Expiration date before edit is: ["+expirationDate+"]", ILogLevel.METHOD);
				return expirationDate;
			}else{
				continue;
			}
		}
		return expirationDate;
	}

	public boolean isExpirationDateUpdate(String _gridId, String tableName, String expectedItemName, String _oldDate){
		waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td[3]"));
			if(name.equals(expectedItemName)){
				String expirationDate = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td[5]"));
				if(!expirationDate.equals(_oldDate)){
					log("Expiration date updated from ["+_oldDate+"] to new date: ["+expirationDate+"]", ILogLevel.METHOD);
					return true;
				}else{
					return false;
				}

			}else{
				continue;
			}
		}
		return false;
	}

	public boolean isExpectedColumnPresent(String _locator, String _expectedColumnName){
		int size = driver.findElements(By.xpath("//table["+_locator+"]/thead/tr/th")).size();
		for(int i=1; i<=size; i++){
			String columnName = getText(By.xpath("//table["+_locator+"]/thead/tr/th["+i+"]"));
			if(columnName.contains(_expectedColumnName)){
				log("Expected column heading ["+columnName+"] present in ["+i+"] no position.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue; 
			}
		}
		log("Expected column heading ["+_expectedColumnName+"] doesn't present.", ILogLevel.ASSERTS);
		return false;
	}


	public boolean isExpectedFieldMandatory(String _labelName, int _index){
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("("+InventoryPageObject.label_xapth+")["+_index+"]")));
		boolean label = isElementPresent(By.xpath(InventoryPageObject.label_xapth));
		String text = getText(By.xpath("("+InventoryPageObject.label_xapth+")["+_index+"]"));

		if (label && text.contains("*")) {
			log(_labelName+" field is [Required Field]", ILogLevel.ASSERTS);
			return true;
		}
		else if(text.contains(_labelName)){
			log(_labelName+" field is [Present]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}

	}

	public boolean isEyeIconPresent(String _gridId, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _gridName, int _columnNoForExpectedString){
		// clickOnViewEditDelIcons(Grid locator id, Searched String, icond index(view/edit/delete icon position), perform Action(view/edit/delete), Icon's column no, Grid Name, searched String column No.) 
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				boolean eyeIcon = isElementPresent(By.xpath(("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i[@class='fa fa-eye'])["+_buttonindex+"]")));
				if(eyeIcon){
					log("[Eye Icon] present in ["+_gridName+"] table", ILogLevel.ASSERTS);
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

	public boolean isItemPresentInLocationGrid(String _gridId, String expectedItemName, int _itemColumnNumber){
		boolean data = isElementPresent(By.xpath("//div["+_gridId+"]/div"));

		if(data){
			int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();
			for(int i=1; i<=gridSize; i++){
				String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_itemColumnNumber+"]"));

				if(name.equals(expectedItemName)){
					log("["+name+"] is present in Location grid.", ILogLevel.ASSERTS);
					return true;
				}else{
					log("["+name+"] is doesn't present in Location grid.", ILogLevel.ASSERTS);
					return false;
				}
			}

		}else{
			boolean dataNotPresent = isElementPresent(By.xpath("//div["+_gridId+"]/ul"));
			if(dataNotPresent){
				String tableMessage = getText(By.xpath("//div["+_gridId+"]/ul/li"));
				if(tableMessage.equalsIgnoreCase("location not found!")){
					log("["+tableMessage+"] message appears for searching Location: "+expectedItemName+"", ILogLevel.ASSERTS);
					return false;
				}
			}
		}
		return data;
	}


	public void selectLocation(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"),"["+name+"] is selected to create nested location or other operation.", 2);
				break;
			}else{
				continue;
			}
		}
	}

	public int returnQTY(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();
		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"),"["+name+"] is selected to create nested location or other operation.", 2);
				int qty = Integer.parseInt(getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li[3]")));
				log("Quantity  is ["+qty+"] of searched location ["+expectedItemName+"]", ILogLevel.METHOD);
				return qty;
			}
		}
		return 0;
	}

	public boolean verifyNestedIconPresent(String _gridId, String tableName, String expectedItemName, int _columnNumber, boolean expectedCondition){

		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"));
			if(name.equals(expectedItemName)){
				boolean nestedIcon = isElementPresent(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li/a"));
				boolean cirCleIcon = isElementPresent(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li/span"));
				if(nestedIcon){
					log("[Nested Arrow] icon appears and nested location created", ILogLevel.ASSERTS);
					return true;
				}else if(cirCleIcon){
					log("[Circle] icon appears for new location.", ILogLevel.ASSERTS);
					return false;
				}
			}
		}
		return expectedCondition;
	}

	public boolean isNestedLocationCreated(String tableName,int _columnNumber){
		waitForElementDisplayed(By.xpath("//div[@class='location_tabs']/div/ul"));
		boolean nestedGrid = isElementPresent(By.xpath("//div[@class='location_tabs']/div/ul"));
		int gridSize = driver.findElements(By.xpath("//div[@class='location_tabs']/div/ul")).size();

		if(nestedGrid && gridSize>=1){
			log("["+gridSize+"] Nested Location created", ILogLevel.ASSERTS);
			for(int i=1; i<=gridSize; i++){
				String name = getText(By.xpath("//div[@class='location_tabs']/div["+i+"]/ul/li["+_columnNumber+"]"));
				log("Location Type: ["+name+"]", ILogLevel.ASSERTS);
				continue;
			}
			return true;
		}else{
			return false;
		}  
	}

	public void clickOnNestedArrowIcon(String _gridId, String tableName, String expectedItemName, int _columnNumber){

		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"));
			if(name.equals(expectedItemName)){
				boolean nestedIcon = isElementPresent(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li/a"));
				if(nestedIcon){
					click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li/a"), "[Nested Arrow Icon] clicked", 1);
				}
			}
		}
	}

	public void deleteLocation(String _gridId, String tableName, String expectedItemName,int _searchedStringColumnNo, int _iconColumnNumber, int _iconPosition){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_searchedStringColumnNo+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_iconColumnNumber+"]/div/a["+_iconPosition+"]"),"[Delete] icon clicked for ["+name+"] location", 2);
				break;
			}else{
				continue;
			}
		}
	} 

	public int returnRandomNumber(int _limit){
		// create instance of Random class
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		return rand.nextInt(1000);
	}

	public int returnLocationGridSize(String _gridId, int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		waitForElementDisplayed(By.xpath("//div["+_gridId+"]/div"));
		boolean paginationtab = driver.findElement(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]")).isDisplayed();
		boolean paginationInfo = isElementPresent(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();
		if(gridSize<=10 && paginationtab && paginationInfo){
			String info = getText(By.xpath(_paginationInfoLocator+"["+paginationInfoIndex+"]"));
			String word[]=info.split(" ");
			String gridSizeInfo = word[word.length-2];
			log("Pagination Info, pagination tab and ["+gridSizeInfo+"] records present in 'Location' table", ILogLevel.METHOD);
			return Integer.parseInt(gridSizeInfo);
		}else if(gridSize<=10 && paginationtab==false && paginationInfo==false){
			log("["+(gridSize)+"] records present in 'Location' table", ILogLevel.METHOD);
			return gridSize;
		}else{
			return 0;
		}
	}

	public boolean isPaginationTabPresent(int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		boolean paginationtab = isElementPresent(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]"));
		boolean paginationInfo = isElementPresent(By.xpath(_paginationInfoLocator+"["+paginationInfoIndex+"]"));
		if(paginationtab && paginationInfo){
			log("Pagination tab and pagination info present in table.", ILogLevel.METHOD);
			return true;
		}else{
			log("Pagination tab and pagination info doesn't present in table.", ILogLevel.METHOD);
			return false;
		}  
	}

	public void checkGridSizeAndCreateLocation(boolean _pagination , int gridSize, int pageNumber, String _paginationInfoLocator, int paginationInfoIndex){

		if(gridSize<=10 && _pagination == false){
			for(int i=gridSize+1; i<=11; i++){
				click(By.xpath(SignUpPageObject.buttonActionDropDown_xpath));
				click(By.xpath(InventoryPageObject.buttonNewLocation), "[New location] button clicked.", 2);
				sendKeys(By.xpath(InventoryPageObject.textFieldLocationName_xpath), "Loc_"+returnRandomNumber(100000), 1, "Location Name: ["+returnRandomNumber(100000)+"]");
				sendKeys(By.xpath(InventoryPageObject.textFieldLocationType_xpath), "Region", 1, "Location Type: [Region]");
				click(By.xpath(InventoryPageObject.buttonCreateInpopup_xpath), "[Create] button clicked.", 2);
				log("["+i+"]th number Location created.", ILogLevel.METHOD);

				boolean paginationInfo = isElementPresent(By.xpath(_paginationInfoLocator+"["+paginationInfoIndex+"]"));
				if(paginationInfo){
					click(By.xpath(InventoryPageObject.pageNo1_xpath+pageNumber+InventoryPageObject.pageNo2_xpath), "Click on ["+pageNumber+"] no tab on pagination.", 2);
				} 
			}
		}else{
			click(By.xpath(InventoryPageObject.pageNo1_xpath+pageNumber+InventoryPageObject.pageNo2_xpath), "Click on ["+pageNumber+"] no tab on pagination.", 2);
		}
	}

	public boolean isNavigateToExpectedPage(String _expectedUrl, int _pageNo, String _expectedActivePage){
		String activePageUrl = driver.getCurrentUrl();
		if(activePageUrl.equalsIgnoreCase(_expectedUrl+_pageNo)){
			log("["+_expectedActivePage+"] table is opens", ILogLevel.ASSERTS);
			return true;
		}else if(activePageUrl.equalsIgnoreCase(_expectedUrl)){
			log("["+_expectedActivePage+"] table is opens", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}


	public void deleteRandomLocation(String _gridId, String tableName, int _iconColumnNumber, int _iconPosition){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; ){
			click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_iconColumnNumber+"]/div/a["+_iconPosition+"]"),"[Delete] icon clicked on location table.", 2);
			break;
		}
	}

	public boolean isItemPresentInGridAfterDelete(String _locator, String _searchedItem, String _expectedBlankString){
		boolean gridElement= isElementPresent(By.xpath(_locator));
		String gridElementText = getText(By.xpath(_locator));
		if(gridElement && gridElementText.equalsIgnoreCase(_expectedBlankString)){
			log("["+gridElementText+"] message appears in grid when search deleted item ["+_searchedItem+"].", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}


	public void clickOnLabel(String _expectedLabel,int _index){
		driver.findElement(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"+"["+_index+"]")).click();

	}

	public void clickOnApplyButton(int _index){
		click(By.xpath(AssetsCategriesPageObject.button_xpath+"["+_index+"]"),"[Button] click",2);

	}

	public boolean verifyColor(int _index,String _value) throws InterruptedException{
		waitForElementDisplayed(By.cssSelector(AssetsCategriesPageObject.arrowIcon_css));
		String text = driver.findElements(By.cssSelector(AssetsCategriesPageObject.arrowIcon_css)).get(_index).getCssValue("color");
		if(text.equals(_value)){
			log( "[color] is present on screen", ILogLevel.ASSERTS);
			return true;
		}else{
			log("[color] not present on screen", ILogLevel.ASSERTS);
			return false;
		}
	}

	public void clickOnArrow(){
		click(By.xpath(AssetsCategriesPageObject.arrowIcon1_xpath),"Click on arrow button",3);
	}

	public boolean isItemAdded_PresentInLocationGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		waitForElementDisplayed(By.xpath("//div["+_gridId+"]/div")); 
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_columnNumber+"]"));////div[@id="searchTable"]/div[1]/ul/li

			if(name.equals(expectedItemName)){
				log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		return false;
	}

	public boolean isLocationPresentInChildTable(String tableName,int _columnNumber, String _locationNameWhichWantMoveUnderRoot){
		waitForElementDisplayed(By.xpath("//div[@class='location_tabs childs']/ul"));
		boolean nestedGrid = isElementPresent(By.xpath("//div[@class='location_tabs childs']/ul"));
		int gridSize = driver.findElements(By.xpath("//div[@class='location_tabs childs']/ul")).size();
		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div[@class='location_tabs childs']/ul/li["+_columnNumber+"]"));
			if (nestedGrid && name.contains(_locationNameWhichWantMoveUnderRoot)) {
				return true;
			}else{
				continue;
			}

		}
		return false;
	}



	public static String rootLocation = "Root_"+BasePage.currentDate();

	public static String buttonNewLocation = "//button[contains(text(), 'New location')]";


	public void clickOnViewEditDeleteLocationIcon(String _gridId, String tableName, String expectedItemName,int _searchedStringColumnNo, int _iconColumnNumber, int _iconPosition){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_searchedStringColumnNo+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_iconColumnNumber+"]/div/a["+_iconPosition+"]"),"[Edit] icon clicked for ["+name+"] location", 2);
				break;
			}else{
				continue;
			}
		}
	}


	public void clickActionButtonDropdown(int _index,String _text){
		click(By.xpath(InventoryPageObject.actionDropdown_Xpath+"["+_index+"]"),"Dropdown option ["+_text+"] clicked",2);
		pause(35);
	}

	public boolean fileExist(String _fileExt) throws FileNotFoundException{

		File s = new File(AssetsCategriesPageObject.filePath);
		File[] name = s.listFiles();
		for(File file : name){
			if(file.isFile()){
				fileName = file.getName();
				if(fileName.contains(_fileExt)){
					log("["+fileName+"] exist",ILogLevel.ASSERTS);
					return true;
				}
			}
		}
		log("File not exist",ILogLevel.ASSERTS);
		return false;


	}

	public void deleteFile(){
		File s = new File(AssetsCategriesPageObject.filePath+fileName);
		if(s.delete()){
			log("Downloaded file delted",ILogLevel.TEST);
		}
		else{
			log("No File to delte",ILogLevel.TEST);
		}
	}

	public void readExcelFile(){
		Xls_Reader excel = new Xls_Reader(AssetsCategriesPageObject.filePath+fileName);
		Name = excel.getCellData("Assets", "Asset Name", 2);
	}

	
	public String readExcelFileCellData(String _fileName, String _module, String _sheetName, String _colName, int _rowNum){
		Xls_Reader excel = new Xls_Reader(InventoryPageObject.existingFilePath+_fileName);
		String expectedData = excel.getCellData(_sheetName, _colName, _rowNum);
		log("Excel Sheet: "+_module+" name is ["+expectedData+"] in sheet name "+_sheetName+"", ILogLevel.METHOD);
		return expectedData;
		
	}

	public int countExportedFileRow(String sheetName){
		Xls_Reader excel = new Xls_Reader(AssetsCategriesPageObject.filePath+fileName);
		int nomberOfRecords = excel.getRowCount(sheetName)-1;
		log("Number of records present in Exported file: "+nomberOfRecords, ILogLevel.METHOD);
		return nomberOfRecords;
	}

	public boolean isTableRecordSameWithExporetedRecord(int tabRecord, int exportRecord){

		if(tabRecord==exportRecord){
			log("Downloaded Report contains same record as per Table records.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	
	}


	public boolean verifyDataInExcelFile(String name) throws IOException{
		readExcelFile();
		if(Name.contains(name)){
			return true;
		}else{
			return false;
		}
	}

	public void readCSVFile() throws IOException{
		CSVReader reader = new CSVReader(new FileReader(AssetsCategriesPageObject.filePath+fileName));
		String [] csvCell;

		while ((csvCell = reader.readNext()) != null) { 
			assetName = csvCell[2];
		}
		reader.close();
	}

	public boolean verifyDataInCSVFile(String name) throws IOException{
		readCSVFile();
		if(assetName.contains(name)){
			log("["+name+"] present in file",ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+name+"] not present in file",ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean verifyHistoryTabRow(String rowName,int _index){
		boolean row = isElementPresent(By.xpath("//table/thead/tr/th"));
		String value = driver.findElement(By.xpath("//div[5]/div/table/thead/tr/th["+_index+"]")).getText();
		if(value.equals(rowName)){
			log("["+value+"]"+ "is present on field",ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public boolean verifyHistoryFieldPresent(){
		boolean row = isElementPresent(By.xpath("//div[5]/div/table/tbody/tr[2]/td[1]"));
		if(row){
			log("[Field] present in history tab",ILogLevel.ASSERTS);
			return true;
		}else{
			log("[Field] not present in history tab",ILogLevel.ASSERTS);
			return false;
		}

	}

	public boolean verifyHistoryFieldDataAfterChange(String expectedValue,int _index,String name){
		boolean row = isElementPresent(By.xpath("//div[5]/div/table/tbody/tr[1]/td["+_index+"]"));
		String value = driver.findElement(By.xpath("//div[5]/div/table/tbody/tr[1]/td["+_index+"]")).getText();
		if(value.equals(expectedValue)){
			log("["+name+"]"+" value is "+"["+value+"]",ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public void clickOnLabel(int _index,int number,String _value){
		click(By.xpath("(//input[@value='"+_index+"'])["+number+"]"),_value+" Clicked",2);
	}

	public boolean countRowInExcel(String _numberOfFilterAsset){
		Xls_Reader excel = new Xls_Reader(AssetsCategriesPageObject.filePath+fileName);
		int count = excel.getRowCount("Licenses");
		int length = count-1;
		String value = Integer.toString(length);
		if(value.equals(_numberOfFilterAsset)){
			log("Number of row["+value+"] is equal to ["+_numberOfFilterAsset+"]",ILogLevel.ASSERTS);
			return true;
		}else{
			log("Number of row["+value+"] is not equal to ["+_numberOfFilterAsset+"]",ILogLevel.ASSERTS);
			return false;
		}
	}

	public void selectClientToUpload(String _path, String _clientName){
		dropdownSelect(By.xpath(_path), _clientName);

	}

	public void clickOnExpectedFilterIconFromTableHeader(String _tableName, String _columnName, int _columnNumber){
		boolean filterIcon = isElementPresent(By.xpath("//th/div/span"));
		if(filterIcon){
			click(By.xpath("//th["+_columnNumber+"]/div/span"), "Table Name - "+_tableName+": Click on filter icon for ["+_columnName+"] column", 2);
		}

	}

	public boolean isExpectedFieldPresent(String _tablename, String _filedName, String _locator){
		boolean field = isElementPresent(By.xpath(_locator));
		if(field){
			log("["+_filedName+"] present in "+_tablename+" table.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public boolean isExpectedFilterBoxPresent(String _filterBoxName){
		boolean filterBox = isElementEnabled(By.xpath(InventoryPageObject.filterBox1_xpath+_filterBoxName+InventoryPageObject.filterBox2_xpath));
		log("["+_filterBoxName.toUpperCase()+"] filter box open.", ILogLevel.ASSERTS);
		return filterBox;
	}

	int i;
	public int clickOnExpectedSortIconFromTableHeader(String _tableName, String _columnName, String _locatorClass){
		int tableTitles = driver.findElements(By.xpath("//a[@class='textSpan']")).size();

		for(i=1; i<=tableTitles; i++){
			boolean sortIcon = isElementPresent(By.xpath("//th["+i+"]/div/a/i["+_locatorClass+"]"));
			String titlename = getText(By.xpath("//th["+i+"]/div/a"));
			if (sortIcon && titlename.contains(_columnName)) {
				click(By.xpath("//th["+i+"]/div/a/i["+_locatorClass+"]"), "["+_tableName+":] click Sort Icon in ["+_columnName+"] column", 3);
				return i;
			}else{
				continue;
			}
		}

		return i;	
	}

	public boolean isAscendingIconPresent(String _columnName, String _iconName, String _locatorClass){
		boolean sortIcon = driver.findElement(By.xpath("//th["+i+"]/div/a/i["+_locatorClass+"]")).isDisplayed();
		if (sortIcon) {
			click(By.xpath("//th["+i+"]/div/a/i["+_locatorClass+"]"), "["+_iconName+":] icon present and click on ["+_iconName+"] icon in ["+_columnName+"] column", 1);
			return sortIcon;
		}else{
			return false;
		}
	}

	public boolean isDescendingIconPresent(String _columnName, String _iconName, String _locatorClass){
		boolean sortIcon = driver.findElement(By.xpath("//th["+i+"]/div/a/i["+_locatorClass+"]")).isDisplayed();
		if (sortIcon) {
			log("["+_iconName+":] icon present in ["+_columnName+"] column", ILogLevel.METHOD);
			return sortIcon;
		}else{
			return false;
		}
	}

	public void selectOptionFromDropdownList(String _locator, String _filedName, String _option ){
		dropdownSelect(By.xpath(_locator), _option);
		log("["+_option+"] selected from "+_filedName+" list.",ILogLevel.METHOD);
	}

	
	public boolean isItemHide(String _gridId, String _expectedItemName){
		boolean gridElement= isElementPresent(By.xpath("//tbody["+_gridId+"]/tr/td"));
		String gridElementText = getText(By.xpath("//tbody["+_gridId+"]/tr/td"));
		if(gridElement && gridElementText.equalsIgnoreCase(InventoryPageObject.blankGridtext)){
			return true;
		}else{
			log("["+gridElementText+"] message appears in grid while hide ["+_expectedItemName+"].", ILogLevel.ASSERTS);
			return false;
		}
	}

	public void enterAndSelectManufacturer(String _key, int _indexOfSearchField, String _fieldName){
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"));
		sendKeys(By.xpath("("+InventoryPageObject.searchDepartmentField+")["+_indexOfSearchField+"]"), _key, 2, "value ["+_key+"] for field ["+_fieldName+"].");
		click(By.xpath(InventoryPageObject.selectmanufacturer_xpath));
		pause(3);
	}

	public boolean isUploadDone(String _expectedText){
		boolean uploadMesssage = isElementPresent(By.xpath(InventoryPageObject.uploadDescrip_xpath));
		if(uploadMesssage){
			String text = getText(By.xpath(InventoryPageObject.uploadDescrip_xpath));
			if(text.equalsIgnoreCase(_expectedText)){
				log("["+text+"] appears and upload not done", ILogLevel.ASSERTS);
			}
			return true;
		}else{
			return false;
		}
	}

	public boolean isExpectedFiltersAvailable(String _locator){

		boolean filter = isElementPresent(By.xpath(_locator));
		if(filter){
			int availableFilter = driver.findElements(By.xpath(_locator)).size();
			for(int i=1; i<=availableFilter; i++){
				String filterName = getText(By.xpath(_locator+"["+i+"]/span"));
				log("["+filterName+"] filter present in More Filter at position ["+i+"]", ILogLevel.ASSERTS);	  
			}
			pause(3);
			return true;
		}	
		return false;
	}

	public boolean isUserAbleToSearchFilter(String _locatorForFilteredItem, String expectedItemName, String _searchFiled){
		int gridSize = driver.findElements(By.xpath(_locatorForFilteredItem)).size();

		for(int i=1; i<=gridSize; i++){
			if(isElementPresent(By.xpath(_locatorForFilteredItem+"["+i+"]/span"))){
				String filterName = getText(By.xpath(_locatorForFilteredItem+"["+i+"]/span"));
				if(filterName.equals(expectedItemName)){
					log("["+filterName+"] appears after search.", ILogLevel.ASSERTS);
					fieldClear(By.xpath(_searchFiled));
					fieldClear(By.xpath(_searchFiled));
					pause(8);
					return true;
				}  
			}else{
				log("["+expectedItemName+"] Filter unavailable in More Filter section.", ILogLevel.ASSERTS);
				return false;
			}
		}
		return false;
	}

	public int clickOnAvailableItemChekBox(String _locator){	
		boolean filter = isElementPresent(By.xpath(_locator));
		int availableFilter = 0;
		int i = 0;
		if(filter){
			availableFilter = driver.findElements(By.xpath(_locator)).size();
			for(i=1; i<=availableFilter; i++){
				String filterName = getText(By.xpath(_locator+"["+i+"]/span"));
				click(By.xpath(_locator+"["+i+"]/input"), "["+filterName+"] Filter checked at position ["+i+"]", 2);	  
			}
			log("["+(i-1)+"] available filter check to apply filter.", ILogLevel.METHOD);
			pause(1);	
		}

		return availableFilter;	
	}

	public boolean isExpectedNoOfFilterApplied(String _locator, int _expectedAppliedFilter){
		boolean appliedFilterSection = isElementPresent(By.xpath(_locator));
		if(appliedFilterSection){
			int appliedFilter = driver.findElements(By.xpath(_locator)).size();
			if(appliedFilter==_expectedAppliedFilter){
				log("["+appliedFilter+"] available filter applied.", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;
	}



	public boolean isSearchedItemPresentInGrid(String _gridId, String tableName, String expectedItemName, String _columnName, int _columnNumber){
		waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 

		int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=gridSize; i++){
			boolean row = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
			if(row){
				String name = getText(By.xpath("//tbody["+_gridId+"]/tr["+i+"]/td["+_columnNumber+"]"));
				if(name.equals(expectedItemName)){
					log("["+_columnName+": "+name+"] is present in ["+_columnNumber+"] column in ["+tableName+"] grid.", ILogLevel.ASSERTS);
					return true;
				}else if(i>=gridSize){
					continue;
				}

			}
		}
		log("["+expectedItemName+"] is doesn't present in ["+tableName+"] grid.", ILogLevel.ASSERTS);  
		return false;	  

	}

	public boolean verifyFieldMandatory() {
		int flag = 0;
		String[] StringArray = new String[]{"Client *","Category *","Asset Tag *","Manufacturer *","Model *","Asset Name *","Supplier *","Serial Number *","Status *"};
		List<WebElement> label = driver.findElements(By.xpath(AssetsCategriesPageObject.label_Xpath));
		int labelSize = label.size();
		for(int i=1;i<=labelSize-2;i++) {
			for(int j=0;j<StringArray.length;j++) {
				String text = driver.findElement(By.xpath(AssetsCategriesPageObject.label_Xpath+"["+i+"]")).getText();
				if(text.equals(StringArray[j])) {
					log("["+text+"] field is mandatory",ILogLevel.METHOD);
					flag++;
					break;
				}
			}
		}
		if(flag==StringArray.length) {
			return true;
		}else {
			return false;
		}
	}

	public void clickOnEditLocationIcon(String _gridId, String tableName, int _iconColumnNumber, int _iconPosition){
		boolean grid = isElementPresent(By.xpath("//div["+_gridId+"]/div"));
		if(grid){
			int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/div")).size();

			for(int i=1; i<=gridSize; i++){
				click(By.xpath("//div["+_gridId+"]/div["+i+"]/ul/li["+_iconColumnNumber+"]/div/a["+_iconPosition+"]"),"[Edit] icon clicked for location", 4);
				break;
			}
		}else{
			log("[location not found!] text appears in Location grid.", ILogLevel.METHOD);
		}

	}

	public String returnExpectedText(String _locator){
		String text = getText(By.xpath(_locator));
		log("Expected text is: ["+text+"]", ILogLevel.METHOD);
		return text;
	}

	public boolean isExpectedTextPresentInExpectedField(String _locator, String _expectedText, String _fieldName, String _screenName){
		waitForElementDisplayed(By.xpath(_locator));
		String text = getText(By.xpath(_locator));
		if(text.equalsIgnoreCase(_expectedText)){
			log(""+_screenName+": ["+text+"] "+_fieldName+" appears in "+_fieldName+" fields.", ILogLevel.ASSERTS); 
			return true;
		}
		return false;
	}

	public boolean isClientListOpen(String _locator, String _filedName, String _screenName){
		boolean field = isElementPresent(By.cssSelector(_locator));
		if(field){
			log("["+_filedName+"] list opens in "+_screenName+" page.", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_filedName+"] list closed in "+_screenName+" page.", ILogLevel.ASSERTS);
			return false;
		}
	}

	public boolean isEdxpectedFieldsReadOnlyField(String _fieldName, int _index){
		boolean readOnlyField = isElementPresent(By.xpath("("+InventoryPageObject.fieldsReadOnly_xpath+")["+_index+"]"));
		if(readOnlyField){
			log("["+_fieldName+"] is Read Only field.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public boolean isExpectedFiledPresent(String _fieldname, String _screenName){
		boolean field = isElementPresent(By.xpath("//label[contains(text(), '"+_fieldname+"')]"));
		if(field){
			log("["+_fieldname+"] field is presnt in "+_screenName+" screen.", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_fieldname+"] field doesn't presnt in "+_screenName+" screen.", ILogLevel.ASSERTS);
			return false;
		}

	}

	public void clickOnViewEditDeleteSubLocationIcon(String _gridId, String tableName, String expectedItemName,int _searchedStringColumnNo, int _iconPosition){
		int gridSize = driver.findElements(By.xpath("//div["+_gridId+"]/ul")).size(); 
		log_Method("0 Size: "+gridSize);

		for(int i=1; i<=gridSize; i++){
			log_Method("1");
			String name = getText(By.xpath("//div["+_gridId+"]/ul/li["+_searchedStringColumnNo+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//div["+_gridId+"]/ul/div/a["+_iconPosition+"]"),"[Edit] icon clicked for ["+name+"] location", 2);
				break;
			}else{
				continue;
			}
		}
	}

	public boolean verifyParentLoc(String _locator, String expectedLoc, String _screenName){
		String rootLoc = getText(By.xpath(_locator));
		if(rootLoc.equalsIgnoreCase(expectedLoc)){
			log("["+rootLoc+"] is a Parent Location in '"+_screenName+"' screenn.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}

	public int returnGridSize(String _gridId, String _gridname, int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody[@id='searchTable']/tr"));
		boolean paginationtab = driver.findElement(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]")).isDisplayed();
		boolean paginationInfo = isElementPresent(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
		int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody[@id='searchTable']/tr")).size();
		if(gridSize==10 && paginationtab && paginationInfo){		
			String info = getText(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
			String word[]=info.split(" ");
			String gridSizeInfo = word[word.length-2];
			log("Pagination Info, pagination tab and ["+gridSizeInfo+"] records present in '"+_gridname+"' table", ILogLevel.METHOD);
			return Integer.parseInt(gridSizeInfo);
		}else if(gridSize<=10 && paginationtab==false && paginationInfo==false){
			log("["+(gridSize)+"] records present in "+_gridname+" table", ILogLevel.METHOD);
			return gridSize;
		}else{
			log_Method("Grid not found");
			return 0;
		}
	}
	
	public int returnOriginalGridSize(String _gridId, int index, String _gridname, int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		waitForElementDisplayed(By.xpath("(//table[@id='"+_gridId+"']/tbody)["+index+"]/tr"));
		boolean paginationtab = isElementPresent(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]"));
		boolean paginationInfo = isElementPresent(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
		int gridSize = driver.findElements(By.xpath("(//table[@id='"+_gridId+"']/tbody)["+index+"]/tr")).size();
		if(gridSize==10 && paginationtab && paginationInfo){		
			String info = getText(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
			String word[]=info.split(" ");
			String gridSizeInfo = word[word.length-2];
			log("Pagination Info, pagination tab and ["+gridSizeInfo+"] records present in '"+_gridname+"' table", ILogLevel.METHOD);
			return Integer.parseInt(gridSizeInfo);
		}else if(gridSize<=10 && paginationtab==false && paginationInfo==false){
			log("["+(gridSize)+"] records present in "+_gridname+" table", ILogLevel.METHOD);
			return gridSize;
		}else{
			log_Method("Grid not found");
			return 0;
		}
	}
	
	public int returnExpectedOriginalGridSize(String _locator, String _gridname, int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		waitForElementDisplayed(By.xpath(_locator));
		boolean paginationtab = driver.findElement(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]")).isDisplayed();
		boolean paginationInfo = isElementPresent(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
		int gridSize = driver.findElements(By.xpath(_locator)).size();
		if(gridSize==10 && paginationtab && paginationInfo){		
			String info = getText(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
			String word[]=info.split(" ");
			String gridSizeInfo = word[word.length-2];
			log("Pagination Info, pagination tab and ["+gridSizeInfo+"] records present in '"+_gridname+"' table", ILogLevel.METHOD);
			return Integer.parseInt(gridSizeInfo);
		}else if(gridSize<=10 && paginationtab==false && paginationInfo==false){
			log("["+(gridSize)+"] records present in "+_gridname+" table", ILogLevel.METHOD);
			return gridSize;
		}else{
			log_Method("Grid not found");
			return 0;
		}
	}
	
	public int returnAsserReportGridSize(String _gridId, String _gridname, int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex){
		waitForElementDisplayed(By.xpath("//table["+_gridId+"]/tbody/tr"));
		boolean paginationtab = isElementPresent(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]"));//driver.findElement(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]")).isDisplayed();
		boolean paginationInfo = isElementPresent(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
		int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();
		if(gridSize==10 && paginationtab && paginationInfo){		
			String info = getText(By.xpath("("+_paginationInfoLocator+")"+"["+paginationInfoIndex+"]"));
			String word[]=info.split(" ");
			String gridSizeInfo = word[word.length-2];
			log("Pagination Info, pagination tab and ["+gridSizeInfo+"] records present in '"+_gridname+"' table", ILogLevel.METHOD);
			return Integer.parseInt(gridSizeInfo);
		}else if(gridSize<=10 && paginationtab==false && paginationInfo==false){
			log("["+(gridSize)+"] records present in "+_gridname+" table", ILogLevel.METHOD);
			return gridSize;
		}else{
			log_Method("Grid not found");
			return 0;
		}
	}

	public boolean isQtyisEqualWithAssetGridSize(int _qty, int assetGeridSize){
		
		if(_qty==assetGeridSize){
			log("Qty is ["+_qty+"] and regarding asset grid size ["+assetGeridSize+"]", ILogLevel.ASSERTS);
			return true;
		}
		return false;
	}

	public void clickOnArrowIconOfFieldsOnLocation(String _fieldName, int _index){	
		waitForElementDisplayed(By.xpath("("+InventoryPageObject.locationScreenArrowIcon_xpath+")["+_index+"]"));
		click(By.xpath("("+InventoryPageObject.locationScreenArrowIcon_xpath+")["+_index+"]"), "["+_fieldName+"] arrow icon clicked.", 2);
	}

	public boolean returnvalueOfExpectedField(String _locator, String _filedname, String _initialOrSet){
		String fieldValue = getText(By.xpath(_locator));
		if(!fieldValue.equals("")){
			log(_initialOrSet+" "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return true;
		}else{
			log(_initialOrSet+" "+_filedname+": ["+fieldValue+"]", ILogLevel.METHOD);
			return false;
		}
	}

	public void clickOnNextpageOfPagination(int paginationTabIndex, String _paginationInfoLocator, int paginationInfoIndex, int expectedPageNo){
		boolean paginationtab = isElementPresent(By.xpath(InventoryPageObject.tabPagination_xpath+"["+paginationTabIndex+"]"));
		boolean paginationInfo = isElementPresent(By.xpath(_paginationInfoLocator+"["+paginationInfoIndex+"]"));
		if(paginationtab && paginationInfo){
			click(By.linkText(""+expectedPageNo+""), expectedPageNo+ "no. page of pagination clicked", 1);
		}  
	}

	public void setLatLong(){
		//Element(BANK) which need to drag.		
		WebElement From=driver.findElement(By.xpath("//map/area[@log='miw']"));					

		//Using Action class for drag and drop.		
		Actions act=new Actions(driver);					

		//Drag and Drop by Pixel.		
		act.dragAndDropBy(From,150, 10).build().perform();

		pause(3);
	}

	public boolean isLocationsPresentInGrid(String _gridId){
		waitForElementDisplayed(By.xpath("//div["+_gridId+"]"));
		boolean data = isElementPresent(By.xpath("//div["+_gridId+"]/div"));

		if(data){
			log("Location is present in grid.", ILogLevel.ASSERTS);
			return false;
		}
		else{
			log("Location does not present in grid.", ILogLevel.ASSERTS);
			return true;	
			}
	}
	
	public void selectOption(int _index,String _text){
		
		driver.findElement(By.xpath(DashboardPageObject.dropdown+"["+_index+"]")).click();
		
		log("Dropdown Clicked", ILogLevel.TEST);
		click(By.xpath(DashboardPageObject.optionList_xpath+_text+DashboardPageObject.optionList1_xpath), _text+" Selected", 3);
	}
	
	public void clickOnExpectedTab(String _tabName){
		click(By.xpath("//a[text()='"+_tabName+"']"), "Click on expected Tab: ["+_tabName+"]", 1);
	}
	
	public boolean verifyTabColorAccordingToAssignedContract(String _tabName, String _currentDate, String _expireDate){
		boolean licenseGrid = isElementPresent(By.xpath("//div[@id='tab-licenses']//table/tbody/tr"));
		String tabColor = driver.findElement(By.xpath("//a[text()='"+_tabName+"']")).getCssValue("color");
		
		if(licenseGrid){
			String expirationDate = getText(By.xpath("//div[@id='tab-licenses']//table/tbody/tr[1]/td[4]"));
			if(expirationDate.equalsIgnoreCase(_currentDate) && tabColor.equalsIgnoreCase("rgba(255, 0, 0, 1)")){
				log("Expiratation Date: '"+expirationDate+"' and date is over, so Contract tab is highlighted with [Red] color", ILogLevel.ASSERTS);
				return true;
			}else if(expirationDate.equalsIgnoreCase(_expireDate) && tabColor.equalsIgnoreCase("rgba(0, 128, 0, 1)")){
				log("Expiratation Date: '"+expirationDate+"' and date isn't over, so Contract tab is highlighted with [Green] color", ILogLevel.ASSERTS);
				return true;
			}else{
				log("Expiratation Date missing and Contract tab isn't highlighted.", ILogLevel.ASSERTS);
				return false;
			}
		}else if(licenseGrid==false && tabColor.equalsIgnoreCase("rgba(68, 68, 68, 1)")){
			log("[No assigned contracts to display] text appears so Contract tab is highlighted with [Black] color", ILogLevel.ASSERTS);
			return true;
		}
		
		return false;	
	}
	
	public boolean isExpectedOptionAvailableInDropdownList(String _itemName, String pageName){
		boolean dorpdownList = isElementPresent(By.xpath(InventoryPageObject.clientDropdown_xpath));
		int listSize = driver.findElements(By.xpath(InventoryPageObject.clientDropdown_xpath)).size();
		for(int i=1; i<=listSize; i++){
			String item = getText(By.xpath("("+InventoryPageObject.clientDropdown_xpath+")["+i+"]"));
			if(item.equalsIgnoreCase(_itemName)){
				log("["+item+"] option availabel in "+pageName+">Client list.", ILogLevel.ASSERTS);
				return true; 
			}
		}
		return false;
		
	}

	/*public void getLatLong(){
		google.maps.event.addListener() {
		    document.getElementById("latbox").value = this.getPosition().lat();
		    document.getElementById("lngbox").value = this.getPosition().lng();
		});
	}*/
	
	
	






}





