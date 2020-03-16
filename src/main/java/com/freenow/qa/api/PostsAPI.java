package com.freenow.qa.api;

import com.freenow.qa.util.common.GetEnvURL;
import com.freenow.qa.util.common.LogUtils;
import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.common.TestUtil;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

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
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
    }

    public static void getPosts() {
        response = restUtilsInstance.sendGetRequest(endpointUsers);

    }

    public static void getPostByParam(String paramName, String paramValue) {
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramName, paramValue);
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

    public static void validateStatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }

    public static void resetRestAssured() {
        restUtilsInstance.resetRestAssured();
    }
}
