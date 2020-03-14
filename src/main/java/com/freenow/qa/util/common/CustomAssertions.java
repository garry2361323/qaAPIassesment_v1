package com.freenow.qa.util.common;

import org.testng.Assert;

public class CustomAssertions {
    private static CustomAssertions assertInstance = null;

    public static CustomAssertions getInstance() {

        if (assertInstance == null)
            assertInstance = new CustomAssertions();

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
