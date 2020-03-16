package com.freenow.qa.util.common;

import com.freenow.qa.constants.Constants;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.SkipException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.config.ConnectionConfig.connectionConfig;

/**
 * Common methods related to Rest API execution and logging.
 *
 * @author Gaurav Sharma
 */

public class RestUtil {
    private static Response response = null;
    private static RestUtil apiUtilsInstance = null;
    private RequestSpecification request;
    private static final LogUtils LOGGER = LogUtils.getInstance(RestUtil.class);


    public static RestUtil getInstance() {

        if (apiUtilsInstance == null)
            apiUtilsInstance = new RestUtil();

        return apiUtilsInstance;
    }


    public Response sendGetRequest(String resourcePath) {
        request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        try {
            response = request.get(resourcePath);
        } catch (Exception e) {
            LOGGER.info("Exception Occurred: " + e.getMessage() + ", hence skipping the test case");
            throw new SkipException("Skipping this Exception");
        }
        return response;
    }


    public Response sendGetRequestByParam(String resourcePath, HashMap<String, String> param) {


        request = RestAssured.given();

        if (param != null) {
            for (Map.Entry<String, String> e : param.entrySet()) {
                request.param(e.getKey(), e.getValue());
                LOGGER.info(e.getKey() + " : " + e.getValue());
            }
        }
        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        try {
            response = request.get(resourcePath);
            LOGGER.code("GET Response :" + response.getBody().prettyPrint());

        } catch (Exception e) {
            LOGGER.info("Exception Occurred: " + e.getMessage() + ", hence skipping the test case");
            throw new SkipException("Skipping this Exception");
        }

        return response;

    }


    public Response sendPostRequest(String resourcePath) {
        request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.post(resourcePath);
        return response;
    }


    public Response sendPutRequest(String resourcePath) {
        request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.put(resourcePath);
        return response;
    }


    public Response sendDeleteRequest(String resourcePath) {
        request = RestAssured.given();

        request.contentType(Constants.CONTENT_TYPE_APPLICATION_JSON);
        request.log().all();
        response = request.delete(resourcePath);
        return response;
    }


    public void resetRestAssured() {
        apiUtilsInstance = null;
    }


    public void setTimeOut() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", Constants.HTTP_CONNECTION_TIMEOUT).
                setParam("http.socket.timeout", Constants.HTTP_SOCKET_TIMEOUT).
                setParam("http.connection-manager.timeout", Constants.HTTP_CONNECTION_MANAGER_TIMEOUT));
    }


    public void closeConnection() {
        RestAssured.config = RestAssured.config().connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse());
    }
}
