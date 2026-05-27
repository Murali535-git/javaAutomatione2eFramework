# Feature: Navigate to different sections of OrangeHRM
# 
#   As a user of OrangeHRM
#   I want to be able to log in to the application
#   So that I can access my dashboard and manage human resources
# 
#   Background:
#     Given the user is on the OrangeHRM login page
# 
# 
#   Scenario: Successful login with valid credentials
#     When the user enters username "<username>" and password "<password>"
#     And clicks on the login button
#     Then the user should be redirected to the dashboard page
#     And the dashboard page header should be "Dashboard"
# 
#     Examples:
#       | username | password |
#       | Admin    | wrong123 |
# 
# 
# Scenario Outline: Verify navigation to different sections
#   When the user enters a valid username and password
#   And clicks on the login button
#   And the user navigates to the "<section>" section
#   Then the page header should display "<expectedHeader>"
# 
#   Examples:
#     | section     | expectedHeader |
#     | Admin       | Admin          |
#     | PIM         | PIM            |
#     | Leave       | Leave          |
#     | Time        | Time           |
#     | Recruitment | Recruitment    |