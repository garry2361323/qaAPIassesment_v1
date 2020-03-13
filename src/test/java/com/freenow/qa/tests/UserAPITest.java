package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import org.testng.annotations.Test;

public class UserAPITest {

    @Test
    public void getUsers() {
        UsersAPI.get();
    }
}
