package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AdminSteps {
    private final AdminPage adminPage = new AdminPage(DriverFactory.getDriver());
    private final int timeout = ConfigReader.getTimeout();

    @When("the user enters username {string} in Admin search")
    public void the_user_enters_username_in_admin_search(String username) {
        adminPage.enterUsername(username, timeout);
    }

    @When("clicks on the Search button in Admin page")
    public void clicks_on_the_search_button_in_admin_page() {
        adminPage.clickSearch(timeout);
    }

    @Then("the system users table should be displayed")
    public void the_system_users_table_should_be_displayed() {
        boolean isDisplayed = adminPage.isResultsTableDisplayed(timeout);
        Assert.assertTrue("System Users table was not displayed on Admin page!", isDisplayed);
    }
}
