package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeavePage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // Locators
    private final By leaveListHeader = By.xpath("//h5[text()='Leave List']");
    private final By searchButton = By.xpath("//button[@type='submit']");

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public boolean isLeaveListHeaderDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(leaveListHeader, timeout);
    }

    public void clickSearch(int timeout) {
        elementUtil.clickWhenReady(searchButton, timeout);
    }
}
