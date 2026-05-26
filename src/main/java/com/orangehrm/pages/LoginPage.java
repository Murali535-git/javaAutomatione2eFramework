package com.orangehrm.pages;

import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    // 1. Locators (Private By variables)
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By errorMessage = By.xpath("//p[contains(@class, 'oxd-alert-content-text')]");
    private final By forgotPasswordLink = By.xpath("//p[contains(@class, 'orangehrm-login-forgot-header')]");
    private final By resetPasswordHeader = By.xpath("//h6[contains(@class, 'orangehrm-forgot-password-title')]");
    private final By loginLogo = By.xpath("//div[@class='orangehrm-login-logo']/img");
    private final By usernameError = By.xpath("//input[@name='username']/ancestor::div[contains(@class, 'oxd-input-group')]/span[contains(@class, 'oxd-input-field-error-message')]");
    private final By passwordError = By.xpath("//input[@name='password']/ancestor::div[contains(@class, 'oxd-input-group')]/span[contains(@class, 'oxd-input-field-error-message')]");
    private final By cancelButton = By.xpath("//button[contains(@class, 'orangehrm-forgot-password-button--cancel')]");

    // 2. Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(this.driver);
    }

    // 3. Page Actions
    public void enterUsername(String username, int timeout) {
        elementUtil.sendKeysWhenReady(usernameField, username, timeout);
    }

    public void enterPassword(String password, int timeout) {
        elementUtil.sendKeysWhenReady(passwordField, password, timeout);
    }

    public void clickLogin(int timeout) {
        elementUtil.clickWhenReady(loginButton, timeout);
    }

    public DashboardPage doLogin(String username, String password, int timeout) {
        enterUsername(username, timeout);
        enterPassword(password, timeout);
        clickLogin(timeout);
        return new DashboardPage(driver);
    }

    public String getErrorMessageText(int timeout) {
        return elementUtil.getTextWhenVisible(errorMessage, timeout);
    }

    public boolean isErrorMessageDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(errorMessage, timeout);
    }

    public boolean isForgotPasswordLinkDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(forgotPasswordLink, timeout);
    }

    public void clickForgotPassword(int timeout) {
        elementUtil.clickWhenReady(forgotPasswordLink, timeout);
    }

    public String getResetPasswordPageHeader(int timeout) {
        return elementUtil.getTextWhenVisible(resetPasswordHeader, timeout);
    }

    public String getPageTitle(int timeout) {
        elementUtil.waitForTitleContains("OrangeHRM", timeout);
        return driver.getTitle();
    }

    public boolean isLogoDisplayed(int timeout) {
        return elementUtil.isElementDisplayed(loginLogo, timeout);
    }

    public String getUsernameValidationMessage(int timeout) {
        return elementUtil.getTextWhenVisible(usernameError, timeout);
    }

    public String getPasswordValidationMessage(int timeout) {
        return elementUtil.getTextWhenVisible(passwordError, timeout);
    }

    public void clickCancelOnForgotPassword(int timeout) {
        elementUtil.clickWhenReady(cancelButton, timeout);
    }
}
