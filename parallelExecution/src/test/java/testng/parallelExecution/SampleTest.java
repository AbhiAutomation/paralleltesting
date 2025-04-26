package testng.parallelExecution;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SampleTest {
	 private static ExtentReports ext;
	
	    @BeforeSuite
	    public void setUp() {
	        // No need to config ExtentHtmlReporter for each test method
	  
	        ext = new ExtentReports();

	    }

	    @Test
	    public void sampleTestCase1() {
	        // Your test logic here

	        // Log steps
	        ExtentTest demo = ext.createTest("Demo Test 1");
	        demo.log(Status.INFO, "Step 1: Do an action");
	        demo.log(Status.INFO, "Step 2: Verify the result");
	        // Add more logs as needed
	    }

	    @Test
	    public void sampleTestCase2() {
	        // Your logic here

	        // Log steps
	        ExtentTest demo = ext.createTest("Demo Test 2");
	        demo.log(Status.INFO, "Step 1: Do another action");
	        demo.log(Status.INFO, "Step 2: Verify another result");
	        // Add more logs as needed
	    }

	    @AfterSuite
	    public void tearDown() {
	        // Flush the Extent Report
	        ext.flush();
	    }
}
