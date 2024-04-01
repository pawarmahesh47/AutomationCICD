@tag
Feature: Purchase order from E-comerence Website
  I want to use this template for my feature file

Background:
	Given I landed on home page of E-commerce webpage

  @tag2
  Scenario Outline: Test of submitting order
    Given Logged in with username <username> and password <password> 
    When add product <productName> to cart
    And checkout product <productName> and submit order
    Then I verify the confirmation message "THANKYOU FOR THE ORDER."

    Examples: 
      | username        | password  | productName  |
      | nirvi@gmail.com | Nirvi@123 | ZARA COAT 3  |
     