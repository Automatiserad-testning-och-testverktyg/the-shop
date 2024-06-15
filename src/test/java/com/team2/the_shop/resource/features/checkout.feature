Feature: Checkout
# Skrivet av Jamie Blomerus

  Scenario: Checkout page should have the correct title
    Given user is on page "/checkout"
    Then the page title should be "The Shop | Checkout"

  Scenario: Checkout should not have any products on the first visit
    Given user is on page "/checkout"
    Then the cart should have 0 product(s)

  Scenario: Adding a product should be reflected in the checkout
    Given user is on page "/products"
    When user adds a product to the cart
    And user goes to page "/checkout"
    Then the cart should have 1 product(s)

  Scenario: Removing a product should update the cart
    Given user is on page "/products"
    When user adds a product to the cart
    And user goes to page "/checkout"
    And user removes a product from the cart
    Then the cart should have 0 product(s)

  Scenario: Checkout should have the correct total price
    Given user is on page "/products"
    When user adds all products to the cart
    And user goes to page "/checkout"
    Then the cart should have the correct total price

  Scenario: Total price should update when removing product
    Given user is on page "/products"
    When user adds all products to the cart
    And user goes to page "/checkout"
    And user removes a product from the cart
    Then the cart should have the correct total price

  Scenario Outline: Leaving out required fields should show an error
    Given user is on page "/checkout"
    When user leaves out "<field>"
    And user submits the form
    Then the form should show an error message for "<field>"
    Examples:
      | field |
      | firstName |
      | lastName |
      | email |
      | address |
      | country |
      | city |
      | zip |
      | cc-name |
      | cc-number |
      | cc-expiration |
      | cc-cvv |

  Scenario: Payment instructions for PayPal should be hidden by default
    Given user is on page "/checkout"
    Then the payment instructions for PayPal should be hidden

  Scenario: Selecting PayPal as payment method should show the payment instructions for PayPal
    Given user is on page "/checkout"
    When user selects PayPal as payment method
    Then the payment instructions for PayPal should be shown