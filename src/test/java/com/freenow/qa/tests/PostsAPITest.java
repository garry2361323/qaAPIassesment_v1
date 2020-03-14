package com.freenow.qa.tests;

import com.freenow.qa.api.PostsAPI;
import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostsAPITest {

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
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
}
