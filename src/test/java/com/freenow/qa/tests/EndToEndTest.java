package com.freenow.qa.tests;


import com.freenow.qa.api.EndToEndScenario;
import com.freenow.qa.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

/***
 * This is a test class to execute end to end scenario.
 * Search for the user (Samantha).
 * Use the details fetched to make a search for the posts written by the user.
 * For each post, fetch the comments and validate if the emails in the comment section are in the proper format.
 *
 * @author Gaurav Sharma
 */
public class EndToEndTest {

    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }

    @Test(dataProvider = "dataProviderMethod", groups = "e2e",
            description = "Execute end to end scenario for fetching comments for all posts against a user(Samantha) " +
                    "and validate email ids")
    public void executeE2ETest(String paramValue) throws IOException {
        EndToEndScenario.getUserByUsernameForE2E("username", paramValue);
    }
}


