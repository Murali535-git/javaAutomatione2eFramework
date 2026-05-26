Feature: OrangeHRM Login Functionality

  As a user of OrangeHRM
  I want to be able to log in to the application
  So that I can access my dashboard and manage human resources

  Background:
    Given the user is on the OrangeHRM login page

  Scenario: Successful login with valid credentials
    When the user enters a valid username and password
    And clicks on the login button
    Then the user should be redirected to the dashboard page
    And the dashboard page header should be "Dashboard"

  Scenario Outline: Unsuccessful login with invalid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then the user should see an error message "Invalid credentials"

    Examples:
      | username | password |
      | Admin    | wrong123 |
      | Invalid  | admin123 |
      | Invalid  | wrong123 |

  Scenario: Verify forgot password functionality
    When the user clicks on the forgot password link
    Then the user should be redirected to the Reset Password page
    And the page header should display "Reset Password"

  Scenario: Verify logout functionality
    When the user enters a valid username and password
    And clicks on the login button
    And the user clicks on the logout link
    Then the user should be redirected back to the login page
