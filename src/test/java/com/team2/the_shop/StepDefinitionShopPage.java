package com.team2.the_shop;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitionShopPage { // Skrivet av Sweta Bagchi


    WebDriver driver;
    ChromeOptions options;

    //Background
    @Given("webshop page is available")
    public void webshop_page_is_available() {
        options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("headless=new");
        driver = new ChromeDriver(options);
    }
    @When("the user clicks on the {string} option on the header")
    public void the_user_clicks_on_the_option_on_the_header(String string) {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }
    @Then("the title visible should be {string}")
    public void the_title_visible_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle,actualTitle);
    }

    @Then("the number of categories is {int}")
    public void the_number_of_categories_is(Integer expectedCategoryCount ) {
        List<WebElement> allCategories = driver.findElements(By.xpath("/html/body/div[1]/div/ul/li"));
        int actualCategoryCount = allCategories.size();
        assertEquals(expectedCategoryCount,actualCategoryCount);
    }

    @Then("for the list index {int} the visible category is {string}")
    public void the_visible_category_is(Integer listIndex, String expectedCategoryName) {
        List<WebElement> allCategories = driver.findElements(By.xpath("/html/body/div[1]/div/ul/li"));
        String actualCategoryName = allCategories.get(listIndex).getText();
        assertEquals(expectedCategoryName,actualCategoryName);
    }

    @Then("the search field is visible")
    public void the_search_field_is_visible() {
        boolean visibilityOfSearchField = driver.findElement(By.id("search")).isDisplayed();
        assertTrue(visibilityOfSearchField);
    }

    @After
    public void closeDriver() {
        System.out.println("**END OF TEST**");
        if(driver != null) {
            driver.quit();
        }
    }
}
