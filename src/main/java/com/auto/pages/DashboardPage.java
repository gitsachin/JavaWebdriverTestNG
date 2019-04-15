package com.auto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.auto.base.BasePage;
import com.auto.base.ILogLevel;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.SettingPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;


public class DashboardPage extends BasePage{

	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clicks(String _button, int _index){
		if(isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath))){
			clicks(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath), _index, _button+" button clicked", 5);
			
		}
	}
	
	public void clickMenuTab(String _tabName){
		waitForElementDisplayed(By.xpath(DashboardPageObject.menu_xpath+_tabName+DashboardPageObject.menu1_xpath));
		
		WebElement _ele = driver.findElement(By.xpath(DashboardPageObject.menu_xpath+_tabName+DashboardPageObject.menu1_xpath));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();", _ele);
		
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(DashboardPageObject.menu_xpath+_tabName+DashboardPageObject.menu1_xpath)));
     	log("["+_tabName+"] Menu tab clicked", ILogLevel.METHOD);
     	pause(4);
		
	}
	
	public void clickButton(String _subMenu){
		pause(1);
		WebElement _ele = driver.findElement(By.xpath(SignUpPageObject.ButtonTagA_xpath+_subMenu+SignUpPageObject.ButtonTagA1_xpath));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView();", _ele);
		
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", _ele);
    	log("["+_subMenu+"] clicked", ILogLevel.METHOD);
    	pause(4);	
	}
	
	public boolean isExpectedMenuPresentInLeftPlanel(String _locator, String _tabName, String _log){
		boolean menu = isElementPresent(By.xpath(_locator));
		if(menu){
			log("Expected menu ["+_tabName+"] present "+_log, ILogLevel.ASSERTS);
			return true;
		}else{
			log("Expected menu ["+_tabName+"] doesn't present "+_log, ILogLevel.ASSERTS);
			return false;
		}	
	}
	
	
	public boolean verifyUpdatedText(By _by, String _oldText){
		waitForElementDisplayed(_by);
		String name = getText(_by) ;
		if(!name.equals(_oldText)){
			log("Text updated from ["+_oldText+"] to ["+name+"]", ILogLevel.METHOD);
			return true;
		}else{
			return false;
		}
	}
	
	
	public void logOut(String _url){
		
		driver.navigate().to(_url);
		log("Logout from current account and redirected to Login page.", ILogLevel.METHOD);
	}
	
	public void clickMenuListOption(int _index){

		String listName = getTextWithIndex(By.cssSelector(DashboardPageObject.subMenu_css), _index);
		clicks(By.cssSelector(DashboardPageObject.subMenu_css), _index, "["+listName+"] clicked", 3);
		
	}
	
	
	public void clickOnSpecificSubmenu(String _submenuName, int _index){
		String listName = getText(By.xpath(DashboardPageObject.submenuFirstPart_xpath + _submenuName + DashboardPageObject.submenuLastPart_xpath + "["+_index+"]"));
		click(By.xpath(DashboardPageObject.submenuFirstPart_xpath + _submenuName + DashboardPageObject.submenuLastPart_xpath + "["+_index+"]"), "["+listName+"] clicked", 3);
	}
	
	public boolean verifyIcon(String _selector){
		boolean menuIcon = isElementPresent(By.cssSelector(_selector));
		if(menuIcon){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void clickOnInnerSubmenu(String _menu, String _submenu, String _innerSubmenuName){
		waitForElementDisplayed(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_menu.toLowerCase()+"/"+_submenu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"));
		click(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_menu.toLowerCase()+"/"+_submenu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"), "Click on ["+_innerSubmenuName+"]", 3);
		
	}
	
	public void clickOnSubmenu(String _submenu, String _innerSubmenuName){
		waitForElementDisplayed(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_submenu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"));
		click(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_submenu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"), "Click on ["+_innerSubmenuName+"]", 3);
		
	}
	
	public boolean isListItemSameAsExpected(String _locator, int expectedListSize, String _listName){
		
		int actualListSize = driver.findElements(By.xpath(_locator)).size();
		if(actualListSize == expectedListSize){
			log("Total No of ["+_listName+"]: ["+actualListSize+"]", ILogLevel.ASSERTS);
			return true;
		}else if((actualListSize-1) == expectedListSize){
			log("Total No of ["+_listName+"]: ["+(actualListSize-1)+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
		
	}
	
	public void clickOnExpectedButton(String _locator, int _numForSwitch, String _buttonName){ // xpath: 1, cssSelector: 2
		  
		  switch (_numForSwitch)
		     {
		        case 1:
		        	waitForElementDisplayed(By.xpath(_locator));
		            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(_locator)));
		        	log("["+_buttonName+"] button clicked.", ILogLevel.METHOD);
		        	pause(3);
		        	break;
		        case 2:
		        	waitForElementDisplayed(By.cssSelector(_locator));
		        	 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector(_locator)));
		        	log("["+_buttonName+"] button clicked.",ILogLevel.METHOD);
		        	pause(3);
		        	break;
		        case 3:
		        	waitForElementDisplayed(By.id(_locator));
		        	 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id(_locator)));
		        	log("["+_buttonName+"] button clicked.",ILogLevel.METHOD);
		        	pause(3);
		        	break;
		     }
	  }
	
	public boolean isExpectedButtonPresent(String _screenName, String _locator, String _buttonName){
		boolean expectedButton = driver.findElement(By.xpath(_locator)).isDisplayed();
		if(expectedButton){
			log("["+_buttonName+"] button present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return expectedButton;
		}else{
			log("["+_buttonName+"] button doesn't present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return false;
		}
		  
	}
	
	public boolean isExpectedButtonAvailableInPage(String _button, String _screenName){
		boolean expectedButton = isElementPresent(By.xpath(SignUpPageObject.Button_xpath + _button + SignUpPageObject.Button1_xpath));
		if(expectedButton){
			log("["+_button+"] button present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return expectedButton;
		}else{
			log("["+_button+"] button doesn't present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return false;
		}
		  
	}
	
	public boolean isExpectedButtonLinkAvailableInPage(String _button, String _screenName){
		boolean expectedButton = isElementPresent(By.xpath(SignUpPageObject.ButtonTagA_xpath + _button + SignUpPageObject.ButtonTagA1_xpath));
		if(expectedButton){
			log("["+_button+"] button present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return expectedButton;
		}else{
			log("["+_button+"] button doesn't present in ["+_screenName+"] page.", ILogLevel.ASSERTS);
			return false;
		}
		  
	}
	
	public boolean isExpectedStringPresentInList(String _locator, String _expectedString, String _expectedListName){
		int actualListSize = driver.findElements(By.xpath(_locator)).size();
		String actualStriing = getText(By.xpath("//div[@id='js-legend']/ul/li[11]"));
		
		if(actualListSize == 11 && actualStriing.contains(_expectedString)){			
			log("["+_expectedString+"] is present in ["+_expectedListName+"] list.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean isExpectedItemPresentInList(String _locator, String _searchItemName, boolean _expectedAssert, String _listName){
		int countListItem = driver.findElements(By.xpath(_locator)).size();
		
		for(int i=1; i<=countListItem; i++){
			String itemName = getText(By.xpath(_locator+"["+i+"]"));
	
			if(itemName.equals(_searchItemName)){
				log("Search Item ["+itemName+"] present under ["+_listName+"] list.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}

		return _expectedAssert;
	}
	
	
	public boolean verifyExpectedFieldLabelPresent(String _expectedLabelName){
		boolean label = isElementPresent(By.xpath("//label[contains(text(), '"+_expectedLabelName+"')]"));
		
		if(label){
			log("Expected Field present with field name ["+_expectedLabelName+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}	
	}
	
	public void clickOnArrowIcon(int _index){
		List<WebElement> _ele = driver.findElements(By.xpath(DashboardPageObject.arrowIcon_xpath));
		_ele.get(_index).click();
	}
	
	public boolean veryfyCountryList(String _expectedCountry){
		int list = driver.findElements(By.xpath(DashboardPageObject.cuntryList_xpath)).size();
		for(int i=2; i<=list; i++){
			String countryName = getText(By.xpath(DashboardPageObject.cuntryList_xpath+"["+i+"]"));
			if(countryName.equalsIgnoreCase(_expectedCountry)){
				log("["+countryName+"] precent in Country list", ILogLevel.ASSERTS);
				break;
			}else{
				continue;
			}
		}
		return true;
	}
	
	public void clickOnExpectedMenus(String innerMenuName, String _menuName){
		String locator = DashboardPageObject.innerMenu1_xpath+innerMenuName+DashboardPageObject.innerMenu2_xpath;
		click(By.xpath(locator), _menuName+" clicked", 2);
	}
	
	public boolean isLogoPresent(String _locatorBeforeLogin){
		boolean logo = isElementPresent(By.xpath(_locatorBeforeLogin));
		String logoName = driver.findElement(By.xpath(_locatorBeforeLogin)).getAttribute("src");
		if(logo && logoName.contains(".png")){
			String logoSize = driver.findElement(By.xpath(_locatorBeforeLogin)).getAttribute("style");
			log("Logo is present : ["+logoName+"] and Image size: ["+logoSize+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyBackGroundColor(String _locator){
		String color = driver.findElement(By.xpath(_locator)).getCssValue("background-color");
		if(color.equals("rgba(16, 137, 242, 1)") || color.equals("rgba(16, 128, 243, 1)")){
			log("Logo back ground color is: Css["+color+"]", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean verifyAltTextOfLogo(String _expectedtext){
		String text = driver.findElement(By.xpath(DashboardPageObject.altText_xpath)).getAttribute("alt");
		log("Color: ["+text+"]", ILogLevel.METHOD);
		if(text.equalsIgnoreCase(_expectedtext)){
			log("Logo Image ALT text is: ["+_expectedtext+"] ", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpecteItemPresentInDashBoard(String _itemName1){
		int itemRecordBar = driver.findElements(By.xpath("//div[@class='inner']/p")).size();
		for(int i=1; i<=itemRecordBar; i++){
			String getname = getText(By.xpath("(//div[@class='inner']/p)["+i+"]"));
			if (getname.equals(_itemName1)) {
				return true;
			}else{
				continue;
			}
		}
		log("["+_itemName1+"] doesn't present in Dashboard page.", ILogLevel.ASSERTS);
		return false;	
	}
	
	public void sideMenuPanel(String _locator){
		int menuSize = driver.findElements(By.xpath(_locator)).size(); 
		log("["+menuSize+"] menus present in left side menu panel.", ILogLevel.METHOD);
		for(int i=1; i<=menuSize; i++){
			String getname = getText(By.xpath("//ul[@class='sidebar-menu']/li["+i+"]/a/span"));
			log(i+" menu ["+getname+"]", ILogLevel.METHOD);
		}
	}
	
	public boolean isExpectedMenuPresentInLeftSideMenuPlanel(String _locator, String _expectedMenu){
		int menuSize = driver.findElements(By.xpath(_locator)).size(); 
		for(int i=1; i<=menuSize; i++){
			String getname = getText(By.xpath("//ul[@class='sidebar-menu']/li["+i+"]/a/span"));
			if (getname.equals(_expectedMenu)) {
				log("["+_expectedMenu+"] present under left menu panel.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue;
			}
		}
		log("["+_expectedMenu+"] doesn't present under left menu panel.", ILogLevel.ASSERTS);
		return false;	
	}
	
	
	public void clickOnBrowserButton(){
		click(By.xpath(DashboardPageObject.browserButton_Xpath),"click browser button",3);
	}
	
	public void clickOnUser(){
		click(By.cssSelector(DashboardPageObject.userIcon_css),"Click On User",3);
	}
	
	public boolean CheckImage() throws Exception {
		String ImageFile = driver.findElement(By.xpath(DashboardPageObject.image_Xpath)).getAttribute("src");
	        
	        if (ImageFile.contains("image/jpeg"))
	        {
	        	log("Image Present", ILogLevel.ASSERTS);
	        	return true;
	        }
	        else
	        {
	        	log("Image is not Present", ILogLevel.ASSERTS);
				return false;
	        }
		}
	
	public boolean verifyURL(){
        String url = driver.getCurrentUrl();
        String uri = url.split("://")[0];
        if(uri.equals("https")) {
            return true;
        }else {
            return false;
        }
    }
	
	public void clickOnUserTab(){
		click(By.id(DashboardPageObject.userTab_id),"Click on User Tab",2);
	}
	
	public boolean verifyPermission(String _submenuElement){
		
		List<WebElement> submenu = driver.findElements(By.cssSelector(DashboardPageObject.subMenu_css)); 
		for (int i =0; i<submenu.size();i++){
			
			String text = driver.findElements(By.cssSelector(DashboardPageObject.subMenu_css)).get(i).getText();
			if(text.equals(_submenuElement)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isExpectedInnerMenuPresentInLeftSideMenuPlanel(String _parentMenu, String _expectedInnerMenu){
		boolean menu = isElementPresent(By.xpath(DashboardPageObject.leftSideInnerMenuPanel1_xpath+_expectedInnerMenu+DashboardPageObject.innerMenu2_xpath)); 
		
		if (menu) {
			log("["+_expectedInnerMenu+"] present under left menu panel ["+_parentMenu+"].", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_expectedInnerMenu+"] doesn't present under left menu panel.", ILogLevel.ASSERTS);
			return false;
		}		
	}
	
	public boolean isExpectedSubMenuPresentInLeftMenuPlanel(String _parentMenu, String _expectedSubMenu){
		boolean menu = isElementPresent(By.xpath(DashboardPageObject.menu_xpath+_expectedSubMenu+DashboardPageObject.menu1_xpath)); 
		log_Method("Path: "+DashboardPageObject.menu_xpath+_expectedSubMenu+DashboardPageObject.menu1_xpath);
		if (menu) {
			log("["+_expectedSubMenu+"] present under left menu panel ["+_parentMenu+"].", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_expectedSubMenu+"] doesn't present under left menu panel.", ILogLevel.ASSERTS);
			return false;
		}		
	}
	
	public boolean isExpectedFieldPresent(String _pageeName, String _filedName, String _locator){
		boolean field = isElementPresent(By.xpath(_locator));
		if(field){
			log("["+_filedName+"] present in "+_pageeName+" table.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isNewRoleOptionPresent(String _tablename, String _filedName, String _locator){
		boolean field = isElementPresent(By.cssSelector(_locator));
		if(field){
			log("["+_filedName+"] present in "+_tablename+" table.", ILogLevel.ASSERTS);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExpectedIconPresentInGrid(String _gridId,String _tableName, String _expectedName, int _buttonindex, String _buttonName, int columnNoForIcon, String _Name, int _columnNoForExpectedString){
		
		waitForElementDisplayed(By.xpath("//table/tbody["+_gridId+"]/tr"));
		boolean grid = isElementPresent(By.xpath("//table/tbody["+_gridId+"]/tr"));
		int size = driver.findElements(By.xpath("//table/tbody["+_gridId+"]/tr")).size();

		for(int i=1; i<=size; i++){
			String actualName = getText(By.xpath("//table/tbody["+_gridId+"]/tr["+i+"]/td["+_columnNoForExpectedString+"]"));
			if(grid == true && actualName.equals(_expectedName) || actualName.contains(_expectedName)){
				
				WebElement _ele = driver.findElement(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 js.executeScript("arguments[0].scrollIntoView();", _ele);
				 
				 boolean icon = isElementPresent(By.xpath("(//table/tbody["+_gridId+"]/tr[1]/td["+columnNoForIcon+"]//a/i)["+_buttonindex+"]"));
				 log("["+_buttonName+"] icon present in ["+_tableName+"] .", ILogLevel.ASSERTS);
				 return icon;
			}else{
				continue;
			}
		}
		return grid;
	}
	
	public void enterExpectedValueInTextField(String _locator, int _numForSwitch, String _key, String _textFieldName){
		String key = _key.trim();
		switch (_numForSwitch)
		{
		case 1:
			fieldClear(By.xpath(_locator));
			sendKeys(By.xpath(_locator), key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		case 2:
			fieldClear(By.cssSelector(_locator));
			sendKeys(By.cssSelector(_locator), key, 2, "["+_textFieldName+": "+_key+"]");
			break;
		}
	}
	
	public boolean isLoadSpinnerAppear(String _editScreenName, String _locator, String _elementName){
		   boolean spinnerLocate = isElementPresent(By.xpath(_locator));
		   boolean spinnerDisplay = driver.findElement(By.xpath(_locator)).isDisplayed();
		   
		   if(spinnerLocate || spinnerDisplay){
		   
			   log("["+_elementName+"] present in ["+_editScreenName+"] page.", ILogLevel.ASSERTS);
			   return true;
		   }else{
			   log("["+_elementName+"] doesn't present in ["+_editScreenName+"] page.", ILogLevel.ASSERTS);
			   return false;
		   }
	}
	
	public boolean isSearchedItemPresentInGrid(String _gridId, String tableName, String expectedItemName, int _columnNumber){
		  waitForElementDisplayed(By.xpath("//tbody["+_gridId+"]/tr")); 
		  boolean SearchTable = isElementPresent(By.xpath("//tbody["+_gridId+"]/tr"));
		  if(SearchTable){
			  int gridSize = driver.findElements(By.xpath("//tbody["+_gridId+"]/tr")).size();
			  String name = getText(By.xpath("//tbody["+_gridId+"]/tr[1]/td["+_columnNumber+"]"));
			  if(gridSize==1 && name.equals(expectedItemName) || name.contains(expectedItemName)|| name.equalsIgnoreCase(expectedItemName)){
				  log("["+name+"] is present in ["+tableName+"] grid.", ILogLevel.ASSERTS);
				 return true;
			  }else{
				  return false;
			  }
		  }else{
			  return false;
		  }	 
	}
	
	public void clickOnSubscriptionInnerSubmenu(String _menu, String _innerSubmenuName){
		waitForElementDisplayed(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_menu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"));
		click(By.xpath(DashboardPageObject.locatorInnerSubmenus_xpath+_menu.toLowerCase()+"/"+_innerSubmenuName.toLowerCase().replace(" ", "")+ "']"), "Click on ["+_innerSubmenuName+"]", 3);
		
	}
	
	public boolean isSuccessfullMessageAppear(String _operation){
		boolean alert1 = isElementPresent(By.cssSelector(SystemPageObject.error_css));
		//boolean alert2 = driver.findElement(By.cssSelector(SystemPageObject.error_css)).isDisplayed();
		
		//String message = getText(By.cssSelector(SystemPageObject.error_css));
		if(alert1){
			/*String message = getText(By.cssSelector(SystemPageObject.error_css));
			if(message.equalsIgnoreCase(_expectedMessage)){
				log("["+message+"] alert message appears.", ILogLevel.ASSERTS);	 
				return true;
			}
			else{
				log("["+message+"] alert message not visible.", ILogLevel.ASSERTS);  
				return false;	
			}*/	
			log("["+_operation+"] message appears", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_operation+"] message doesn't appears", ILogLevel.ASSERTS);
			return false;
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
	
	public boolean isExpectedHeadertextPresent(String _expectedText){
		boolean content = isElementPresent(By.xpath(InventoryPageObject.headerText_xpath1+_expectedText+InventoryPageObject.headerText_xpath2));
		if(content){
			String text = getText(By.xpath(InventoryPageObject.headerText_xpath1+_expectedText+InventoryPageObject.headerText_xpath2));
			if(text.equalsIgnoreCase(_expectedText)){
				log("["+text+"] header present in opened screen.", ILogLevel.ASSERTS);
				return true;
			}else{
				log("["+text+"] header doesn't present in opened screen.", ILogLevel.ASSERTS);
				return false;
			}
		}else{
			log("["+_expectedText+"] missing and redirected to wrong page.", ILogLevel.ASSERTS);
			return false;
		}
	}
	
	public boolean isExpectedMenuDisplayedInLeftPlanel(String _locator, String _tabName, String _log){
		boolean menu = driver.findElement(By.xpath(_locator)).isDisplayed();//isElementPresent(By.xpath(_locator));
		if(menu){
			log("Expected menu ["+_tabName+"] present "+_log, ILogLevel.ASSERTS);
			return true;
		}else{
			log("Expected menu ["+_tabName+"] doesn't present "+_log, ILogLevel.ASSERTS);
			return false;
		}	
	}
	
	
	public boolean verifyClientComboInExpectedFileds(String _client, String _clientLocator, String _assignedtoLocator, String _assignedTo){
		waitForElementDisplayed(By.xpath(_clientLocator));
		String client = getText(By.xpath(_clientLocator));
		String assignedTo = getText(By.xpath(_assignedtoLocator));
		/*log_Method("Clients get : "+client);
		log_Method("Clients Argu: "+_client);*/
		
		if(client.equalsIgnoreCase(_client)){
			/*log_Method("Asset Admin get : "+assignedTo);
			log_Method("Asset Admin argu: "+_assignedTo);*/
			if (assignedTo.equalsIgnoreCase(_assignedTo)) {
				log("Client is: ["+client+"] and 'Assigned To'/'Asset Admin' is: ["+assignedTo+"]", ILogLevel.ASSERTS);
				return true;
			}
		}
		return false;
	}
	
	public boolean verifyClientComboSuggestionListInExpectedFileds(String _fieldname, String _locator, String _clientLocator){
		waitForElementDisplayed(By.xpath(_locator));
		String client = getText(By.xpath(_clientLocator));
		String text = getText(By.xpath(_locator));
		int size = driver.findElements(By.xpath(_locator)).size();
		if(size>1){
			log("["+size+"] item present in ["+_fieldname+"] field suggestion list when selected client is: ["+client+"]", ILogLevel.ASSERTS);
			log("["+_fieldname+"] Field Contents: ", ILogLevel.METHOD);
			for(int r=1; r<=size; r++){
				log(r+"th item: ["+getText(By.xpath("("+_locator+")["+r+"]"))+"]", ILogLevel.METHOD);
			}
			return true;	
		}else if(size==1){
			if(text.equalsIgnoreCase("Nobody") || text.equalsIgnoreCase("None") || text.equalsIgnoreCase("No results found")){
				log("["+text+"] appears in "+_fieldname+" field suggestion list when selected client is: ["+client+"]", ILogLevel.ASSERTS);
				return false;
			}	
		} 
		return false;
	}
	
	public boolean isExpectedColumnPresent(String _locator, String _expectedColumnName){
		int size = driver.findElements(By.xpath("//table["+_locator+"]/thead/tr/th")).size();
		for(int i=1; i<=size; i++){
			String columnName = getText(By.xpath("//table["+_locator+"]/thead/tr/th["+i+"]"));
			if(columnName.contains(_expectedColumnName)){
				log("["+columnName+"] column present in ["+i+"] no position.", ILogLevel.ASSERTS);
				return true;
			}else{
				continue; 
			}
		}
		log("["+_expectedColumnName+"] column doesn't present.", ILogLevel.ASSERTS);
		return false;
	}
	
	public boolean verifyClientListSize(String _pageName){
		boolean clientList = isElementPresent(By.xpath("//span[@class='select2-results']/ul/li"));
		if(clientList){
			int listSize = driver.findElements(By.xpath("//span[@class='select2-results']/ul/li")).size();
			if(listSize==1 || listSize==2){
				log("Only ["+listSize+"] client present in Client list in '"+_pageName+"'", ILogLevel.ASSERTS);
				return true;
			}else{
				return false;
			}
		}
		return clientList;
	}
	
	public void clickOnClientDropDownnIcon(int _index){
		click(By.xpath(SettingPageObject.dropdown+"["+_index+"]"), "Click on Client drp-down icon.", 1);
	}
	
	public boolean isExpectedClientPresentInClientList(String expectedClient, String _pageName){
		boolean clientList = isElementPresent(By.xpath("//span[@class='select2-results']/ul/li"));
		if(clientList){
			int listSize = driver.findElements(By.xpath("//span[@class='select2-results']/ul/li")).size();
			String clientName = "";
			for(int i=1; i<=listSize; i++){
				clientName = getText(By.xpath("//span[@class='select2-results']/ul/li["+i+"]"));
				log("Client in client List: ["+clientName+"]", ILogLevel.METHOD);
				if(clientName.equalsIgnoreCase(expectedClient)){
					log("["+clientName+"] client present in Client list '"+_pageName+"'", ILogLevel.ASSERTS);
					return true;
				}else if(i>listSize){
					log("["+clientName+"] client doesn't present in Client list '"+_pageName+"'", ILogLevel.ASSERTS);
					return false;
				}
			}
		}else{
			log("Not any client present in Client list '"+_pageName+"'", ILogLevel.ASSERTS);
			return false;
		}
		
		return clientList;
	}
	
	public void selectOption(int _index,String _text){
		
		driver.findElement(By.xpath(DashboardPageObject.dropdown+"["+_index+"]")).click();
		if(isElementPresent(By.xpath(DashboardPageObject.removeChoice_xpath+"["+(_index-2)+"]"))){
			click(By.xpath(DashboardPageObject.removeChoice_xpath+"["+(_index-2)+"]"));
		}
		
		log("Dropdown Clicked", ILogLevel.METHOD);
		//clicks(By.xpath(SettingPageObject.optionList_xpath+_text+SettingPageObject.optionList1_xpath), _index,_text+" Selected", 3);
		click(By.xpath(DashboardPageObject.optionList_xpath+_text+DashboardPageObject.optionList1_xpath), _text+" Selected", 3);
	}
	
	public boolean isExpectedSubMenuPresentInLeftMenuPlanel(String _parentMenu, String _expectedSubMenu, int _index){
		boolean menu = isElementPresent(By.xpath("("+DashboardPageObject.innerMenu1_xpath+_expectedSubMenu+DashboardPageObject.innerMenu2_xpath+")["+_index+"]")); 
		if (menu) {
			log("["+_expectedSubMenu+"] present under left menu panel ["+_parentMenu+"].", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_expectedSubMenu+"] doesn't present under left menu panel.", ILogLevel.ASSERTS);
			return false;
		}		
	}
	
	
	public boolean isExpectedSubMenuPresentInExpectedMenuInLeftMenuPlanel(String _menuName, int _menuListIndex, String _expectedSubMenu){
		boolean menu = isElementPresent(By.xpath("//ul[@class='sidebar-menu']/li[2]/ul//a[contains(text(),'"+_expectedSubMenu+"')]")); 
		if (menu) {
			log("["+_expectedSubMenu+"] present under left menu panel ["+_menuName+"].", ILogLevel.ASSERTS);
			return true;
		}else{
			log("["+_expectedSubMenu+"] doesn't present under left menu panel.", ILogLevel.ASSERTS);
			return false;
		}		
	}
	
	
	
	public boolean verrifySubMenu(String _menuName, int _menuListIndex, int stringArraySize, String submenu[]){
		boolean subMenuList = isElementPresent(By.xpath("//ul[@class='sidebar-menu']/li["+_menuListIndex+"]/ul/li"));
		if(subMenuList){
			for(int i=1; i<=stringArraySize; i++){
				String items = getText(By.xpath("//ul[@class='sidebar-menu']/li["+_menuListIndex+"]/ul/li["+i+"]/a")).replace("\n", " ");
				/*int index= items.lastIndexOf(" ");
				String sub_menu = items.substring(0, index);*/   
				if(items.contains(submenu[i-1])){
					log("["+i+"]th sub-menu ["+items+"] presnent under ["+_menuName+"] menu list.", ILogLevel.ASSERTS);
					if(i==stringArraySize){
						return true;
					}else{
						continue;
					}
					
				}else{
					log("["+items+"] sub menu doesn't present under ["+_menuName+"] menu.", ILogLevel.ASSERTS);
					return false;
				}
			}
		}
		return subMenuList;
	}
	
	public boolean verrifyInnerSubMenu(String _menuName, int _menuListIndex, int stringArraySize, String _submenu, String innerSubmenu[]){
		boolean subMenuList = isElementPresent(By.xpath("//ul[@class='sidebar-menu']/li["+_menuListIndex+"]/ul/li/ul"));
		if(subMenuList){
			for(int i=1; i<=stringArraySize; i++){
				String items = getText(By.xpath("//ul[@class='sidebar-menu']/li["+_menuListIndex+"]/ul/li/ul/li["+i+"]/a")).replace("\n", " ");
				if(items.contains(innerSubmenu[i-1])){
					log("["+i+"]th inner sub-menu ["+items+"] presnent under ["+_menuName+">"+_submenu+"] menu list.", ILogLevel.ASSERTS);
					if(i==stringArraySize){
						return true;
					}else{
						continue;
					}
					
				}else{
					log("["+items+"] sub menu doesn't present under ["+_menuName+"] menu.", ILogLevel.ASSERTS);
					return false;
				}
			}
		}
		return subMenuList;
	}
	
	
	public boolean isExpectedSummaryAvailableOnDashboard(String _itemName, String pageName){
		boolean dorpdownList = isElementPresent(By.xpath(DashboardPageObject.summarySection_xpath));
		if(dorpdownList){
			int listSize = driver.findElements(By.xpath(DashboardPageObject.summarySection_xpath)).size();
			for(int i=1; i<=listSize; i++){
				String summary = getText(By.xpath("("+DashboardPageObject.summarySection_xpath+")["+i+"]"));
				if(summary.equalsIgnoreCase(_itemName)){
					log("["+summary+"] availabel on "+pageName+" at ["+i+"]th position.", ILogLevel.ASSERTS);
					return true; 
				}
			}
		}
		return false;
		
	}
	

	public boolean isExpectedFieldLabelPresent(String _expectedLabel, String _screenName){
		waitForElementDisplayed(By.xpath("(//label)"));
		int totalField = driver.findElements(By.xpath("(//label)")).size();
		for(int i=1; i<=totalField; i++){
			boolean expectedField = isElementPresent(By.xpath("(//label)["+i+"]"));
			String label = getText(By.xpath("(//label)["+i+"]"));

			if(expectedField){

				if(label.equals(_expectedLabel) || label.contains(_expectedLabel)){
					log("["+label+"] is present on '"+_screenName+"' page at ["+i+"]th position.", ILogLevel.ASSERTS);
					return true;
				}
			}else if(i==totalField){
				log("["+label+"] doesn't present on Create New Asset form", ILogLevel.ASSERTS);
				return false;
			}else{
				continue;
			}
		}
		return false;
	}
		
	public boolean verifyServiceRequest(String menuName){
		boolean serviceRequest = isElementPresent(By.xpath(DashboardPageObject.serviceRequest_xpath));
		if(serviceRequest){
			String text = driver.findElement(By.xpath(DashboardPageObject.serviceRequest_xpath)).getText();
			if(text.equals(menuName)){
				driver.findElement(By.xpath(DashboardPageObject.serviceRequest_xpath)).click();
				log("["+text+"] is present on dashboard",ILogLevel.ASSERTS);
				return true;
			}else{
				return false;

			}
		}
		return false;
	}
	
	
	

	

}
