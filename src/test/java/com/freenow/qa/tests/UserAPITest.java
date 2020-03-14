package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
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
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.writeToLogFile("**********************************************************************************" +
                "******************************************************************");
        LOGGER.start("Starting Test : " + method.getName());
    }

    @Test(priority = 1)
    public void getUsers() {
        UsersAPI.getUsers();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "getTestData", priority = 2)
    public void getUserById(int id) {
        UsersAPI.getUserById(id);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_201);
    }

    @AfterMethod
    public void resetInstance() {
        UsersAPI.resetRestAssured();
        System.out.println("test");
    }
}
