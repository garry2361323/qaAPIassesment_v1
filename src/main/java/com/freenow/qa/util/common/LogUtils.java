package com.freenow.qa.util.common;

import com.freenow.qa.listeners.ExtentReportListener;
import com.freenow.qa.util.file.PropertiesUtils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This utility class provides instance of LogUtils for Logging capabilities
 *
 * @author Gaurav Sharma
 */

public class LogUtils {
    private static LogUtils logUtilsInstance = null;
    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static ExtentUtil extentUtilInstance = ExtentUtil.getInstance();
    private Logger logger;
    private String logFilePath;
    private String fileName;

    private LogUtils(Class<?> ClassName) {

        logSetup();
        logger = Logger.getLogger(ClassName);

        new File(System.getProperty("user.dir") + "/output/" + (null != System.getProperty("env") ? System.getProperty("env")
                : propertiesUtilsInstance.readProperty(propertiesUtilsInstance.environment)) + "/logs/").mkdirs();

        logFilePath = System.getProperty("user.dir") + "/output/" + (null != System.getProperty("env") ? System.getProperty("env")
                : propertiesUtilsInstance.readProperty(propertiesUtilsInstance.environment)) + "/logs/";

        fileName = "TestLog_" + System.getProperty("TEST_RUN_TIMESTAMP") + ".log";

    }


    public static LogUtils getInstance(Class<?> ClassName) {

        if (logUtilsInstance == null)
            logUtilsInstance = new LogUtils(ClassName);

        return logUtilsInstance;
    }


    public void info(String message) {

        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        Logger.getRootLogger().setLevel(Level.INFO);

        logger.info("INFO" + " " + currentTime + ": " + message);
        writeToLogFile("INFO" + " " + currentTime + ": " + message);
        extentUtilInstance.getTest().info(message);
    }


    public void start(String message) {

        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        Logger.getRootLogger().setLevel(Level.INFO);

        logger.info("START" + " " + currentTime + ": " + message);
        writeToLogFile("START" + " " + currentTime + ": " + message);

    }


    public void code(String message) {

        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        Logger.getRootLogger().setLevel(Level.INFO);

        logger.info("CODE" + " " + currentTime + ": " + message);
        writeToLogFile("CODE" + " " + currentTime + ": " + message);
        writeToLogFile("CODE-END");

    }


    public void fail(String message) {

        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        Logger.getRootLogger().setLevel(Level.ERROR);

        logger.error("FAIL" + " " + currentTime + ": " + message);
        writeToLogFile("FAIL" + " " + currentTime + ": " + message);
        writeToLogFile("CODE-END");
        extentUtilInstance.getTest().info(message);
        throw new TestFailedExceptionUtil(message + ". Test step failed..");
    }


    public void error(String message) {

        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        Logger.getRootLogger().setLevel(Level.ERROR);

        logger.error("ERROR" + " " + currentTime + ": " + message);
        extentUtilInstance.getTest().info(message);
        writeToLogFile("ERROR" + " " + currentTime + ": " + message);

    }


    public void writeToLogFile(String content) {

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(logFilePath + "/" + fileName, true);
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write("Thread #" + Thread.currentThread().getId() + " :" + content);
            bw.flush();
            fw.close();
            bw.close();
        } catch (IOException e) {

            Logger.getRootLogger().setLevel(Level.ERROR);

            logger.error("ERROR" + " " + "Can't write to Log File, IOException occurred!!!!!!!!!!!!!!!!! \n");
            e.printStackTrace();
        }

    }


    public void logSetup() {

        String currTestRunTimestamp = Long.toString(new Date().getTime());
        System.setProperty("TEST_RUN_TIMESTAMP", currTestRunTimestamp); // Save current Run Timestamp

    }
}
