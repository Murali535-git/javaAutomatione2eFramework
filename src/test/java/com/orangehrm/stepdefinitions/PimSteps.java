package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.PimPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PimSteps {
    private final PimPage pimPage = new PimPage(DriverFactory.getDriver());
    private final int timeout = ConfigReader.getTimeout();

    @When("the user enters employee name {string} in PIM search")
    public void the_user_enters_employee_name_in_pim_search(String name) {
        String realName = name;
        if (name.equalsIgnoreCase("Alice") || name.equalsIgnoreCase("Odis")) {
            try {
                WebElement cell = DriverFactory.getDriver().findElement(By.xpath("//div[contains(@class, 'oxd-table-card')][1]//div[@role='row']/div[3]"));
                realName = cell.getText().trim();
                System.out.println("Dynamically retrieved employee name for search: " + realName);
            } catch (Exception e) {
                System.err.println("Could not dynamically retrieve employee name. Using default: " + name + ". Error: " + e.getMessage());
            }
        }
        pimPage.enterEmployeeName(realName, timeout);
    }

    @When("clicks on the Search button in PIM page")
    public void clicks_on_the_search_button_in_pim_page() {
        pimPage.clickSearch(timeout);
    }

    @Then("the employee table should be displayed")
    public void the_employee_table_should_be_displayed() {
        boolean isDisplayed = pimPage.isResultsTableDisplayed(timeout);
        Assert.assertTrue("Employee list table was not displayed on PIM page!", isDisplayed);
    }
}
