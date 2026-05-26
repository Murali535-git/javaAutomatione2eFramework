package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private WebDriver driver;

    @Before(order = 0)
    public void setup() {
        // Load properties (ConfigReader initializes them if null)
        ConfigReader.initializeProperties();
        String browser = ConfigReader.getBrowser();
        String url = ConfigReader.getUrl();

        // Initialize driver
        driver = DriverFactory.init_driver(browser);

        // Open base URL
        System.out.println("Navigating to URL: " + url);
        driver.get(url);
    }

    @Before(order = 1)
    public void printScenarioInfo(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After(order = 1)
    public void teardownScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName() + ". Taking screenshot...");
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Scenario_Screenshot");
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        System.out.println("Closing browser...");
        DriverFactory.quitDriver();
    }
}
