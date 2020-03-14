package com.freenow.qa.tests;

import com.freenow.qa.api.PostsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class PostsAPITest {

    private LogUtils LOGGER = LogUtils.getInstance(PostsAPITest.class);

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.start("Starting Test : " + method.getName());
    }


    @Test
    public void getPosts() {
        PostsAPI.getPosts();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "getTestData")
    public void getPostById(int id) {
        PostsAPI.getPostById(id);
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(dataProvider = "getTestData",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class)
    public void getPostByInvalidId(int id) {
        PostsAPI.getPostById(id);
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_204);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'")
    public void doPostPost() {
        PostsAPI.doPostPost();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'")
    public void doPostPut() {
        PostsAPI.doPostPut();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'")
    public void doPostDelete() {
        PostsAPI.doPostDelete();
        PostsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @AfterMethod
    public void resetInstance() {
        PostsAPI.resetRestAssured();
    }
}
