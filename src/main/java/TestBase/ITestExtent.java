package TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ITestExtent extends TestBase implements ITestListener 
{
		
		 protected static ExtentReports reports;
		 protected static ExtentTest test;
		 
		 public void onStart(ITestContext context)
		 {
			  System.out.println("on start");
			  reports = new ExtentReports(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + "reports.html");
			  reports = new ExtentReports(System.getProperty("user.dir")+ "reports.html");
		}
		 
		 public void onTestStart(ITestResult result) 
		 {
		  System.out.println("on test start");
		  test = reports.startTest(result.getMethod().getMethodName());
		  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
		 }
		 
		 
		 public void onTestSuccess(ITestResult result) 
		 {
		  System.out.println("on test success");
		  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
		 }
		 
		 public void onTestFailure(ITestResult result) 
		 {
			 
		  System.out.println("on test failure");
		  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
		  
		  File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  
		  try 
		  	{
			  
			   FileUtils.copyFile(src, new File("C:\\ReservationImages\\" + result.getMethod().getMethodName() + ".png"));
			   String file = test.addScreenCapture("C:\\ReservationImages\\" + result.getMethod().getMethodName() + ".png");
			   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
			   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
			   
			 } catch (IOException e) 
			  {
			   e.printStackTrace();
			  }
		 }
		 
		 
		 public void onTestSkipped(ITestResult result) 
		 {
		  System.out.println("on test skipped");
		  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
		 }
		 
		 public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
		 {
		  System.out.println("on test sucess within percentage");
		 }
		
		 public void onFinish(ITestContext context) 
		 {
		  System.out.println("on finish");
		  driver.close();
		  reports.endTest(test);
		  reports.flush();
		 }

	}


