package com.freenow.qa.util.file;

import com.freenow.qa.util.common.RestUtil;
import com.freenow.qa.util.common.TestUtil;
import io.restassured.path.json.JsonPath;

import java.io.File;

public class JsonUtil {

    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    private static JsonUtil jsonUtilInstance = null;
    //JSON path to fetch values from config file
    public String baseURIQA = "config.baseURI.QA";
    public String baseURIUAT = "config.baseURI.UAT";
    public String usersEndpoint = "config.endpoints.users";
    public String postsEndpoint = "config.endpoints.posts";
    public String commentsEndpoint = "config.endpoints.comments";

    public static JsonUtil getInstance() {

        if (jsonUtilInstance == null)
            jsonUtilInstance = new JsonUtil();

        return jsonUtilInstance;
    }


    //fetch config value
    public String readConfigValue(String filename, String configJsonPath) {
        String configValue = null;
        File jsonFile = new File(propertiesUtilsInstance.readProperty(filename));
        configValue = JsonPath.with(jsonFile).get(configJsonPath);
        return configValue;
    }

}
