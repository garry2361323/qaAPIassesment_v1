package com.freenow.qa.listeners;

import com.freenow.qa.constants.Constants;
import com.freenow.qa.util.common.LogUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/***
 * Retrylistener class used to provide retrial mechanism for failed tests
 *MAX_NUM_OF_RETRY can be set to desired number for retrial
 *
 * @author Gaurav Sharma
 */
public class RetryListener implements IRetryAnalyzer {

    private int count = 0;
    private LogUtils LOGGER = LogUtils.getInstance(RetryListener.class);

    @Override
    public boolean retry(ITestResult iTestResult) {
        //Check if test not succeed
        if (!iTestResult.isSuccess()) {
            LOGGER.info("Test case failed: Retrying for " + Constants.MAX_NUM_OF_RETRY + " times");

            //Check if maxtry count is reached
            if (count < Constants.MAX_NUM_OF_RETRY) {
                count++;
                LOGGER.info(count + "...");

                //Mark test as failed
                iTestResult.setStatus(ITestResult.FAILURE);

                //Tells TestNG to re-run the test
                return true;
            } else {
                //If maxCount reached,test marked as failed
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            //If test passes, TestNG marks it as passed
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
