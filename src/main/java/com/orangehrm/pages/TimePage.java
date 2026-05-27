package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimePage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // Locators
    private final By selectEmployeeHeader = By.xpath("//h6[text()='Select Employee' or contains(., 'Timesheets') or contains(@class, 'oxd-topbar-header-breadcrumb')]");
    private final By employeeNameField = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private final By viewButton = By.xpath("//button[@type='submit']");

    public TimePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public boolean isSelectEmployeeHeaderDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(selectEmployeeHeader, timeout);
    }

    public void enterEmployeeName(String name, int timeout) {
        elementUtil.sendKeysWhenReady(employeeNameField, name, timeout);
    }

    public void clickView(int timeout) {
        elementUtil.clickWhenReady(viewButton, timeout);
    }
}
