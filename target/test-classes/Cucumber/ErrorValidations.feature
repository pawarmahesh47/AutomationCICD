
@tag
Feature: Error Validations

  @ErrorValidation
  Scenario Outline: Validate error message of login page
    Given I landed on home page of E-commerce webpage
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username        | password  |
      | nirvi@gmail.com | Nirvi@12 |
