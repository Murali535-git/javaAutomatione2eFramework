@leave
Feature: OrangeHRM Leave Module Functionality

  As an employee/manager
  I want to access leave management
  So that I can view and manage leave lists

  Background:
    Given the user is logged in to OrangeHRM
    And the user navigates to the "Leave" section

  Scenario: Verify Leave page header and layout
    Then the module header should display "Leave"
    And the leave list header should be visible
