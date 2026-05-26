package com.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtil {
    private final WebDriver driver;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait for element to be present in DOM and visible, then retrieve it.
     */
    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable, then click it.
     */
    public void clickWhenReady(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Wait for element to be visible, clear its existing content, and type the value.
     */
    public void sendKeysWhenReady(By locator, String value, int timeoutInSeconds) {
        WebElement element = waitForElementVisible(locator, timeoutInSeconds);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Wait for element to be visible, then extract and return its text.
     */
    public String getTextWhenVisible(By locator, int timeoutInSeconds) {
        return waitForElementVisible(locator, timeoutInSeconds).getText();
    }

    /**
     * Checks if the element is displayed on the page within the given timeout.
     * Returns false if TimeoutException is caught.
     */
    public boolean isElementDisplayed(By locator, int timeoutInSeconds) {
        try {
            waitForElementVisible(locator, timeoutInSeconds);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Wait for page title to contain a specific string fraction.
     */
    public boolean waitForTitleContains(String titleFraction, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.titleContains(titleFraction));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Wait for page URL to contain a specific string fraction.
     */
    public boolean waitForUrlContains(String urlFraction, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.urlContains(urlFraction));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
