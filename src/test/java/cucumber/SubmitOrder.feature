
@tag
Feature: Purchase the order from E commerce website
	Background:
	Given I landed on Ecommerce Page 
	@Regression
	Scenario Outline: Positive Test of Submitting order
    Given Logged in with username<name> and password<password>
    When I add<productName> to Cart
    And CheckOut<productName> and submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on Confirmation Page
      Examples:
      | name                               | password   | productName    |
      | gauravsingh12345rocks@gmail.com    | 123456     | ADIDAS ORIGINAL|
               
