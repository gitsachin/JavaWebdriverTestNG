package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.TemplatePageObject;

public class TemplatePage extends BasePage{

	public TemplatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCreateTemplatePresent(String _id, String _gridName, String _expectedTemplate){
		waitForElementDisplayed(By.xpath("//table["+_id+"]/tbody"));
		
		int rowSize = driver.findElements(By.xpath("//table["+_id+"]/tbody/tr")).size();
		
		for(int i=1; i<=rowSize; i++){
			String templateName = getText(By.xpath("//table["+_id+"]/tbody/tr["+i+"]/td[1]"));
			if(templateName.equals(_expectedTemplate)){
				log("["+_expectedTemplate+"] template present in '"+_gridName+"'and Grid Size ["+rowSize+"]", ILogLevel.ASSERTS);
				return true;
			}
		}
		log("["+_expectedTemplate+"] template doesn't present in '"+_gridName+"' when Grid Size ["+rowSize+"]", ILogLevel.ASSERTS);
		return false;
	}
	
	 public void clickOnViewEditDelIcons(String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		 
		  boolean grid = isElementPresent(By.xpath("//table/tbody/tr"));
		  int size = driver.findElements(By.xpath("//table/tbody/tr")).size();
		  
		  for(int i=1; i<=size; i++){
			  String actualName = getText(By.xpath("//table/tbody/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			  if(grid == true && actualName.equalsIgnoreCase(_expectedName)){
				  click(By.xpath(("(//table/tbody/tr["+i+"]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]")), "["+_buttonName+"] icon clicked for ["+_Name+"].", 2);
				  break;
			  }else{
				  continue;
			  }
		  }
	  }
	 
	 public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		        	sendKeys(By.xpath(_locator), _key, 2, "["+_textFieldName+"]");
		        	break;
		        case 2:
		        	sendKeys(By.cssSelector(_locator), _key, 2, "["+_textFieldName+"]");
		        	break;
		     }
	  }

}
