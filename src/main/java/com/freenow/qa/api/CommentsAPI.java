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
 * This class provides methods for Comments API
 *
 * @author Gaurav Sharma
 */
public class CommentsAPI {

    private static String endpointUsers;
    private static Response response = null;
    private static RestUtil restUtilsInstance = RestUtil.getInstance();
    private static TestUtil testUtilInstance = TestUtil.getInstance();
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();
    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static GetEnvURL getEnvURLInstance = GetEnvURL.getInstance();
    private static LogUtils LOGGER = LogUtils.getInstance(CommentsAPI.class);

    static {
        endpointUsers = getEnvURLInstance.getBaseUrl() + jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath,
                jsonUtilInstance.commentsEndpoint);

    }

    public static void get_All_Comments() {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        response = restUtilsInstance.sendGetRequest(endpointUsers);
    }


    public static Response get_Comment_By_Id(String paramName, String paramValue) {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static Response get_Comment_By_PostId(String paramName, String paramValue) {
        LOGGER.info("Setting API_ENDPOINT as :" + endpointUsers);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(paramName, paramValue);
        response = restUtilsInstance.sendGetRequestByParam(endpointUsers, paramMap);
        return response;
    }


    public static void validate_Response_StatusCode(int statusCode) {
        testUtilInstance.checkStatusIs(response, statusCode);
    }


    public static void doCommentPost() {
        response = restUtilsInstance.sendPostRequest(endpointUsers);
    }


    public static void doCommentPut() {
        response = restUtilsInstance.sendPutRequest(endpointUsers);
    }


    public static void doCommentDelete() {
        response = restUtilsInstance.sendDeleteRequest(endpointUsers);
    }

    public static void validate_CommentAPI_Response_Schema() {
        testUtilInstance.validateResponseSchema(response, "CommentsAPIResponse.json");
    }

    public static void validate_Same_CommentId_Available_In_Response(String id) {
        testUtilInstance.validateResponseAttributes(response, "id", Integer.parseInt(id));
    }

    public static void validate_Same_PostId_Available_In_Response(String postId) {
        testUtilInstance.validateResponseAttributes(response, "postId", Integer.parseInt(postId));
    }

    public static void validate_Blank_Response() {
        testUtilInstance.checkBlankResponse(response);
    }

}
