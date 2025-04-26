package testng.parallelExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class DIModule extends AbstractModule {

    @Override
    protected void configure() {
        // No additional bindings needed for now
    }

    @Provides
    public ThreadLocal<WebDriver> provideWebDriver() {
        ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>() {
            @Override
            protected WebDriver initialValue() {
                // Initialize WebDriver for each thread
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        };
        return driverThreadLocal;
    }
}
