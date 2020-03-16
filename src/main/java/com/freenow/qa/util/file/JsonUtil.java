package com.freenow.qa.util.file;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.List;

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

    public List<String> readTestData(String fileName, String jsonPath) {
        File jsonFile = new File(System.getProperty("user.dir") + "/src/main/java/com/freenow/qa/testdata/" + fileName + ".json");
        List<String> valueList = JsonPath.with(jsonFile).get(jsonPath);
        return valueList;
    }
}
