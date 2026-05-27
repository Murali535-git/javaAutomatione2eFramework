package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // 1. Locators
    private final By dashboardHeader    = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb')]");
    private final By userDropdown       = By.className("oxd-userdropdown-tab");
    private final By logoutLink         = By.xpath("//a[text()='Logout']");

    // Sidebar Navigation Locators
    private final By adminNavLink       = By.xpath("//a[contains(@href, 'viewAdminModule')]");
    private final By pimNavLink         = By.xpath("//a[contains(@href, 'viewPimModule')]");
    private final By leaveNavLink       = By.xpath("//a[contains(@href, 'viewLeaveModule')]");
    private final By timeNavLink        = By.xpath("//a[contains(@href, 'viewTimeModule')]");
    private final By recruitmentNavLink = By.xpath("//a[contains(@href, 'viewRecruitmentModule')]");

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

    /**
     * Generic sidebar navigation dispatcher.
     * Supported sections (case-insensitive): Admin, PIM, Leave, Time, Recruitment
     */
    public void navigateToSection(String sectionName, int timeout) {
        switch (sectionName.trim().toLowerCase()) {
            case "admin":
                elementUtil.clickWhenReady(adminNavLink, timeout);
                break;
            case "pim":
                elementUtil.clickWhenReady(pimNavLink, timeout);
                break;
            case "leave":
                elementUtil.clickWhenReady(leaveNavLink, timeout);
                break;
            case "time":
                elementUtil.clickWhenReady(timeNavLink, timeout);
                break;
            case "recruitment":
                elementUtil.clickWhenReady(recruitmentNavLink, timeout);
                break;
            default:
                throw new IllegalArgumentException("Unknown section: " + sectionName);
        }
    }

    /**
     * Returns the current breadcrumb/page header text after navigation.
     */
    public String getPageHeader(int timeout) {
        return elementUtil.getTextWhenVisible(dashboardHeader, timeout);
    }
}
