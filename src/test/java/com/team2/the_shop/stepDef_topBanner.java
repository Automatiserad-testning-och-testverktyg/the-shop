package com.team2.the_shop;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class stepDef_topBanner {

    public ChromeDriver driver;
    @Before
    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Given("User is on homepage")
    public void user_is_on_homepage() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app");
    }

    @When("User clicks Shop link")
    public void user_clicks_shop_link() {
        WebElement shopLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[2]/a"));
        shopLink.click();
    }
    @Then("Shop page title should be {string}")
    public void shop_page_title_should_be(String title) {
            Assertions.assertEquals(title, driver.getTitle());
        }

    @And("Top banner should be visible")
    public void top_banner_should_be_visible() {
        boolean theShopIcon = driver.findElement(By.cssSelector("body > header > div > div > a")).isDisplayed();
        Assertions.assertTrue(theShopIcon);
        boolean homeLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[1]/a")).isDisplayed();
        Assertions.assertTrue(homeLink);
        boolean shopLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[2]/a")).isDisplayed();
        Assertions.assertTrue(shopLink);
        boolean aboutLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[3]/a")).isDisplayed();
        Assertions.assertTrue(aboutLink);
        boolean checkoutButton = driver.findElement(By.xpath("/html/body/header/div/div/div/a")).isDisplayed();
        Assertions.assertTrue(checkoutButton);
    }

    @When("User clicks About link")
    public void user_clicks_about_link() {
        WebElement aboutLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[3]/a"));
        aboutLink.click();
    }

    @Then("About page title should be {string}")
    public void about_page_title_should_be(String title) {
        Assertions.assertEquals(title, driver.getTitle());
    }

    @When("User clicks on Checkout button")
    public void user_clicks_on_checkout_button() {
        WebElement checkoutLink = driver.findElement(By.xpath("/html/body/header/div/div/div/a"));
        checkoutLink.click();
    }

    @Then("Checkout page title should be {string}")
    public void checkout_page_title_should_be(String title) {
        Assertions.assertEquals(title, driver.getTitle());
    }

    @When("User clicks Home link")
    public void user_clicks_home_link() {
        WebElement homeLink = driver.findElement(By.xpath("/html/body/header/div/div/ul/li[1]/a"));
        homeLink.click();
    }
    @Then("Home page title should be {string}")
    public void home_page_title_should_be(String title) {
        Assertions.assertEquals(title, driver.getTitle());
    }


}
