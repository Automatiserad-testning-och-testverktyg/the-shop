Feature: Top Banner
  Background: Start on home page
    Given User is on homepage

  Scenario: shop link is clickable and top banner is visible
    When User clicks Shop link
    Then Top banner should be visible

  Scenario: About link is clickable and top banner is visible
    When User clicks About link
    Then Top banner should be visible

  Scenario: Checkout button is clickable and top banner is visible
    When User clicks on Checkout button
    Then Top banner should be visible

  Scenario: Home link is clickable and top banner is visible
    When User clicks on About link
    When User clicks on Home link
    Then Top banner is visible