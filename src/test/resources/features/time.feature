@time
Feature: OrangeHRM Time Module Functionality

  As an employee/manager
  I want to access timesheets
  So that I can view and record work hours

  Background:
    Given the user is logged in to OrangeHRM
    And the user navigates to the "Time" section

  Scenario: Verify Time page header and layout
    Then the module header should display "Time"
    And the select employee form should be visible
