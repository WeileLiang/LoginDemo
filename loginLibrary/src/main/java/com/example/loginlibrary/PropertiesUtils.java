package com.example.loginlibrary;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by gzliangweile on 2017/7/13.
 */

public class PropertiesUtils {

    private static Properties getProperties(Context context,String fileName){
        Properties properties=new Properties();
        try {
            properties.load(context.getAssets().open(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static String getStringProperty(Context context,String fileName,String propertyName){
        return getProperties(context,fileName).getProperty(propertyName);

    }

    public static boolean getBooleanProperty(Context context,String fileName,String propertyName){
        return Boolean.parseBoolean(getStringProperty(context,fileName,propertyName));
    }
}
