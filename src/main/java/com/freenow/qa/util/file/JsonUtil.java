package com.freenow.qa.util.file;

import io.restassured.path.json.JsonPath;

import java.io.File;

public class JsonUtil {

    //JSON path to fetch values from config file
    public static String baseURIQA = "config.baseURI.QA";
    public static String baseURIUAT = "config.baseURI.UAT";
    public static String usersEndpoint = "config.endpoints.users";
    public static String postsEndpoint = "config.endpoints.posts";
    public static String commentsEndpoint = "config.endpoints.comments";


    //fetch config value
    public static String readConfigValue(String filename, String configJsonPath) {
        String configValue = null;
        File jsonFile = new File(PropertiesUtils.readProperty(filename));
        configValue = JsonPath.with(jsonFile).get(configJsonPath);
        return configValue;
    }

}
