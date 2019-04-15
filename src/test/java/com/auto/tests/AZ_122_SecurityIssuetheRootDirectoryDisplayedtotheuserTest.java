package com.auto.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.auto.base.TestCore;
import com.auto.pageobject.SignUpPageObject;
import com.auto.pages.SignUpPage;


public class AZ_122_SecurityIssuetheRootDirectoryDisplayedtotheuserTest extends TestCore {
	SignUpPage signup;
	
	@Test
	public void az_122_SecurityIssuetheRootDirectoryDisplayedtotheuser(){
		signup = new SignUpPage(driver);
		
        
        assertEquals(driver.getTitle(), SignUpPageObject.title);
        signup.enterTestedUrl(SignUpPageObject.rootDirectoryUrl);
        assertTrue(signup.isOopsMessageAppears("Oops!"), "Fail: [Oops!] message doesn't present in error page.");
        assertTrue(signup.isErrorUrlLaunched(SignUpPageObject.errorUrl), "Fail: [Error Page] doesn't launnch.");
		
	}

}
