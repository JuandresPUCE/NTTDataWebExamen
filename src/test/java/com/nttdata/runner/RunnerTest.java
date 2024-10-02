package com.nttdata.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber/cucumber-report.html",
                "json:target/cucumber/cucumber.json"},
        stepNotifications = true,
        features = "src/test/resources/features",
        glue = "com.nttdata",

        //tags = "@loginQALab-test"
        //tags = "@categoria-inexistente-test"
        //tags = "@categoria-existente-test"
        tags = "@examen"

)
public class RunnerTest {
}
