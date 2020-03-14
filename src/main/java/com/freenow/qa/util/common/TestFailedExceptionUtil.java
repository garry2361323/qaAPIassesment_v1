package com.freenow.qa.util.common;

/**
 * This class provides a custom RuntimeException
 */
public class TestFailedExceptionUtil extends RuntimeException {

    public TestFailedExceptionUtil(String s) {
        // Call constructor of parent Exception
        super(s);
    }

}
