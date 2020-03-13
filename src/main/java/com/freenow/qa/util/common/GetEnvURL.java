package com.freenow.qa.util.common;


import com.freenow.qa.util.file.JsonUtil;
import com.freenow.qa.util.file.PropertiesUtils;

public class GetEnvURL {

    static String envName = PropertiesUtils.readProperty(PropertiesUtils.environment);

    public static String getBaseUrl() {
        String serviceBaseUrl = null;

        switch (envName) {
            case "QA":
                serviceBaseUrl = JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.baseURIQA);
                break;
            case "UAT":
                serviceBaseUrl = JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.baseURIUAT);
                break;
            default:
                serviceBaseUrl = JsonUtil.readConfigValue(PropertiesUtils.configFilePath, JsonUtil.baseURIQA);
                break;
        }

        return serviceBaseUrl;
    }

}
