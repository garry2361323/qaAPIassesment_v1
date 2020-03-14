package com.freenow.qa.tests;

import com.freenow.qa.api.CommentsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.util.common.LogUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CommentsAPITest {

    private LogUtils LOGGER = LogUtils.getInstance(CommentsAPITest.class);

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
    }

    @BeforeMethod
    public void initialize(Method method) {
        LOGGER.start("Starting Test : " + method.getName());
    }


    @Test
    public void getComments() {
        CommentsAPI.getComments();
    }


    @Test(dataProvider = "getTestData")
    public void getCommentById(int id) {
        CommentsAPI.getCommentById(id);
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'POST'")
    public void doCommentPost() {
        CommentsAPI.doCommentPost();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'PUT'")
    public void doCommentPut() {
        CommentsAPI.doCommentPut();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @Test(description = "Verify status code 405 is received, when user provides request Type as 'DELETE'")
    public void doCommentDelete() {
        CommentsAPI.doCommentDelete();
        CommentsAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_405);
    }


    @AfterMethod
    public void resetInstance() {
        CommentsAPI.resetRestAssured();
    }

}
