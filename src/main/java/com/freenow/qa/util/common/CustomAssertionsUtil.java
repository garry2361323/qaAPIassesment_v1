package com.freenow.qa.util.common;

import org.testng.Assert;

public class CustomAssertionsUtil {
    private static CustomAssertionsUtil assertInstance = null;

    public static CustomAssertionsUtil getInstance() {

        if (assertInstance == null)
            assertInstance = new CustomAssertionsUtil();

        return assertInstance;
    }

    public void assertEquals(int actual, int expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);

        } catch (AssertionError e) {

            //   LOGGER.error(message + " : " + e);
            Assert.fail(e.getMessage());
        }
    }
}
