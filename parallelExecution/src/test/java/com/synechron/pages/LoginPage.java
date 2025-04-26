package com.synechron.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.synechron.base.BasePage;


/**
 * Contains your Page Object classes, as discussed in the POM.
 *  Each class represents a web page and encapsulates its elements and interactions.
 */
public class LoginPage  extends BasePage{
	
	
    public LoginPage(WebDriver driver) {
        super(driver); // Call the constructor of the BasePage to initialize driver and PageFactory
    }
    
	 	@FindBy(id = "username")
	    private WebElement usernameField;

	    @FindBy(name = "password")
	    private WebElement passwordField;

	    @FindBy(xpath = "//input[@type='submit']")
	    private WebElement loginButton;
	    
	  
	    @FindBy( xpath="//ul[@id='sessionLocation']/li[text()='Inpatient Ward']")
	    private	WebElement elementByText ;
	    
	    @FindBy( xpath="//ul[@id='sessionLocation']/li")
	    private	List<WebElement> li ;
	
        public void enterUsername(String username) {
	        sendKeys(usernameField, username); // Use the inherited sendKeys method
	    }
	    
	    public void enterPassword(String password) {
	        sendKeys(passwordField, password); // Use the inherited sendKeys method
	    }
	    
	    public void waitForelementByTextClickable()
	    {
	    	waitForClickability(elementByText);
	    }
	    
	    public void clickLocation(String text)
	    {
	    	click1(li, text);
	    	System.out.println("Clicked on Inpatient Ward using Xpath and Visible Text");
	    	
	    }
	    
	    public void clickLoginButton() {
	        click(loginButton); // Use the inherited click method
	    }

	    
}
