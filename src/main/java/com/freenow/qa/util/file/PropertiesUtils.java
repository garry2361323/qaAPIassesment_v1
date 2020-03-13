package com.freenow.qa.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {

    //file path
    public static String envPropFilePath = "../qaAPIassesment_v1/src/test/resources/common.properties";
    public static String configFilePath = "configFilePath";
    public static String extentConfigPath = "extentConfigPath";

    //properties in environment.properties file
    public static String environment = "environment";

    // read property value from properties file
    public static String readProperty(String propertyName) {
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
