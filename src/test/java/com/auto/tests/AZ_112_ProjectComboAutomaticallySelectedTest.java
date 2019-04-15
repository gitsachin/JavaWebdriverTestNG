package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.ProjectPageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.DashboardPage;
import com.auto.pages.ProjectPage;
import com.auto.pages.SignUpPage;


public class AZ_112_ProjectComboAutomaticallySelectedTest extends TestCore {
	SignUpPage signup;
	BasePage basepage;
	DashboardPage dashboard ;
	ProjectPage project;
		
	@Test
	public void az_112_ProjectComboAutomaticallySelected(){
		
		signup = new SignUpPage(driver);
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        project = new ProjectPage(driver);
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), DashboardPageObject.clientAdminName);
		dashboard.clickMenuTab("Project");
		dashboard.clickMenuTab("All Projects");
		project.enterExpectedValueInTextField(ProjectPageObject.textFieldSearch_xpath, 1, ConfigProperties.projects, "Search String");
		project.clickOnExpectedButton(ProjectPageObject.buttonSearch_xpath, 1, "Go");
		project.clickOnViewEditDelIcons(ProjectPageObject.searchTableId, ConfigProperties.projects, 1, "View Icon", 5, "Project", 3);
		project.clickOnExpectedButton(ProjectPageObject.buttonNewIssue_xpath, 1, "New Issue");
		assertTrue(project.isProjecSelected(ConfigProperties.projects), "Fail: Default project doesn't present in Project field.");
	}

}
