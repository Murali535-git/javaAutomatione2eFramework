package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecruitmentPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // Locators
    private final By candidatesHeader = By.xpath("//h5[text()='Candidates']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultsTable = By.xpath("//div[contains(@class, 'oxd-table-card')]");

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public boolean isCandidatesHeaderDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(candidatesHeader, timeout);
    }

    public void clickSearch(int timeout) {
        elementUtil.clickWhenReady(searchButton, timeout);
    }

    public boolean isResultsTableDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(resultsTable, timeout);
    }
}
