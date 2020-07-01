package com.extentReport;

import com.aventstack.extentreports.Status;
import org.testng.*;
import static org.testng.Reporter.log;

public class TestListener implements ITestListener, ISuiteListener {
    private String status;
    private String category;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.getTest().pass(iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().pass(iTestResult.getMethod().getDescription());
        ExtentTestManager.getTest().log(Status.PASS, iTestResult.getMethod().getMethodName());
        status = iTestResult.getTestContext().getName() + " -- Test case passed";

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log(iTestResult.getTestContext().getName() + " -- Test method failed");
        ExtentTestManager.getTest().fail(iTestResult.getThrowable().getLocalizedMessage());
        ExtentTestManager.getTest().log(Status.FAIL, iTestResult.getTestContext().getName());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log(iTestResult.getTestContext().getName() + " -- Test method skipped");
        status = "Skipped";
        ExtentTestManager.getTest().skip(iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentTestManager.startTest(iTestContext.getName(), "");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentTestManager.endTest();
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("suite Name: " + iSuite.getName());
       // ExtentTestManager.startSuite(iSuite.getName(), "");
       // ExtentTestManager.startSuite(iSuite.getXmlSuite().getName(), "");


    }

    @Override
    public void onFinish(ISuite iSuite) {
        ExtentTestManager.endTest();
    }
}
