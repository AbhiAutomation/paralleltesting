package com.synechron.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.synechron.base.BasePage;

/**
 * Contains your Page Object classes, as discussed in the POM.
 *  Each class represents a web page and encapsulates its elements and interactions.
 */
public class HomePage  extends BasePage {
	
	public HomePage(WebDriver driver )
	{
		super(driver);
	}
	
	@FindBy(xpath="//h4[contains(text(),' Connecté ')]")
	WebElement homePageTitlMessage;
	
	@FindBy(linkText="Déconnexion")
	WebElement logoutLinkLocator;
	
	 /**
	    * Verifies the title of the Home Page
	    */
	    public void verifyHomePageTitle(String expectedTitle){
	        String actualTitle = driver.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle, "Home page title is incorrect. Expected: " + expectedTitle + ", Actual: " + actualTitle);
	        System.out.println("Home page title is correct: " + actualTitle);
	    }
	    


}
