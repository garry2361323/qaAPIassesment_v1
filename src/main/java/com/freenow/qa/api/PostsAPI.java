package com.freenow.qa.api;

import com.freenow.qa.util.common.*;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

import java.util.HashMap;

/***
 * This class provides various methods for Post API
 *
 * @author Gaurav Sharma
 */
public class PostsAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtilsInstance = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();
    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static GetEnvURL getEnvURLInstance = GetEnvURL.getInstance();
    private static LogUtils LOGGER = LogUtils.getInstance(PostsAPI.class);
    private static ExtentUtil extentUtilInstance = ExtentUtil.getInstance();
    private static PostsAPI postsAPIInstance = null;

    static {
        endpointUsers = getEnvURLInstance.getBaseUrl() + jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath,
                jsonUtilInstance.postsEndpoint);

    }

    public static PostsAPI getInstance() {

        if (postsAPIInstance == null)
            postsAPIInstance = new PostsAPI();

        return postsAPIInstance;
    }

    public static void get_All_Posts() {
        extentUtilInstance.getTest().assignCategory("smoke", "regression");
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        response = restUtilsInstance.sendGetRequest(endpointUsers);

    }


    public static Response get_Post_By_Id(String paramName, String paramValue) {
        extentUtilInstance.getTest().assignCategory("regression");
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static void doPostPost() {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        extentUtilInstance.getTest().assignCategory("negative");
        response = restUtilsInstance.sendPostRequest(endpointUsers);
    }


    public static void doPostPut() {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        extentUtilInstance.getTest().assignCategory("negative");
        response = restUtilsInstance.sendPutRequest(endpointUsers);
    }


    public static void doPostDelete() {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        extentUtilInstance.getTest().assignCategory("negative");
        response = restUtilsInstance.sendDeleteRequest(endpointUsers);
    }


    public static void validate_Response_StatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }

    public static void validate_PostAPI_Response_Schema() {
        testUtilInstance.validateResponseSchema(response, "PostAPIResponse.json");
    }

    public static void validate_Same_PostId_Available_In_Response(String id) {

        testUtilInstance.validateResponseAttributes(response, "id", Integer.parseInt(id));
    }

    public static Response get_Post_By_UserId(String userId, String paramValue) {
        extentUtilInstance.getTest().assignCategory("regression");
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(userId, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }

    public static void validate_Same_UserId_Available_In_Response(String userId) {

        testUtilInstance.validateResponseAttributes(response, "userId", Integer.parseInt(userId));
    }

    public static void validate_Blank_Response() {
        testUtilInstance.checkBlankResponse(response);
    }
}
