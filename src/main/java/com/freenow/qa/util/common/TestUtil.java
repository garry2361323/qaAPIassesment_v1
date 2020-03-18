package com.freenow.qa.util.common;


import com.freenow.qa.util.file.FileUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

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


    public boolean validateEmailId(String email) {
        try {
            String ePattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
            assertions.assertTrue(email.matches(ePattern), "Email Id is invalid");
            return true;
        } catch (AssertionError e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
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

    public boolean checkBlankResponse(Response res) {
        try {
            res.then().body("", Matchers.hasSize(0));
            LOGGER.info("The response is blank as expected " + res.getBody().prettyPrint());
            return true;
        } catch (AssertionError e) {
            LOGGER.fail("Response expected was [] and actual is " + res.getBody().prettyPrint() + "");
            return false;
        }
    }

    /*Validate the Response JSON schema against schema definition present at src/main/java/com/freenow/qa/schema
     * using Rest Assured JsonSchemaValidator */
    public void validateResponseSchema(Response res, String schemaName) {
        try {
            String schema = FileUtil.readFromFile(System.getProperty("user.dir") + "/src/main/java/com/freenow/qa/schema/" + schemaName);
            res.then().assertThat().body(matchesJsonSchema(schema));
            LOGGER.info("Response Schema Validation is PASS");

        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't find [" + schemaName + "] schema in src/main/java/com/freenow/qa/schema/ folder");

        } catch (AssertionError ex) {
            ex.printStackTrace();
            LOGGER.error("Response schema validation failed!!");
            LOGGER.fail("STACKTRACE:" + Joiner.on("\n").join(Iterables.limit(Arrays.asList(ex.getStackTrace()), 10)));  //stores only 10 lines from error stacktrace in log file
        }
    }

    /*Validate a given element value is equal to response*/
    public void validateResponseAttributes(Response res, String paramName, String paramValue) {
        ArrayList<String> expected = null;
        try {
            expected = res.getBody().prettyPeek().jsonPath().get(paramName);
            assertions.assertEquals(paramValue, expected.get(0), paramName + " is not as expected");
            LOGGER.info("The " + paramName + " is as expected " + paramValue);

        } catch (AssertionError e) {
            LOGGER.fail("Response expected was [ " + expected + " ] and actual is " + paramValue + "");
        }
    }

    /*Validate a given element value is equal to response*/
    public void validateResponseAttributes(Response res, String paramName, int paramValue) {
        ArrayList<Integer> expected = null;
        try {
            expected = res.getBody().prettyPeek().jsonPath().get(paramName);
            assertions.assertEquals(paramValue, expected.get(0), paramName + " is not as expected");
            LOGGER.info("The " + paramName + " is as expected " + paramValue);

        } catch (AssertionError e) {
            LOGGER.fail("Response expected was [ " + expected + " ] and actual is " + paramValue + "");
        }
    }

}
