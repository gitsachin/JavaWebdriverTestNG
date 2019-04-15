package com.auto.pages;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.KnowledgeBasePageObject;

public class KnowledgeBasePage extends BasePage {

	public KnowledgeBasePage(WebDriver driver) {
		super(driver);
	}
	
	public static String fileName = "";
	
	public boolean isUploadedFilePresent(int index){
		boolean file = isElementPresent(By.xpath(KnowledgeBasePageObject.uploadedFile_xpath));
		
		if(file){
			click(By.xpath(KnowledgeBasePageObject.fileButton_xpath+"["+index+"]"));
			log("file is present click on [download] button",ILogLevel.ASSERTS);
			return true;
		}else{
			log("file not present in Article report",ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public boolean verifyUploadedFile(String _expectedExcelFile){
		boolean file = isElementPresent(By.xpath(KnowledgeBasePageObject.uploadedFile_xpath));
		int size = driver.findElements(By.xpath(KnowledgeBasePageObject.uploadedFile_xpath)).size();
		String totalNumber = Integer.toString(size);
		if(totalNumber.equals(_expectedExcelFile)){
			log("File sucessfully uploaded",ILogLevel.ASSERTS);
			return true;
		}else{
			log("File not uploaded",ILogLevel.ASSERTS);
			return false;
		}
	}
	public void clickOnViewEditDeleteLocationIcon(String _gridId, String tableName, String expectedItemName,int _searchedStringColumnNo, int _iconColumnNumber, int _iconPosition){
		int gridSize = driver.findElements(By.xpath("//table["+_gridId+"]/tbody/tr")).size();

		for(int i=1; i<=gridSize; i++){
			String name = getText(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_searchedStringColumnNo+"]"));
			if(name.equals(expectedItemName)){
				click(By.xpath("//table["+_gridId+"]/tbody/tr["+i+"]/td["+_iconColumnNumber+"]/div//a["+_iconPosition+"]"),"[Edit] icon clicked for ["+name+"] location", 2);
				break;
			}else{
				continue;
			}
		}
	}
	
	public boolean fileExist(String _fileExt) throws FileNotFoundException{

		File s = new File(AssetsCategriesPageObject.filePath);
		File[] name = s.listFiles();
		for(File file : name){
			if(file.isFile()){
				fileName = file.getName();
				if(fileName.contains(_fileExt)){
					log("["+fileName+"] exist",ILogLevel.ASSERTS);
					pause(20);
					return true;
				}
			}
		}
		log("File not exist",ILogLevel.ASSERTS);
		return false;


	}
	
	public void sendFile(String _fileName,int _index){

		pause(2);
		System.out.println(System.getProperty("user.dir") + "//src//main//resources//files//"+_fileName);
		sendKeys(By.xpath(KnowledgeBasePageObject.uploadFileButton_xpath+"["+_index+"]"), System.getProperty("user.dir") + "//src//main//resources//files//"+_fileName,3, "File");  
	}
	
	public void sendFiles(String _fileName,int _index){
		pause(2);
		sendKeys(By.xpath(KnowledgeBasePageObject.uploadFileButton_xpath+"["+_index+"]"), AssetsCategriesPageObject.filePath+_fileName,3, "File");  
	}

	
	

}
