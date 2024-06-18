package com.team2.the_shop;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class stepDef_AddRemoveItem { // Skrivet av Sebastian Cardona Cervantes

    WebDriver driver;
    //WebDriverWait wait;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the page {string}")
    public void the_user_is_on_the_page(String url) {
        driver.get(url);
    }

    @When("the user adds an item to the cart")
    public void the_user_adds_an_item_to_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main > div:nth-child(14) > div > div > button")));
        //visibilityOfElementLocated(By.cssSelector(".col:nth-child(14) .btn")));
        addToCartButton.click();
    }

    @Then("{int} item should be added to the cart")
    public void item_should_be_added_to_the_cart(int expectedSize) {
        WebElement buttonSize = driver.findElement(By.id("buttonSize"));
        String buttonSizeValue = buttonSize.getAttribute("value");
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize);
    }

    @Given("the cart contains {int} item")
    public void the_cart_contains_item(int expectedSize) {
        WebElement buttonSize = driver.findElement(By.id("buttonSize"));
        String buttonSizeValue = buttonSize.getAttribute("value");
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize);
    }

    @When("the user removes 1 item from the cart")
    public void the_user_removes_item_from_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cartList > li.list-group-item.d-flex.justify-content-between.lh-sm > div > button")));
        addToCartButton.click();
    }

    @Then("the cart should contain {int} items")
    public void the_cart_should_contain_items(int expectedSize) {
        WebElement buttonSize = driver.findElement(By.id("buttonSize"));
        String buttonSizeValue = buttonSize.getAttribute("value");
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize);
    }

}
