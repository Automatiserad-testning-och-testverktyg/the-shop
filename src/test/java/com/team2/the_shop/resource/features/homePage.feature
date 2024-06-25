Feature: Home page

  Scenario: page loads and title is "Webbutiken"
    Given User is on the home page
    Then title is "The Shop"

  Scenario: Text is presented on homepage
    Given User is on the home page
    Then text is presented

  Scenario: Image is presented
    Given User is on the home page
    Then image is presented

  Scenario: Button All products leads to the shop page
    Given User is on the home page
    When User clicks all products button
    Then User is on new page with title "The Shop | Products"


