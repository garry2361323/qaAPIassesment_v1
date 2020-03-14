package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.SQLOutput;


public class UserAPITest {

    private LogUtils LOGGER = LogUtils.getInstance(UserAPITest.class);

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod(Method method) {
        if (method.getName().equalsIgnoreCase("getUserByInvalidId"))
            return new Object[][]{{99}, {100}};
        else
            return new Object[][]{{1}, {2}};
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.start("Starting Test : " + method.getName());
    }


    @Test(priority = 1, description = "Verify that user is able to get list of all users")
    public void getUsers() {
        UsersAPI.getUsers();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "getTestData",
            description = "Verify that user is able to get user details by valid id")
    public void getUserById(int id) {
        UsersAPI.getUserById(id);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "getTestData",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class)
    public void getUserByInvalidId(int id) {
        UsersAPI.getUserById(id);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_204);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'")
    public void doUserPost() {
        UsersAPI.doUserPost();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'")
    public void doUserPut() {
        UsersAPI.doUserPut();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'")
    public void doUserDelete() {
        UsersAPI.doUserDelete();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @AfterMethod
    public void resetInstance() {
        UsersAPI.resetRestAssured();
    }
}
