Feature: smoke test

  Scenario: page loads and title is "Webbutiken"
    Given User is on the webshop page
    Then title is "The Sho"