package com.synechron.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
* 
* A class responsible for creating and managing WebDriver instances 
* (e.g., handling different browsers, remote execution).
*/

public class WebDriverFactory {
	
	 private WebDriver driver;
	 private Properties config;

	    /**
	     * Constructor for WebDriverFactory.
	     *
	     * @param config The Properties object containing configuration settings.
	     */
	    
	 public WebDriverFactory(Properties config) {
	        this.config = config;
	    }
	 
	  /**
	     * Creates and returns a WebDriver instance based on the configuration.
	     *
	     * @return The WebDriver instance.
	     */
	    public WebDriver createDriver() {
	        String browser = config.getProperty("browser", "chrome").toLowerCase(); // Default to chrome
	        String remote = config.getProperty("remote", "false").toLowerCase();
	        //System.out.println("Browser: " + browser + ", Remote: " + remote); //For Debug
	        if ("true".equals(remote)) {
	            driver = createRemoteDriver(browser);
	        } else {
	            driver = createLocalDriver(browser);
	        }
	        return driver;
	    }

	    /**
	     * Creates a WebDriver instance for local execution.
	     *
	     * @param browser The browser to use (e.g., "chrome", "firefox", "edge").
	     * @return The WebDriver instance for local execution.
	     */
	    private WebDriver createLocalDriver(String browser) {
	        switch (browser) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                WebDriverManager.edgedriver().setup();
	                driver = new EdgeDriver();
	                break;
	            default:
	                System.err.println("Unsupported browser: " + browser + ". Defaulting to Chrome.");
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	        }
	        return driver;
	    }

	    /**
	     * Creates a WebDriver instance for remote execution (using Selenium Grid).
	     *
	     * @param browser The browser to use (e.g., "chrome", "firefox", "edge").
	     * @return The WebDriver instance for remote execution.
	     */
	    private WebDriver createRemoteDriver(String browser) {
	        String gridUrl = config.getProperty("grid_url"); // e.g., "http://localhost:4444/wd/hub"
	        if (gridUrl == null || gridUrl.isEmpty()) {
	            throw new IllegalArgumentException("grid_url property must be specified in config.properties for remote execution.");
	        }

	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        switch (browser) {
	            case "chrome":
	                capabilities.setBrowserName("chrome");
	                break;
	            case "firefox":
	                capabilities.setBrowserName("firefox");
	                break;
	            case "edge":
	                capabilities.setBrowserName("MicrosoftEdge"); // Corrected browser name for Edge
	                break;
	            default:
	                System.err.println("Unsupported browser for remote execution: " + browser + ". Defaulting to Chrome.");
	                capabilities.setBrowserName("chrome");
	        }

	        try {
	            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
	        } catch (MalformedURLException e) {
	            System.err.println("Invalid grid URL: " + gridUrl);
	            throw new RuntimeException("Invalid grid URL", e); // Wrap for better handling
	        }
	        return driver;
	    }

	     /**
	     * Gets the WebDriver instance.  This should be called *after*
	     * calling createDriver().
	     * @return The WebDriver instance.
	     * @throws IllegalStateException if the driver has not been initialized.
	     */
	    public WebDriver getDriver() {
	        if (driver == null) {
	            throw new IllegalStateException("WebDriver has not been initialized.  Call createDriver() first.");
	        }
	        return driver;
	    }

	    /**
	     * Closes the WebDriver instance.
	     */
	    public void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	            driver = null; //  Set to null to prevent further use.
	        }
	    }

}
