package com.freenow.qa.api;

import com.freenow.qa.util.common.GetEnvURL;
import com.freenow.qa.util.common.LogUtils;
import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.common.TestUtil;
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

    static {
        endpointUsers = getEnvURLInstance.getBaseUrl() + jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath,
                jsonUtilInstance.postsEndpoint);

    }

    public static void get_All_Posts() {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        response = restUtilsInstance.sendGetRequest(endpointUsers);

    }


    public static Response get_Post_By_Id(String paramName, String paramValue) {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static void doPostPost() {
        response = restUtilsInstance.sendPostRequest(endpointUsers);
    }


    public static void doPostPut() {
        response = restUtilsInstance.sendPutRequest(endpointUsers);
    }


    public static void doPostDelete() {
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
