package com.freenow.qa.tests;

import com.freenow.qa.api.UsersAPI;
import com.freenow.qa.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class EndToEndTest {

//    @DataProvider
//    public Object[][] dataProviderMethod(Method method) {
//        return TestData.getTestData(method);
//    }

    @Test(dataProvider = "dataProviderMethod")
    public void getUser(String paramName, String paramValue) {
        UsersAPI.getUserByParam(paramName, paramValue);
    }
}


