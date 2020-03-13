package com.freenow.qa.tests;

import com.freenow.qa.api.CommentsAPI;
import org.testng.annotations.Test;

public class CommentsAPITest {

    @Test
    public void getComments() {
        CommentsAPI.get();
    }
}
