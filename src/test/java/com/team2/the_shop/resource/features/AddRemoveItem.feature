Feature: AddRemoveItem

  Scenario: Test add 1 item to the cart
    Given the user is on the page "https://webshop-agil-testautomatiserare.netlify.app/products.html"
    When  the user adds an item to the cart
    Then  1 item should be added to the cart

  Scenario: Test remove 1 item from the cart
    Given the user is on the page "https://webshop-agil-testautomatiserare.netlify.app/checkout"
    And   the cart contains 1 item
    When  the user removes 1 item from the cart
    Then  the cart should contain 0 items
