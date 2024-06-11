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

public class stepDef_SmokeTest { // Skrivet av Linn Bergström


    WebDriver driver;

    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver();
    }

    @After
    public void closeDriver() {
        if(driver != null);
        driver.quit();
    }

    @Given("User is on the webshop page")
    public void user_is_on_the_webshop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
    }

    @Then("title is {string}")
    public void title_is(String string) {
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assertions.assertEquals(string, actualTitle);
    }

}
