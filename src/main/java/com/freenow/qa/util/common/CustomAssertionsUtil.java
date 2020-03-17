package com.freenow.qa.util.common;

import org.testng.Assert;
/***
 * This class provides a wrapper around TestNG assertions; with logging
 *
 * @author Gaurav Sharma
 */
public class CustomAssertionsUtil {
    private static CustomAssertionsUtil assertInstance = null;
    private LogUtils LOGGER;

    private CustomAssertionsUtil() {
        LOGGER = LogUtils.getInstance(CustomAssertionsUtil.class);
    }

    public static CustomAssertionsUtil getInstance() {

        if (assertInstance == null)
            assertInstance = new CustomAssertionsUtil();

        return assertInstance;
    }

    public void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {

            LOGGER.error(message + " : " + e);
            Assert.fail(e.getMessage());
        }
    }

    public void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {

            LOGGER.error(message + " : " + e);
            Assert.fail(e.getMessage());
        }
    }

    public void assertTrue(boolean condition, String message) {

        try {
            Assert.assertTrue(condition, message);

        } catch (AssertionError e) {

            LOGGER.error(message + " : " + e);
            Assert.fail(e.getMessage());

        }

    }

    public void assertFalse(boolean condition, String message) {

        try {
            Assert.assertFalse(condition, message);

        } catch (AssertionError e) {

            LOGGER.error(message + " : " + e);
            Assert.fail(e.getMessage());

        }

    }
}
