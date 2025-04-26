package testng.parallelExecution;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SampleReport {
	 ExtentReports extent = new ExtentReports();
     ExtentSparkReporter spark = new ExtentSparkReporter("Spark1.html");
     @Test
     public void merge()
     {
    	 ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
    	 ExtentSparkReporter spark1 = new ExtentSparkReporter("Spark1.html");
    	 ExtentReports merged = new ExtentReports();
    	 merged.attachReporter(spark, spark1);
    	// ExtentTest mergedTest = merged.createTest("Merged Test");
    	// mergedTest.log(Status.INFO, "This report is a combination of two individual reports.");

         // Flush the merged report
         merged.flush();
     }
     
 
public void reporting()
{
    	 extent.attachReporter(spark);
    extent.createTest("ScreenCapture")
            .addScreenCaptureFromPath("extent.png")
            .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());

    extent.createTest("LogLevels")
            .info("info")
            .pass("pass")
            .warning("warn")
            .skip("skip")
            .fail("fail");

//    extent.createTest("CodeBlock").generateLog(
//            Status.PASS,
//            MarkupHelper.createCodeBlock(CODE1, CODE2));

    extent.createTest("ParentWithChild")
            .createNode("Child")
            .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");

    extent.createTest("Tags")
            .assignCategory("MyTag")
            .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");

    extent.createTest("Authors")
            .assignAuthor("TheAuthor")
            .pass("This test 'Authors' was assigned by a special kind of author tag.");

    extent.createTest("Devices")
            .assignDevice("TheDevice")
            .pass("This test 'Devices' was assigned by a special kind of devices tag.");

    extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
            .fail(new RuntimeException("A runtime exception occurred!"));

    extent.flush();
}
}
