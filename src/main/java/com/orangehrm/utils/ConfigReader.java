package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    /**
     * Initializes the properties from config.properties.
     * Searches classpath first, then falls back to relative file path.
     * @return Properties object
     */
    public static Properties initializeProperties() {
        prop = new Properties();
        try {
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties");
            if (input != null) {
                prop.load(input);
            } else {
                // Fallback to project root file path if not found in classpath
                try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
                    prop.load(fis);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load config.properties file: " + e.getMessage());
        }
        return prop;
    }

    public static String getBrowser() {
        if (prop == null) initializeProperties();
        return prop.getProperty("browser", "chrome").trim();
    }

    public static String getUrl() {
        if (prop == null) initializeProperties();
        return prop.getProperty("url").trim();
    }

    public static int getTimeout() {
        if (prop == null) initializeProperties();
        String timeoutStr = prop.getProperty("timeout");
        try {
            return Integer.parseInt(timeoutStr.trim());
        } catch (NumberFormatException e) {
            return 10; // Default timeout of 10 seconds
        }
    }

    public static String getUsername() {
        if (prop == null) initializeProperties();
        return prop.getProperty("username").trim();
    }

    public static String getPassword() {
        if (prop == null) initializeProperties();
        return prop.getProperty("password").trim();
    }
}
