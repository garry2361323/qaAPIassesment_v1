package com.freenow.qa.tests;

import com.freenow.qa.api.PostsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class PostsAPITest {

    private LogUtils LOGGER = LogUtils.getInstance(PostsAPITest.class);
    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.start("Starting Test : " + method.getName());
    }


    @Test(description = "Verify that user is able to get list of all posts", groups = {"sanity"})
    public void getPosts() {
        PostsAPI.getPosts();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "dataProviderMethod", groups = {"sanity"})
    public void getPostById(String paramValue) {
        PostsAPI.getPostByParam("id", paramValue);
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "dataProviderMethod")
    public void getPostByUserId(String paramValue) {
        PostsAPI.getPostByParam("userId", paramValue);
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"negative"})
    public void getPostByInvalidId(String paramValue) {
        PostsAPI.getPostByParam("id", paramValue);
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_204);
    }


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


    @AfterMethod
    public void resetInstance() {
        PostsAPI.resetRestAssured();
    }
}
