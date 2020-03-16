package com.freenow.qa.tests;

import com.freenow.qa.api.CommentsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CommentsAPITest {

    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }


    @Test(description = "Verify that user is able to get list of all comments", groups = {"sanity"})
    public void getComments() {
        CommentsAPI.getComments();
    }


    @Test(dataProvider = "dataProviderMethod", groups = {"sanity"})
    public void getCommentById(String paramValue) {
        CommentsAPI.getCommentByParam("id", paramValue);
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "dataProviderMethod", groups = {"sanity"})
    public void getCommentByPostId(String paramValue) {
        CommentsAPI.getCommentByParam("postId", paramValue);
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code 204 is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"negative"})
    public void getCommentByInvalidId(String paramValue) {
        CommentsAPI.getCommentByParam("id", paramValue);
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_204);
    }

    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'", groups = {"negative"})
    public void doCommentPost() {
        CommentsAPI.doCommentPost();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'", groups = {"negative"})
    public void doCommentPut() {
        CommentsAPI.doCommentPut();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'", groups = {"negative"})
    public void doCommentDelete() {
        CommentsAPI.doCommentDelete();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }

}
