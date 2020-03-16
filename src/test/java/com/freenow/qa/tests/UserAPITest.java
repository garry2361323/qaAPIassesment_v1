package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class UserAPITest {

    private LogUtils LOGGER = LogUtils.getInstance(UserAPITest.class);
    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.start("Starting Test : " + method.getName());
    }


    @Test(description = "Verify that user is able to get list of all users", groups = {"sanity"})
    public void getUsers() {
        UsersAPI.getUsers();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify that user is able to get user details by valid id",
            groups = {"sanity"})
    public void getUserById(String paramValue) {
        UsersAPI.getUserByParam("id", paramValue);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "dataProviderMethod",
            description = "Verify that user is able to get user details by valid id",
            groups = {"sanity"})
    public void getUserByUserName(String paramValue) {
        UsersAPI.getUserByParam("username", paramValue);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"negative"})
    public void getUserByInvalidId(String paramValue) {
        UsersAPI.getUserByParam("id", paramValue);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_204);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'", groups = {"negative"})
    public void doUserPost() {
        UsersAPI.doUserPost();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'", groups = {"negative"})
    public void doUserPut() {
        UsersAPI.doUserPut();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'", groups = {"negative"})
    public void doUserDelete() {
        UsersAPI.doUserDelete();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @AfterMethod
    public void resetInstance() {
        UsersAPI.resetRestAssured();
    }
}
