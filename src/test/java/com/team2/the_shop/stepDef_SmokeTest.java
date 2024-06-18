package com.team2.the_shop;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.*;
import java.time.Duration;

public class stepDef_SmokeTest {

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

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the webshop page")
    public void user_is_on_the_webshop_page() {
        driver.get( "https://webshop-agil-testautomatiserare.netlify.app");
    }

    @Then("title is {string}")
    public void title_is(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }
}
