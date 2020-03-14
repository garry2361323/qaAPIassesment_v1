package com.freenow.qa.api;

import com.freenow.qa.util.common.GetEnvURL;
import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.common.TestUtil;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

public class PostsAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtil = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();

    static {
        endpointUsers = GetEnvURL.getBaseUrl() + JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.postsEndpoint);
    }

    public static void getPosts() {
        response = restUtil.sendGetRequest(endpointUsers);

    }

    public static void getPostById(int id) {
        response = restUtil.sendGetRequestById(endpointUsers, id);
    }

    public static void validateStatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }
}
