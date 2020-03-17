package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class UserAPITest {

    private static TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }


    @Test(description = "Verify that user is able to get list of all users and status code as 200", groups = {"sanity"})
    public void get_All_Users() {
        UsersAPI.get_All_Users();
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        UsersAPI.validate_UserAPI_Response_Schema();
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify that user is able to get user details by valid id and status code as 200",
            groups = {"sanity"})
    public void get_User_By_Id(String id) {
        UsersAPI.get_User_By_Id("id", id);
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        UsersAPI.validate_Same_UserId_Available_In_Response(id);
        UsersAPI.validate_UserAPI_Response_Schema();
    }

    @Test(dataProvider = "dataProviderMethod",
            description = "Verify that user is able to get user details by valid id",
            groups = {"sanity"})
    public void get_User_By_User_Name(String username) {
        UsersAPI.get_User_By_Username("username", username);
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        UsersAPI.validate_Same_UserName_Available_In_Response(username);
        UsersAPI.validate_UserAPI_Response_Schema();
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"sanity"})
    public void get_User_By_Invalid_Id(String id) {
        UsersAPI.get_User_By_Id("id", id);
        UsersAPI.validate_Blank_Response();
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
    }

/*
    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'", groups = {"negative"})
    public void doUserPost() {
        UsersAPI.doUserPost();
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'", groups = {"negative"})
    public void doUserPut() {
        UsersAPI.doUserPut();
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'", groups = {"negative"})
    public void doUserDelete() {
        UsersAPI.doUserDelete();
        UsersAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_405);
    }
*/
}
