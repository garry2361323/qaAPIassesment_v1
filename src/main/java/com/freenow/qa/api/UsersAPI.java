package com.freenow.qa.api;


import com.freenow.qa.util.common.*;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

import java.util.HashMap;

/***
 * This class provides various methods for Users API
 *
 * @author Gaurav Sharma
 */
public class UsersAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtilsInstance = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();
    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static GetEnvURL getEnvURLInstance = GetEnvURL.getInstance();
    private static LogUtils LOGGER = LogUtils.getInstance(UsersAPI.class);
    private static ExtentUtil extentUtilInstance = ExtentUtil.getInstance();
    private static UsersAPI usersAPIInstance = null;

    static {
        endpointUsers = getEnvURLInstance.getBaseUrl() + jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath,
                jsonUtilInstance.usersEndpoint);
    }

    public static UsersAPI getInstance() {

        if (usersAPIInstance == null)
            usersAPIInstance = new UsersAPI();

        return usersAPIInstance;
    }

    public static void get_All_Users() {

        extentUtilInstance.getTest().assignCategory("smoke", "regression");
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        response = restUtilsInstance.sendGetRequest(endpointUsers);

    }


    public static Response get_User_By_Id(String paramName, String paramValue) {

        extentUtilInstance.getTest().assignCategory("regression");
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static Response get_User_By_Username(String paramName, String paramValue) {
        extentUtilInstance.getTest().assignCategory("regression");
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static void doUserPost() {
        response = restUtilsInstance.sendPostRequest(endpointUsers);
    }


    public static void doUserPut() {
        response = restUtilsInstance.sendPutRequest(endpointUsers);
    }


    public static void doUserDelete() {
        response = restUtilsInstance.sendDeleteRequest(endpointUsers);
    }


    public static void validate_Response_StatusCode(int statusCode) {

        testUtilInstance.checkStatusIs(response, statusCode);
    }


    public static void validate_Blank_Response() {

        testUtilInstance.checkBlankResponse(response);
    }


    public static void validate_UserAPI_Response_Schema() {

        testUtilInstance.validateResponseSchema(response, "UserAPIResponse.json");
    }


    public static void validate_Same_UserId_Available_In_Response(String paramValue) {

        testUtilInstance.validateResponseAttributes(response, "id", Integer.parseInt(paramValue));
    }


    public static void validate_Same_UserName_Available_In_Response(String paramValue) {

        testUtilInstance.validateResponseAttributes(response, "username", paramValue);
    }
}
