package com.freenow.qa.tests;

import com.freenow.qa.api.PostsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class PostsAPITest {

    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }


    @Test(description = "Verify that user is able to get list of all posts",
            groups = {"smoke", "regression"})
    public void get_All_Posts() {
        PostsAPI.get_All_Posts();
        PostsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        PostsAPI.validate_PostAPI_Response_Schema();
    }


    @Test(dataProvider = "dataProviderMethod", description = "Verify that user is able to get post by valid post id",
            groups = {"regression"})
    public void get_Post_By_Id(String id) {
        PostsAPI.get_Post_By_Id("id", id);
        PostsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        PostsAPI.validate_Same_PostId_Available_In_Response(id);
        PostsAPI.validate_PostAPI_Response_Schema();
    }

    @Test(dataProvider = "dataProviderMethod", description = "Verify that user is able to get post by valid user id",
            groups = {"regression"})
    public void get_Post_By_UserId(String userId) {
        PostsAPI.get_Post_By_UserId("userId", userId);
        PostsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        PostsAPI.validate_Same_UserId_Available_In_Response(userId);
        PostsAPI.validate_PostAPI_Response_Schema();
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code 200 and a blank response is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"regression"})
    public void get_Post_By_Invalid_Id(String id) {
        PostsAPI.get_Post_By_Id("id", id);
        PostsAPI.validate_Blank_Response();
        PostsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
    }

/*
    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'", groups = {"negative"})
    public void doPostPost() {
        PostsAPI.doPostPost();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'", groups = {"negative"})
    public void doPostPut() {
        PostsAPI.doPostPut();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'", groups = {"negative"})
    public void doPostDelete() {
        PostsAPI.doPostDelete();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }
*/
}
