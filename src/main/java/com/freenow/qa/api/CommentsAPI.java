package com.freenow.qa.api;

import com.freenow.qa.util.common.GetEnvURL;
import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
import io.restassured.response.Response;

public class CommentsAPI {

    private static String endpointUsers;

    static {
        endpointUsers = GetEnvURL.getBaseUrl() + JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.commentsEndpoint);
    }

    public static Response get() {
        return RestUtil.sendGetRequest(endpointUsers);
    }
}
