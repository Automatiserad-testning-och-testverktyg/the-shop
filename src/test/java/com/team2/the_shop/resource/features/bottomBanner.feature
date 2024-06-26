 Feature: Bottom Banner // Tests by Swapnal Hardikar

   Background:
     Given User is on home page of the webshop

  Scenario: Bottom Banner should be visible
    Then Bottom banner should be visible

  Scenario: Home button should be clickable
    When user clicks on home button
    Then page title is "The Shop"

  Scenario: Shop button should be clickable
    When user clicks on shop button
    Then Shop page should open and page title should be "The Shop | Products"

   Scenario: About button should be clickable
     When user clicks on About button
     Then About page should open and h2 text should be "About The Shop"

   Scenario: Checkout button should be clickable
     When user clicks on Checkout button
     Then Checkout page should open and h2 text should be "Checkout form"