@admin
Feature: OrangeHRM Admin Module Functionality

  As an admin user
  I want to manage system users
  So that I can control access to the application

  Background:
    Given the user is logged in to OrangeHRM
    And the user navigates to the "Admin" section

  Scenario: Verify Admin page header and layout
    Then the module header should display "Admin"
    And clicks on the Search button in Admin page
    Then the system users table should be displayed

  Scenario: Search system user by username
    When the user enters username "Admin" in Admin search
    And clicks on the Search button in Admin page
    Then the system users table should be displayed
