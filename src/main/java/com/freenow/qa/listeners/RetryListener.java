package com.freenow.qa.listeners;

import com.freenow.qa.constants.Constants;
import com.freenow.qa.util.common.LogUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

    private int count = 0;
    private LogUtils LOGGER = LogUtils.getInstance(RetryListener.class);

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                         //Check if test not succeed
            LOGGER.info("Test case failed: Retrying for " + Constants.MAX_NUM_OF_RETRY + " times");
            if (count < Constants.MAX_NUM_OF_RETRY) {                               //Check if maxtry count is reached
                count++;                                        //Increase the maxTry count by 1
                LOGGER.info(count + "...");
                iTestResult.setStatus(ITestResult.FAILURE);     //Mark test as failed
                return true;                                    //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
}
