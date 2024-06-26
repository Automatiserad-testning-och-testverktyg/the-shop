Feature: Search Field
  # Skrivet av Sebastian Cardona Cervantes

  Background: The webshop is available
    Given User is on the page ""
    Then  The title is "The Shop"

  Scenario: The search field is visible
    Given User is on the page "products"
    Then  Search field is visible

  Scenario: A query redirects to correct url
    Given User is on the page "products"
    When  User performs a search with an empty query
    Then  User should land on page "products?"
    And   All products should be displayed

  Scenario: Check that all content is searchable
    Given User is on the page "products"
    And   Get all card titles
    Then  All the products are searchable





