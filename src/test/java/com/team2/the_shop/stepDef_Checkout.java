package com.team2.the_shop;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.time.Duration;
import java.util.List;

public class stepDef_Checkout { // Skrivet av Jamie Blomerus
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
    @Given("user is on page {string}")
    @And("user goes to page {string}")
    public void user_is_on_page(String path) {
        driver.get( "https://webshop-agil-testautomatiserare.netlify.app" + path);
    }
    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }
    @Then("the cart should have {int} product\\(s)")
    public void the_cart_should_have_int_products(int expectedCartSize) {
        List<WebElement> cartItems = driver.findElements(By.xpath("//ul[@id='cartList']/li"));

        // Remove total item from cart
        cartItems.removeIf((item) -> {
            try {
                item.findElement(By.xpath(".//strong"));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        });

        Assertions.assertEquals(expectedCartSize, cartItems.size());
    }
    @When("user adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        List<WebElement> productItems = driver.findElements(By.xpath("//*[@id=\"main\"]/div[1]/div/div/button"));

        productItems.getFirst().click();
    }
    @When("user removes a product from the cart")
    public void user_removes_a_product_from_the_cart() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//ul[@id='cartList']/li"));

        cartItems.getFirst().findElement(By.xpath("//button")).click();
    }
    @When("user adds all products to the cart")
    public void user_adds_all_products_to_the_cart() throws InterruptedException {
        List<WebElement> productItems = driver.findElements(By.xpath("//*[@id=\"main\"]/div/div/div/button"));

        for (WebElement productItem : productItems) {
            productItem.click();
            // Scroll a bit (somehow the click doesn't always register else)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
            Thread.sleep(250);
        }
    }
    @Then("the cart should have the correct total price")
    public void the_cart_should_have_the_correct_total_price() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//ul[@id='cartList']/li[not(descendant::strong)]"));
        double expectedTotal = cartItems.stream()
            .mapToDouble(item -> Double.parseDouble(item.findElement(By.xpath(".//span[@class='text-muted']")).getText().replace("$", "")))
            .sum();

        double actualTotal = Double.parseDouble(driver.findElement(By.xpath("//ul[@id='cartList']/li/strong")).getText().replace("$", ""));

        Assertions.assertEquals(expectedTotal, actualTotal, 0.01);
    }
    @When("user leaves out {string}")
    public void user_leaves_out(String left_out_field) {
        WebElement form = driver.findElement(By.xpath("//form"));
        form.findElements(By.xpath(".//input")).forEach((input) -> {
            if ( ! input.isDisplayed() ) {
                return;
            }

            if (input.getAttribute("id").equals(left_out_field)) {
                input.clear();
            } else {
                if (input.getAttribute("type").equals("email")) {
                    input.sendKeys("testare@example.com");
                } else {
                    input.sendKeys("Test");
                }
            }
        });
    }
    @And("user submits the form")
    public void user_submits_the_form() throws InterruptedException {
        WebElement form = driver.findElement(By.xpath("//form"));
        // Scroll to the button (somehow the click doesn't always register else)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", form.findElement(By.tagName("button")));
        Thread.sleep(500);
        form.findElement(By.tagName("button")).click();
    }
    @Then("the form should show an error message for {string}")
    public void the_form_should_show_an_error_message_for(String field_name) {
        WebElement errorElement = driver.findElement(By.xpath("//input[@id='" + field_name + "']/following-sibling::div[@class='invalid-feedback']"));
        Assertions.assertTrue(errorElement.isDisplayed());
    }
    @Then("the payment instructions for PayPal should be hidden")
    public void the_payment_instructions_for_paypal_should_be_hidden() {
        WebElement paypalInstructions = driver.findElement(By.xpath("//div[@id='paypalInfo']"));
        WebElement creditCardInstructions = driver.findElement(By.xpath("//div[@id='card']"));
        Assertions.assertAll(
            () -> Assertions.assertFalse(paypalInstructions.isDisplayed()),
            () -> Assertions.assertTrue(creditCardInstructions.isDisplayed())
        );
    }
    @When("user selects PayPal as payment method")
    public void user_selects_paypal_as_payment_method() {
        WebElement form = driver.findElement(By.xpath("//form"));
        form.findElement(By.xpath(".//input[@id='paypal']")).click();
    }
    @Then("the payment instructions for PayPal should be shown")
    public void the_payment_instructions_for_paypal_should_be_shown() {
        WebElement paypalInstructions = driver.findElement(By.xpath("//div[@id='paypalInfo']"));
        WebElement creditCardInstructions = driver.findElement(By.xpath("//div[@id='card']"));
        Assertions.assertAll(
            () -> Assertions.assertTrue(paypalInstructions.isDisplayed()),
            () -> Assertions.assertFalse(creditCardInstructions.isDisplayed())
        );
    }
    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
