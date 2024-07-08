package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>(); //Thread Safe
	@Override
	public void onTestStart(ITestResult result) {
		
		//ITestListener.super.onTestStart(result);
		 test = extent.createTest(result.getMethod().getMethodName());
		 threadLocal.set(test); //unique thread id - >Object test - it craetes map inside this
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//ITestListener.super.onTestSuccess(result);
		threadLocal.get().log(Status.PASS, "Test Case passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//ITestListener.super.onTestFailure(result);
		//test.log(Status.FAIL, "Test Case failed");
		threadLocal.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch(Exception e){//catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//Screenshot and attach it to report
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		//ITestListener.super.onFinish(context);
	}

	
}
