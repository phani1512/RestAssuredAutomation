package com.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class Listeners extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;


	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/Report.html");
		// Create an object of Extent Reports
		htmlReporter.config().setDocumentTitle("Automation Report");
		// Name of the report
		htmlReporter.config().setReportName("Rest API Testing");
		// Dark Theme
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("Project Name", "Transportal Rest API");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Phaneendra");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED is " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case FAILED is " + result.getName());
		test.log(Status.FAIL, "Test Case FAILED is " + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is " + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}