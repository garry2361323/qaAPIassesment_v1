package com.freenow.qa.util.common;

/**
 * This class provides a custom RuntimeException
 */
public class TestFailedException extends RuntimeException {

    public TestFailedException(String s) {
        // Call constructor of parent Exception
        super(s);
    }

}
