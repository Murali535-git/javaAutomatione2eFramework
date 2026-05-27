package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.TimePage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class TimeSteps {
    private final TimePage timePage = new TimePage(DriverFactory.getDriver());
    private final int timeout = ConfigReader.getTimeout();

    @Then("the select employee form should be visible")
    public void the_select_employee_form_should_be_visible() {
        boolean isDisplayed = timePage.isSelectEmployeeHeaderDisplayed(timeout);
        Assert.assertTrue("Select Employee form or Timesheets header was not displayed on Time page!", isDisplayed);
    }
}
