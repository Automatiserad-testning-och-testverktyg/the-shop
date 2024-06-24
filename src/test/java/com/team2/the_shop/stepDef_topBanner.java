package com.team2.the_shop;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
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
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Top banner should be visible")
    public void top_banner_should_be_visible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
