package com.freenow.qa.util.common;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.Arrays;

/**
 * This utility class provides validation methods for test
 *
 * @author Gaurav Sharma
 */
public class TestUtil {
    private static CustomAssertionsUtil assertions = CustomAssertionsUtil.getInstance();
    private static TestUtil testUtilInstance = null;
    private static final LogUtils LOGGER = LogUtils.getInstance(TestUtil.class);

    public static TestUtil getInstance() {

        if (testUtilInstance == null)
            testUtilInstance = new TestUtil();

        return testUtilInstance;
    }


    public void validateEmailId(String email) {
        try {
            String ePattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
            assertions.assertTrue(email.matches(ePattern), "Email Id is invalid");
            LOGGER.info(email + " is in correct format");
        } catch (AssertionError e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            LOGGER.fail(email + " is not in correct format");
        }

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

    public void checkBlankResponse(Response res) {
        try {
            res.then().body("", Matchers.hasSize(0));
            LOGGER.info("The response is blank as expected " + res.getBody().prettyPrint());
        } catch (AssertionError e) {
            LOGGER.fail("Response expected was [] and actual is " + res.getBody().prettyPrint() + "");
        }
    }
}
