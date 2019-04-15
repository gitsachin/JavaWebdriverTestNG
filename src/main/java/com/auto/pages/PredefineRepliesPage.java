package com.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.PredefineRepliesPageObject;
import com.auto.pageobject.SystemPageObject;

public class PredefineRepliesPage extends BasePage {

	public PredefineRepliesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public int returnRowNoOfGrid(){
		if(isElementPresent(By.xpath(PredefineRepliesPageObject.gridId_xpath))){
			int size = driver.findElements(By.xpath(PredefineRepliesPageObject.gridId_xpath)).size();
			log("Now of Row in grid: ["+size+"]", ILogLevel.METHOD);
			return 	size;		
		}
		return 0;
	}
	
	public boolean isPaginationTabAndInfoBarPresent(int _gridSize){
		boolean paginationTab = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationTab_xpath));
		if(paginationTab && _gridSize==10){
			boolean paginationInfo = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath));
			if(paginationInfo){
				log("["+getText(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath))+"] paginatiom info appears.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+getText(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath))+"] paginatiom info doesn't appear.", ILogLevel.ASSERTS);
				return false;
			}
		}
		log("Paginatiom tab doesn't appears.", ILogLevel.ASSERTS);
		return false;
	}
	
	
	
	public boolean paginationInfoAppears(){
		int gridSize = driver.findElements(By.xpath(PredefineRepliesPageObject.gridId_xpath)).size();
		boolean paginationTab = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationTab_xpath));
		boolean paginationInfo = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath));
		if(paginationTab && paginationInfo && gridSize==10){
			log("[Pagination Tab And Info] present for redirect to another page.", ILogLevel.ASSERTS);
			log("["+getText(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath))+"] paginatiom info appears.", ILogLevel.ASSERTS);
			return true;	
		}else{
			log("Paginatiom Tab  And Pagination Info doesn't appears in page.", ILogLevel.ASSERTS);
			return false;
		}
		
	}
	
	public void checkAndCreatePredefinedReplie(){
		int size = driver.findElements(By.xpath(PredefineRepliesPageObject.gridId_xpath)).size();
		for(int i=size; i<=(10+1); i++){
			boolean paginationTab = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationTab_xpath));
			boolean paginationInfo = isElementPresent(By.xpath(PredefineRepliesPageObject.paginationInfo_xpath));
			if(paginationTab==false && paginationInfo==false && size<=10){
				click(By.xpath(PredefineRepliesPageObject.buttonPredefine_xapth), "Click on [Predefine Reply] button to create ["+(i+1)+"]th number records.", 2);
				sendKeys(By.xpath(SystemPageObject.textFieldsName_xpath), "PredefineReply_"+genAutoNumber(4), 1, "["+(i+1)+"]th number Predefined Replies.");
				click(By.xpath(SystemPageObject.buttonCreate_xpath));
				pause(1);
			}
		}
		log("[Pagination Tab And Info] appears in page when table have more than 10th numbers of records.", ILogLevel.METHOD);
	}

}
