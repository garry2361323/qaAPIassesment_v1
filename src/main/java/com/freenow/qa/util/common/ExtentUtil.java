package com.freenow.qa.util.common;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.HashMap;
import java.util.Map;

/***
 * This class provides methods to initialize instance of ExtentHtmlReporter, ExtentReport and some generic methods,
 *
 * @author Gaurav Sharma
 */

public class ExtentUtil {

    private static ExtentUtil extentUtilInstance = null;
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

    public ExtentUtil() {
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.config", "src/test/resources/extent-config.xml");

        System.setProperty("extent.reporter.html.out", "output/" + System.getProperty("env") + "/Test_Report.html");
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/output/Test_Report.html");
    }

    public static ExtentUtil getInstance() {

        if (extentUtilInstance == null)
            extentUtilInstance = new ExtentUtil();

        return extentUtilInstance;
    }

    public ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
        }
        return extentReports;
    }

    /**
     * This method returns configured ExtentHtmlReporter instance
     */

    public ExtentHtmlReporter getExtentHtmlReporter() {
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentHtmlReporter.setAnalysisStrategy(AnalysisStrategy.TEST);
        extentHtmlReporter.config().setDocumentTitle("Free Now API Automation Test");
        extentHtmlReporter.config().setReportName("Free Now API Automation Test Results");
        extentHtmlReporter.loadConfig(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml");
        return extentHtmlReporter;
    }

    public ExtentTest getTest() {
        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /**
     * This method flushes extent report
     */
    public void endTest() {
        extentReports.flush();
    }

    public ExtentTest startTest(String testName, String testDescription) {
        ExtentTest test = extentReports.createTest(testName, testDescription);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }


    /**
     * This method copies artifacts necessary to make great extent report !
     * Though it's not working and I am running out of time, will comment it meanwhile.
     */
    /*
    public void createAssetsDirectory() {

        File source = new File("src/test/resources/assets");
        File destination = null;

        destination = new File(System.getProperty("user.dir") + "/output/");

        try {
            FileUtils.copyDirectory(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
