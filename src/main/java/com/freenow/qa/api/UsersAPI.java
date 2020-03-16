package com.freenow.qa.api;


import com.freenow.qa.util.common.*;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

public class UsersAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtilsInstance = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();
    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static GetEnvURL getEnvURLInstance = GetEnvURL.getInstance();
    private static LogUtils LOGGER = LogUtils.getInstance(UsersAPI.class);

    static {
        endpointUsers = getEnvURLInstance.getBaseUrl() + jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath,
                jsonUtilInstance.usersEndpoint);
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
    }

    public static void getUsers() {
        response = restUtilsInstance.sendGetRequest(endpointUsers);
    }

    public static void getUserById(int id) {
        response = restUtilsInstance.sendGetRequestById(endpointUsers, id);
    }

    public static void getUserByParam(String paramName, String paramValue) {
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramName, paramValue);
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

    public static void validateStatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }

    public static void resetRestAssured() {
        restUtilsInstance.resetRestAssured();
    }
}
