package com.freenow.qa.util.common;

import io.restassured.response.Response;

import java.util.Arrays;

public class TestUtil {
    private static CustomAssertionsUtil assertions = CustomAssertionsUtil.getInstance();
    private static TestUtil testUtilInstance = null;
    private static final LogUtils LOGGER = LogUtils.getInstance(TestUtil.class);

    public static TestUtil getInstance() {

        if (testUtilInstance == null)
            testUtilInstance = new TestUtil();

        return testUtilInstance;
    }

    public void checkStatusIs(Response res, int statusCode) {


        try {
            assertions.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status code is not as expected.");
            LOGGER.info("Http Response Status code is as expected : " + statusCode);
        } catch (AssertionError e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            LOGGER.fail("API Response Http Status expected was [" + statusCode + "] and actual is [" + res.getStatusCode() + "]");
        }
    }
}
