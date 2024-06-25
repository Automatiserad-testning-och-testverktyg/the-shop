Feature: Top Banner

  Scenario:
    Given User is on Home page
    Then Top banner is visible
    And Shop link leads to shop page

  Scenario:
    Given User is on Shop page
    Then Top banner is visible
    And Checkout button leads to checkout page

  Scenario:
    Given User is on Checkout page
    Then Top banner is visible
    And Home link leads to home page

  Scenario:
    Given User is on About page
    Then Top banner is visible
    And The shop icon leads to home page

  Scenario:
    Given User is on Home page
    Then Top banner is visible
    And About link leads to about page