Feature: Top Banner
  Background: Start on home page
    Given User is on homepage

  Scenario: shop link is clickable and top banner is visible
    When User clicks Shop link
    Then Shop page title should be "The Shop | Products"
    And Top banner should be visible

  Scenario: About link is clickable and top banner is visible
    When User clicks About link
    Then About page title should be "The Shop | About"
    And Top banner should be visible

  Scenario: Checkout button is clickable and top banner is visible
    When User clicks on Checkout button
    Then Checkout page title should be "The Shop | Checkout"
    And Top banner should be visible

  Scenario: Home link is clickable and top banner is visible
    When User clicks About link
    When User clicks Home link
    Then Home page title should be "The Shop"
    And Top banner should be visible