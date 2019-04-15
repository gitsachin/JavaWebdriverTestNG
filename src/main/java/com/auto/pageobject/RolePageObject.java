package com.auto.pageobject;

import com.auto.base.BasePage;

public class RolePageObject {
	
	public static String roleName = "Role_"+BasePage.currentDate();
	public static String clientName = "QATEST";
	public static String role = "Role24_03_2018_22_27_05";
	public static String userName = "User"+BasePage.currentDate();
	
	public static String client_id = "select2-clientId-container";
	public static String checkbox_xpath = "//label[contains(text(),'";
    public static String checkbox_xpath1 = "')]";
    
    public static String roleGridId = "@id='dataTablesRole3'";
    public static String searchRoleGridId = "@id='dataTablesRoleView'";
    public static String roles_xpath = "//li[@class='select2-results__option']"; 
    public static String clientDropdown = "//ul[@id='select2-roleclientFilter-results']/li";
    

}
