package com.team2.the_shop;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class stepDef_AddRemoveItem { // Skrivet av Sebastian Cardona Cervantes

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the page {string}")
    public void theUserIsOnThePage(String url) {
        driver.get(url);
    }
    @And("the user goes to the page {string}")
    public void theUserGoesToThePage(String url) {
        driver.get(url);
    }

    @When("the user adds {int} item to the cart")
    public void theUserAddsAnItemToTheCart(int noOfItems ) {
        List<WebElement> productItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"main\"]/div[" + noOfItems + "]/div/div/button")));
        WebElement addToCartButton = productItems.getFirst();
        addToCartButton.click();
    }

    @Then("{int} item should be added to the cart")
    public void itemShouldBeAddedToTheCart(int expectedSize) {
        WebElement buttonSize = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonSize")));
        String buttonSizeValue = buttonSize.getText();
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize, "The number of items added to the cart is not correct.");
    }


    @Given("the cart contains {int} item")
    public void theCartContainsItem(int expectedSize) {
        WebElement buttonSize = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttonSize")));
        String buttonSizeValue = buttonSize.getText();
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize, "The number of items in the cart is not correct.");
    }

    @When("the user removes {int} item from the cart")
    public void theUserRemoves1ItemFromTheCart(int noOfItems) {
        List<WebElement> removeButton = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"cartList\"]/li[" + noOfItems + "]/div/button")));
        WebElement addToCartButton = removeButton.getFirst();
        addToCartButton.click();
    }

    @Then("the cart should contain {int} items")
    public void theCartShouldContainItems(int expectedSize) {
        WebElement buttonSize = driver.findElement(By.id("buttonSize"));
        String buttonSizeValue = buttonSize.getText();
        int actualSize = Integer.parseInt(buttonSizeValue);
        assertEquals(expectedSize, actualSize, "The number of items in the cart is not correct.");
    }
}
