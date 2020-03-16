package com.freenow.qa.util.common;


import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;
/***
 * This is a utility class to fetch environment details in runtime from file common.properties
 *
 * @author Gaurav Sharma
 */
public class GetEnvURL {

    private static PropertiesUtils propertiesUtilsInstance = PropertiesUtils.getInstance();
    static String envName = propertiesUtilsInstance.readProperty(propertiesUtilsInstance.environment);
    private static JsonUtil jsonUtilInstance = JsonUtil.getInstance();
    private static GetEnvURL getEnvURLInstance = null;

    public static GetEnvURL getInstance() {

        if (getEnvURLInstance == null)
            getEnvURLInstance = new GetEnvURL();

        return getEnvURLInstance;
    }

    public String getBaseUrl() {
        String serviceBaseUrl = null;

        switch (envName) {
            case "QA":
                serviceBaseUrl = jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath, jsonUtilInstance.baseURIQA);
                break;
            case "UAT":
                serviceBaseUrl = jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath, jsonUtilInstance.baseURIUAT);
                break;
            default:
                serviceBaseUrl = jsonUtilInstance.readConfigValue(propertiesUtilsInstance.configFilePath, jsonUtilInstance.baseURIQA);
                break;
        }

        return serviceBaseUrl;
    }

}
