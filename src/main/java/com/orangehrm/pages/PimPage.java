package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // Locators
    private final By employeeNameSearchField = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private final By employeeIdSearchField = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultsTable = By.xpath("//div[contains(@class, 'oxd-table-card')]");

    private final By autocompleteOption = By.xpath("//div[@role='listbox']//div[contains(@class, 'oxd-autocomplete-option')]");

    public PimPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public void enterEmployeeName(String name, int timeout) {
        elementUtil.sendKeysWhenReady(employeeNameSearchField, name, timeout);
        // Wait up to 5 seconds for the autocomplete dropdown options and click the first suggestion
        elementUtil.clickWhenReady(autocompleteOption, 5);
    }

    public void enterEmployeeId(String id, int timeout) {
        elementUtil.sendKeysWhenReady(employeeIdSearchField, id, timeout);
    }

    public void clickSearch(int timeout) {
        elementUtil.clickWhenReady(searchButton, timeout);
    }

    public boolean isResultsTableDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(resultsTable, timeout);
    }
}
