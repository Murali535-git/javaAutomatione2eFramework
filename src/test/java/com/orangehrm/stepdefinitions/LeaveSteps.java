package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.LeavePage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LeaveSteps {
    private final LeavePage leavePage = new LeavePage(DriverFactory.getDriver());
    private final int timeout = ConfigReader.getTimeout();

    @Then("the leave list header should be visible")
    public void the_leave_list_header_should_be_visible() {
        boolean isDisplayed = leavePage.isLeaveListHeaderDisplayed(timeout);
        Assert.assertTrue("Leave List header was not displayed on Leave page!", isDisplayed);
    }

    @When("clicks on the Search button in Leave page")
    public void clicks_on_the_search_button_in_leave_page() {
        leavePage.clickSearch(timeout);
    }
}
