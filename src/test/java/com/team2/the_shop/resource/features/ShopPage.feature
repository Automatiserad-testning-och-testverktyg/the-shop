Feature: Test for "Shop" page
  # Skrivet av Sweta Bagchi

  Background:
    Given webshop page is available
    When the user clicks on the "shop" option on the header

  Scenario: Correct title should be visible
    Then the title visible should be "The Shop | Products"

  Scenario: Count the number of categories
    Then the number of categories is 5


  Scenario Outline: The category name should match the index
    Then for the list index <index> the visible category is <category>

    Examples:
      | index | category |
      | 0     | "All"    |
      | 1     | "Men's clothing" |
      | 2     | "Women's clothing" |
      | 3     | "Jewelery"   |
      | 4     | "Electronics" |

  Scenario: The search field is visible
    Then the search field is visible






