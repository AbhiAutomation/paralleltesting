package com.synechron.tests;

import org.testng.annotations.Test;

import com.synechron.base.BaseTest;
import com.synechron.pages.HomePage;
import com.synechron.pages.LoginPage;

public class LoginTest extends BaseTest {

	  @Test
	    public void testSuccessfulLogin() throws InterruptedException
	    {
		  // 1. Navigate to the login page
		  
		LoginPage login=  new LoginPage(driver); // Uset the drvier from the BaseTestClass
		HomePage homepage = new HomePage(driver);
		login.navigateTo(config.getProperty("base_url"));
		  
		// 2. Perform the login action
		login.enterUsername(config.getProperty("valid_username"));
		login.enterPassword(config.getProperty("valid_password"));
		login.waitForelementByTextClickable();
		login.clickLocation("Outpatient Clinic"); // Hard coded value provide for now 
		login.getPageTitle();
		login.clickLoginButton();
		
		 // 3. Verify successful login (e.g., check for a welcome message)
		homepage.verifyHomePageTitle("Maison");
		
		
		
		  
	  
		  
		  
		  
	    }
	
	
	

}
