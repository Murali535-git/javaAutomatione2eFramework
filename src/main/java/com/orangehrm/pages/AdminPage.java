package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // Locators
    private final By usernameSearchField = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultsTable = By.xpath("//div[contains(@class, 'oxd-table-card')]");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public void enterUsername(String username, int timeout) {
        elementUtil.sendKeysWhenReady(usernameSearchField, username, timeout);
    }

    public void clickSearch(int timeout) {
        elementUtil.clickWhenReady(searchButton, timeout);
    }

    public boolean isResultsTableDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(resultsTable, timeout);
    }
}
