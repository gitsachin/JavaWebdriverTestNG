package com.auto.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;

public class BaseMobilePage extends Page {
	
	protected AppiumDriver driver1;
	public static String SCREENSHOT_FOLDER = "target/screenshots/";
	public static final String SCREENSHOT_FORMAT = currentDate()+".png";

	public BaseMobilePage(AppiumDriver driver1)
	{

		this.driver1 = driver1;

	}

	public final boolean waitForElementDisplayed(By by) {
		for(int sec=1; sec<=100; sec++){
			try{
				if ( driver1.findElement(by).isDisplayed() ) {
					Thread.sleep(1000);
					return true;
				}
			}catch(Exception e){}
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
		}
		//analyzeBrowserLogs();
		System.out.println("Debug Log : waitForElementDisplayed method : Element not displayed.");
		return false;
	}
	
	public final boolean waitForElementClickableAndClick(By _by) throws InterruptedException {
		for(int sec=1; sec<=10; sec++){
			try{
				driver1.findElement(_by).click();
				log_Method("Debug Log : waitForElementClickableAndClick - Able to Click Element");
				return true;
			}catch(Exception e){
				Thread.sleep(1000);
				log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click Element - Retrying again");
			}
		}
		//analyzeBrowserLogs();
		log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click");
		return false;
	}

	public final boolean waitForElementDisplayed(WebElement _ele) {
		for(int sec=1; sec<=100; sec++){
			try{
				if ( _ele.isDisplayed() ) {
					/*Thread.sleep(1000);*/
					return true;
				}
			}catch(Exception e){}
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
		}
		//analyzeBrowserLogs();
		return false;
	}

	public final WebDriver getWebDriver() {
		return driver1;
	}

	public final void pause(int seconds){
		pauseMilis(seconds*500);  
	}

	public final void pauseMilis(long miliSeconds){
		try { 
			Thread.sleep(miliSeconds); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * generate a rundom number of given length and returns it
	 * 
	 * @param length
	 * @return
	 */
	public String AutogenerateNumber(int length){
		return RandomStringUtils.randomNumeric(length);
	}

	public boolean isElementPresent(By by) {
		try {

			driver1.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitForAlert()
	{
		int i=0;
		while(i++<10)
		{
			try
			{
				driver1.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				pause(1);
				continue;
			}
		}
	}


	/**
	 * Returns true if alert is present
	 * else returns false
	 * 
	 * @return
	 */
	public boolean isAlertPresent(){
		pauseMilis(500);
		try{
			driver1.switchTo().alert();
			return true;
		} catch(NoAlertPresentException nep){
			return false;
		}
	}

	/**
	 * Get alert text
	 * 
	 * @return
	 */
	public String getAlertText(){
		pauseMilis(1000);
		return driver1.switchTo().alert().getText();
	}

	/**
	 * Accepts alert.
	 */
	public void acceptAlert(){
		pause(1);
		driver1.switchTo().alert().accept();
	}

	/**
	 * Dismiss alert
	 */
	public void dismissAlert(){
		pause(1);
		driver1.switchTo().alert().dismiss();
	}

	public void click(By _by){

		driver1.findElement(_by).click();
	}

	public void click(By _by, String _log, int _wait){

		driver1.findElement(_by).click();
		log("["+_log+"]",ILogLevel.TEST);
		pause(_wait);
	}

	public void findExpectedElement(By _by){
		driver1.findElement(_by);

	}

	public void fieldClear(By _by){
		driver1.findElement(_by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver1.findElement(_by).clear();
		pause(2);
	}

	public void sendKeys(By _by, String _key, int _wait,String _log){
		driver1.findElement(_by).sendKeys(_key);
		log("Enter "+ _log,ILogLevel.METHOD);
		pause(_wait);

	}
	
	public String getText(By _by){
		String text = driver1.findElement(_by).getText();
		return text;

	}

	public String getValue(By _by){
		String value = driver1.findElement(_by).getAttribute("value");
		return value;
	}
	
	public String dropDownGetSelectedOptionText(By _by){
		Select select = new Select(driver1.findElement(_by));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}
	
	public void log_Method(String _massageString){
		log("["+_massageString+"]",ILogLevel.METHOD);
	}

	public void dropdownSelect(By _by, String _option){
		new Select(driver1.findElement(_by)).selectByVisibleText(_option);
		pause(2);
	}

	public void dropdownSelectByIndex(By _by, int _arg){
		new Select(driver1.findElement(_by)).selectByIndex(_arg);;

	}

	public void dropdownSelectAll (By _by) {
		List<WebElement> listOfOptions = new Select(driver1.findElement(_by)).getOptions(); 
		Iterator<WebElement> it = listOfOptions.iterator();
		while (it.hasNext()) {
			WebElement ele = (WebElement) it.next();
			dropdownSelect(_by, ele.getText());
		}
	}

	public final boolean isElementEnabled(By by) {
		return driver1.findElement(by).isEnabled();
	}

	public final boolean waitForElementEnabled(By by) {
		for(int sec=1; sec<=100; sec++){
			try{
				if ( driver1.findElement(by).isEnabled() ) {
					Thread.sleep(1000);
					return true;
				}
			}catch(Exception e){}
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
		}
		//analyzeBrowserLogs();
		return false;
	}

	public String getValue(String _property) {
		return TestCore.config.getProperty(_property);
	}
	
	public List<WebElement> findElements(By by){
		return driver1.findElements(by);
	}
	
	public static String autogenerateNumber(int length){
		return RandomStringUtils.randomNumeric(length);
	}
	
	public static String currentDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		Date date = new Date(); 
		String date1= dateFormat.format(date).replaceAll(" ", "_");;
		return date1;
	}
	
	public void navigateBack(){
		driver1.navigate().back();
	}
	
	public void screenShot(String result){
		File f = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(f, new File(SCREENSHOT_FOLDER+ result + SCREENSHOT_FORMAT)
			.getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
