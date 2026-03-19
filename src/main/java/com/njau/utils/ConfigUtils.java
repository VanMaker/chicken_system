package com.njau.utils;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private String config_path;

    public ConfigUtils(String config_path) {
        this.config_path = config_path;
    }

    public ConfigUtils() {
        this.config_path = "config.properties";
    }

    //  test
    public static void main(String[] args){
        ConfigUtils cu = new ConfigUtils();
        try {
            System.out.println(cu.getProperties("module"));
        } catch (ErrorUtils e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String property) throws ErrorUtils {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(config_path);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String value = properties.getProperty(property);
        if(value == null){
            throw new ErrorUtils("property_error", property);
        }
        return value;
    }
}
