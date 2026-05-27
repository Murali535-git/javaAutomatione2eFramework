@pim
Feature: OrangeHRM PIM Module Functionality

  As an HR Manager
  I want to manage employee records
  So that I can keep track of staff members

  Background:
    Given the user is logged in to OrangeHRM
    And the user navigates to the "PIM" section

  Scenario: Verify PIM page header and layout
    Then the module header should display "PIM"
    And clicks on the Search button in PIM page
    Then the employee table should be displayed

  Scenario: Search employee by name
    When the user enters employee name "Alice" in PIM search
    And clicks on the Search button in PIM page
    Then the employee table should be displayed
