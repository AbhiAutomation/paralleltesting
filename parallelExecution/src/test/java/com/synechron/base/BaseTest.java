package com.synechron.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
* 
* A base class for your test classes, handling WebDriver setup (@BeforeSuite, @BeforeClass, @BeforeMethod), teardown (@AfterSuite, @AfterClass, @AfterMethod),
* and potentially common test configurations.
*/
public class BaseTest {
	
		protected WebDriver driver;
	    protected Properties config;
	    protected WebDriverFactory driverFactory;  // Declare WebDriverFactory


	    private static final String CONFIG_FILE_PATH = "src/test/java/com/synechron/resources/config.properties";
	   
	    /**
	     * Loads the configuration properties from the config.properties file.
	     */
	    public BaseTest() {
	        config = new Properties();
	        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
	            config.load(fis);
	        } catch (IOException e) {
	            System.err.println("Failed to load config.properties: " + e.getMessage());
	            // Consider throwing a RuntimeException or handling it more gracefully.
	            //  It is often better to throw an exception here, as the tests cannot
	            //  run correctly without the configuration.
	            throw new RuntimeException("Failed to load configuration file", e);
	        }
	    }
	    
	  //  @BeforeSuite
	//    @Parameters("browser") //Allows specifying the browser via TestNG suite XML
//	    public void beforeSuite(@Optional("chrome") String browser)
//	    { // Default to chrome if no parameter
//	      // Initialize WebDriver based on the browser specified in the TestNG suite file
//	    	
//	    	if("chrome".equalsIgnoreCase(browser))
//	    	{
//	    		WebDriverManager.chromedriver().setup();
//	    		driver=new ChromeDriver();
//	    	}else if ("firefox".equalsIgnoreCase(browser)) {
//	            WebDriverManager.firefoxdriver().setup();
//	            driver = new FirefoxDriver();
//	        } else if ("edge".equalsIgnoreCase(browser)) {
//	            WebDriverManager.edgedriver().setup();
//	            driver = new EdgeDriver();
//	        }
//	        else {
//	            System.err.println("Unsupported browser: " + browser + ".  Defaulting to Chrome.");
//	            WebDriverManager.chromedriver().setup();
//	            driver = new ChromeDriver(); // Default to Chrome
//	            
//	            driver.manage().window().maximize();
//	           
//	          
//	        }
//	  }
//	    
//	    
	    

	    /**
	     * Performs setup operations before the entire test suite.  This includes
	     * setting up the browser driver using WebDriverFactory.
	     */
	    @BeforeSuite
	    @Parameters("browser")
	    public void beforeSuite(@Optional("chrome") String browser) {
	        // Initialize WebDriverFactory with the configuration
	        driverFactory = new WebDriverFactory(config);
	        driver = driverFactory.createDriver();  // Use WebDriverFactory to create the driver

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit_wait_timeout", "10"))));
	    }
	    
	    
	    /**
	     * Performs setup operations before each test class.  This might include
	     * navigating to the base URL.
	     */
	    @BeforeClass
	    public void beforeClass() {
	        driver.get(config.getProperty("base_url"));
	    }

	        /**
	         * Performs setup operations before each test method.  This can be used
	         * for any setup that needs to be done before every single test.
	         */
	        @BeforeMethod
	        public void beforeMethod() {
	            //  You might add code here to:
	            //  -  Clear cookies
	            //  -  Navigate to a specific page
	            //  -  Initialize test data
	        }
	        
	        /**
	         * Performs teardown operations after each test method.
	         */
	        @AfterMethod
	        public void afterMethod() {
	            //  You might add code here to:
	            //  -  Take a screenshot if the test failed.
	            //  -  Log test status.
	        }

	        /**
	         * Performs teardown operations after each test class.
	         */
	        @AfterClass
	        public void afterClass() {
	            //  You might add code here to:
	            //  -  Delete cookies.
	        }

	        /**
	         * Performs teardown operations after the entire test suite.  This
	         * includes closing the WebDriver instance using WebDriverFactory.
	         */
	        @AfterSuite
	        public void afterSuite() {
	            if (driverFactory != null) {
	                driverFactory.quitDriver();  // Use WebDriverFactory to quit the driver
	            }
	        }

	        //  Add more common utility methods as needed, for example:
	        //  -  Method to get a property from the configuration.
	        //  -  Method to take a screenshot.
	    
}
