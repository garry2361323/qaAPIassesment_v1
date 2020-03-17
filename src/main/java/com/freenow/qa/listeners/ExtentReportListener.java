package com.freenow.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.freenow.qa.util.common.ExtentUtil;
import com.freenow.qa.util.common.LogUtils;
import com.freenow.qa.util.common.RestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.HashMap;

/***
 * TestNG listener class with below functions:
 *Initializes Extent Reports
 * Logging on different test states
 * Setting request timeout
 * Closing connection after successful test execution
 *
 * @author Gaurav Sharma
 */

public class ExtentReportListener implements ITestListener {

    private static final LogUtils LOGGER = LogUtils.getInstance(ExtentReportListener.class);
    private static RestUtil restUtilsInstance = RestUtil.getInstance();
    private static ExtentUtil extentUtilInstance = ExtentUtil.getInstance();
    private static ExtentReports extentReports = initializeExtentReport();


    public static ExtentReports initializeExtentReport() {
        extentReports = extentUtilInstance.getExtentReports();
        extentReports.attachReporter(extentUtilInstance.getExtentHtmlReporter());
        return extentReports;
    }

    @Override
    public void onTestStart(ITestResult result) {

        //Setting timeout for request

        restUtilsInstance.setTimeOut();
        LOGGER.start("Starting Test : " + result.getName());

        //Extent Reporting
        extentUtilInstance.startTest(result.getName(), result.getMethod().getDescription());

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        LOGGER.info("Finished successfully executing Test: " + result.getName() + "\n\n");

        //Close Connection after each successful request
        restUtilsInstance.closeConnection();

        //Nullifying RestUtil Instance
        restUtilsInstance.resetRestAssured();

        extentUtilInstance.getTest().log(Status.PASS, "");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        LOGGER.info("Test failed: " + result.getName() + "\n\n");
        extentUtilInstance.getTest().log(Status.FAIL, "");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        LOGGER.info("Test skipped: " + result.getName() + "\n\n");
        extentUtilInstance.getTest().log(Status.SKIP, "");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extentUtilInstance.endTest();
//      extentUtilInstance.createAssetsDirectory();
    }
}
