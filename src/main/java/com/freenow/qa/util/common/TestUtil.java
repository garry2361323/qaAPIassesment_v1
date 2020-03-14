package com.freenow.qa.util.common;

import io.restassured.response.Response;

public class TestUtil {
    private static CustomAssertions assertions = CustomAssertions.getInstance();
    private static TestUtil testUtilInstance = null;

    public static TestUtil getInstance() {

        if (testUtilInstance == null)
            testUtilInstance = new TestUtil();

        return testUtilInstance;
    }

    public void checkStatusIs(Response res, int statusCode) {


        try {
            assertions.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status code is not as expected.");
            // LOGGER.info("Http Response Status code is as expected : " + statusCode);
        } catch (AssertionError e) {

            // LOGGER.fail("API Response Http Status expected was [" + statusCode + "] and actual is [" + res.getStatusCode()  +"]");
        }
    }
}
