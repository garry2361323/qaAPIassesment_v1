package com.freenow.qa.util.common;

import com.freenow.qa.constants.Constants;
import com.sun.deploy.util.ParameterUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestUtil {
    public static Response response;

    public static Response sendGetRequest(String resourcePath) {
        RequestSpecification request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.get(resourcePath);

        System.out.println("Response Header and Body is: " + "\n" + response.getHeaders());
        System.out.println("Response Body is: " + "\n" + response.getBody().prettyPrint());
        return response;
    }

}
