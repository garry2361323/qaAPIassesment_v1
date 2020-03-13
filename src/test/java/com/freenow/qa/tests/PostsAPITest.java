package com.freenow.qa.tests;

import com.freenow.qa.api.PostsAPI;
import org.testng.annotations.Test;

public class PostsAPITest {

    @Test
    public void getPosts() {
        PostsAPI.get();
    }
}
