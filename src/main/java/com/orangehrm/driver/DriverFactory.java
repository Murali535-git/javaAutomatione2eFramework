package com.orangehrm.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initializes the thread-safe WebDriver based on the browser parameter.
     * Supports Chrome, Firefox, and Edge.
     * Configurable headless mode via System property: -Dheadless=true
     *
     * @param browser Browser name (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver init_driver(String browser) {
        System.out.println("Initializing browser: " + browser);

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (headless) {
            System.out.println("Running in HEADLESS mode");
        }

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");
            options.addArguments("--lang=en-US");
            options.setAcceptInsecureCerts(true);
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            tlDriver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("intl.accept_languages", "en-US");
            options.setAcceptInsecureCerts(true);
            if (headless) {
                options.addArguments("-headless");
            }
            tlDriver.set(new FirefoxDriver(options));

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--lang=en-US");
            options.setAcceptInsecureCerts(true);
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            tlDriver.set(new EdgeDriver(options));

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser + ". Please use chrome, firefox, or edge.");
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return getDriver();
    }

    /**
     * Retrieves the thread-safe WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal.
     */
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            try {
                tlDriver.get().quit();
            } catch (Exception e) {
                System.err.println("Exception while quitting driver: " + e.getMessage());
            } finally {
                tlDriver.remove();
            }
        }
    }
}
