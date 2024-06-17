package com.team2.the_shop;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class stepDef_homepage { // Skrivet av Linn Bergström


    WebDriver driver;

    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the webshop page")
    public void user_is_on_the_webshop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app");
    }

    @Then("title is {string}")
    public void title_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Then("text is presented")
    public void text_is_presented() {
        boolean informationTitle = driver.findElement(By.cssSelector("body > div.container.my-5 > div > div.col-lg-7.p-3.p-lg-5.pt-lg-3 > h2")).isDisplayed();
        assertTrue(informationTitle);
        boolean informationText = driver.findElement(By.cssSelector("body > div.container.my-5 > div > div.col-lg-7.p-3.p-lg-5.pt-lg-3 > p")).isDisplayed();
        assertTrue(informationText);
    }

    @Then("image is presented")
    public void image_is_presented() {
        boolean image1 = driver.findElement(By.cssSelector("body > div.container.my-5 > div > div.col-lg-4.offset-lg-1.p-0.overflow-hidden.shadow-lg > img")).isDisplayed();
        assertTrue(image1);
    }

    @When("User clicks all products button")
    public void user_clicks_all_products_button() {
        WebElement buttonAllProducts = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/button"));
        buttonAllProducts.click();
    }

    @Then("User is on new page with title {string}")
    public void user_is_on_new_page_with_title(String expectedNewPageTitle) {
        String newPageTitle = driver.getTitle();
        Assertions.assertEquals(expectedNewPageTitle,newPageTitle);

    }


}
