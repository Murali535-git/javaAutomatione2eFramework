package com.orangehrm.stepdefinitions;

import com.orangehrm.driver.DriverFactory;
import com.orangehrm.pages.RecruitmentPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class RecruitmentSteps {
    private final RecruitmentPage recruitmentPage = new RecruitmentPage(DriverFactory.getDriver());
    private final int timeout = ConfigReader.getTimeout();

    @Then("the candidates subheader should be visible")
    public void the_candidates_subheader_should_be_visible() {
        boolean isDisplayed = recruitmentPage.isCandidatesHeaderDisplayed(timeout);
        Assert.assertTrue("Candidates subheader/title was not displayed on Recruitment page!", isDisplayed);
    }

    @When("clicks on the Search button in Recruitment page")
    public void clicks_on_the_search_button_in_recruitment_page() {
        recruitmentPage.clickSearch(timeout);
    }

    @Then("the candidates table should be displayed")
    public void the_candidates_table_should_be_displayed() {
        boolean isDisplayed = recruitmentPage.isResultsTableDisplayed(timeout);
        Assert.assertTrue("Candidates results table was not displayed on Recruitment page!", isDisplayed);
    }
}
