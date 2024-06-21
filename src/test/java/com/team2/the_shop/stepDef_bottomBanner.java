package com.team2.the_shop;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class stepDef_bottomBanner {

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
    //Background

    @Given("User is on home page of the webshop")
    public void user_is_on_home_page_of_the_webshop() {
        driver.get( "https://webshop-agil-testautomatiserare.netlify.app");
    }
    //Scenario: Bottom Banner should be visible
    @Then("Bottom banner should be visible")
    public void bottom_banner_should_be_visible() {
        WebElement bannerElement = driver.findElement(By.xpath("//body/div[2]"));
        boolean isVisible = bannerElement.isDisplayed();
        Assertions.assertTrue(isVisible);
    }

    //Scenario: Home button should be clickable
    @When("user clicks on home button")
    public void user_clicks_on_home_button() {
        WebElement bannerElement = driver.findElement(By.xpath("//a[@class='nav-link px-2 text-muted'][normalize-space()='Home']"));
        bannerElement.click();
    }
    @Then("page title is {string}")
    public void page_title_is(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    //Scenario: Shop button should be clickable
    @When("user clicks on shop button")
    public void user_clicks_on_shop_button() {
        WebElement bannerElement = driver.findElement(By.xpath("//a[@class='nav-link px-2 text-muted'][normalize-space()='Shop']"));
        bannerElement.click();
    }
    @Then("Shop page should open and page title should be {string}")
    public void shop_page_should_open_and_page_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    //Scenario: About button should be clickable
    @When("user clicks on About button")
    public void user_clicks_on_about_button() {
        WebElement bannerElement = driver.findElement(By.xpath("//a[@class='nav-link px-2 text-muted'][normalize-space()='About']"));
        bannerElement.click();
    }
    @Then("About page should open and h2 text should be {string}")
    public void about_page_should_open_and_h2_text_should_be(String expectedText) {
        WebElement h2Element = driver.findElement(By.cssSelector(".h2.fw-bold.lh-1"));
        String actualText = h2Element.getText();
        Assertions.assertEquals(expectedText,actualText);
    }

    //Scenario: Checkout button should be clickable
    @When("user clicks on Checkout button")
    public void user_clicks_on_checkout_button() {
        WebElement bannerElement = driver.findElement(By.xpath("//a[normalize-space()='Checkout']"));
        bannerElement.click();
    }
    @Then("Checkout page should open and h2 text should be {string}")
    public void checkout_page_should_open_and_h2_text_should_be(String expectedText) {
        WebElement h2Element = driver.findElement(By.xpath("//h2[normalize-space()='Checkout form']"));
        String actualText = h2Element.getText();
        Assertions.assertEquals(expectedText,actualText);
    }

    @After
    public void closeDriver() {
        System.out.println("**END OF TEST**");
        if(driver != null) {
            driver.quit();
        }
    }
}
