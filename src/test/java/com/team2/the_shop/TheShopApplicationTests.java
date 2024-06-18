package com.team2.the_shop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/team2/the_shop/resource/features",
		plugin = {"pretty", "html:target/cucumber-report.html"},
		glue={"com.team2.the_shop.stepDef_SmokeTest", "com.team2.the_shop.stepDef_Checkout"}
		)

@SpringBootTest
public class TheShopApplicationTests {
}
