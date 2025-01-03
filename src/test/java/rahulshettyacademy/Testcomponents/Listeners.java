package rahulshettyacademy.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();// It makes ThreadSafe and only one thread is executed once.
	 @Override
	    public void onTestStart(ITestResult result) 
	 {
		test= extent.createTest(result.getMethod().getMethodName());
	       extentTest.set(test); // It will assign a unique Thread id and it will solve the concurrency problem.
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS,"Test is Passes");
	    }

	    @Override
	    public void onTestFailure(ITestResult result)
	    {
	    	
	    	extentTest.get().fail(result.getThrowable()); // It will print why the test is failed and here we will get the unique thread id.
	    	try {
				driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
	    	String filePath = null;
	    	try {
	    	filePath=getScreenShot(result.getMethod().getMethodName(),driver);
	    			
	    		
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        // Example: Capture a screenshot on failure
	    	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    	
	    }

	   /* @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	   / @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        System.out.println("Test Failed but within success percentage: " + result.getName());
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        System.out.println("Test Suite Started: " + context.getName());
	    }*/

	   
		

		@Override
	    public void onFinish(ITestContext context) {
	        extent.flush(); // It will show the authentic report
	    }
	}


