package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private DashboardPage dashboardPage;
    private final int timeout = ConfigReader.getTimeout();

    @Given("the user is on the OrangeHRM login page")
    public void the_user_is_on_the_orangehrm_login_page() {
        String title = loginPage.getPageTitle(timeout);
        Assert.assertTrue("Page title did not match!", title.contains("OrangeHRM"));
    }

    @When("the user enters a valid username and password")
    public void the_user_enters_a_valid_username_and_password() {
        loginPage.enterUsername(ConfigReader.getUsername(), timeout);
        loginPage.enterPassword(ConfigReader.getPassword(), timeout);
    }

    @When("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginPage.clickLogin(timeout);
        // Initialize dashboard page as we expect navigation to happen
        dashboardPage = new DashboardPage(DriverFactory.getDriver());
    }

    @Then("the user should be redirected to the dashboard page")
    public void the_user_should_be_redirected_to_the_dashboard_page() {
        boolean isDisplayed = dashboardPage.isDashboardHeaderDisplayed(timeout);
        Assert.assertTrue("Dashboard header was not displayed!", isDisplayed);
    }

    @Then("the dashboard page header should be {string}")
    public void the_dashboard_page_header_should_be(String expectedHeader) {
        String actualHeader = dashboardPage.getDashboardHeader(timeout);
        Assert.assertEquals("Dashboard header text mismatch!", expectedHeader, actualHeader);
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username, timeout);
        loginPage.enterPassword(password, timeout);
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedErrorMessage) {
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed(timeout);
        Assert.assertTrue("Error message was not displayed!", isErrorDisplayed);
        String actualErrorMessage = loginPage.getErrorMessageText(timeout);
        Assert.assertEquals("Error message text mismatch!", expectedErrorMessage, actualErrorMessage);
    }

    @When("the user clicks on the forgot password link")
    public void the_user_clicks_on_the_forgot_password_link() {
        loginPage.clickForgotPassword(timeout);
    }

    @Then("the user should be redirected to the Reset Password page")
    public void the_user_should_be_redirected_to_the_reset_password_page() {
        // Assert URL contains the requestPasswordResetCode path
        Assert.assertTrue("Not redirected to Reset Password page!", 
                DriverFactory.getDriver().getCurrentUrl().contains("requestPasswordResetCode"));
    }

    @Then("the page header should display {string}")
    public void the_page_header_should_display(String expectedHeader) {
        String actualHeader = loginPage.getResetPasswordPageHeader(timeout);
        Assert.assertEquals("Reset Password page header text mismatch!", expectedHeader, actualHeader);
    }

    @When("the user clicks on the logout link")
    public void the_user_clicks_on_the_logout_link() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(DriverFactory.getDriver());
        }
        dashboardPage.clickLogout(timeout);
    }

    @Then("the user should be redirected back to the login page")
    public void the_user_should_be_redirected_back_to_the_login_page() {
        // Verify user is back on login page (e.g. login button or forgot password link is displayed)
        boolean isForgotLinkDisplayed = loginPage.isForgotPasswordLinkDisplayed(timeout);
        Assert.assertTrue("Forgot password link not displayed, login page not verified!", isForgotLinkDisplayed);
        Assert.assertTrue("URL does not indicate login page!", 
                DriverFactory.getDriver().getCurrentUrl().contains("auth/login"));
    }

    @Then("the OrangeHRM logo should be visible")
    public void the_orange_hrm_logo_should_be_visible() {
        Assert.assertTrue("OrangeHRM logo is not visible!", loginPage.isLogoDisplayed(timeout));
    }

    @Then("the username field validation message should be {string}")
    public void the_username_field_validation_message_should_be(String expectedMsg) {
        String actualMsg = loginPage.getUsernameValidationMessage(timeout);
        Assert.assertEquals("Username field validation message mismatch!", expectedMsg, actualMsg);
    }

    @Then("the password field validation message should be {string}")
    public void the_password_field_validation_message_should_be(String expectedMsg) {
        String actualMsg = loginPage.getPasswordValidationMessage(timeout);
        Assert.assertEquals("Password field validation message mismatch!", expectedMsg, actualMsg);
    }

    @When("the user clicks on the cancel button on the Reset Password page")
    public void the_user_clicks_on_the_cancel_button_on_the_reset_password_page() {
        loginPage.clickCancelOnForgotPassword(timeout);
    }
}
