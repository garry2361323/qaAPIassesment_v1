package com.freenow.qa.util.common;

import com.freenow.qa.constants.Constants;
import com.sun.deploy.util.ParameterUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestUtil {
    private static Response response = null;
    private static RestUtil apiUtilsInstance = null;
    private RequestSpecification request;

    public static RestUtil getInstance() {

        if (apiUtilsInstance == null)
            apiUtilsInstance = new RestUtil();

        return apiUtilsInstance;
    }

    public Response sendGetRequest(String resourcePath) {
        request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.get(resourcePath);

        System.out.println("Response Header and Body is: " + "\n" + response.getHeaders());
        System.out.println("Response Body is: " + "\n" + response.getBody().prettyPrint());
        return response;
    }

    public Response sendGetRequestById(String resourcePath, int id) {
        RequestSpecification request = RestAssured.given();
        request.param("id", id);
        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.get(resourcePath);

        System.out.println("Response Header and Body is: " + "\n" + response.getHeaders());
        System.out.println("Response Body is: " + "\n" + response.getBody().prettyPrint());
        return response;
    }

    public void resetRestAssured() {
        apiUtilsInstance = null;
    }

}
