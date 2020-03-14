package com.freenow.qa.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static PropertiesUtils propertiesUtilsInstance = null;
    //file path
    public String envPropFilePath = "../qaAPIassesment_v1/src/test/resources/common.properties";
    public String configFilePath = "configFilePath";
    public String extentConfigPath = "extentConfigPath";
    //properties in environment.properties file
    public String environment = "environment";

    public static PropertiesUtils getInstance() {

        if (propertiesUtilsInstance == null)
            propertiesUtilsInstance = new PropertiesUtils();

        return propertiesUtilsInstance;
    }

    // read property value from properties file
    public String readProperty(String propertyName) {
        String propertyValue = null;
        try {
            FileInputStream fis = new FileInputStream(new File(envPropFilePath));
            Properties prop = new Properties();
            prop.load(fis);
            propertyValue = prop.getProperty(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

}
