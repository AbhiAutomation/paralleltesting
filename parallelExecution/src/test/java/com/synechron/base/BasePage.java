package com.synechron.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * An abstract base class for all Page Object classes,
 *  potentially containing common WebDriver instance and utility methods accessible to all pages.
 */
public  abstract class BasePage {
	
	protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long DEFAULT_TIMEOUT = 10; // seconds
    
    
    // Constructor to be called by subclasses
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }

    
    // ------------------------------------------------------------------------
    //  Reusable Utility Methods for Page Objects
    // ------------------------------------------------------------------------
    // Common utility methods accessible to all Page Objects
    
    

    /**
     * Waits for the visibility of a WebElement.
     *
     * @param element The WebElement to wait for.
     * @return The WebElement if visible within the default timeout, otherwise null.
     */
    protected boolean waitForVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("WebElement not visible within the timeout: " + element);
            return false;
        }
    } 
    
    
    /**
     * Waits for an element to be clickable.
     *
     * @param element The WebElement to wait for.
     * @return The WebElement if clickable within the default timeout, otherwise null.
     */
    protected boolean waitForClickability(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("WebElement not clickable within the timeout: " + element);
            return false;
        }
    }
    
    /**
     * Clicks on a WebElement after waiting for it to be clickable.
     *
     * @param element The WebElement to click.
     */
    protected void click(WebElement element) {
        if (waitForClickability(element)) {
            element.click();
        }
    }
    
    protected void click1(List<WebElement> list, String text) {
       for (WebElement element : list) {
    	  String elementText =  element.getText();
    	  if (elementText.equals(text))
    		  element.click();
       }
    }
    
       
    /**
     * Sends keys to a WebElement after waiting for it to be visible.
     *
     * @param element The WebElement to send keys to.
     * @param text    The text to enter.
     */
    protected void sendKeys(WebElement element, String text) {
        if (waitForVisibility(element)) {
            element.sendKeys(text);
        }
    }

    /**
     * Gets the text of a WebElement after waiting for it to be visible.
     *
     * @param element The WebElement to get the text from.
     * @return The text of the WebElement, or an empty string if not visible.
     */
    protected String getText(WebElement element) {
        if (waitForVisibility(element)) {
            return element.getText();
        }
        return "";
    }
    
    /**
     * Gets the title of the current page.
     *
     * @return The title of the page.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    
    /**
     * Navigates to a specific URL.
     *
     * @param url The URL to navigate to.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    


   protected boolean isElementDisplayed(WebElement element) {
       try {
           return element.isDisplayed();
       } catch (org.openqa.selenium.NoSuchElementException e) {
           return false;
       }
   }

   /**
    * Clears the text of an input element.
    * @param element The WebElement to clear.
    */
   protected void clearElementText(WebElement element) {
	    if (waitForVisibilityOf(element)) {
	        try {
	            element.clear();
	        } catch (ElementNotInteractableException e) {
	            System.err.println("Element is not interactable and cannot be cleared: " + element);
	            // Consider throwing a custom exception or logging the error.
	        } catch (StaleElementReferenceException e) {
	            System.err.println("Stale element reference while clearing text: " + element);
	            //  Handle stale element, possibly by re-finding.  For now, just log.
	        }
	    } else {
	        System.err.println("Element was not visible and could not be cleared: " + element);
	        //  Consider throwing an exception or logging.
	    }
	}
   
   
   /**
    * Waits for the visibility of a WebElement.
    *
    * @param element The WebElement to wait for.
    * @return true if the element is visible within the timeout, false otherwise.
    */
   protected boolean waitForVisibilityOf(WebElement element) { // Corrected method name
       try {
           wait.until(ExpectedConditions.visibilityOf(element));
           return true;
       } catch (org.openqa.selenium.TimeoutException e) {
           System.err.println("Element not visible: " + element);
           return false;
       }
   }

   // Add more utility methods as needed for your specific project.
   // Examples:
   // -  switchToFrame()
   // -  switchToDefaultContent()
   // -  executeJavaScript()
   // -  get current url
    
    
   
    
    

}
