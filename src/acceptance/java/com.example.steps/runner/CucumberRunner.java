package com.example.steps.runner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/acceptance/resources/features",
        glue = "com.example.steps",
        plugin = {
                "html:build/reports/tests/acceptanceTest/html/index.html"
        }
)
public class CucumberRunner {
}
