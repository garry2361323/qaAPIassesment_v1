package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.constants.Constants;
import com.freenow.qa.util.common.RestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserAPITest {

    @DataProvider(name = "getTestData")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{1}, {2}};
    }

    @Test
    public void getUsers() {
        UsersAPI.getUsers();
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }

    @Test(dataProvider = "getTestData")
    public void getUserById(int id) {
        UsersAPI.getUserById(id);
        UsersAPI.validateStatusCode(Constants.HTTP_STATUS_CODE_200);
    }
}
