package com.freenow.qa.api;


import com.freenow.qa.util.common.CustomAssertions;
import com.freenow.qa.util.common.GetEnvURL;
import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.common.TestUtil;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

public class UsersAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtils = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();

    static {
        endpointUsers = GetEnvURL.getBaseUrl() + JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.usersEndpoint);
    }

    public static void getUsers() {
        response = restUtils.sendGetRequest(endpointUsers);

    }

    public static void getUserById(int id) {
        response = restUtils.sendGetRequestById(endpointUsers, id);
    }

    public static void validateStatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }
}
