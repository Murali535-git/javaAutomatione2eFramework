package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // 1. Locators
    private final By dashboardHeader = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb')]");
    private final By userDropdown = By.className("oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");

    // 2. Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(this.driver);
    }

    // 3. Page Actions
    public String getDashboardHeader(int timeout) {
        return elementUtil.getTextWhenVisible(dashboardHeader, timeout);
    }

    public boolean isDashboardHeaderDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(dashboardHeader, timeout);
    }

    public boolean isUserDropdownDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(userDropdown, timeout);
    }

    public void clickUserDropdown(int timeout) {
        elementUtil.clickWhenReady(userDropdown, timeout);
    }

    public LoginPage clickLogout(int timeout) {
        clickUserDropdown(timeout);
        elementUtil.clickWhenReady(logoutLink, timeout);
        return new LoginPage(driver);
    }
}
