@recruitment
Feature: OrangeHRM Recruitment Module Functionality

  As an HR Recruiter
  I want to manage job applications and candidates
  So that I can hire new talent

  Background:
    Given the user is logged in to OrangeHRM
    And the user navigates to the "Recruitment" section

  Scenario: Verify Recruitment page header and layout
    Then the module header should display "Recruitment"
    And the candidates subheader should be visible
    And clicks on the Search button in Recruitment page
    Then the candidates table should be displayed
