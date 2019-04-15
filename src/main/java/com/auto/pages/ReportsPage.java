package com.auto.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.ReportsPageObject;
import com.sun.jna.platform.win32.OaIdl.TYPEDESC._TYPEDESC;


public class ReportsPage extends BasePage {

	public ReportsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isExpectedTabPresent(String _locator, String _tabName){
		boolean tab = isElementPresent(By.xpath(_locator));
		if(tab){
			String text = getText(By.xpath(_locator));
			if(text.equalsIgnoreCase(_tabName)){
				log("["+text+"] tab present in tab bar.", ILogLevel.ASSERTS);		
			}
			return true;
		}else{
			return false;
		}
	}
	
	public void clickOnExpectedTab(String _locator, String tabName){
		click(By.xpath(_locator), "["+tabName+"] tab clicked.", 3); 
	}
	
	public boolean expectedSectionPresent(String _expectedSection){
		int size = driver.findElements(By.xpath(ReportsPageObject.boxTitle_xpath)).size();
		for(int i=1; i<=size; i++){
			String sectionName = getText(By.xpath(ReportsPageObject.boxTitle_xpath+"["+i+"]"));
			if(sectionName.equalsIgnoreCase(_expectedSection)){
				log("["+sectionName+"] present at ["+i+"]th position", ILogLevel.ASSERTS);
				return true;
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
		return false;
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
			pause(4);
			break;
		case 3: 
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.linkText(_locator)));
			log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
			pause(5);
			break;
		}
	}
	
	public void enterStartAndEndDate(String _locator, int _index, String _fieldName, String _date){
		sendKey(By.xpath(_locator), _date, _index, 1, "Enter "+_fieldName+": "+_date);
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
	
	
	public void clickOnGenerateButton(int _index, String _buttonName){
	
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(ReportsPageObject.buttonGenrate_xapth+"["+_index+"]")));
		log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		pause(4);
				
	}
	int element;
	public boolean isTimeSheetReportCreated(String _sectionName, String _clientname, String _timePeriod){
		String[] content = {_sectionName, _clientname, _timePeriod};
		int flag = 0;
		
		 element = driver.findElements(By.xpath("(//h4/b)")).size();
		
		for(int a=1; a<=element; a++){
			
			if(isElementPresent(By.xpath("(//h4/b)["+a+"]"))){
				
				String text = getText(By.xpath("(//h4/b)["+a+"]"));
				
				String arrayText="";
		        for (int i=0; i<content.length; i++) {
		        	
		        	arrayText = content[i];
		        	if(arrayText.equalsIgnoreCase(text) || arrayText.contains(text) || arrayText.equals(" ") ){
		        		log("Page content is: ["+content[i]+"]", ILogLevel.ASSERTS);
		        		flag++;
		        		
		        		break;
		        	}
		        }
	        //continue;
		}
			//continue;
	  }
		if(flag==element){
		return true;
			}
		else{
			return false;
			}
	}
	
	
	public String  expectedButtonName(String _locator){
		if(isElementPresent(By.linkText(_locator))){
			return getText(By.linkText(_locator));
		}
		return null;
	}
	
	public boolean isExpectedPageOpen(String _locator, String _expectedText){
		boolean content = isElementPresent(By.cssSelector(_locator));
		if(content){
			String text = getText(By.cssSelector(_locator));
			String argu = _expectedText.replace("(", "").replace(")", ""); 
			
			if(text.equalsIgnoreCase(argu ) || text.contains(argu) ){
				log("["+text+"] Page is Open.", ILogLevel.ASSERTS);
				return true;
			}else{
				return false;
			}
			
		}else{
			log("Expecte element not present in page", ILogLevel.ASSERTS);
			return false;
		}	  
	}
	
	public boolean isExpectedFieldPresent(String _expectedLabel){

		waitForElementDisplayed(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));

		boolean expectedField = isElementPresent(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));
		String label = getText(By.xpath(InventoryPageObject.labelModel_xpath+_expectedLabel+"')])"));

		if(expectedField){

			if(label.equals(_expectedLabel)){
				log("["+label+"] is present on Reports:Asset page.", ILogLevel.ASSERTS);
				return true;
			}
		}else{
			log("["+label+"] doesn't present on Reports:Asset page.", ILogLevel.ASSERTS);
			return false;
		}
		return expectedField;
	}
	
	public String clickOnReRunButton(){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[@class='text-right']")).size();
		for(int i=1; i<=gridSize; i++){
			boolean reRunLink = isElementPresent(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[8]/a[contains(text(),'Re-Run')]"));
			if(reRunLink){
				click(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'Re-Run')]"), "Click on Re-Run button.", 1);
				String reporterName = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[3]"));
				return reporterName;
			}else if(i<=gridSize){
				continue;
			}else{
				log("[Re-run] button doesn't present in grid.", ILogLevel.METHOD);
			}
		}
		return null;
	}
	
	public boolean isReRunLinkPresentAfterClickOnIt(String _expectedReporter){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr")).size();
		for(int i=1; i<=gridSize; i++){
			String reporterName = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[3]"));
			if(reporterName.equalsIgnoreCase(_expectedReporter) || reporterName.contains(_expectedReporter)){
				boolean reRunLinnk = isElementPresent(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'Re-Run')]"));
				if(reRunLinnk){
					log("[Re-Run] link doesn't present for Reporter: "+reporterName+"", ILogLevel.ASSERTS);
					return true;
				}else{
					return false;
				}
			}
		}
		return false;	
	}
	
	
	public boolean countDaysBetweenDate() throws IOException{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		log_Method("Current date: "+currentDate);
		
		int gridSize = driver.findElements(By.xpath("//table[@id='assetTable']/tbody/tr")).size();

		for(int i=1 ; i<=gridSize; i++){
			String startDate = getText(By.xpath("//table[@id='assetTable']/tbody/tr["+i+"]/td[16]")).replace("-", " ");
			String endDate = getText(By.xpath("//table[@id='assetTable']/tbody/tr["+i+"]/td[17]")).replace("-", " ");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
	        String firstInput = startDate;
	        String secondInput = endDate;
	        LocalDate firstDate = LocalDate.parse(firstInput, formatter);
	        LocalDate secondDate = LocalDate.parse(secondInput, formatter);
	        LocalDate curDate = LocalDate.parse(currentDate, formatter);
	        
	        long days = ChronoUnit.DAYS.between(firstDate, secondDate);
	        long daysFromCurrentDate = ChronoUnit.DAYS.between(curDate, secondDate);
	        
	       
	        if(i<=gridSize){
	        	if(days==30 || days==31 || days<=100){
	        		 System.out.println("Row:["+i+"], 'Start Date: "+firstDate+"', 'End Date: "+secondDate+"' and Days between: " + days);	
		        }else if(daysFromCurrentDate<=32){	
		        	System.out.println("Row:["+i+"], 'System Date: "+curDate+"', 'End Date: "+secondDate+"' and Days between: " + daysFromCurrentDate);	
		        }
	        	if(i==gridSize){
	        		 return true;
	        	}
			}
	       
	     }
		return false;
	        
	}
	
	public boolean isReRunLinkPresentInReportGrid(String _linkName){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr")).size();
		for(int i=1; i<=gridSize; i++){
			boolean reRunLinnk = isElementPresent(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'"+_linkName+"')]"));
			if(i<gridSize && reRunLinnk){
				continue;
			}else if(reRunLinnk==false){
				continue;
			}else if(i==gridSize && reRunLinnk){
				log("[Re-Run] link present in Reports Grid under 'Reports Job' tab.", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;	
	}
	
	public String returnReporterName(){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[@class='text-right']")).size();
		for(int i=1; i<=gridSize; i++){
			boolean reRunLink = isElementPresent(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'Re-Run')]"));
			if(reRunLink){
				String reporterName = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[3]"));
				return reporterName;
			}else if(i<=gridSize){
				continue;
			}else{
				log("[Re-run] button doesn't present in grid.", ILogLevel.METHOD);
			}
		}
		return null;
	}
	
	public String returnDownloadFormatOfExpectedReporter(String _expectedReporter){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[@class='text-right']")).size();
		for(int i=1; i<=gridSize; i++){
			String reporterName = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[3]"));
			if(reporterName.equalsIgnoreCase(_expectedReporter) || reporterName.contains(_expectedReporter)){
				String downloadFormat = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[6]"));
				log("Download fromat is ["+downloadFormat+"] of expected reporter ["+reporterName+"]", ILogLevel.METHOD);
				return downloadFormat;
			}
		}
		return null;
	}
	
	public void clickOnExpectedLink(String _expectedReporter){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[@class='text-right']")).size();
		for(int i=1; i<=gridSize; i++){
			String reporterName = getText(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[3]"));
			if(reporterName.equalsIgnoreCase(_expectedReporter) || reporterName.contains(_expectedReporter)){
				click(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'Edit')]"), "Click on Edit button.", 1);	
			}
		}	
	}
	
	public boolean isExpectedPopupOpen(String _expectedText){
		boolean content = isElementPresent(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
		if(content){
			String text = getText(By.xpath(InventoryPageObject.popupName_xpath1+_expectedText+InventoryPageObject.popupName_xpath2));
			if(text.equalsIgnoreCase(_expectedText)){
				log("["+text+"] pop-up opens/appears.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+text+"] pop-up doesn't open.", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("["+_expectedText+"] pop-up Close.", ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public boolean verifyExportTypeButton(String _expectedExportType){
		boolean exportType = isElementPresent(By.xpath(ReportsPageObject.buttonExportType_xpath));
		if(exportType){
			String text = getText(By.xpath(ReportsPageObject.buttonExportType_xpath));
			if(text.equalsIgnoreCase(_expectedExportType)){
				log("Report job Export Type: ["+text+"]", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;
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
	
	public boolean isExpectedLinkPresentInReportGrid(String _linkName){
		int gridSize = driver.findElements(By.xpath("//table[@id='report_table_view']/tbody[1]/tr/td[@class='text-right']")).size();
		for(int i=1; i<=gridSize; i++){
			boolean reRunLink = isElementPresent(By.xpath("//table[@id='report_table_view']/tbody[1]/tr["+i+"]/td[8]/a[contains(text(),'"+_linkName+"')]"));
			if(reRunLink){
				log("["+_linkName+"] button present in Reports Job grid.", ILogLevel.METHOD);
				return true;
			}else if(i<=gridSize){
				continue;
			}else{
				log("["+_linkName+"] button doesn't present in Reports Job grid.", ILogLevel.METHOD);
			}
		}
		return false;
	}
	
	


}
