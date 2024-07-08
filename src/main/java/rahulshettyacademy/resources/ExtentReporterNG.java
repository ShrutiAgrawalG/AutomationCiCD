package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		//ExtentReports , ExtentSparkReporter - Class
				//Specify the path to save the reports
				 String path = System.getProperty("user.dir") + "//reports//index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("TestReults");
				
				//main class responsible to crweate test redults
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Shruti");
				return extent;
	}
	
}
