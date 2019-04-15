package com.auto.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;



public class BasePage extends Page {

	protected WebDriver driver;
	public static String SCREENSHOT_FOLDER = "target/screenshots/";
	public static final String SCREENSHOT_FORMAT = currentDate() + ".png";

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	public final boolean waitForElementDisplayed(By by) {
		for (int sec = 1; sec <= 5; sec++) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					//Thread.sleep(1000);
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		System.out.println("Debug Log : waitForElementDisplayed method : Element not displayed.");
		return false;
	}
	
	/*public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }*/

	public final boolean waitForElementClickableAndClick(By _by) throws InterruptedException {
		for (int sec = 1; sec <= 10; sec++) {
			try {
				driver.findElement(_by).click();
				log_Method("Debug Log : waitForElementClickableAndClick - Able to Click Element");
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click Element - Retrying again");
			}
		}
		// analyzeBrowserLogs();
		log_Method("Debug Log : waitForElementClickableAndClick - Unable to Click");
		return false;
	}

	public final boolean waitForElementDisplayed(WebElement _ele) {
		for (int sec = 1; sec <= 10; sec++) {
			try {
				if (_ele.isDisplayed()) {
					/* Thread.sleep(1000); */
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		return false;
	}

	public final WebDriver getWebDriver() {
		return driver;
	}

	public final void pause(int seconds) {
		pauseMilis(seconds * 500);
	}

	public final void pauseMilis(long miliSeconds) {
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
	public String AutogenerateNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public String AutogenerateAlphanumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitForAlert() {
		int i = 0;
		while (i++ < 10) {
			try {
				driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				pause(1);
				continue;
			}
		}
	}

	/**
	 * Returns true if alert is present else returns false
	 * 
	 * @return
	 */
	public boolean isAlertPresent() {
		pauseMilis(500);
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException nep) {
			return false;
		}
	}

	/**
	 * Get alert text
	 * 
	 * @return
	 */
	public String getAlertText() {
		pauseMilis(1000);
		return driver.switchTo().alert().getText();
	}

	/**
	 * Accepts alert.
	 */
	public void acceptAlert() {
		pause(1);
		driver.switchTo().alert().accept();
	}

	/**
	 * Dismiss alert
	 */
	public void dismissAlert() {
		pause(1);
		driver.switchTo().alert().dismiss();
	}

	public void click(By _by) {

		driver.findElement(_by).click();
	}

	public void click(By _by, String _log, int _wait) {

		driver.findElement(_by).click();
		log("[" + _log + "]", ILogLevel.TEST);
		pause(_wait);
	}

	public void clicks(By _by, int _index, String _log, int _wait) {
		driver.findElements(_by).get(_index).click();
		log("[" + _log + "]", ILogLevel.TEST);
		pause(_wait);
	}

	public void findExpectedElement(By _by) {
		driver.findElement(_by);

	}

	public void fieldClear(By _by) {
		driver.findElement(_by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(_by).clear();
		pause(2);
	}
	
	public void fieldClears(By _by,int _index) {
		driver.findElements(_by).get(_index).clear();
		pause(2);
	}

	public void sendKeys(By _by, String _key, int _wait, String _log) {
		fieldClear(_by);
		driver.findElement(_by).sendKeys(_key);
		log("Enter " + _log, ILogLevel.METHOD);
		pause(_wait);

	}
	
	public void fileUpload(By _by, String _key, int _wait, String _log) {
		driver.findElement(_by).sendKeys(_key);
		log("Enter " + _log, ILogLevel.METHOD);
		pause(_wait);

	}

	public void sendKey(By _by, String _key, int _index, int _wait, String _log) {
		fieldClears(_by, _index);
		driver.findElements(_by).get(_index).sendKeys(_key);
		log(_log, ILogLevel.TEST);
		pause(_wait);

	}

	public String getText(By _by) {
		waitForElementDisplayed(_by);
		String text = driver.findElement(_by).getText();
		return text;

	}

	public String getTextWithIndex(By _by, int _index) {
		String text = driver.findElements(_by).get(_index).getText();
		return text;

	}

	public String getValue(By _by) {
		String value = driver.findElement(_by).getAttribute("value");
		return value;
	}

	public String dropDownGetSelectedOptionText(By _by) {
		Select select = new Select(driver.findElement(_by));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

	public void log_Method(String _massageString) {
		log("[" + _massageString + "]", ILogLevel.METHOD);
	}

	public void dropdownSelect(By _by, String _option) {
		new Select(driver.findElement(_by)).selectByVisibleText(_option);
		pause(2);
	}

	public void dropdownSelectByIndex(By _by, int _arg) {
		new Select(driver.findElement(_by)).selectByIndex(_arg);

	}

	public void dropdownSelectsByIndex(By _by, int _index, int _arg) {
		new Select(driver.findElements(_by).get(_index)).selectByIndex(_arg);

	}

	public void dropdownSelectAll(By _by) {
		List<WebElement> listOfOptions = new Select(driver.findElement(_by)).getOptions();
		Iterator<WebElement> it = listOfOptions.iterator();
		while (it.hasNext()) {
			WebElement ele = (WebElement) it.next();
			dropdownSelect(_by, ele.getText());
		}
	}

	public final boolean isElementEnabled(By by) {
		return driver.findElement(by).isEnabled();
	}

	public final boolean waitForElementEnabled(By by) {
		for (int sec = 1; sec <= 100; sec++) {
			try {
				if (driver.findElement(by).isEnabled()) {
					Thread.sleep(1000);
					return true;
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		// analyzeBrowserLogs();
		return false;
	}

	public String getValue(String _property) {
		return TestCore.config.getProperty(_property);
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public static String autogenerateNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	public String genAutoNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		Date date = new Date();
		String date1 = dateFormat.format(date).replaceAll(" ", "_");
		return date1;
	}
	
	
	public double getRandomNumber(){
	    double num = Math.random();
	    return num;
	}

	public static String currentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
		Date date = new Date();
		String date1 = dateFormat.format(date).replaceAll(" ", "_");
		return date1;
	}
	
	public static String currentOnlyDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		return date1;
	}
	
	public static String addDateToCurrentDate(int day){
		LocalDate tomorrow = LocalDate.now().plusDays(day);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String date = tomorrow.format(formatter);
	    return date;
	}

	public void navigateBack() {
		driver.navigate().back();
		
		pause(4);
	}
	
	public void navigateRefresh() {
		driver.navigate().refresh();
	}
	
	public void navigateUrl(String _url){
		driver.navigate().to(_url);
	}
	public void switchFrame(String _frameName){
		driver.switchTo().frame(_frameName);
	}
	
	public void switchToNewTab(){
		// Create Array List to keep Tab information
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        // Navigate to New Tab
        driver.switchTo().window(tabs2.get(1));
        //driver.close();
        
       
	}
	
	public void switchToParentTab(){
		// Create Array List to keep Tab information
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        // Navigate to Tab 0
        driver.switchTo().window(tabs2.get(0));
        
        
        // Navigate to Tab 0
        //driver.switchTo().window(tabs2.get(0));

	}
	
	

	public void takeScreenshot(){
		String timeStamp;
        File screenShotName;
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
        screenShotName = new File(System.getProperty("user.dir")+"//target//screenshots//"+timeStamp+".png");
        try {
            FileUtils.copyFile(scrFile, screenShotName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        String filePath = screenShotName.toString();
        String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
        Reporter.log(path);
	}
	
	public void waitForWorkAroundTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
