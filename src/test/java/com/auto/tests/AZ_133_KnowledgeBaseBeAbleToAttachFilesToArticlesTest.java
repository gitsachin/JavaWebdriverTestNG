package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.auto.base.BasePage;
import com.auto.base.TestCore;
import com.auto.configproperties.ConfigProperties;
import com.auto.pageobject.AssetsCategriesPageObject;
import com.auto.pageobject.DashboardPageObject;
import com.auto.pageobject.InventoryPageObject;
import com.auto.pageobject.KnowledgeBasePageObject;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pageobject.SystemPageObject;
import com.auto.pages.AssetsPage;
import com.auto.pages.DashboardPage;
import com.auto.pages.KnowledgeBasePage;
import com.auto.pages.SignUpPage;

public class AZ_133_KnowledgeBaseBeAbleToAttachFilesToArticlesTest extends TestCore {
	
	SignUpPage signup;
	ConfigProperties configprop;
	BasePage basepage;
	DashboardPage dashboard;
	AssetsPage assetPage;
	KnowledgeBasePage knowledgePage;
	
	@Test(priority=0)
	public void az_133_KnowledgeBaseBeAbleToAttachFilesToArticles() throws Exception{
		
		signup = new SignUpPage(driver);
		configprop = new ConfigProperties();
		basepage = new BasePage(driver);
        dashboard = new DashboardPage(driver);
        assetPage = new AssetsPage(driver);
        knowledgePage = new KnowledgeBasePage(driver);
        SoftAssert softAssertion= new SoftAssert();
        
		assertEquals(driver.getTitle(), SignUpPageObject.title);
		signup.enterInput(0, ConfigProperties.email);
		signup.enterInput(1, ConfigProperties.pass);
		signup.clickSubmit();
		assertEquals(basepage.getText(By.cssSelector(DashboardPageObject.user_css)), "SA");
		
		dashboard.clickMenuTab("Knowledge Base");
		dashboard.clickButton("NEW ARTICLE");
		assertEquals(basepage.getText(By.cssSelector(InventoryPageObject.popTitle_css)), "Create New Article");
		
		signup.enterInput(0,KnowledgeBasePageObject.articalName);
		signup.clickWithScroll("Create");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.sucessMessage), "Fail: Create Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.sucessMessage);
		knowledgePage.clickOnViewEditDeleteLocationIcon(KnowledgeBasePageObject.knowledgeTable_id,"Artical",KnowledgeBasePageObject.articalName,2,3,2);
		
		knowledgePage.sendFile(ConfigProperties.assetBatchFile,2);
		signup.click("Save");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: Add Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.addedSucessMessage);
		
		knowledgePage.clickOnViewEditDeleteLocationIcon(KnowledgeBasePageObject.knowledgeTable_id,"Artical",KnowledgeBasePageObject.articalName,2,3,2);
		assertTrue(knowledgePage.isUploadedFilePresent(1),"button not present in Article");
		assertTrue(knowledgePage.fileExist(".xlsx"));
		
		knowledgePage.sendFiles(KnowledgeBasePage.fileName,2);
		signup.click("Save");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: Add Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.addedSucessMessage);
		
		knowledgePage.clickOnViewEditDeleteLocationIcon(KnowledgeBasePageObject.knowledgeTable_id,"Artical",KnowledgeBasePageObject.articalName,2,3,2);
		knowledgePage.verifyUploadedFile("2");
		signup.click("Save");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.addedSucessMessage), "Fail: Add Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.addedSucessMessage);
		
		knowledgePage.clickOnViewEditDeleteLocationIcon(KnowledgeBasePageObject.knowledgeTable_id,"Artical",KnowledgeBasePageObject.articalName,2,3,3);
		signup.click("Yes");
		softAssertion.assertTrue(assetPage.isConfirmationMessageAppear(InventoryPageObject.deleteSucessMessage), "Fail: Delete Success aleart untracked.");
		//assertEquals(basepage.getText(By.cssSelector(SignUpPageObject.successMessage_css)),InventoryPageObject.deleteSucessMessage);
		
	}
}
