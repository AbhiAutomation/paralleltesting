package testng.parallelExecution;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;

public class TestSuite1 {
	ExtentReports extentReports = null;
	@BeforeSuite
	public void setup1()
	{
		extentReports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("TS1_Spark.html");
		JsonFormatter jsonFormatter = new JsonFormatter("TS1_Spark.json");
		extentReports.attachReporter(jsonFormatter, spark);
	}
	
	@Test
	public void test1()
	{
		extentReports.createTest("TS1_TC1").pass("Expected: True | Actual: True");
	}
	
	@Test
	public void test2()
	{
		extentReports.createTest("TS1_TC1").fail("Expected: True | Actual: False");
	}
	
	@AfterSuite
	public void tearDown()
	{
		extentReports.flush();
	}

}
