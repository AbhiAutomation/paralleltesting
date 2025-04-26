package testng.parallelExecution;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;

import io.github.bonigarcia.wdm.WebDriverManager;

@Guice(modules = DIModule.class)
public class GoogleSerachTest {
	
	 private final ThreadLocal<WebDriver> driverThreadLocal ;

	    @jakarta.inject.Inject
	    public GoogleSerachTest(ThreadLocal<WebDriver> driverThreadLocal) {
	        this.driverThreadLocal = driverThreadLocal;
	    }

	    @BeforeMethod
	    public void setup(Method method) {
	        WebDriver driver = driverThreadLocal.get();
	        driver.manage().window().maximize(); // Optional: Maximize the browser window

	        // Print thread ID, WebDriver hashcode, and method name for debugging
	        System.out.println("HashCode: " + driver.hashCode() + " | Method: " + method.getName());
	        System.out.println("Thread ID: " + Thread.currentThread().getId());
	    }

//    @BeforeMethod
//    public void setup(Method method) {
//        // Initialize the browser using ThreadLocal
//        WebDriver driver = WebDriverManager1.getDriver();
//        driver.manage().window().maximize(); // Optional: Maximize the browser window
//
//        // Print thread ID, WebDriver hashcode, and method name for debugging
//        System.out.println("HashCode: " + driver.hashCode() + " | Method: " + method.getName());
//        System.out.println("Thread ID: " + Thread.currentThread().getId());
//    }

    @Test(priority = 1)
    public void search() {
    	WebDriver driver = driverThreadLocal.get();
        System.out.println("search: " + Thread.currentThread().getId());
        driver.get("https://www.google.com");
        WebElement txtSearch = driver.findElement(By.xpath("//textarea[@name='q']"));
        txtSearch.sendKeys("delhi");
        txtSearch.sendKeys(Keys.ENTER);
    }

    @Test(priority = 2)
    public void openMaven() {
    	WebDriver driver = driverThreadLocal.get();
        System.out.println("openMaven: " + Thread.currentThread().getId());
        driver.get("https://mvnrepository.com/artifact/com.google.inject/guice/7.0.0");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
       WebDriverManager1.quitDriver();
        System.out.println("teardown");
    }
}


class WebDriverManager1 {
    // ThreadLocal to store WebDriver instances
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            // Initialize the WebDriver instance
            WebDriverManager.chromedriver().setup();
            driverThreadLocal.set(new ChromeDriver());
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        System.out.println("Hashcode in terdown:" + driver.hashCode());
        
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove(); // Remove the WebDriver instance from ThreadLocal
        }
    }
}
