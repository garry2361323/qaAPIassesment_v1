package com.freenow.qa.tests;

import com.freenow.qa.api.CommentsAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.listeners.RetryListener;
import com.freenow.qa.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class CommentsAPITest {

    private TestData testDataInstance = TestData.getInstance();

    @DataProvider
    public Object[] dataProviderMethod(Method method) {
        return testDataInstance.getTestData(method).toArray();
    }


    @Test(description = "Verify that user is able to get list of all comments and status code is 200",
            groups = {"smoke", "regression"})
    public void get_All_Comments() {
        CommentsAPI.get_All_Comments();
        CommentsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        CommentsAPI.validate_CommentAPI_Response_Schema();
    }


    @Test(dataProvider = "dataProviderMethod", description = "Verify that user is able to get Comments by Id and status code is 200",
            groups = {"regression"})
    public void get_Comment_By_Id(String id) {
        CommentsAPI.get_Comment_By_Id("id", id);
        CommentsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        CommentsAPI.validate_Same_CommentId_Available_In_Response(id);
        CommentsAPI.validate_CommentAPI_Response_Schema();
    }

    @Test(dataProvider = "dataProviderMethod", description = "Verify that user is able to get Comment by PostId and status code is 200",
            groups = {"regression"})
    public void get_Comment_By_Post_Id(String postId) {
        CommentsAPI.get_Comment_By_PostId("postId", postId);
        CommentsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
        CommentsAPI.validate_Same_PostId_Available_In_Response(postId);
        CommentsAPI.validate_CommentAPI_Response_Schema();
    }

    @Test(dataProvider = "dataProviderMethod",
            description = "Verify status code is 200 and blank response is received, when user provides invalid id",
            retryAnalyzer = RetryListener.class,
            groups = {"regression"})
    public void get_Comment_By_Invalid_Id(String id) {
        CommentsAPI.get_Comment_By_Id("id", id);
        CommentsAPI.validate_Blank_Response();
        CommentsAPI.validate_Response_StatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    /*
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
*/
}
