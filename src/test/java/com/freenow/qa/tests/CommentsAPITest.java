package com.freenow.qa.tests;

import com.freenow.qa.api.CommentsAPI;
import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CommentsAPITest {

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
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

}
