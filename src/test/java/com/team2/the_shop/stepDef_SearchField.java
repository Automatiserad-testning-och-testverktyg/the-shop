package com.team2.the_shop;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class stepDef_SearchField { // Skrivet av Sebastian Cardona Cervantes

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://webshop-agil-testautomatiserare.netlify.app/";
    private List<String> cardTitles; // Changed to List<String>

    // Method to retrieve card titles
    public List<String> getAllCardTitles() {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("card-title")));
        System.out.println("Total elements found: " + elements.size());
        List<String> titles = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                titles.add(element.getText());
            }
        }
        return titles;
    }

    // Method to perform search of all stored card-title[s] and assert results
    public void searchAllTitlesAndAssert(List<String> titles) {
        for (String title : titles) {
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
            searchField.clear();
            searchField.sendKeys(title);

            // Wait for search results to be displayed and verify the title is present
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-title")));
            List<WebElement> results = driver.findElements(By.className("card-title"));
            boolean isTitlePresent = results.stream().anyMatch(element -> element.getText().equals(title));

            // Assert that the title is present in the search results
            assertTrue(isTitlePresent, "The title '" + title + "' was not found in the search results!");
            System.out.println("The title '" + title + "' was successfully found in the search results.");
        }
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Background
    @Given("User is on the page {string}")
    public void userIsOnThePage(String pageUrl) {
        driver.get(baseUrl + pageUrl);
    }

    @Then("The title is {string}")
    public void theTitleIs(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "The title is not correct.");
    }

    // Scenario: The search field is visible
    @Then("Search field is visible")
    public void searchFieldIsVisible() {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        assertTrue(searchField.isDisplayed(), "The search field should be visible.");
    }

    // Scenario: A query redirects to correct url
    @When("User performs a search with an empty query")
    public void userEntersIntoSearchField() {
        WebElement searchField = driver.findElement(By.id("search"));
        searchField.clear();
        searchField.sendKeys("");
        searchField.submit();
    }

    @Then("User should land on page {string}")
    public void userShouldLandOnPage(String pageUrl) {
        String expectedUrl = baseUrl + pageUrl;
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Then("All products should be displayed")
    public void allProductsShouldBeDisplayed() {
        List<WebElement> productElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("card-title")));
        int expectedProductCount = getAllCardTitles().size(); // Store the initial count of product titles
        int actualProductCount = productElements.size();
        assertEquals(expectedProductCount, actualProductCount, "The number of displayed products should match the initial count.");
    }

    // Scenario: Retrieve and store card titles
    @And("Get all card titles")
    public void GetAllCardTitles() {
        cardTitles = getAllCardTitles();
    }

    @Then("All the products are searchable")
    public void allProductsAreSearchable() {
        searchAllTitlesAndAssert(cardTitles);
    }
}
